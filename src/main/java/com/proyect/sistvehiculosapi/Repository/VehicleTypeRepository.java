package com.proyect.sistvehiculosapi.Repository;

import com.proyect.sistvehiculosapi.Entity.VehicleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleTypeEntity,Integer> {

}
