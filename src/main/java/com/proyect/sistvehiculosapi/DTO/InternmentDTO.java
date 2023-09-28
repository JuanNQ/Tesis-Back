package com.proyect.sistvehiculosapi.DTO;

import com.proyect.sistvehiculosapi.Entity.AdmissionTicketEntity;
import com.proyect.sistvehiculosapi.Entity.InfractionEntity;
import com.proyect.sistvehiculosapi.Entity.VehicleEntity;
import lombok.Data;

@Data
public class InternmentDTO {
    private AdmissionTicketDTO admission_ticket;
    private String freedom_number;
    private int infraction;
    private VehicleDTO vehicle;
}
