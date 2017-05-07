package com.github.nwillc.rpgraphql.model;

import graphql.annotations.GraphQLField;

import java.util.UUID;

public class Entity {
    private final String id;

    public Entity(String id) {
        this.id = id;
    }

    public Entity() {
        this(UUID.randomUUID().toString());
    }

    @GraphQLField
    public String getId() {
        return id;
    }
}
