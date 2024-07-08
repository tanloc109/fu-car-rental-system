package com.fucar.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CarProducer")
public class CarProducer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProducerID")
    private int producerID;

    @Column(name = "ProducerName", nullable = false)
    private String producerName;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "Country", nullable = false)
    private String country;

    public CarProducer() {
    }
    
    public CarProducer(String producerName) {
        this.producerName = producerName;
    }

    public CarProducer(String producerName, String address, String country) {
        this.producerName = producerName;
        this.address = address;
        this.country = country;
    }

    public CarProducer(int producerID, String producerName, String address, String country) {
        this.producerID = producerID;
        this.producerName = producerName;
        this.address = address;
        this.country = country;
    }

    public int getProducerID() {
        return producerID;
    }

    public void setProducerID(int producerID) {
        this.producerID = producerID;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarProducer that = (CarProducer) o;
        return producerID == that.producerID && Objects.equals(producerName, that.producerName) && Objects.equals(address, that.address) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producerID, producerName, address, country);
    }

    @Override
    public String toString() {
        return producerID + " " + producerName + " " +  country;
    }
}
