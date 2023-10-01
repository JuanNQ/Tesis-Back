package com.proyect.sistvehiculosapi.Repository;

import com.proyect.sistvehiculosapi.Entity.PlateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlateRepository extends JpaRepository<PlateEntity,Integer> {

    PlateEntity findByPlate (String plate);
}
