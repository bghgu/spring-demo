package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by ccoli on 2021/03/27.
 */

@RestController
public class ExcelController {

    private final ExcelService excelService;

    public ExcelController(final ExcelService excelService) {
        this.excelService = excelService;
    }

    @PostMapping("/test")
    public List<Problem> test(MultipartFile excel) throws IOException {
        return excelService.readExcel(excel);
    }
}