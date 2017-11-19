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

import com.github.nwillc.rpgraphql.graphql.RuntimeWiringFactory;
import com.github.nwillc.rpgraphql.model.Company;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.pmw.tinylog.Logger;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static ratpack.jackson.Jackson.json;

public class GraphQLHandler implements Handler {
    private static final String GRAPHQL_SCHEMA_IDL = "schema.graphqls";
    private static final String QUERY = "query";
    private static final String ERRORS = "errors";
    private static final String DATA = "data";
    private static final String VARIABLES = "variables";
    private final GraphQL graphql;
    private Map<String, Company> companies = new HashMap<>();

    public GraphQLHandler() throws Exception {
        final SchemaParser schemaParser = new SchemaParser();
        final TypeDefinitionRegistry registry;
        try (final InputStream inputStream = getClass().getClassLoader().getResourceAsStream(GRAPHQL_SCHEMA_IDL);
             final InputStreamReader streamReader = new InputStreamReader(inputStream)) {
            registry = schemaParser.parse(streamReader);
        } catch (Exception e) {
            throw new IllegalStateException("Could not parse graphql schema", e);
        }
        final RuntimeWiring wiring = RuntimeWiringFactory.getRuntimeWiring(companies);
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        graphql = GraphQL.newGraphQL(schemaGenerator.makeExecutableSchema(registry, wiring)).build();
    }

    @Override
    public void handle(Context context) throws Exception {
        context.parse(Map.class).then(payload -> {
            Logger.info(QUERY + ": " + payload.get(QUERY));
            @SuppressWarnings("unchecked")
            Map<String, Object> variables = (Map<String, Object>) payload.get(VARIABLES);
            ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                    .query(payload.get(QUERY).toString())
                    .variables(variables)
                    .build();
            final ExecutionResult executionResult = graphql.execute(executionInput);
            Map<String, Object> result = new LinkedHashMap<>();
            if (executionResult.getErrors().isEmpty()) {
                result.put(DATA, executionResult.getData());
            } else {
                result.put(ERRORS, executionResult.getErrors());
                Logger.error("Errors: {}", executionResult.getErrors());
            }
            context.render(json(result));
        });
    }

    protected GraphQL getGraphql() {
        return graphql;
    }

    protected Map<String, Company> getCompanies() {
        return companies;
    }
}
