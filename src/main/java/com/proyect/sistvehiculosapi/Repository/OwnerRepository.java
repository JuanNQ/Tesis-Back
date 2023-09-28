package com.proyect.sistvehiculosapi.Repository;

import com.proyect.sistvehiculosapi.Entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity,Integer> {
}
