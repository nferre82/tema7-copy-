package e3;

import java.io.Serializable;

public class Comercial implements Serializable{

	private String nombre;
	private int salario;
	private TelefonoMovil movil;
	
	public Comercial(String nombre, int salario) {
		super();
		this.nombre = nombre;
		this.salario = salario;
	}
	
	public void ver() {
		System.out.println("Nombre: "+nombre+", salario: "+salario);
		if (movil!=null) {
			System.out.println(", movil: ");
			movil.ver();
		}
	}
	
	public void trabajar() {
		salario+=10;
		movil.llamar(15);
	}
	
	public void trabajar(int g, int m) {
		salario+=g;
		movil.llamar(m);
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
