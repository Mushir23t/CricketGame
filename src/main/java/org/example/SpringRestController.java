package org.example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@Controller
public class SpringRestController {

    @GetMapping("/Scorecard")
    @ResponseBody
    public ResponseEntity<String> connect() {
        ArrayList<String> strings = SpringUtility.getStrings();
        String joinedString = String.join("<br>", strings);
        joinedString = joinedString.replace(" ", "&nbsp;");
        return ResponseEntity.ok(joinedString);
    }

}