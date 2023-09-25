package com.proyect.sistvehiculosapi.Service;

import com.proyect.sistvehiculosapi.DTO.ListInternmentDTO;
import com.proyect.sistvehiculosapi.Entity.InternmentEntity;
import com.proyect.sistvehiculosapi.Repository.InternmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InternmentServiceImpl implements InternmentService{

    @Autowired
    private InternmentRepository internmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public InternmentEntity SaveInterment (InternmentEntity internmentEntity){
        return internmentRepository.save(internmentEntity);
    }

    @Override
    public InternmentEntity getInternment(int id){
        return internmentRepository.findById(id).get();
    }

    @Override
    public Page<ListInternmentDTO> getListPageInternment(Pageable pageable, String search){
        if (search.equals(null) || search.isEmpty()){
            Page<InternmentEntity> internmentEntities = internmentRepository.findAll(pageable);
            Page<ListInternmentDTO> listInternmentDTOPage = mapDTO(internmentEntities, pageable);
            return listInternmentDTOPage;
        }
        Page<InternmentEntity> internmentEntityPage = internmentRepository.getListPageInternment(pageable,search);
        Page<ListInternmentDTO> listInternmentDTOPage = mapDTO(internmentEntityPage, pageable);
        return listInternmentDTOPage;

    }

    @Override
    public void deleteInterment(int id){
        System.out.println("entroooooooo");
        internmentRepository.deleteById(id);
    }

    public Page<ListInternmentDTO> mapDTO(Page<InternmentEntity> internmentEntities, Pageable pageable){
        if (internmentEntities.getContent().isEmpty()) return null;

        List<ListInternmentDTO> listInternmentDTOList = new ArrayList<>();
        for (InternmentEntity internmentEntityPage :internmentEntities) {
            ListInternmentDTO listInternmentDTOS = new ListInternmentDTO();
            listInternmentDTOS.setId(internmentEntityPage.getId());
            listInternmentDTOS.setType(internmentEntityPage.getVehicleEntity().getVehicleTypeEntity().getName());
            listInternmentDTOS.setBrand(internmentEntityPage.getVehicleEntity().getVehicleBrandEntity().getBrand());
            listInternmentDTOS.setPlate(internmentEntityPage.getVehicleEntity().getPlateEntity().getPlate());
            listInternmentDTOS.setNumber(internmentEntityPage.getAdmissionTicketEntity().getNumber());
            listInternmentDTOList.add(listInternmentDTOS);
        }

        return new PageImpl<>(listInternmentDTOList, pageable, internmentEntities.getTotalElements());
    }
}
