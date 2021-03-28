package com.example.demo;

import com.example.demo.excel.ExcelRead;
import com.example.demo.excel.ExcelReadOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by ccoli on 2021/03/27.
 */

@Slf4j
@Service
public class ExcelService {

    private ProblemRepository problemRepository;

    public ExcelService(final ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public List<Problem> readExcel(MultipartFile excel) throws IOException {
        File file = new File(System.getProperty("user.dir") + "/" + excel.getOriginalFilename());
        excel.transferTo(file);

        ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(file.getAbsolutePath());
        excelReadOption.setStartRow(1);
        excelReadOption.setOutputColumns("A", "B", "C", "D", "E", "F", "G", "H");

        List<Map<String, String>> excelContent = ExcelRead.read(excelReadOption, 8);
        List<Problem> problemList = new LinkedList<>();

        for (Map<String, String> content : excelContent) {
            Problem problem = new Problem();

            problem.setSeq((int) Double.parseDouble(content.get("A")));
            problem.setNumber((int) Double.parseDouble(content.get("B")));
            problem.setName(content.get("C"));
            problem.setCorrect_user((int) Double.parseDouble(content.get("D")));
            problem.setSubmission_cnt((int) Double.parseDouble(content.get("E")));
            problem.setCorrect_rate(Double.parseDouble(content.get("F")));
            problem.setLevel((int) Double.parseDouble(content.get("G")));
            problem.setAvg_try(Double.parseDouble(content.get("H")));
            problemList.add(problem);

            log.info(problem.getName());
        }

        problemRepository.saveAll(problemList);

        file.delete();

        return problemList;
    }
}