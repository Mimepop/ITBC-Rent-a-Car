package com.RentACar.controller;

import com.RentACar.dao.*;
import com.RentACar.model.CarModel;
import com.RentACar.model.SearchCarModel;
import com.RentACar.model.request.ChangeCarInfoRequestModel;
import com.RentACar.model.response.GetCarResponseModel;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CarController {
    private CarDao carDao = new CarDaoSQL();
    private UserDao userDao = new UserDaoSQL();
    private ContractDao contractDao = new ContractDaoSQL();

    //1. /cars - GET
    @GetMapping("/cars")
    public List<GetCarResponseModel> getAllCars() {
        return carDao.getAllCars();
    }

    //2. /cars/search? - GET
    @GetMapping("/cars/search")
    public List<GetCarResponseModel> searchCars(@RequestParam(required = false) int year,
                                                @RequestParam(required = false) String make,
                                                @RequestParam(required = false) String model,
                                                @RequestParam(required = false) boolean automatic,
                                                @RequestParam(required = false) double price,
                                                @RequestParam(required = false) int power,
                                                @RequestParam(required = false) int doors) {
        return carDao.searchCars(new SearchCarModel(year, make, model, automatic, price, power, doors),
                carDao.getAllCars());
    }

    //3. /cars/{carId} - GET
    @GetMapping("/cars/{carId}")
    public GetCarResponseModel getCar(@PathVariable("carId") String id) {
        return carDao.getCar(id);
    }

    //4. /cars/{carId} - PATCH
    @PatchMapping("cars/{carId}")
    public void updateCar(@PathVariable("carId") String carId,
                          @RequestHeader("authorization") String adminId,
                          @RequestBody ChangeCarInfoRequestModel carInfo) {
        if (!userDao.isAdmin(adminId)) {
            return;
        }
        carDao.updateCarInfo(carId, carInfo);
    }

    //5. /cars/{carId} - DELETE
    @DeleteMapping("/cars/{carId}")
    public void deleteCar(@PathVariable("carId") String carId,
                          @RequestHeader("authorization") String adminId) {
        if (!userDao.isAdmin(adminId)) {
            return;
        }
        carDao.delete(carId);
    }

    //6. /cars - POST
    @PostMapping("/cars")
    public void addCar(@RequestHeader("authorization") String adminId,
                       @RequestBody CarModel car) {
        if (!userDao.isAdmin(adminId)) {
            return;
        }
        carDao.addCar(car);
    }


    //7. /cars/available? - GET
    @GetMapping("/cars/available")
    public List<GetCarResponseModel> availableCars(@RequestParam String start,
                                                   @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return carDao.getAvailableCars(startDate, endDate);
    }

    //8. /cars/available/search? - GET
    @GetMapping("/cars/available/search")
    public List<GetCarResponseModel> searchCars(@RequestParam String start,
                                                @RequestParam String end,
                                                @RequestParam(required = false) int year,
                                                @RequestParam(required = false) String make,
                                                @RequestParam(required = false) String model,
                                                @RequestParam(required = false) boolean automatic,
                                                @RequestParam(required = false) double price,
                                                @RequestParam(required = false) int power,
                                                @RequestParam(required = false) int doors) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return carDao.searchCars(new SearchCarModel(year, make, model, automatic, price, power, doors),
                carDao.getAvailableCars(startDate, endDate));
    }

    //9. /cars/{carId}/calendar - GET
    @GetMapping("/cars/{carId}/calendar")
    public List<LocalDate> getCarOccupied(@PathVariable("carId") String id) {
        return contractDao.getCarOccupiedDates(id);
    }
}


