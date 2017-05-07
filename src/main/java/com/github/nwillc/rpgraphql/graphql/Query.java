package com.github.nwillc.rpgraphql.graphql;

import com.github.nwillc.rpgraphql.model.Company;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;
import graphql.schema.DataFetchingEnvironment;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@GraphQLName(Schema.QUERY)
public class Query {

    @GraphQLField
    public static List<Company> companies(final DataFetchingEnvironment env) {
        return new ArrayList<>();
    }

    @GraphQLField
    public static Company company(final DataFetchingEnvironment env,
                                  @NotNull @GraphQLName(Schema.ID) final String id) {
        return null;
    }
}
