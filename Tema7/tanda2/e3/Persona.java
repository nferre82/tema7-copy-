package e3;

public class Persona {

	private String nombre, telefono, lugar, edad;
	
	public Persona(String nombre, String telefono, String lugar, String edad) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.lugar = lugar;
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", telefono=" + telefono + ", lugar=" + lugar + ", edad=" + edad + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getLugar() {
		return lugar;
	}

	public String getEdad() {
		return edad;
	}
	
	
}
