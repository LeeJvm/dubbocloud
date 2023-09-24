package com.gavin.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 17428 on 2023/3/5.
 */



@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor

public class SentimentDTO  implements Serializable {


    private static final long serialVersionUID = 7450261711626882013L;
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

    public static void main(String[] args) {
        //@Builder的使用方式
        SentimentDTO sentimentDTO = SentimentDTO.builder().startDate("1111").build();



    }


}
