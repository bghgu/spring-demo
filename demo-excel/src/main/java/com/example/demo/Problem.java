package com.example.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by ccoli on 2021/03/27.
 */

@Data
@Entity(name = "Problem2")
public class Problem {

    @Id
    private int seq;

    private int number;

    private String name;

    private int correct_user;

    private int submission_cnt;

    private Double correct_rate;

    private int level;

    private Double avg_try;
}