/*
 * Copyright (c) 2017, nwillc@gmail.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 */

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
