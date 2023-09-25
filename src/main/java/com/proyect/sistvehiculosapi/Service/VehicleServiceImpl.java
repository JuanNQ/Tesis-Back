package com.proyect.sistvehiculosapi.Service;

import com.proyect.sistvehiculosapi.Entity.VehicleBrandEntity;
import com.proyect.sistvehiculosapi.Entity.VehicleTypeEntity;
import com.proyect.sistvehiculosapi.Repository.VehicleBrandRepository;
import com.proyect.sistvehiculosapi.Repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    private VehicleBrandRepository vehicleBrandRepository;

    @Override
    public List<VehicleTypeEntity> getListVehicleType(){
//        List<VehicleTypeEntity> listVehicleType = vehicleType.findAll();
//        List<String> types = new ArrayList<>();
//        for (VehicleTypeEntity vehicleType :listVehicleType) {
//            types.add(vehicleType.getName());
//        }
        return vehicleTypeRepository.findAll();
    }

    @Override
    public List<VehicleBrandEntity> getListVehicleBrand(){
        return vehicleBrandRepository.findAll();
    }


}
