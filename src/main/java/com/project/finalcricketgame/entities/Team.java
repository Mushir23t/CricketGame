package com.project.finalcricketgame.entities;

import com.project.finalcricketgame.dto.TeamDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "team")
@Data
@NoArgsConstructor
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

    public Team(TeamDTO teamDTO) {
        this.name = teamDTO.getName();
    }

}
