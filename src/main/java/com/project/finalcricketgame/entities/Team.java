package com.project.finalcricketgame.entities;

import com.project.finalcricketgame.dto.TeamDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "team")
public class Team {
    @Id
    private String name;
    @Temporal(TemporalType.TIME)
    @Column(name = "created_at")
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    public Team() {
    }

    public Team(TeamDTO teamDTO){
        this.name = teamDTO.getName();
    }

    Team(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
