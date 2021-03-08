package e3cambio;

import java.io.Serializable;

public class Comercial implements Serializable{

	private String nombre;
	private int salario;
	private TelefonoMovil movil;
	
	public Comercial(String nombre, int salario, TelefonoMovil movil) {
		this.nombre = nombre;
		this.salario = salario;
		this.movil = movil;
	}

	public void ver() {
		System.out.print("Nombre: "+nombre+", salario: "+salario);
		if (movil!=null) {
			System.out.print(", movil: ");
			movil.ver();
		}
	}
	
	public void trabajar() {
		if (movil.getSaldo()>=15) {
			salario+=10;
			movil.llamar(15);
		}
		
	}
	
	public void trabajar(int g, int m) {
		if (movil.getSaldo()>=m*2) {
			salario+=g;
			movil.llamar(m);
		}
	}

	public String getNombre() {
		return nombre;
	}

	public TelefonoMovil getMovil() {
		return movil;
	}

	public void setMovil(TelefonoMovil movil) {
		this.movil = movil;
	}
	
	
	
	
}
