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

package com.github.nwillc.rpgraphql.handlers;

import com.github.nwillc.rpgraphql.graphql.Schema;
import com.github.nwillc.rpgraphql.model.Company;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetchingEnvironment;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import org.pmw.tinylog.Logger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static ratpack.jackson.Jackson.json;

public class GraphQLHandler implements Handler {
    private static final String QUERY = "query";
    private static final String ERRORS = "errors";
    private static final String DATA = "data";
    private final GraphQL graphql;
    private final List<Company> companies = new ArrayList<>();

    public GraphQLHandler() throws Exception {
        graphql = GraphQL.newGraphQL(new Schema().getSchema()).build();
    }

    @Override
    public void handle(Context context) throws Exception {
        Logger.info("GraphQLHandler.handle");
        context.parse(Map.class).then(payload -> {
            @SuppressWarnings("unchecked")
            Map<String, Object> variables = (Map<String, Object>) payload.get("variables");
            ExecutionResult executionResult = graphql.execute(payload.get(QUERY).toString(), null, this, variables);
            Map<String, Object> result = new LinkedHashMap<>();
            if (executionResult.getErrors().isEmpty()) {
                result.put(DATA, executionResult.getData());
            } else {
                result.put(ERRORS, executionResult.getErrors());
                Logger.warn("Errors: " + executionResult.getErrors());
            }
            context.render(json(result));
        });
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public static List<Company> getCompanies(DataFetchingEnvironment env) {
        return ((GraphQLHandler) env.getSource()).getCompanies();
    }
}
