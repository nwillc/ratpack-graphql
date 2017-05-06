package com.github.nwillc.rpgraphql.handlers;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.util.Map;
import java.util.logging.Logger;

import static ratpack.jackson.Jackson.json;

public class GraphQLHandler implements Handler {
    private static final Logger LOGGER = Logger.getLogger(GraphQLHandler.class.getSimpleName());

    @Override
    public void handle(Context context) throws Exception {
        LOGGER.info("GraphQLHandler.handle");
        context.parse(Map.class).then(payload -> {
            context.render(json(payload));
        });
    }
}
