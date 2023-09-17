package com.utn.Tp1;

import com.utn.Tp1.entidades.*;
import com.utn.Tp1.enumeraciones.Estado;
import com.utn.Tp1.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class Tp1Application {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	DomicilioRepository domicilioRepository;

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	DetallePedidoRepository detallePedidoRepository;

	@Autowired
	FacturaRepository facturaRepository;

	@Autowired
	ProductoRepository productoRepository;

	@Autowired
	RubroRepository rubroRepository;

	public static void main(String[] args) {
		SpringApplication.run(Tp1Application.class, args);
		System.out.println("---Estoy Funcionando---");
	}


	@Bean
	CommandLineRunner init(ClienteRepository clienteRepository, DomicilioRepository domicilioRepository){
	return args -> {

		Cliente cliente1 = Cliente.builder()
				.nombre("Enzo")
				.apellido("Cervi")
				.telefono("2612517651")
				.email("enzo@gmail.com")
				.build();

		Domicilio domicilio1 = Domicilio.builder()
				.calle("Calle 1")
				.numero("123")
				.localidad("Lujan de Cuyo")
				.build();

		Domicilio domicilio2 = Domicilio.builder()
				.calle("Calle 2")
				.numero("456")
				.localidad("Capital")
				.build();

		Pedido pedido1= Pedido.builder()
				.estado("Iniciado")
				.fecha(new Date())
				.tipoEnvio("Delivery")
				.total(1500)
				.build();

		Pedido pedido2= Pedido.builder()
				.estado("Entregado")
				.fecha(new Date())
				.tipoEnvio("Retira")
				.total(2500)
				.build();

		DetallePedido detallePedido1 = DetallePedido.builder()
				.cantidad(2)
				.subtotal(1500)
				.build();

		DetallePedido detallePedido2 = DetallePedido.builder()
				.cantidad(1)
				.subtotal(4500)
				.build();

		Factura factura1 = Factura.builder()
				.numero(1)
				.fecha(new Date())
				.descuento(15)
				.formaPago("Efectivo")
				.total(1295)
				.build();

		Factura factura2 = Factura.builder()
				.numero(1)
				.fecha(new Date())
				.descuento(15)
				.formaPago("Efectivo")
				.total(2980)
				.build();

		Producto producto1 = Producto.builder()
				.tipo("Manufacturado")
				.tiempoEstimadoCocina(2)
				.denominacion("el producto 1")
				.precioVenta(750)
				.precioCompra(400)
				.stockActual(150)
				.stockMinimo(10)
				.unidadMedida("kg y g")
				.receta("Receta del producto 1")
				.build();

		Producto producto2 = Producto.builder()
				.tipo("Insumo")
				.tiempoEstimadoCocina(3)
				.denominacion("el producto 2")
				.precioVenta(3500)
				.precioCompra(400)
				.stockActual(150)
				.stockMinimo(10)
				.unidadMedida("kg y g")
				.receta("Receta del producto 2")
				.build();

		Rubro rubro1 = Rubro.builder()
				.denominacion("Comidas")
				.build();

		Rubro rubro2 = Rubro.builder()
				.denominacion("Ingredientes")
				.build();

		//Agregados a cada entidad
		cliente1.agregarDomicilio(domicilio1);
		cliente1.agregarDomicilio(domicilio2);

		cliente1.agregarPedido(pedido1);
		cliente1.agregarPedido(pedido2);

		pedido1.agregarDetallePedido(detallePedido1);
		pedido2.agregarDetallePedido(detallePedido2);

		pedido1.setFactura(factura1);
		pedido2.setFactura(factura2);

		rubro1.agregarProducto(producto1);
		rubro2.agregarProducto(producto2);


		//Mostrar Cliente y Domicilio
		clienteRepository.save(cliente1);

		Cliente clienteRecuperado = clienteRepository.findById(cliente1.getId()).orElse(null);


		if (clienteRecuperado != null) {
			System.out.println("Nombre: " + clienteRecuperado.getNombre());
			System.out.println("Apellido: " + clienteRecuperado.getApellido());
			System.out.println("E-Mail: " + clienteRecuperado.getEmail());
			System.out.println("Teléfono:" + clienteRecuperado.getTelefono());
			clienteRecuperado.mostrarDomicilios();
			clienteRecuperado.mostrarPedidos();

			}

		//Mostrar Pedido 1
		pedidoRepository.save(pedido1);

		Pedido pedidoRecuperado1 = pedidoRepository.findById(pedido1.getId()).orElse(null);

		if (pedidoRecuperado1!= null) {
			System.out.println("Estado: " + pedidoRecuperado1.getEstado());
			System.out.println("Fecha: " + pedidoRecuperado1.getFecha());
			System.out.println("Tipo de envío: " + pedidoRecuperado1.getTipoEnvio());
			System.out.println("Total: " + pedidoRecuperado1.getTotal());
			pedidoRecuperado1.mostrarDetallePedidos();
			}


		//Mostrar Pedido 2
		pedidoRepository.save(pedido2);

		Pedido pedidoRecuperado2 = pedidoRepository.findById(pedido2.getId()).orElse(null);

		if (pedidoRecuperado2!= null) {
			System.out.println("Estado: " + pedidoRecuperado2.getEstado());
			System.out.println("Fecha: " + pedidoRecuperado2.getFecha());
			System.out.println("Tipo de envío: " + pedidoRecuperado2.getTipoEnvio());
			System.out.println("Total: " + pedidoRecuperado2.getTotal());
			pedidoRecuperado2.mostrarDetallePedidos();
		}

		//Mostrar Factura 1
		facturaRepository.save(factura1);

		Factura facturarecuperado1 = facturaRepository.findById(factura1.getId()).orElse(null);

		if (pedidoRecuperado2!= null) {
			System.out.println("Numero: " + facturarecuperado1.getNumero());
			System.out.println("Fecha: " + facturarecuperado1.getFecha());
			System.out.println("Descuento: " + facturarecuperado1.getDescuento() + "%");
			System.out.println("Forma de pago: " + facturarecuperado1.getFormaPago());
			System.out.println("Total: " + facturarecuperado1.getTotal());

		}

		//Mostrar Factura 2
		facturaRepository.save(factura1);

		Factura facturarecuperado2 = facturaRepository.findById(factura2.getId()).orElse(null);

		if (pedidoRecuperado2!= null) {
			System.out.println("Numero: " + facturarecuperado2.getNumero());
			System.out.println("Fecha: " + facturarecuperado2.getFecha());
			System.out.println("Descuento: " + facturarecuperado2.getDescuento() + "%");
			System.out.println("Forma de pago: " + facturarecuperado2.getFormaPago());
			System.out.println("Total: " + facturarecuperado2.getTotal());
		}

		//Mostrar Rubro 1
		rubroRepository.save(rubro1);

		Rubro rubroRecuperado1 = rubroRepository.findById(rubro1.getId()).orElse(null);

		if (rubroRecuperado1!= null) {
			System.out.println("Denominación del rubro: " + rubroRecuperado1.getDenominacion());

			rubroRecuperado1.mostrarProducto();
		}

		//Mostrar Rubro 2
		rubroRepository.save(rubro2);

		Rubro rubroRecuperado2 = rubroRepository.findById(rubro2.getId()).orElse(null);

		if (rubroRecuperado2!= null) {
			System.out.println("Denominación del rubro: " + rubroRecuperado2.getDenominacion());

			rubroRecuperado2.mostrarProducto();
		}

		//Mostrar Producto 1
		productoRepository.save(producto1);
		Producto productoRecuperado1 = productoRepository.findById(producto1.getId()).orElse(null);

		if (productoRecuperado1!= null) {

			System.out.println("Tipo: " + productoRecuperado1.getTipo());
			System.out.println("Tiempo estimado de cocina: " + productoRecuperado1.getTiempoEstimadoCocina());
			System.out.println("Denominación: " + productoRecuperado1.getDenominacion());
			System.out.println("Precio Compra: " + productoRecuperado1.getPrecioCompra());
			System.out.println("Precio Venta: " +productoRecuperado1.getPrecioVenta());
			System.out.println("Stock actual: " + productoRecuperado1.getStockActual());
			System.out.println("Stock minimo: " + productoRecuperado1.getStockMinimo());
			System.out.println("Unidad de medida: " + productoRecuperado1.getUnidadMedida());
			System.out.println("Receta: " + productoRecuperado1.getReceta());

			productoRecuperado1.mostrarDetallesPedido();
		}

		//Mostrar Producto 2
		productoRepository.save(producto2);
		Producto productoRecuperado2 = productoRepository.findById(producto2.getId()).orElse(null);

		if (productoRecuperado1!= null) {

			System.out.println("Tipo: " + productoRecuperado2.getTipo());
			System.out.println("Tiempo estimado de cocina: " + productoRecuperado2.getTiempoEstimadoCocina());
			System.out.println("Denominación: " + productoRecuperado2.getDenominacion());
			System.out.println("Precio Compra: " + productoRecuperado2.getPrecioCompra());
			System.out.println("Precio Venta: " +productoRecuperado2.getPrecioVenta());
			System.out.println("Stock actual: " + productoRecuperado2.getStockActual());
			System.out.println("Stock minimo: " + productoRecuperado2.getStockMinimo());
			System.out.println("Unidad de medida: " + productoRecuperado2.getUnidadMedida());
			System.out.println("Receta: " + productoRecuperado2.getReceta());

			productoRecuperado2.mostrarDetallesPedido();
		}
		};
	}
}






