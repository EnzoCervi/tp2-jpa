package com.utn.Tp1.repositorios;

import com.utn.Tp1.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository <Producto, Long> {
}
