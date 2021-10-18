package com.RentACar.dao;

import com.RentACar.connection.DatabaseConnection;
import com.RentACar.model.CarModel;
import com.RentACar.model.SearchCarModel;
import com.RentACar.model.request.ChangeCarInfoRequestModel;
import com.RentACar.model.response.GetCarResponseModel;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public interface CarDao {

    Connection conn = DatabaseConnection.getConnection();
    List<GetCarResponseModel> getAllCars();
    List<GetCarResponseModel> searchCars(SearchCarModel searchCarModel, List<GetCarResponseModel> cars);
    List<GetCarResponseModel> getAvailableCars(LocalDate startDate, LocalDate endDate);
    boolean isCarAvailable(LocalDate startDate, LocalDate endDate, String carId);
    GetCarResponseModel getCar(String id);
    void updateCarInfo(String id, ChangeCarInfoRequestModel carInfo);
    void delete(String id);
    void addCar(CarModel car);
    double getPrice(String id);
}
