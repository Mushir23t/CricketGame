package com.project.finalcricketgame.service;

import com.project.finalcricketgame.entities.Overs;
import com.project.finalcricketgame.repository.jpa.InningsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.apache.tomcat.jni.Time.sleep;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class InningsServiceTest {
    @Mock
    private InningsRepository inningsRepository;
    @Mock
    private BowlingService bowlingService;

    @Mock
    private BattingService battingService;

    @Mock
    private OversService oversService;

    @Spy
    @InjectMocks
    private InningsService inningsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBeginInnings() {
        int target = 40;
        doReturn(1).when(inningsService).getNextBowler();
        doReturn(6).when(inningsService).beginOver(anyInt(), any(Overs.class));
        ArrayList<Overs> oversList = new ArrayList<>();
        inningsService.setOversList(oversList);
        inningsService.beginInnings(target);
        verify(inningsService, atMost(20)).beginOver(anyInt(), any(Overs.class));
        verify(inningsService, times(1)).save();
    }

    @Test
    public void testBeginOver(){
        int target = 100;
        Overs curOver = mock(Overs.class);
        doNothing().when(inningsService).updateStats(anyInt());
        inningsService.beginOver(target,curOver);
        verify(curOver, atMost(6)).addBalls(anyInt());
        verify(oversService, atLeastOnce()).save(any(Overs.class));
    }

    @Test
    public void testUpdateStats(){
        Integer score = 5;
        inningsService.setBowlerNumber(1);
        inningsService.setOnStrikeBatsman(1);
        inningsService.setNonStrikeBatsman(2);
        int runs = inningsService.getRuns();
        doNothing().when(bowlingService).updateStats(anyInt(), anyInt());
        doNothing().when(battingService).updateStats(anyInt(), anyInt());
        inningsService.updateStats(score);
        Assertions.assertEquals(inningsService.getRuns(), runs + score);
    }


}
