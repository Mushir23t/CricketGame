package com.project.finalcricketgame.service;

import com.project.finalcricketgame.entities.Innings;
import com.project.finalcricketgame.entities.Overs;
import com.project.finalcricketgame.repository.OversRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class OversService {
    @Autowired
    OversRepository oversRepository;

    public void save(Overs overs) {
        oversRepository.save(overs);
    }

    public void updateInnings(int id, Innings innings) {
        Optional<Overs> optionalOvers = oversRepository.findById(id);
        if (optionalOvers.isPresent()) {
            Overs overs = optionalOvers.get();
            overs.setInnings(innings);
            oversRepository.save(overs);
        } else {
            throw new EntityNotFoundException("Over with ID " + id + " not found.");
        }
    }
}
