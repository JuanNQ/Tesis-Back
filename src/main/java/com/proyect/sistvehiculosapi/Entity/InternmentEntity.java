package com.proyect.sistvehiculosapi.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Data
@Entity
@Table(name = "tainternment")
@AllArgsConstructor
@NoArgsConstructor
public class InternmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date input_date;
    private Date output_date;
    private Time input_time;
    private Time output_time;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicleEntity;

    @OneToOne
    @JoinColumn(name = "admission_ticket_id")
    private AdmissionTicketEntity admissionTicketEntity;

    private String freedom_number;

    @OneToOne
    @JoinColumn(name = "infraction_id")
    private InfractionEntity infractionEntity;

}
