package com.project.finalcricketgame.dto;

import lombok.Data;

@Data
public class MatchDTO {
    private int match_id;
    private String winner = "";
    private String status = "Not started yet";
}
