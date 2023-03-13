package com.project.finalcricketgame.service;

import com.project.finalcricketgame.entities.Match;
import com.project.finalcricketgame.entities.MatchTeamMapping;
import com.project.finalcricketgame.entities.Team;
import com.project.finalcricketgame.repository.jpa.MatchRepository;
import com.project.finalcricketgame.repository.jpa.MatchTeamMappingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class MatchServiceTest {

    @InjectMocks
    private MatchService matchService;
    @Mock
    MatchRepository mockRepository;
    @Mock
    MatchTeamMappingService mockMatchTeamMappingService;
    @Mock
    InningsService mockInningsService;
    @Mock
    TeamService mockTeamService;
    @Mock
    ScoreCardService scoreCardService;
    @Mock
    MatchTeamMappingRepository matchTeamMappingRepository;
    @Spy
    @InjectMocks
    private MatchService matchServiceSpy;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void CreateMatchTest_returnsMatchId() {
        String team1 = "Team 1";
        String team2 = "Team 2";
        Match match = Match.builder().match_id(1).build();
        when(mockRepository.save(any(Match.class))).thenReturn(match);
        doNothing().when(mockMatchTeamMappingService).createMapping(1, team1, team2);
        assertEquals(matchService.createMatch(team1, team2), 1);
        verify(mockRepository, times(1)).save(any(Match.class));
    }

    @Test
    public void playMatchTest_returnsNothing() {
        int match_id = 1;
        MatchTeamMapping mockMatchTeamMapping = Mockito.mock(MatchTeamMapping.class);
        Mockito.when(mockMatchTeamMappingService.findByMatchId((match_id)))
                .thenReturn(mockMatchTeamMapping);

        Match mockMatch = Mockito.mock(Match.class);
        Mockito.when(mockRepository.findById(match_id)).thenReturn(mockMatch);
        Mockito.when(mockMatchTeamMapping.getTeam1_id()).thenReturn("1");
        Mockito.when(mockMatchTeamMapping.getTeam2_id()).thenReturn("2");
        Team mockTeam1 = Mockito.mock(Team.class);
        Team mockTeam2 = Mockito.mock(Team.class);

        Mockito.when(mockTeamService.findTeamById(mockMatchTeamMapping.getTeam1_id())).
                thenReturn(mockTeam1);
        Mockito.when(mockTeamService.findTeamById(mockMatchTeamMapping.getTeam2_id())).
                thenReturn(mockTeam2);
        doNothing().when(mockInningsService).initialiseInnings(1, mockTeam1, mockTeam2, mockMatch);
        doNothing().when(mockInningsService).beginInnings(Integer.MAX_VALUE);

        Mockito.when(mockInningsService.getRuns()).thenReturn(100);
        doNothing().when(mockInningsService).initialiseInnings(2, mockTeam2, mockTeam1, mockMatch);
        doNothing().when(mockInningsService).beginInnings(100 + 1);
        doNothing().when(scoreCardService).createScoreCard(match_id);
        Mockito.doReturn("LOL").when(matchServiceSpy).updateMatchWinner(any(), any(), anyInt());
        matchServiceSpy.playMatch(match_id);
        verify(mockMatchTeamMappingService, times(1)).findByMatchId(any(Integer.class));
        verify(mockTeamService, times(2)).findTeamById(any(String.class));
        verify(mockInningsService, times(2)).initialiseInnings(any(Integer.class), any(Team.class), any(Team.class), any(Match.class));
        verify(mockInningsService, times(2)).beginInnings(any(Integer.class));
        verify(scoreCardService, times(1)).createScoreCard(match_id);
        Mockito.verify(matchServiceSpy, times(0)).updateMatchWinner(mockTeam1, mockTeam2, 1);
    }

    @ParameterizedTest
    @CsvSource({
            "100, 150, Team 2",
            "200, 100, Team 1",
            "150, 150,'' "
    })
    public void testUpdateMatchWinner_returnsNothing(int firstInningsTotal, int secondInningsTotal, String expectedWinner) {
        Match mockMatch = Mockito.mock(Match.class);
        Team mockTeam1 = Mockito.mock(Team.class);
        Team mockTeam2 = Mockito.mock(Team.class);
        Mockito.when(mockRepository.findById(anyInt())).thenReturn(mockMatch);
        Mockito.when(mockTeam1.getName()).thenReturn("Team 1");
        Mockito.when(mockTeam2.getName()).thenReturn("Team 2");
        matchService.setFirstInningsTotal(firstInningsTotal);
        matchService.setSecondInningsTotal(secondInningsTotal);
        matchService.updateMatchWinner(mockTeam1, mockTeam2, 1);
        Mockito.verify(mockMatch, Mockito.times(1)).setWinner(expectedWinner);
        Mockito.verify(mockMatch, Mockito.times(1)).setStatus("Finished");
        Mockito.verify(mockRepository, Mockito.times(1)).save(mockMatch);
    }

    @ParameterizedTest
    @CsvSource({
            "1,Deleted successfully",
            "2,No such match with match_id : 2",
            "3,Deleted successfully",
            "4,No such match with match_id : 4"
    })
    public void remove_ReturnsStringOnCall(int match_id, String expectedOutput) {
        Match mockMatch = Mockito.mock(Match.class);
        if (match_id % 2 == 0)
            Mockito.when(mockRepository.findById(anyInt())).thenReturn(null);
        else {
            Mockito.when(mockRepository.findById(anyInt())).thenReturn(mockMatch);
            when(mockMatch.isDeleted()).thenReturn(true);
        }
        assertEquals(expectedOutput, matchService.remove(match_id));
        if (match_id % 2 == 0) {
            verify(mockRepository, never()).save(any());
        } else
            verify(mockRepository, times(1)).save(any());
    }


}
