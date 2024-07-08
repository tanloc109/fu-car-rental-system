package com.fucar.pojo;

import javax.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarID")
    private int carID;

    @Column(name = "CarName", nullable = false)
    private String carName;

    @Column(name = "CarModelYear", nullable = false)
    private int carModelYear;

    @Column(name = "Color", nullable = false)
    private String color;

    @Column(name = "Capacity", nullable = false)
    private int capacity;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "ImportDate", nullable = false)
    private Date importDate;

    @ManyToOne
    @JoinColumn(name = "ProducerID", nullable = false)
    private CarProducer producer;

    @Column(name = "RentPrice", nullable = false)
    private double rentPrice;

    @Column(name = "Status", nullable = false)
    private String status;

    public Car() {
    }
    
    

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCarModelYear() {
        return carModelYear;
    }

    public void setCarModelYear(int carModelYear) {
        this.carModelYear = carModelYear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public CarProducer getProducer() {
        return producer;
    }

    public void setProducer(CarProducer producer) {
        this.producer = producer;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carID == car.carID && carModelYear == car.carModelYear && capacity == car.capacity && Double.compare(rentPrice, car.rentPrice) == 0 && Objects.equals(carName, car.carName) && Objects.equals(color, car.color) && Objects.equals(description, car.description) && Objects.equals(importDate, car.importDate) && Objects.equals(producer, car.producer) && Objects.equals(status, car.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carID, carName, carModelYear, color, capacity, description, importDate, producer, rentPrice, status);
    }

    @Override
    public String toString() {
        return carID + " | " +  carName + " | " + carModelYear + " | " + color + " | " + capacity + " seats | " + rentPrice +"$";
    }
}
