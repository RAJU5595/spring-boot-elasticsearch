package com.raju.sringbootelasticsearch.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.raju.sringbootelasticsearch.entity.Product;
import com.raju.sringbootelasticsearch.service.ElasticSearchService;
import com.raju.sringbootelasticsearch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping("/getAll")
    public List<Product> findAll(){
        Iterable<Product> all = productService.getProducts();
        List<Product> products = new ArrayList<>();
        for(Product product : all){
            products.add(product);
        }
        return products;
    }

    @PostMapping("/create")
    public void create(@RequestBody Product product){
        productService.createProduct(product);
    }

    @GetMapping(value = "/matchAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public String matchAll() throws IOException {
        SearchResponse<Map>  searchResponse = elasticSearchService.matchAllService();
        return searchResponse.hits().hits().toString();
    }

    @GetMapping(value = "/matchAllProducts",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> matchAllProds() throws IOException {
        SearchResponse<Product>  searchResponse = elasticSearchService.matchAllProductService();
        List<Hit<Product>> hits = searchResponse.hits().hits();
        List<Product> products = new ArrayList<>();
        for(Hit<Product> hit : hits){
            products.add(hit.source());
        }
        return products;
    }

    @GetMapping(value = "/matchAllProductsWithName/{fieldValue}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> matchAllProdsWithName(@PathVariable String fieldValue) throws IOException {
        SearchResponse<Product>  searchResponse = elasticSearchService.matchProductsWithName(fieldValue);
        List<Hit<Product>> hits = searchResponse.hits().hits();
        List<Product> products = new ArrayList<>();
        for(Hit<Product> hit : hits){
            products.add(hit.source());
        }
        return products;
    }

    @GetMapping(value = "/fuzzySearch/{fieldValue}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> fuzzySearch(@PathVariable String fieldValue) throws IOException {
        SearchResponse<Product>  searchResponse = elasticSearchService.fuzzySearch(fieldValue);
        List<Hit<Product>> hits = searchResponse.hits().hits();
        List<Product> products = new ArrayList<>();
        for(Hit<Product> hit : hits){
            products.add(hit.source());
        }
        return products;
    }


}
