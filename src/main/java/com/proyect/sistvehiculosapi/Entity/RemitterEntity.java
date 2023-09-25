package com.proyect.sistvehiculosapi.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@Table(name = "tmremitter")
@AllArgsConstructor
@NoArgsConstructor
public class RemitterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String authority;
    private String ballot;

    @OneToOne
    @JoinColumn(name = "infraction_id")
    private InfractionEntity infractionEntity;
}
