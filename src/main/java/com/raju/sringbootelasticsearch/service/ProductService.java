package com.raju.sringbootelasticsearch.service;

import com.raju.sringbootelasticsearch.entity.Product;
import com.raju.sringbootelasticsearch.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Iterable<Product> getProducts(){
        return  productRepo.findAll();
    }

    public void createProduct(Product product){
        productRepo.save(product);
    }

    public void updateProduct(Product product,int id){
        Product existing = productRepo.findById(id).get();
        existing.setPrice(product.getPrice());
    }

    public void deleteProduct(int id){
        productRepo.deleteById(id);
    }
}
