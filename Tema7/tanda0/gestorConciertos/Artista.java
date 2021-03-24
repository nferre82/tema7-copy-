package gestorConciertos;

import java.io.Serializable;

public class Artista implements Serializable{

	private String nombre;
	private char categoria;
	
	public Artista(String nombre, char categoria) {
		this.nombre = nombre;
		this.categoria = categoria;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artista other = (Artista) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}


	public String getNombre() {
		return nombre;
	}


	@Override
	public String toString() {
		return "Artista [nombre=" + nombre + ", categoria=" + categoria + "]";
	}
	
	
}
