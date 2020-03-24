package org.jahom.demo.model;

/*
 *@author jahom
 *@date 2020/3/20 16:15
 */

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 4369478066082631910L;
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
