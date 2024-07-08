package com.fucar.dao;

import com.fucar.pojo.Car;

import java.util.List;

public interface ICarDAO {
    List<Car> getCars();
    Car findById(int carID);
    void save(Car car);
    void update(Car car);
    void delete(int carID);
}