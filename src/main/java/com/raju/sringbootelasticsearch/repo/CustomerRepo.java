package com.raju.sringbootelasticsearch.repo;

import com.raju.sringbootelasticsearch.entity.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends ElasticsearchRepository<Customer,Integer> {
}
