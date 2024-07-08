package com.fucar;

import com.fucar.dao.CarRentalDAO;
import com.fucar.pojo.*;
import com.fucar.service.CarRentalService;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CarRentalService carRentalService = new CarRentalService();
        LocalDate startDate = LocalDate.of(2024, 7, 6);
        LocalDate endDate = LocalDate.of(2024, 7, 8);
        boolean check = carRentalService.checkCarIsRentedInThisDate( 6,startDate, endDate);
        System.out.println(check);
    }
}
