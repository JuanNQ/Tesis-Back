package com.proyect.sistvehiculosapi.Repository;

import com.proyect.sistvehiculosapi.Entity.InfractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfractionRepository extends JpaRepository<InfractionEntity,Integer> {
}
