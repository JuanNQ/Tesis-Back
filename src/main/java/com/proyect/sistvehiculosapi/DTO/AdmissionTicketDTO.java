package com.proyect.sistvehiculosapi.DTO;

import lombok.Data;

@Data
public class AdmissionTicketDTO {
    private OwnerDTO owner;
    private RemitterDTO remitter;
    private VehicleDTO vehicle;
    private String number;
    private String observations;
    private String in_charge;
}
