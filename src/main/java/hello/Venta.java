package hello;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Venta {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(optional = false)
	private Cliente cliente;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LineaVenta> lineas = new ArrayList<LineaVenta>();


	protected Venta(Cliente cliente) {
		this.cliente=cliente;
	}

	public void agregarProducto(Producto producto, Integer cantidad) {
		LineaVenta lineaVenta = new LineaVenta(producto, cantidad);
		lineas.add(lineaVenta);
	}
	
	public void agregarLinea() {
		LineaVenta lineaVenta = new LineaVenta();
		lineas.add(lineaVenta);
	}
	
	public List<LineaVenta> getLineas() {
		return lineas;
	}
	
	public Double getTotal() {
		Double total=0.0;
		for(LineaVenta linea : lineas) {
			total += linea.getSubtotal();
		}
		return total;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public LineaVenta getLineaAt(Integer index)  {
		LineaVenta linea = null;
		if (lineas.size() > index) {
			linea = lineas.get(index);
		}
		return linea;
	}

}
