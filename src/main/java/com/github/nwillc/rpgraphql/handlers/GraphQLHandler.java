package com.github.nwillc.rpgraphql.handlers;

import com.github.nwillc.rpgraphql.graphql.Schema;
import com.github.nwillc.rpgraphql.model.Company;
import graphql.ExecutionResult;
import graphql.GraphQL;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static ratpack.jackson.Jackson.json;

public class GraphQLHandler implements Handler {
    private static final Logger LOGGER = Logger.getLogger(GraphQLHandler.class.getSimpleName());
    private static final String QUERY = "query";
    private static final String ERRORS = "errors";
    private static final String DATA = "data";
    private final GraphQL graphql;

    private final List<Company> companies;

    public GraphQLHandler(List<Company> companies) throws Exception {
        this.companies = companies;
        graphql = new GraphQL(new Schema().getSchema());
    }

    @Override
    public void handle(Context context) throws Exception {
        LOGGER.info("GraphQLHandler.handle");
        context.parse(Map.class).then(payload -> {
            Map<String, Object> variables = (Map<String, Object>) payload.get("variables");
            ExecutionResult executionResult = graphql.execute(payload.get(QUERY).toString(), null, this, variables);
            Map<String, Object> result = new LinkedHashMap<>();
            if (executionResult.getErrors().isEmpty()) {
                result.put(DATA, executionResult.getData());
            } else {
                result.put(ERRORS, executionResult.getErrors());
                LOGGER.warning("Errors: " +  executionResult.getErrors());
            }
            context.render(json(result));
        });
    }

    public List<Company> getCompanies() {
        return companies;
    }
}
