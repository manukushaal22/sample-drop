package org.example.resources;


import com.codahale.metrics.annotation.Timed;
import org.example.api.Saying;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    private Jdbi jdbi = null;

    public HelloWorldResource(String template, String defaultName, Jdbi jdbi) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
        this.jdbi = jdbi;
    }


    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.orElse(defaultName));
        List<String> names = jdbi.withHandle(handle ->
                handle.createQuery("select username from users")
                        .mapTo(String.class)
                        .list());
        System.out.println(names.toString());
        return new Saying(counter.incrementAndGet(), names.toString());
    }
}


