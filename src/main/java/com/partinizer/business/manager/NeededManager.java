package com.partinizer.business.manager;

import com.partinizer.data.entity.Needed;
import com.partinizer.data.repository.NeededRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vincent on 19/06/2016.
 */
@Component
public class NeededManager {

    private NeededRepository neededRepository;

    @Autowired
    public NeededManager(NeededRepository neededRepository){
        this.neededRepository=neededRepository;
    }

    public void delete(Needed needed){
        neededRepository.delete(needed);
    }
}
