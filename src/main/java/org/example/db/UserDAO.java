package org.example.db;

import org.jdbi.v3.core.Jdbi;

public class UserDAO {
    private Jdbi jdbi;

    public UserDAO(Jdbi jdbi){
        this.jdbi = jdbi;
    }

    public String getName(){
        //line
        return "";
    }
}
