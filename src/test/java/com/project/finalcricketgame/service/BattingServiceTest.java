package com.project.finalcricketgame.service;

import com.project.finalcricketgame.entities.Player;
import com.project.finalcricketgame.repository.jpa.BattingStatsRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import javax.persistence.Tuple;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BattingServiceTest {

    @InjectMocks
    BattingService battingService;
    @Mock
    BattingStatsRepository mockRepository;
    @Mock
    PlayerTeamMapService playerTeamMapService;

    @Mock
    PlayerService playerService;

    @BeforeEach()
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @SuppressWarnings("unchecked")
    public void getBattingStatsTest_returnsResponseMadeOfTuple(){
        int playerId = 1;
        Tuple tuple = mock(Tuple.class);
        Optional<Player> player = Optional.of(new Player());
        when(playerService.findByisActive(playerId)).thenReturn(player);
        when(mockRepository.findByPlayer(playerId)).thenReturn(tuple);
        when(tuple.get("sum(balls_played)")).thenReturn(BigDecimal.valueOf(100));
        when(tuple.get("sum(runs_scored)")).thenReturn(BigDecimal.valueOf(50));
        when(tuple.get("matches")).thenReturn(BigInteger.valueOf(5));
        ResponseEntity<?> resultResponse = battingService.getBattingStats(playerId);
        Map<String, BigDecimal> result = (Map<String, BigDecimal>) resultResponse.getBody();
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(BigDecimal.valueOf(100), result.get("balls_played"));
        assertEquals(BigDecimal.valueOf(50), result.get("runs_scored"));
        assertEquals(BigDecimal.valueOf(5), result.get("matches"));
    }

}

