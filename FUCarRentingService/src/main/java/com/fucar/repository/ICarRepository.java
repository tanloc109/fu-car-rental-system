package com.fucar.repository;

import com.fucar.pojo.Car;

import java.util.List;

public interface ICarRepository {
    List<Car> findAll();
    Car findById(int carID);
    void save(Car car);
    void update(Car car);
    void delete(int carID);
}