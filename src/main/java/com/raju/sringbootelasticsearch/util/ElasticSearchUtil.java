package com.raju.sringbootelasticsearch.util;

import co.elastic.clients.elasticsearch._types.query_dsl.FuzzyQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;

import java.util.function.Supplier;

public class ElasticSearchUtil {

    public static Supplier<Query> supplier(){
        return () -> Query.of(q->q.matchAll(matchAllQuery()));
    }

    public static MatchAllQuery matchAllQuery(){
        return new MatchAllQuery.Builder().build();
    }

    public static Supplier<Query> supplierMatchQueryWithName(String fieldValue){
        return () -> Query.of(q->q.match(matchQueryWithName(fieldValue)));
    }

    public static MatchQuery matchQueryWithName(String fieldValue){
        return new MatchQuery.Builder().field("name").query(fieldValue).build();
    }

    public static Supplier<Query> fuzzyQuerySupplier(String value){
        Supplier<Query> supplier = () -> Query.of(q->q.fuzzy(createFuzzyQuery(value)));
        return supplier;
    }

    public static FuzzyQuery createFuzzyQuery(String value){
        return new FuzzyQuery.Builder().field("name").value(value).build();
    }

}
