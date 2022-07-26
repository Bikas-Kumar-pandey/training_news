package com.news.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
public class Articles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titles;
    private String description;

    Date date=new Date();
//            Date date=new Timestamp(System.currentTimeMillis());

}
