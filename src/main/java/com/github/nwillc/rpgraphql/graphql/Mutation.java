package com.github.nwillc.rpgraphql.graphql;

import com.github.nwillc.rpgraphql.model.Company;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;
import graphql.schema.DataFetchingEnvironment;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import static com.github.nwillc.rpgraphql.handlers.GraphQLHandler.getCompanies;

@GraphQLName(Schema.MUTATION)
public class Mutation {
    @GraphQLField
    public static Company addCompany(final DataFetchingEnvironment env,
                                     @NotNull @GraphQLName(Schema.NAME) final String name,
                                     @NotNull @GraphQLName(Schema.REVENUE) final String revenue) {
        List<Company> companies = getCompanies(env);
        final Company company = new Company(name, Integer.valueOf(revenue));
        companies.add(company);
        return company;
    }

    @GraphQLField
    public static Company updateCompany(final DataFetchingEnvironment env,
                                        @NotNull @GraphQLName(Schema.NAME) final String name,
                                        @NotNull @GraphQLName(Schema.REVENUE) final String revenue) {
        final Optional<Company> company = getCompanies(env).stream().filter(c -> c.getName().equals(name)).findAny();
        if (!company.isPresent()) {
            return null;
        }

        company.get().setRevenue(Integer.valueOf(revenue));
        return company.get();
    }

    @GraphQLField
    public static Company deleteCompany(final DataFetchingEnvironment env,
                                        @NotNull @GraphQLName(Schema.NAME) final String name) {
        final List<Company> companies = getCompanies(env);
        final Optional<Company> company = companies.stream().filter(c -> c.getName().equals(name)).findAny();
        if (!company.isPresent()) {
            return null;
        }
        companies.removeIf(c -> c.getName().equals(name));
        return company.get();
    }
}
