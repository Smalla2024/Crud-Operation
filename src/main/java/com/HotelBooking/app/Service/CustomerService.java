package com.HotelBooking.app.Service;

import com.HotelBooking.app.Entity.Customer;
import com.HotelBooking.app.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private double PerNightCost = 20;
    private double GstRate = 0.09;

    @Autowired
    private CustomerRepo repository;

    public String addCustomer (Customer customer){
        repository.save(customer);
        return "added";
    }

    public Optional<Customer> getCustomer (int id){
        return repository.findById(id);

    }

    public Customer updateCustomer(int id, Customer customer){
        Customer c = repository.findById(id).get();
        if(Objects.nonNull(c)){
            c.setName(customer.getName());
            c.setRoomNumber(customer.getRoomNumber());
            c.setCheckInDate(customer.getCheckInDate());
            return repository.save(c);
        }
        return null;
    }

    public Customer deleteBooking(int id){
        repository.deleteById(id);
        return null;
    }


    public double calculateTotalCost(int id) {
        Customer customer = repository.findById(id).get();
        long nights = ChronoUnit.DAYS.between(customer.getCheckInDate(), customer.getCheckOutDate());
        double base = nights * PerNightCost;
        double gst = base * GstRate;
        return base + gst;
    }

}
