package com.example.demo.votingplatform;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/data-insert")
public class DataInsertController {
    private final DataInsert dataInsert;


    @GetMapping
    public void dataInsert(){
        dataInsert.data_insert();
    }
}
