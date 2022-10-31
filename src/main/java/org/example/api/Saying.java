package org.example.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.db.User;

import java.util.Optional;

public class Saying {
    private long id;

    private String content;

    private Optional<User> user;

    public Saying() {
        // Jackson deserialization
    }



    public Saying(long id, String content, Optional<User> date) {
        this.id = id;
        this.content = content;
        this.user = date;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }

    @JsonProperty
    public Optional<User> getDate() {
        return user;
    }
}

