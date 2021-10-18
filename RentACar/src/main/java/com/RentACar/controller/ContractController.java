package com.RentACar.controller;

import com.RentACar.dao.*;
import com.RentACar.model.request.ContractApprovalRequestModel;
import com.RentACar.model.request.ContractSampleRequestModel;
import com.RentACar.model.request.SignedContractRequestModel;
import com.RentACar.model.response.ContractResponseModel;
import com.RentACar.model.response.ContractSampleResponseModel;
import com.RentACar.model.response.SignedContractResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import static java.time.temporal.ChronoUnit.DAYS;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.List;


public class ContractController {
    private static ContractDao contractDao = new ContractDaoSQL();
    private static UserDao userDao = new UserDaoSQL();
    private static CarDao carDao = new CarDaoSQL();

    private static double getContractPrice(LocalDate startDate, LocalDate endDate, String carId) {
        double price = carDao.getPrice(carId);
        int days = (int) (DAYS.between(startDate, endDate) + 1);
        return price * days;
    }

    //1. /contracts/sample - POST
    @PostMapping("/contracts/sample")
    public ContractSampleResponseModel getContractSample(@RequestBody ContractSampleRequestModel conSample) {
        double contractPrice = getContractPrice(conSample.getStartDate(),
                conSample.getEndDate(), conSample.getCarId());
        return new ContractSampleResponseModel(conSample.getUserId(),
                conSample.getCarId(), conSample.getStartDate(), conSample.getEndDate(),
                contractPrice, false);
    }

    //2. /contracts - POST
    @PostMapping("/contracts")
    public SignedContractResponseModel postSingedContract
    (@RequestBody SignedContractRequestModel contract) {
        if (contractDao.userHasPendingContract(contract.getUserId())) {
            return new SignedContractResponseModel(false, "User already has pending contract!!");
        }
        if (!carDao.isCarAvailable(contract.getStartDate(), contract.getEndDate(), contract.getCarId())) {
            return new SignedContractResponseModel(false, "Car is not available for whole duration of the contract!!");
        }
        contractDao.addContractToDatabase(contract);
        return new SignedContractResponseModel(true, "Contract created, waiting for approval!!");
    }

    //3. /contracts - GET
    @GetMapping("/contracts")
    public List<ContractResponseModel> getAllcontracts(@RequestHeader("authorization") String adminId) {
        if (!userDao.isAdmin(adminId)) {
            return null;
        }
        return contractDao.getAllContracts();
    }

    //4. /contracts/pending - GET
    @GetMapping("/contracts/pending")
    public List<ContractResponseModel> getAllPendingContracts
    (@RequestHeader("authorization") String adminId) {
        if (!userDao.isAdmin(adminId)) {
            return null;
        }
        return contractDao.getAllPendingContracts();
    }

    //6. /contracts/{userId}/history - GET
    @GetMapping("/contracts/{userId}/history")
    public List<ContractResponseModel> getContractHistory(@PathParam("userId") String userId) {
        return contractDao.getContractHistory(userId);
    }

    //5. /contracts/{contractId}/approval - POST
    @PostMapping("/contracts/{contractId}/approval")
    public void approveContract(@PathParam("contractId") String contractId,
                                @RequestHeader("authorization") String adminId,
                                @RequestBody ContractApprovalRequestModel adminApproval) {
        if (!userDao.isAdmin(adminId)) {
            return;
        }
        if (adminApproval.isApproved()) {
            contractDao.updateContractApproval(contractId, true);
        } else {
            contractDao.deleteContract(contractId);
        }
    }
}
