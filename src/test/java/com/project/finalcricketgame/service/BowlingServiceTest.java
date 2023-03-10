package com.project.finalcricketgame.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Random;

@RunWith(MockitoJUnitRunner.class)
public class BowlingServiceTest {

    @Mock
    BowlingService bowlingService;

    @Test
    public void getBowlerTest_returnsBowlerNumberForNextBowler() {
        int prevBowler = 2;
        HashMap<Integer, Integer> oversBowledByPlayers = new HashMap<>();
        oversBowledByPlayers.put(3, 4);
        oversBowledByPlayers.put(4, 4);
        oversBowledByPlayers.put(5, 4);
        oversBowledByPlayers.put(2, 1);
        Assertions.assertNotEquals(2,BowlingService.GetBowler(prevBowler,oversBowledByPlayers));
        Assertions.assertNotEquals(3,BowlingService.GetBowler(prevBowler,oversBowledByPlayers));
        Assertions.assertNotEquals(4,BowlingService.GetBowler(prevBowler,oversBowledByPlayers));
        Assertions.assertNotEquals(5,BowlingService.GetBowler(prevBowler,oversBowledByPlayers));
    }

    @Test
    void RepeatedTestForGetBowler(){
        for(int i = 0; i < 1000000; i++){
            getBowlerTest_returnsBowlerNumberForNextBowler();
        }
    }
}

