package com.proyect.sistvehiculosapi.Controller;

import com.proyect.sistvehiculosapi.Service.InfractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/infraction")
public class InfractionController {

    @Autowired
    private InfractionService infractionService;

    @GetMapping("/getListInfraction")
    public ResponseEntity<?> getListInfraction(){
        try{
            return new ResponseEntity<>(infractionService.getListInfraction(), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
