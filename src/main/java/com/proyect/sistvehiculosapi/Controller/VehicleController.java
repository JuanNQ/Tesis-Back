package com.proyect.sistvehiculosapi.Controller;

import com.proyect.sistvehiculosapi.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/getListVehicleType")
    public ResponseEntity<?> getListVehicleType(){
        try{
            return new ResponseEntity<>(vehicleService.getListVehicleType(), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getListVehicleBrand")
    public ResponseEntity<?> getListVehicleBrand(){
        try{
            return new ResponseEntity<>(vehicleService.getListVehicleBrand(), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
