package com.raju.sringbootelasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "customers")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    private int id;
    private String name;
    private String address;
}
