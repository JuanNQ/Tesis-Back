package com.proyect.sistvehiculosapi.DTO;

import com.proyect.sistvehiculosapi.Entity.InfractionEntity;
import lombok.Data;

@Data
public class RemitterDTO {
    private String authority;
    private String ballot;
    private int infraction;
}
