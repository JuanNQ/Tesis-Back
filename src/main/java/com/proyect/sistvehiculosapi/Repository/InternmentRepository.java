package com.proyect.sistvehiculosapi.Repository;

import com.proyect.sistvehiculosapi.Entity.InternmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InternmentRepository extends JpaRepository<InternmentEntity,Integer> {

    @Query(value = "select ie " +
            "from InternmentEntity ie " +
            "inner join AdmissionTicketEntity at on at.id = ie.admissionTicketEntity " +
            "where at.number like %:search% ")
    Page<InternmentEntity> getListPageInternment(Pageable pageable, String search);
}
