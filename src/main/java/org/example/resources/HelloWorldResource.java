package org.example.resources;


import com.codahale.metrics.annotation.Timed;
import org.example.api.Saying;
import org.example.db.User;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
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
    public Saying sayHello(@QueryParam("name") Optional<String> name, @QueryParam("num") Optional<Integer> num) {

        jdbi.registerRowMapper(User.class,
                (rs, ctx) -> {
                    String d1 = rs.getString(5);
                    String d2 = rs.getString(6);
                    DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
                    Instant date1 = null;
                    Instant date2 = null;
                    try {
                        date1 = formatter.parse(d1).toInstant();
                        date2 = formatter.parse(d2).toInstant();
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    return new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), date1, date2
                    , rs.getInt(7), rs.getInt(8));
                });
        Optional<User> names = jdbi.withHandle(handle ->
                handle.createQuery(String.format("select * from users where DATE(created_on) = '%s' order by price ASC",name.orElse(defaultName)))
                        .mapTo(User.class)
                        .findFirst());

        int num_tickets = names.get().getTickets() - num.orElse(0);
        int id = Math.toIntExact(names.get().getUserId());

        jdbi.withHandle(handle ->
                handle.createUpdate(String.format("update users set tickets = '%d' where user_id = '%d' ", num_tickets, id))
                        .execute());
//        System.out.println(names.toString());
//        String date = names.get(0);
//        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
//        Instant date1 = null;
//        try {
//            date1 = formatter.parse(date).toInstant();
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
        return new Saying(counter.incrementAndGet(), names.toString(), names);
    }
}


