package com.news.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data

@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String categories;


//@DateTimeFormat(pattern = "yyyy-MM-dd")
@JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
    private Date date = new Date();


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_category")
    private List<Articles> articles;


}
