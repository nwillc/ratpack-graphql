package com.github.nwillc.rpgraphql.model;

import com.github.nwillc.rpgraphql.graphql.Schema;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;

@GraphQLName(Schema.COMPANY)
public class Company extends Entity {
    private final String name;

    public Company(String name) {
        this.name = name;
    }

    @GraphQLField
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
