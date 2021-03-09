package e2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DuplicarCar extends DuplicadorDeArchivos{

	public DuplicarCar(String nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}

	public boolean duplicar(String destino) throws IOException {
		if (getNombre().equals(destino)) {
			return false;
		}
		if (getNombre().isEmpty()) {
			return false;
		}
		FileReader fr=new FileReader(getNombre());
		FileWriter fw=new FileWriter(destino);
		int car=fr.read();
		while (car>0) {
			fw.write(car);
			car=fr.read();
		}
		fw.close();
		fr.close();
		return true;
	}

	public static void main(String[] args) throws IOException {
		DuplicarCar dc=new DuplicarCar("letras.txt");
		dc.duplicar("letras2.txt");
	
	}
}
