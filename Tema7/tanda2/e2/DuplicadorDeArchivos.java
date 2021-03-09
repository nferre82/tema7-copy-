package e2;

import java.io.IOException;

public abstract class DuplicadorDeArchivos {

	private String nombre;
	private final static String NOMBRE="archivo.bin";

	public DuplicadorDeArchivos(String nombre) {
		this.nombre = nombre;
	}

	public DuplicadorDeArchivos() {
		this.nombre=NOMBRE;
	}
	
	public abstract boolean duplicar(String destino) throws IOException;

	public String getNombre() {
		return nombre;
	}
	
	
	
	
}
