package com.proyect.sistvehiculosapi.Repository;

import com.proyect.sistvehiculosapi.Entity.VehicleBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleBrandRepository extends JpaRepository<VehicleBrandEntity,Integer> {
}
