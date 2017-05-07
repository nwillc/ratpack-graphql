package com.github.nwillc.rpgraphql.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class EntityTest {

    public static final String ID = "foo";

    @Test
    public void getId() throws Exception {
        Entity instance = new Entity();
        assertThat(instance.getId()).isNotEmpty();
    }

    @Test
    public void getSetId() throws Exception {
        Entity instance = new Entity(ID);
        assertThat(instance.getId()).isEqualTo(ID);
    }
    
}