package com.example.breno.appbus.Appbus.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;


@Setter
@Getter
@Entity
@Table(name = "TB_APPBUS")
public class AppbusModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Idcard;
    private double TarifaOfBus;
    private double Saldo;
}
