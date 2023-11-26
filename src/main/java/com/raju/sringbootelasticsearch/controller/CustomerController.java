package com.raju.sringbootelasticsearch.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.raju.sringbootelasticsearch.entity.Customer;
import com.raju.sringbootelasticsearch.entity.Product;
import com.raju.sringbootelasticsearch.service.CustomerService;
import com.raju.sringbootelasticsearch.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @PostMapping("/create")
    public void create(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
    }

    @GetMapping(value = "/matchAllCustomers",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Customer> matchAllProds() throws IOException {
        SearchResponse<Customer> searchResponse = elasticSearchService.matchAllCustomerService();
        List<Hit<Customer>> hits = searchResponse.hits().hits();
        List<Customer> customers = new ArrayList<>();
        for(Hit<Customer> customerHit : hits){
            customers.add(customerHit.source());
        }
        return customers;
    }

}
