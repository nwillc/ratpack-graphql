package com.github.nwillc.rpgraphql;

import com.github.nwillc.rpgraphql.handlers.GraphQLHandler;
import ratpack.server.RatpackServer;

import java.util.logging.Logger;

public class Application {
    private static final Logger LOGGER = Logger.getLogger(Application.class.getSimpleName());

    public static void main(String[] args) throws Exception {
        LOGGER.info("Start");
        new Application(args);
        LOGGER.info("Finish");
    }

    private Application(String[] args) throws Exception {
        final RatpackServer server = RatpackServer.of(s -> s
                .handlers(chain -> chain
                        .post("graphql", new GraphQLHandler())));
        server.start();
    }

}
