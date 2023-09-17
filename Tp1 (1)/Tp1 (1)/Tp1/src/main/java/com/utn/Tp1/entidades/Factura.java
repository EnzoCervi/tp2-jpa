package com.utn.Tp1.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Factura extends BaseEntidad{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private int numero;

    private Date fecha;

    private double descuento;

    private String formaPago;

    private int total;
}



