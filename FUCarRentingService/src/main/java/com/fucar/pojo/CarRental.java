package com.fucar.pojo;

import java.time.LocalDate;

import javax.persistence.
        *;

@Entity
@Table(name = "CarRental")
public class CarRental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RentalID")
    private int rentalID;

    @ManyToOne
    @JoinColumn(name = "CustomerID", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "CarID", nullable = false)
    private Car car;

    @Column(name = "PickupDate", nullable = false)
    private LocalDate pickupDate;

    @Column(name = "ReturnDate", nullable = false)
    private LocalDate returnDate;

    @Column(name = "RentPrice", nullable = false)
    private double rentPrice;

    @Column(name = "Status", nullable = false)
    private String status;

    public CarRental() {
    }

    public CarRental(Car car, Customer customer, LocalDate pickupDate, LocalDate returnDate, double rentPrice, String status) {
		super();
		this.customer = customer;
		this.car = car;
		this.pickupDate = pickupDate;
		this.returnDate = returnDate;
		this.rentPrice = rentPrice;
		this.status = status;
	}
    
    public CarRental(int rentalID, Car car, Customer customer, LocalDate pickupDate, LocalDate returnDate, double rentPrice, String status) {
		super();
		this.rentalID = rentalID;
		this.customer = customer;
		this.car = car;
		this.pickupDate = pickupDate;
		this.returnDate = returnDate;
		this.rentPrice = rentPrice;
		this.status = status;
	}

	public int getRentalID() {
        return rentalID;
    }

    public void setRentalID(int rentalID) {
        this.rentalID = rentalID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
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
    public String toString() {
        return "CarRental{" +
                "rentalID=" + rentalID +
                ", customer=" + customer +
                ", car=" + car +
                ", pickupDate=" + pickupDate +
                ", returnDate=" + returnDate +
                ", rentPrice=" + rentPrice +
                ", status='" + status + '\'' +
                '}';
    }
}
