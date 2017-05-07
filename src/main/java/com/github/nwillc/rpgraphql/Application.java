package com.github.nwillc.rpgraphql;

import com.github.nwillc.rpgraphql.handlers.GraphQLHandler;
import com.github.nwillc.rpgraphql.model.Company;
import ratpack.server.RatpackServer;

import java.util.List;
import java.util.logging.Logger;

import static java.util.Arrays.asList;

public class Application {
    private static final Logger LOGGER = Logger.getLogger(Application.class.getSimpleName());

    public static void main(String[] args) throws Exception {
        new Application(args);
    }

    private Application(String[] args) throws Exception {
        LOGGER.info("Start");

        final List<Company> companies = asList(
                new Company("Spacely Sprockets"),
                new Company("Cogswell Cogs"));
        
        final RatpackServer server = RatpackServer.of(s -> s
                .handlers(chain -> chain
                        .post("graphql", new GraphQLHandler(companies))));
        server.start();
        LOGGER.info("Finish");
    }

}
