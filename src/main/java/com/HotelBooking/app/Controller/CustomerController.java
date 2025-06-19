package com.HotelBooking.app.Controller;

import com.HotelBooking.app.Entity.Customer;
import com.HotelBooking.app.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/add")
    public String postMethodName(@RequestBody Customer customer) {
        service.addCustomer(customer);
        return "New Customer " + customer.getName() + "added.";
    }

    @GetMapping("/get/{id}")
    public Optional<Customer> getCustomer (@PathVariable int id){
        return service.getCustomer(id);
    }

    @PutMapping ("update/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer customer){
        return service.updateCustomer(id, customer);
    }

    @DeleteMapping("delete/{id}")
    public Customer deleteBooking(@PathVariable int id, @RequestBody Customer customer){
        return service.deleteBooking(id);
    }

    @GetMapping("get/{id}/cost")
    public Double calculateCost(@PathVariable int id) {
        return service.calculateTotalCost(id);
    }

}
