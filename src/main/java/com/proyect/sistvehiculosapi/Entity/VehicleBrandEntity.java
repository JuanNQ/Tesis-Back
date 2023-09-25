package com.proyect.sistvehiculosapi.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@Table(name = "tmvehiclebrand")
@AllArgsConstructor
@NoArgsConstructor
public class VehicleBrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String brand;
}
