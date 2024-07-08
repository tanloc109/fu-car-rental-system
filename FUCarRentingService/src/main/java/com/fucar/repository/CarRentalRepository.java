package com.fucar.repository;

import com.fucar.dao.ICarRentalDAO;
import com.fucar.dao.CarRentalDAO;
import com.fucar.pojo.CarRental;

import java.time.LocalDate;
import java.util.List;

public class CarRentalRepository implements ICarRentalRepository {

    private ICarRentalDAO carRentalDAO;

    public CarRentalRepository() {
        this.carRentalDAO = new CarRentalDAO();
    }

    @Override
    public void save(CarRental carRental) {
        carRentalDAO.save(carRental);
    }

    @Override
    public void update(CarRental carRental) {
        carRentalDAO.update(carRental);
    }

    @Override
    public void delete(int rentalID) {
        carRentalDAO.delete(rentalID);
    }

    @Override
    public CarRental findById(int rentalID) {
        return carRentalDAO.findById(rentalID);
    }

    @Override
    public List<CarRental> findAll() {
        return carRentalDAO.findAll();
    }

    @Override
    public List<CarRental> findByCustomerIdOrderByPickupDateDesc(int customerID) {
        return carRentalDAO.findByCustomerIdOrderByPickupDateDesc(customerID);
    }

    @Override
    public List<CarRental> findByPeriodDate(LocalDate startDate, LocalDate endDate) {
        return carRentalDAO.findByPeriodDate(startDate, endDate);
    }

    @Override
    public boolean checkCarIsRentedInThisDate(int carID, LocalDate startDate, LocalDate endDate) {
        return carRentalDAO.checkCarIsRentedInThisDate(carID, startDate, endDate);
    }

    @Override
    public boolean checkCarIsRent(int carID) {
        return carRentalDAO.checkCarIsRent(carID);
    }
}
