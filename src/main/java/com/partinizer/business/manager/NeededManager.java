package com.partinizer.business.manager;

import com.partinizer.data.entity.Needed;
import com.partinizer.data.repository.NeededRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * I am a manager which should contains the behavior of the Neededs of the project.
 */
@Component
public class NeededManager {

    protected NeededRepository neededRepository;

    @Autowired
    public NeededManager(NeededRepository neededRepository){
        this.neededRepository=neededRepository;
    }

    public void delete(Needed needed){
        neededRepository.delete(needed);
    }
}
