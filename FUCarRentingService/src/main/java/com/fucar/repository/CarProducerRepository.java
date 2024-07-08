package com.fucar.repository;

import com.fucar.dao.CarProducerDAO;
import com.fucar.dao.ICarProducerDAO;
import com.fucar.pojo.CarProducer;

import java.util.List;

public class CarProducerRepository implements ICarProducerRepository {

    private ICarProducerDAO carProducerDAO;

    public CarProducerRepository() {
        if(carProducerDAO == null) {
            carProducerDAO = new CarProducerDAO();
        }
    }

    @Override
    public List<CarProducer> findAll() {
        return carProducerDAO.findAll();
    }

    @Override
    public CarProducer findById(int producerID) {
        return carProducerDAO.findById(producerID);
    }

    @Override
    public void save(CarProducer carProducer) {
        carProducerDAO.save(carProducer);
    }

    @Override
    public void update(CarProducer carProducer) {
        carProducerDAO.update(carProducer);
    }

    @Override
    public void delete(int producerID) {
        carProducerDAO.delete(producerID);
    }
}
