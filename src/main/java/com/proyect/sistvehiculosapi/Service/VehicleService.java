package com.proyect.sistvehiculosapi.Service;

import com.proyect.sistvehiculosapi.Entity.VehicleBrandEntity;
import com.proyect.sistvehiculosapi.Entity.VehicleTypeEntity;

import java.util.List;

public interface VehicleService {
    List<VehicleTypeEntity> getListVehicleType();
    List<VehicleBrandEntity> getListVehicleBrand();
}
