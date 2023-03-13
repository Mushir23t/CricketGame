package com.project.finalcricketgame.entities;


import com.project.finalcricketgame.dto.MatchDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "matches")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int match_id;
    private String winner = "";
    private String status = "Not started yet";
    private boolean isDeleted = false;

    public static MatchDTO toDTO(Match match){
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setMatch_id(match.getMatch_id());
        matchDTO.setStatus(match.getStatus());
        matchDTO.setWinner(match.getWinner());
        return matchDTO;
    }

}
