package com.news.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class NewsByDateResponse {

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @Temporal(TemporalType.DATE)
@JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
    private Date date;
    private String titles;
    private String description;
    private String Categories;
}
