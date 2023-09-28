package com.proyect.sistvehiculosapi.Service;

import com.proyect.sistvehiculosapi.DTO.InternmentDTO;
import com.proyect.sistvehiculosapi.DTO.ListInternmentDTO;
import com.proyect.sistvehiculosapi.Entity.InternmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InternmentService {
    InternmentEntity SaveInterment(InternmentDTO internmentDTO);

    InternmentEntity getInternment(int id);

    Page<ListInternmentDTO> getListPageInternment(Pageable pageable, String search);

    void deleteInterment(int id);
}
