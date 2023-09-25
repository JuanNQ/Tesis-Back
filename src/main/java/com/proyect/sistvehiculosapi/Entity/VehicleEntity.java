package com.proyect.sistvehiculosapi.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@Table(name = "tmvehicle")
@AllArgsConstructor
@NoArgsConstructor
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "type_vehicle_id")
    private VehicleTypeEntity vehicleTypeEntity;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private VehicleBrandEntity vehicleBrandEntity;

    @OneToOne
    @JoinColumn(name = "plate_id")
    private PlateEntity plateEntity;

    private String color;

}
