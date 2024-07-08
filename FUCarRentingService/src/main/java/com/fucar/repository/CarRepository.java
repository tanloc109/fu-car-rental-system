package com.fucar.repository;

import com.fucar.dao.CarDAO;
import com.fucar.pojo.Car;

import java.util.List;

public class CarRepository implements ICarRepository {

    private static CarDAO carDAO = null;

    public CarRepository() {
        if (carDAO == null) {
            carDAO = new CarDAO();
        }
    }

    @Override
    public List<Car> findAll() {
        return carDAO.getCars();
    }

    @Override
    public Car findById(int carID) {
        return carDAO.findById(carID);
    }

    @Override
    public void save(Car car) {
        carDAO.save(car);
    }

    @Override
    public void update(Car car) {
        carDAO.update(car);
    }

    @Override
    public void delete(int carID) {
        carDAO.delete(carID);
    }
}