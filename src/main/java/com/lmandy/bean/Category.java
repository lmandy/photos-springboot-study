package com.lmandy.bean;

import javax.persistence.*;
import java.util.List;

/**
 * Created by lmandy on 2017/10/18.
 */
public class Category {
    private Integer id;
    private String category;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
