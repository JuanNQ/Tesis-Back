package com.proyect.sistvehiculosapi.Repository;

import com.proyect.sistvehiculosapi.Entity.AdmissionTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionTicketRepository extends JpaRepository<AdmissionTicketEntity, Integer> {
}
