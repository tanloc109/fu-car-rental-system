package com.fucar.repository;

import com.fucar.pojo.CarProducer;
import java.util.List;

public interface ICarProducerRepository {
    List<CarProducer> findAll();
    CarProducer findById(int producerID);
    void save(CarProducer carProducer);
    void update(CarProducer carProducer);
    void delete(int producerID);
}
