package com.proyect.sistvehiculosapi.Service;

import com.proyect.sistvehiculosapi.DTO.InternmentDTO;
import com.proyect.sistvehiculosapi.DTO.ListInternmentDTO;
import com.proyect.sistvehiculosapi.Entity.*;
import com.proyect.sistvehiculosapi.Repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InternmentServiceImpl implements InternmentService{

    @Autowired
    private InternmentRepository internmentRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private PlateRepository plateRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private RemitterRepository remitterRepository;

    @Autowired
    private AdmissionTicketRepository admissionTicketRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public InternmentEntity SaveInterment (InternmentDTO internmentDTO){
        InternmentEntity internmentEntity = new InternmentEntity();
        if (internmentDTO == null) return null;
        boolean infractionRepeat = false;
        boolean vehicleRepeat = false;

        VehicleEntity vehicle = new VehicleEntity();
        internmentEntity.setInput_date(Date.valueOf(LocalDate.now()));
        internmentEntity.setInput_time(Time.valueOf(LocalTime.now()));

        if (!internmentDTO.getFreedom_number().isEmpty()) internmentEntity.setFreedom_number(internmentDTO.getFreedom_number());

        if(internmentDTO.getInfraction() != 0){
            InfractionEntity infractionEntity = new InfractionEntity();
            infractionEntity.setId(internmentDTO.getInfraction());
            internmentEntity.setInfractionEntity(infractionEntity);
            infractionRepeat=true;
        }

        if(!internmentDTO.getVehicle().equals(null) || !internmentDTO.getVehicle().equals("") ){
            VehicleEntity vehicleEntity = new VehicleEntity();
            VehicleTypeEntity vehicleTypeEntity = new VehicleTypeEntity();
            VehicleBrandEntity vehicleBrandEntity = new VehicleBrandEntity();
            vehicleTypeEntity.setId(internmentDTO.getVehicle().getType());
            vehicleBrandEntity.setId(internmentDTO.getVehicle().getBrand());
            if (!internmentDTO.getVehicle().getPlate().isEmpty()){
                PlateEntity plateEntity = new PlateEntity();
                plateEntity.setPlate(internmentDTO.getVehicle().getPlate());
                PlateEntity plate = plateRepository.save(plateEntity);
                vehicleEntity.setPlateEntity(plate);
            }

            vehicleEntity.setColor(internmentDTO.getVehicle().getColor());
            vehicleEntity.setVehicleBrandEntity(vehicleBrandEntity);
            vehicleEntity.setVehicleTypeEntity(vehicleTypeEntity);
            vehicle = vehicleRepository.save(vehicleEntity);
            internmentEntity.setVehicleEntity(vehicle);
            vehicleRepeat = true;
        }

        if(!internmentDTO.getAdmission_ticket().equals(null) || !internmentDTO.getAdmission_ticket().equals("")){
            AdmissionTicketEntity admissionTicketEntity = new AdmissionTicketEntity();
            if (!internmentDTO.getAdmission_ticket().getOwner().equals("") || !internmentDTO.getAdmission_ticket().getOwner().equals(null)){
                OwnerEntity ownerEntity = new OwnerEntity();
                if (!internmentDTO.getAdmission_ticket().getOwner().getName().isEmpty()) ownerEntity.setName(internmentDTO.getAdmission_ticket().getOwner().getName());
                if (!internmentDTO.getAdmission_ticket().getOwner().getLastname().isEmpty()) ownerEntity.setLastname(internmentDTO.getAdmission_ticket().getOwner().getLastname());
                if (!internmentDTO.getAdmission_ticket().getOwner().getAddress().isEmpty()) ownerEntity.setAddress(internmentDTO.getAdmission_ticket().getOwner().getAddress());
                if (!internmentDTO.getAdmission_ticket().getOwner().getPhone().isEmpty()) ownerEntity.setPhone(internmentDTO.getAdmission_ticket().getOwner().getPhone());
                OwnerEntity owner = ownerRepository.save(ownerEntity);
                admissionTicketEntity.setOwnerEntity(owner);
            }
            if (!internmentDTO.getAdmission_ticket().getRemitter().equals("") || !internmentDTO.getAdmission_ticket().getRemitter().equals(null)){
                RemitterEntity remitterEntity = new RemitterEntity();
                remitterEntity.setAuthority(internmentDTO.getAdmission_ticket().getRemitter().getAuthority());
                remitterEntity.setBallot(internmentDTO.getAdmission_ticket().getRemitter().getBallot());
                if(infractionRepeat){
                    InfractionEntity infractionEntity = new InfractionEntity();
                    infractionEntity.setId(internmentDTO.getAdmission_ticket().getRemitter().getInfraction());
                    remitterEntity.setInfractionEntity(infractionEntity);
                }

                RemitterEntity remitter = remitterRepository.save(remitterEntity);
                admissionTicketEntity.setRemitterEntity(remitter);
            }
            if (vehicleRepeat) admissionTicketEntity.setVehicleEntity(vehicle);
            if (!internmentDTO.getAdmission_ticket().getNumber().isEmpty()) admissionTicketEntity.setNumber(Integer.parseInt(internmentDTO.getAdmission_ticket().getNumber()));
            if (!internmentDTO.getAdmission_ticket().getObservations().isEmpty()) admissionTicketEntity.setObservations(internmentDTO.getAdmission_ticket().getObservations());
            if (!internmentDTO.getAdmission_ticket().getIn_charge().isEmpty()) admissionTicketEntity.setIn_charge(internmentDTO.getAdmission_ticket().getIn_charge());
            AdmissionTicketEntity admission = admissionTicketRepository.save(admissionTicketEntity);
            internmentEntity.setAdmissionTicketEntity(admission);
        }

        return internmentRepository.save(internmentEntity);
    }

    @Override
    public InternmentEntity putInternment(int id, InternmentDTO internmentDTO){
        InternmentEntity internmentEntity = getInternment(id);
        if (internmentEntity == null ) return null;
        boolean infractionRepeat = false;
        boolean vehicleRepeat = false;

        VehicleEntity vehicle = new VehicleEntity();

        if (!internmentDTO.getFreedom_number().isEmpty() || internmentDTO.getFreedom_number() != null)
            internmentEntity.setFreedom_number(internmentDTO.getFreedom_number());

        if(internmentDTO.getInfraction() != 0){
            InfractionEntity infractionEntity = new InfractionEntity();
            infractionEntity.setId(internmentDTO.getInfraction());
            internmentEntity.setInfractionEntity(infractionEntity);
            infractionRepeat=true;
        }

        if(internmentDTO.getVehicle() != null || !internmentDTO.getVehicle().equals("") ){
            VehicleEntity vehicleEntity = vehicleRepository.findById(internmentEntity.getVehicleEntity().getId()).get();
            VehicleTypeEntity vehicleTypeEntity = new VehicleTypeEntity();
            VehicleBrandEntity vehicleBrandEntity = new VehicleBrandEntity();
            vehicleTypeEntity.setId(internmentDTO.getVehicle().getType());
            vehicleBrandEntity.setId(internmentDTO.getVehicle().getBrand());
            if (!internmentDTO.getVehicle().getPlate().isEmpty()){
                PlateEntity plateEntity = new PlateEntity();
                if (internmentEntity.getVehicleEntity().getPlateEntity() != null){
                    plateEntity = plateRepository.findById(internmentEntity.getVehicleEntity().getPlateEntity().getId()).get();
                }
                plateEntity.setPlate(internmentDTO.getVehicle().getPlate());
                PlateEntity plate = plateRepository.save(plateEntity);
                vehicleEntity.setPlateEntity(plate);
            } else if(internmentDTO.getVehicle().getPlate().isEmpty() && internmentEntity.getVehicleEntity().getPlateEntity() != null){
                plateRepository.deleteById(internmentEntity.getVehicleEntity().getPlateEntity().getId());
            }

            vehicleEntity.setColor(internmentDTO.getVehicle().getColor());
            vehicleEntity.setVehicleBrandEntity(vehicleBrandEntity);
            vehicleEntity.setVehicleTypeEntity(vehicleTypeEntity);
            vehicle = vehicleRepository.save(vehicleEntity);
            internmentEntity.setVehicleEntity(vehicle);
            vehicleRepeat = true;
        }

        if(internmentDTO.getAdmission_ticket() != null || !internmentDTO.getAdmission_ticket().equals("")){
            AdmissionTicketEntity admissionTicketEntity = admissionTicketRepository.findById(internmentEntity.getAdmissionTicketEntity().getId()).get();
            if (!internmentDTO.getAdmission_ticket().getOwner().equals("") || internmentDTO.getAdmission_ticket().getOwner() != null){
                OwnerEntity ownerEntity = ownerRepository.findById(internmentEntity.getAdmissionTicketEntity().getOwnerEntity().getId()).get();
                if (internmentDTO.getAdmission_ticket().getOwner().getName() != null)
                    ownerEntity.setName(internmentDTO.getAdmission_ticket().getOwner().getName());
                if (internmentDTO.getAdmission_ticket().getOwner().getLastname() != null)
                    ownerEntity.setLastname(internmentDTO.getAdmission_ticket().getOwner().getLastname());
                if (internmentDTO.getAdmission_ticket().getOwner().getAddress() != null)
                    ownerEntity.setAddress(internmentDTO.getAdmission_ticket().getOwner().getAddress());
                if (internmentDTO.getAdmission_ticket().getOwner().getPhone() != null)
                    ownerEntity.setPhone(internmentDTO.getAdmission_ticket().getOwner().getPhone());
                OwnerEntity owner = ownerRepository.save(ownerEntity);
                admissionTicketEntity.setOwnerEntity(owner);
            }
            if (!internmentDTO.getAdmission_ticket().getRemitter().equals("") || internmentDTO.getAdmission_ticket().getRemitter() != null){
                RemitterEntity remitterEntity = remitterRepository.findById(internmentEntity.getAdmissionTicketEntity().getRemitterEntity().getId()).get();
                remitterEntity.setAuthority(internmentDTO.getAdmission_ticket().getRemitter().getAuthority());
                remitterEntity.setBallot(internmentDTO.getAdmission_ticket().getRemitter().getBallot());
                if(infractionRepeat){
                    InfractionEntity infractionEntity = new InfractionEntity();
                    infractionEntity.setId(internmentDTO.getAdmission_ticket().getRemitter().getInfraction());
                    remitterEntity.setInfractionEntity(infractionEntity);
                }

                RemitterEntity remitter = remitterRepository.save(remitterEntity);
                admissionTicketEntity.setRemitterEntity(remitter);
            }
            if (vehicleRepeat) admissionTicketEntity.setVehicleEntity(vehicle);
            if (!internmentDTO.getAdmission_ticket().getNumber().isEmpty() || internmentDTO.getAdmission_ticket().getNumber() != null)
                admissionTicketEntity.setNumber(Integer.parseInt(internmentDTO.getAdmission_ticket().getNumber()));
            if (!internmentDTO.getAdmission_ticket().getObservations().isEmpty() || internmentDTO.getAdmission_ticket().getObservations() != null)
                admissionTicketEntity.setObservations(internmentDTO.getAdmission_ticket().getObservations());
            if (!internmentDTO.getAdmission_ticket().getIn_charge().isEmpty() || internmentDTO.getAdmission_ticket().getIn_charge() != null)
                admissionTicketEntity.setIn_charge(internmentDTO.getAdmission_ticket().getIn_charge());
            AdmissionTicketEntity admission = admissionTicketRepository.save(admissionTicketEntity);
            internmentEntity.setAdmissionTicketEntity(admission);
        }
        return internmentRepository.save(internmentEntity);
    }

    @Override
    public InternmentEntity getInternment(int id){
        return internmentRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro un internamiento con ese id"));
    }

    @Override
    public InternmentEntity getInternmentByPlate(String plate){
        PlateEntity plateEntity = plateRepository.findByPlate(plate);
        if (plateEntity == null) return null;
        VehicleEntity vehicleEntity = vehicleRepository.findByPlateEntity(plateEntity);
        if (vehicleEntity == null) return null;
        InternmentEntity internmentEntity = internmentRepository.findByVehicleEntity(vehicleEntity);
        if (internmentEntity == null) return null;
        return internmentEntity;
    }

    @Override
    public Page<ListInternmentDTO> getListPageInternment(Pageable pageable, String search){
        if (search.isEmpty()){
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
        internmentRepository.deleteById(id);
    }

    public Page<ListInternmentDTO> mapDTO(Page<InternmentEntity> internmentEntities, Pageable pageable){
        if (internmentEntities.getContent().isEmpty()) return null;

        List<ListInternmentDTO> listInternmentDTOList = new ArrayList<>();
        for (InternmentEntity internmentEntityPage :internmentEntities) {
            ListInternmentDTO listInternmentDTOS = new ListInternmentDTO();
            listInternmentDTOS.setId(internmentEntityPage.getId());
            if (internmentEntityPage.getVehicleEntity().getVehicleTypeEntity() != null)
                listInternmentDTOS.setType(internmentEntityPage.getVehicleEntity().getVehicleTypeEntity().getName());
            if (internmentEntityPage.getVehicleEntity().getVehicleBrandEntity() != null)
                listInternmentDTOS.setBrand(internmentEntityPage.getVehicleEntity().getVehicleBrandEntity().getBrand());
            if (internmentEntityPage.getVehicleEntity().getPlateEntity() != null)
                listInternmentDTOS.setPlate(internmentEntityPage.getVehicleEntity().getPlateEntity().getPlate());
            listInternmentDTOS.setNumber(internmentEntityPage.getAdmissionTicketEntity().getNumber());
            listInternmentDTOList.add(listInternmentDTOS);
        }

        return new PageImpl<>(listInternmentDTOList, pageable, internmentEntities.getTotalElements());
    }
}
