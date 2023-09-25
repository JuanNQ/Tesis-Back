package com.proyect.sistvehiculosapi.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@Table(name = "taadmissionticket")
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionTicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerEntity ownerEntity;

    @ManyToOne
    @JoinColumn(name = "remitter_id")
    private RemitterEntity remitterEntity;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicleEntity;

    private int number;
    private String observations;
    private String in_charge;
}
