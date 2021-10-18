package com.RentACar.dao;

import com.RentACar.connection.DatabaseConnection;
import com.RentACar.model.request.SignedContractRequestModel;
import com.RentACar.model.response.ContractResponseModel;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;


public interface ContractDao {

    Connection conn = DatabaseConnection.getConnection();
    List<LocalDate> getCarOccupiedDates(String carId);
    List<ContractResponseModel> getAllContracts();
    List<ContractResponseModel> getAllPendingContracts();
    List<ContractResponseModel> getContractHistory (String userId);
    void deleteContract (String contractId);
    void updateContractApproval (String contractId, boolean approval);
    boolean userHasPendingContract (String userId);
    void addContractToDatabase(SignedContractRequestModel contract);

}


