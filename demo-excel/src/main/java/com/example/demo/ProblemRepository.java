package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ccoli on 2021/03/27.
 */

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Integer> {

}