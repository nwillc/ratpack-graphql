package com.github.nwillc.rpgraphql.model;

import com.github.nwillc.rpgraphql.graphql.Schema;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;

@GraphQLName(Schema.PRODUCT)
public class Product extends Entity {
    private final Company company;
    private final String name;

    public Product(Company company, String name) {
        this.company = company;
        this.name = name;
    }

    @GraphQLField
    public Company getCompany() {
        return company;
    }

    @GraphQLField
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + getId() +
                ", company=" + company +
                ", name='" + name + '\'' +
                '}';
    }
}
