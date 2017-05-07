package com.github.nwillc.rpgraphql.graphql;

import com.github.nwillc.rpgraphql.model.Company;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;
import graphql.schema.DataFetchingEnvironment;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import static com.github.nwillc.rpgraphql.handlers.GraphQLHandler.getCompanies;

@GraphQLName(Schema.QUERY)
public class Query {

    @GraphQLField
    public static List<Company> companies(final DataFetchingEnvironment env) {
        return getCompanies(env);
    }

    @GraphQLField
    public static Company company(final DataFetchingEnvironment env,
                                  @NotNull @GraphQLName(Schema.NAME) final String name) {
        final Optional<Company> any = getCompanies(env).stream()
                .filter(c -> c.getName().equals(name))
                .findAny();

        return any.orElse(null);
    }


}
