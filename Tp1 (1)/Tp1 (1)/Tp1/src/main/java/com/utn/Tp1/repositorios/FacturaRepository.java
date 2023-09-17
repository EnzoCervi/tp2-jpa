package com.utn.Tp1.repositorios;

import com.utn.Tp1.entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
