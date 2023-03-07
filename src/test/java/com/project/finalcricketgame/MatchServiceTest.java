package com.project.finalcricketgame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class MatchServiceTest {

    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "1, 2, 3",
            "5, -1, 4",
            "-1, 0, -1"
    })
    public void dummyTest(int a, int b, int sum){
        Assertions.assertEquals(sum, add(a,  b));
    }

    private int add(int a, int b){
        return a + b;
    }

}
