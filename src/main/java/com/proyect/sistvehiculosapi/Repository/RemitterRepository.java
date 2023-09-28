package com.proyect.sistvehiculosapi.Repository;

import com.proyect.sistvehiculosapi.Entity.RemitterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemitterRepository extends JpaRepository<RemitterEntity, Integer> {
}
