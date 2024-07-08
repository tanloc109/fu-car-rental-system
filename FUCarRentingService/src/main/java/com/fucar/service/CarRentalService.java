package com.fucar.service;

import com.fucar.pojo.CarRental;
import com.fucar.repository.ICarRentalRepository;
import com.fucar.repository.CarRentalRepository;

import java.time.LocalDate;
import java.util.List;

public class CarRentalService implements ICarRentalService {

    private ICarRentalRepository carRentalRepository;

    public CarRentalService() {
        this.carRentalRepository = new CarRentalRepository();
    }

    @Override
    public void save(CarRental carRental) {
        carRentalRepository.save(carRental);
    }

    @Override
    public void update(CarRental carRental) {
        carRentalRepository.update(carRental);
    }

    @Override
    public void delete(int rentalID) {
        carRentalRepository.delete(rentalID);
    }

    @Override
    public CarRental findById(int rentalID) {
        return carRentalRepository.findById(rentalID);
    }

    @Override
    public List<CarRental> findAll() {
        return carRentalRepository.findAll();
    }

    @Override
    public List<CarRental> findByCustomerIdOrderByPickupDateDesc(int customerID) {
        return carRentalRepository.findByCustomerIdOrderByPickupDateDesc(customerID);
    }

    @Override
    public List<CarRental> findByPeriodDate(LocalDate startDate, LocalDate endDate) {
        return carRentalRepository.findByPeriodDate(startDate, endDate);
    }

    @Override
    public boolean checkCarIsRentedInThisDate(int carID, LocalDate startDate, LocalDate endDate) {
        return carRentalRepository.checkCarIsRentedInThisDate(carID, startDate, endDate);
    }

    @Override
    public boolean checkCarIsRent(int carID) {
        return carRentalRepository.checkCarIsRent(carID);
    }


}
