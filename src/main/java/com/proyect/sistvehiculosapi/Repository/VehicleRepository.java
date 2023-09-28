package com.proyect.sistvehiculosapi.Repository;

import com.proyect.sistvehiculosapi.Entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity,Integer> {
}
