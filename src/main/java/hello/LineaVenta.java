package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LineaVenta {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(optional = false)
	private Producto producto=null;
	private Integer cantidad=null;
	
	public LineaVenta(Producto producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	
	public LineaVenta() {
	}


	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getDescripcion() {
		return producto.getDescripcion();
	}
	public Double getPrecio() {
		return producto.getPrecio();
	}


	public Double getSubtotal() {
		return producto.getPrecio() * cantidad;
	}
}
