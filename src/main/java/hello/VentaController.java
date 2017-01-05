package hello;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("session")
public class VentaController {

    private static final String template = "Hello, %s!";
   
    private final ClienteRepository customerRepository;
    private final ProductoRepository productRepository;
    private Venta venta;

	@Autowired
	public VentaController(ClienteRepository customerRepository, ProductoRepository productRepository) {
		this.customerRepository = customerRepository;
		this.productRepository = productRepository;
	}

	 @RequestMapping("/recargarVenta")
	    public Venta recargarVenta() {
	    	return venta;
	 }
	
    @RequestMapping("/iniciarVenta")
    public Venta iniciarVenta(@RequestParam(value="clienteId") Long id) {
    	Cliente cliente = customerRepository.findOne(id);
    	
    	venta = new Venta(cliente);
    	
    	return venta;
    }
    
    @RequestMapping("/actualizarProducto")
    public Venta actualizarLinea(@RequestParam(value="index") Integer index, @RequestParam(value="id") Long id, @RequestParam(value="cantidad", defaultValue="1") Integer cantidad) {
    	Producto producto = productRepository.findOne(id);
    	if (producto == null)
    		return venta;
    	LineaVenta linea = venta.getLineaAt(index);
    	if (linea == null) {
    		agregarProducto(producto, cantidad);
    	} else {
	    	linea.setProducto(producto);
	    	linea.setCantidad(cantidad);
    	}
		return venta;
    }
    
    public Venta agregarProducto(Producto producto , Integer cantidad) {
    	if (producto!=null) {
	    	venta.agregarProducto(producto, cantidad);
    	}
    	return venta;
    }
    
    @RequestMapping("/actualizarCantidad")
    public Venta actualizarCantidad(@RequestParam(value="index") Integer index, @RequestParam(value="cantidad", defaultValue="1") Integer cantidad) {
    	if (cantidad<1) {
    		return venta;
    	}
    	LineaVenta linea = venta.getLineaAt(index);
    	if (linea != null) {
	    	linea.setCantidad(cantidad);
    	}
		return venta;
    }
    
    @RequestMapping("/agregarLinea")
    public Venta agregarLinea() {
    	System.out.println(venta);
    	venta.agregarLinea();
		return venta;
    }
    
   
}
