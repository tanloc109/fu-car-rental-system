package com.fucar.service;

import com.fucar.pojo.Car;
import com.fucar.repository.CarRepository;
import com.fucar.repository.ICarRepository;

import java.util.List;

public class CarService implements ICarService {

    private ICarRepository carRepository;

    private ICarRentalService carRentalService;

    public CarService() {
        carRepository = new CarRepository();
        carRentalService = new CarRentalService();
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(int carID) {
        return carRepository.findById(carID);
    }

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }

    @Override
    public void update(Car car) {
        carRepository.update(car);
    }

    @Override
    public void delete(int carID) {
        Car car = carRepository.findById(carID);
        if (car != null) {
            if (carRentalService.checkCarIsRent(carID)) {
                car.setStatus("INACTIVE");
                update(car);
            } else {
                carRepository.delete(carID);
            }
        }
    }

}
