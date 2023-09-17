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

public class Rubro extends BaseEntidad{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String denominacion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER )
    @JoinColumn(name = "rubro_id")
    @Builder.Default

    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto produ){

        productos.add(produ);
    }

    public void mostrarProducto() {
        for (Producto producto : productos) {
            System.out.println("Tipo: " + producto.getTipo() + ", Tiempo estimado de cocina: " + producto.getTiempoEstimadoCocina() +
                    ", Denominación: " + producto.getDenominacion() + ", Precio de venta: " + producto.getPrecioVenta() + ", Precio de compra: " + producto.getPrecioCompra() +
                    ", Stock actual: " + producto.getStockActual() + ", Stock mínimo: " + producto.getStockMinimo() + ", Unidad de medida: " + producto.getUnidadMedida() +
                    ", Receta: " + producto.getReceta());
        }
    }
}
