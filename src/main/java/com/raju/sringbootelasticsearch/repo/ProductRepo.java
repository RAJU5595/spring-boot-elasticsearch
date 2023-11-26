package com.raju.sringbootelasticsearch.repo;

import com.raju.sringbootelasticsearch.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends ElasticsearchRepository<Product,Integer> {
}
