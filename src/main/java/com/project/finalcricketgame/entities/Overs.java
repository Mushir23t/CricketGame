package com.project.finalcricketgame.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "overs")
@Data
public class Overs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int overs_id;
    private int over_number;
    private ArrayList<Integer> balls = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "innings_id")
    private Innings innings;


    public void addBalls(Integer ball) {
        balls.add(ball);
    }
}

