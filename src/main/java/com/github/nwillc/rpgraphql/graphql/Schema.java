package com.github.nwillc.rpgraphql.graphql;

import graphql.annotations.GraphQLAnnotations;
import graphql.schema.GraphQLSchema;

import static graphql.schema.GraphQLSchema.newSchema;

/**
 *
 */
public class Schema {
    public static final String COMPANY = "company";
    public static final String PRODUCT = "product";
    public static final String QUERY = "query";
    public static final String ID = "id";
    public static final String MUTATION = "mutation";

    private final GraphQLSchema schema;

    public Schema() throws IllegalAccessException, NoSuchMethodException, InstantiationException {
        schema = newSchema()
                .query(GraphQLAnnotations.object(Query.class))
                .build();
    }

    public GraphQLSchema getSchema() {
        return schema;
    }
}
