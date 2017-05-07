package com.github.nwillc.rpgraphql.model;

import com.github.nwillc.rpgraphql.graphql.Schema;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;

@GraphQLName(Schema.COMPANY)
public class Company {
    private final String name;
    private Integer revenue;

    public Company(String name) {
        this(name, 0);
    }

    public Company(String name, Integer revenue) {
        this.name = name;
        this.revenue = revenue;
    }

    @GraphQLField
    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    @GraphQLField
    public String getName() {
        return name;
    }

}
