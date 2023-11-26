package com.raju.sringbootelasticsearch.service;

import com.raju.sringbootelasticsearch.entity.Customer;
import com.raju.sringbootelasticsearch.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public void saveCustomer(Customer customer){
        customerRepo.save(customer);
    }

}
