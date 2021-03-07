package e3;

public class Comercial {

	private String nombre;
	private int salario;
	private TelefonoMovil movil;
	
	public Comercial(String nombre, int salario) {
		super();
		this.nombre = nombre;
		this.salario = salario;
	}
	
	public void ver() {
		System.out.println("Nombre: "+nombre+", salario: "+salario+", movil: ");
		movil.ver();
	}
	
	public void trabajar() {
		salario+=10;
		movil.llamar(15);
	}
	
	public void trabajar(int g, int m) {
		salario+=g;
		movil.llamar(m);
	}
}
