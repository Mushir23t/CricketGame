package com.project.finalcricketgame.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "overs")
public class Overs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int overs_id;
    private int over_number;
    private ArrayList<Integer> balls = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "innings_id")
    private Innings innings;

    public int getOver_number() {
        return over_number;
    }

    public void setOver_number(int over_number) {
        this.over_number = over_number;
    }

    public void setInnings(Innings innings) {
        this.innings = innings;
    }

    public List<Integer> getBalls() {
        return balls;
    }

    public void addBalls(Integer ball) {
        balls.add(ball);
    }

    public int getOvers_id() {
        return overs_id;
    }
}
