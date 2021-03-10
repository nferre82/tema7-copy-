package museo;

import java.io.Serializable;
import java.util.Arrays;

public class Visita implements Serializable{

	private String nombre;
	private int cantidad;
	private int hora[];//[0]hora,[1]minutos
	
	public Visita(String nombre, int cantidad, int hora, int minutos) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		if (hora>24 || hora<0) {
			System.out.println("Hora incorrecta. Nueva hora=10");
			hora=10;
		}
		if (minutos>60 || minutos<0) {
			System.out.println("Minutos incorrectos. Nuevos minutos=0");
			minutos=0;
		}
		this.hora = new int[] {hora, minutos};
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
		Visita other = (Visita) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Visita [nombre=" + nombre + ", cantidad=" + cantidad + ", hora=" + hora[0]+":"+hora[1] + "]";
	}
	
	
}
