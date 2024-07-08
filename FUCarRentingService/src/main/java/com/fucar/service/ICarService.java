package com.fucar.service;

import com.fucar.pojo.Car;

import java.util.List;

public interface ICarService {
    List<Car> findAll();
    Car findById(int carID);
    void save(Car car);
    void update(Car car);
    void delete(int carID);
}