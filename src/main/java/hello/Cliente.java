package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cliente {

	@Id
	@GeneratedValue
	private Long id;

	private String nombre;
	private String apellido;
	private String profesion;

	protected Cliente() {
	}

	public Cliente(String nombre, String apellido, String profesion) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.profesion = profesion;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

}
