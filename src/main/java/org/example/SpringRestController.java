package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
//import rep.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@RestController
public class SpringRestController {

    @GetMapping("/Scorecard")
    @ResponseBody
    public ResponseEntity<?> getMatch() {
        MatchResult last = this.matchResultRepository.findAll().get(0);
        String temp = last.getResult();
        String finalString = String.join("<br>", temp);
//        finalString = finalString.replace(" ", "&nbsp;");
        return ResponseEntity.ok(finalString);
    }

    @Autowired
    private MatchResultRepository matchResultRepository;
    @PostMapping("/Scorecard")
    public ResponseEntity<?> addMatch(){
        String result = SpringUtility.getStrings();
        MatchResult temp = new MatchResult(result);
        MatchResult matchResult = this.matchResultRepository.save(temp);
        return ResponseEntity.ok(matchResult);
    }
//


}