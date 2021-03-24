package e3;

public class Persona {

	private String nombre, telefono, lugar;
	private int edad;
	
	public Persona(String nombre, String telefono, String lugar, int edad) {
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

	public int getEdad() {
		return edad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		return result;
	}
	
	
}
