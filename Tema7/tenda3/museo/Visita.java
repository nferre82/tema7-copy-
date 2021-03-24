package museo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public class Visita implements Serializable{

	private String nombre;
	private int cantidad;
	private int hora[];//[0]hora,[1]minutos
	
	
	public Visita(String nombre, int cantidad, int hora[]) throws IOException {
		this.nombre = nombre;
		this.cantidad = cantidad;
		if (VisitasDia.horaBuena(hora)) {
			this.hora = hora;
		} else {
			primeraHora();
		}
	}
	
	public void primeraHora() throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("tiempos_visita.txt"));
		String linea=br.readLine();
		String separado[]=linea.split("\t");
		int horaBuena=Integer.parseInt(separado[0]);
		int minutosBuena=Integer.parseInt(separado[1]);
		this.hora = new int[] {horaBuena, minutosBuena};
		br.close();
	}
	
	public boolean horaMax50(int horas[]) {
		if (hora==null) {
			return false;
		}
		if (hora[0]==horas[0] && hora[1]==horas[1]) {
			return true;
		}
		return false;
	}
	
	public int getCantidad() {
		return cantidad;
	}

	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setHora(int[] hora) throws IOException {
		if (VisitasDia.horaBuena(hora)) {
			this.hora = hora;
		} else {
			primeraHora();
		}
	}

	
	public String getNombre() {
		return nombre;
	}

	public int[] getHora() {
		return hora;
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
