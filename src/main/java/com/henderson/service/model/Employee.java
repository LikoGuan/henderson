package com.henderson.service.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by likoguan on 4/11/17.
 */
public class Employee implements Serializable {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
