package com.proyect.sistvehiculosapi.Service;

import com.proyect.sistvehiculosapi.Entity.InfractionEntity;
import com.proyect.sistvehiculosapi.Repository.InfractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfractionServiceImpl implements InfractionService {

    @Autowired
    InfractionRepository infractionRepository;

    @Override
    public List<InfractionEntity> getListInfraction (){
        return infractionRepository.findAll();
    }
}
