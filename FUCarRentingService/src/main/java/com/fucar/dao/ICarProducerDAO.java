package com.fucar.dao;

import com.fucar.pojo.CarProducer;
import java.util.List;

public interface ICarProducerDAO {
    List<CarProducer> findAll();
    CarProducer findById(int producerID);
    void save(CarProducer carProducer);
    void update(CarProducer carProducer);
    void delete(int producerID);
}
