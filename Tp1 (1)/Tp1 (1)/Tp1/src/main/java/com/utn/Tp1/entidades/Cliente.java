package com.utn.Tp1.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Cliente extends BaseEntidad{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nombre;

    private String apellido;

    private String telefono;

    private String email;

    //Relacion one to many con domicilio

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER )
    @JoinColumn(name = "cliente_id")
    @Builder.Default

    private List<Domicilio> domicilios = new ArrayList<>();

    public void agregarDomicilio(Domicilio domi){
    domicilios.add(domi);
    }

    public void mostrarDomicilios() {
        System.out.println("Domicilios de " + nombre + " " + apellido + ":");
        for (Domicilio domicilio : domicilios) {
            System.out.println("Calle: " + domicilio.getCalle() + ", Número: " + domicilio.getNumero());
        }
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default

    private List<Pedido> pedidos = new ArrayList<>();

    public void agregarPedido (Pedido pedi){
        pedidos.add(pedi);
    }

    public void mostrarPedidos(){
        System.out.println("Pedido de " + nombre + " " + apellido + ":");

        for (Pedido pedido : pedidos){
            System.out.println("Estado: " + pedido.getEstado() + ", Fecha: " + pedido.getFecha() + ", Tipo de envío: " + pedido.getTipoEnvio() + ", Total del pedido: " + pedido.getTotal());
        }
    }
}
