package com.fucar.service;

import com.fucar.pojo.CarProducer;
import com.fucar.repository.CarProducerRepository;
import com.fucar.repository.ICarProducerRepository;

import java.util.List;

public class CarProducerService implements ICarProducerService {

    private ICarProducerRepository carProducerRepository;

    public CarProducerService() {
        if(carProducerRepository == null) {
            carProducerRepository = new CarProducerRepository();
        }
    }

    @Override
    public List<CarProducer> findAll() {
        return carProducerRepository.findAll();
    }

    @Override
    public CarProducer findById(int producerID) {
        return carProducerRepository.findById(producerID);
    }

    @Override
    public void save(CarProducer carProducer) {
        carProducerRepository.save(carProducer);
    }

    @Override
    public void update(CarProducer carProducer) {
        carProducerRepository.update(carProducer);
    }

    @Override
    public void delete(int producerID) {
        carProducerRepository.delete(producerID);
    }
}