package com.raju.sringbootelasticsearch.util;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;

import java.util.function.Supplier;

public class ElasticSearchUtil {

    public static Supplier<Query> supplier(){
        return () -> Query.of(q->q.matchAll(matchAllQuery()));
    }

    public static MatchAllQuery matchAllQuery(){
        return new MatchAllQuery.Builder().build();
    }

}
