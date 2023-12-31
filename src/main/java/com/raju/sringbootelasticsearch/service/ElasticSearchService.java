package com.raju.sringbootelasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.raju.sringbootelasticsearch.entity.Customer;
import com.raju.sringbootelasticsearch.entity.Product;
import com.raju.sringbootelasticsearch.util.ElasticSearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class ElasticSearchService {
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public SearchResponse<Map> matchAllService() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplier();
        return elasticsearchClient.search(s->s.query(supplier.get()),Map.class);
    }

    public SearchResponse<Product> matchAllProductService() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplier();
        return elasticsearchClient.search(s->s.index("products").query(supplier.get()),Product.class);
    }

    public SearchResponse<Customer> matchAllCustomerService() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplier();
        return elasticsearchClient.search(s->s.index("customers").query(supplier.get()),Customer.class);
    }

    public SearchResponse<Product> matchProductsWithName(String fieldValue) throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplierMatchQueryWithName(fieldValue);
        return elasticsearchClient.search(s->s.index("products").query(supplier.get()),Product.class);
    }

    public SearchResponse<Product> fuzzySearch(String fieldValue) throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.fuzzyQuerySupplier(fieldValue);
        return elasticsearchClient.search(s->s.index("products").query(supplier.get()),Product.class);
    }
}
