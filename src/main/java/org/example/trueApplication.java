package org.example;

import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.example.db.UserDAO;
import org.example.resources.HelloWorldResource;
import org.jdbi.v3.core.Jdbi;

public class trueApplication extends Application<trueConfiguration> {

    public static void main(final String[] args) throws Exception {
        new trueApplication().run(args);
    }

    @Override
    public String getName() {
        return "true";
    }

    @Override
    public void initialize(final Bootstrap<trueConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final trueConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application

        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName(),
                jdbi
        );
//        final UserDAO userDAO = new UserDAO(jdbi);
//        environment.jersey().register(userDAO);
        environment.jersey().register(resource);

    }

}
