package com.gavin.pojo;

import lombok.*;

import java.util.List;

/**
 * Created by 17428 on 2023/3/5.
 */



@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor

public class SentimentDTO {


    private String startDate;

    private String endDate;

    private List<String> indexList;


    @Override
    public String toString() {
        return "SentimentDTO{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", indexList=" + indexList +
                '}';
    }
}
