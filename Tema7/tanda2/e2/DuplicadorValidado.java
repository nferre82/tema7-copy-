package e2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DuplicadorValidado extends DuplicadorDeArchivos{

	public DuplicadorValidado(String nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean duplicar(String destino) throws IOException {
		if (getNombre().equals(destino)) {
			return false;
		}
		if (getNombre().isEmpty()) {
			return false;
		}
		char[] ignorar=new char[] {'a','b'};
		FileReader fr=new FileReader(getNombre());
		FileWriter fw=new FileWriter(destino);
		int car=fr.read();
		boolean ignora=false;
		while (car>0) {
			for (int i = 0; i < ignorar.length; i++) {
				if (ignorar[i]==car) {
					ignora=true;
				}
			}
			if (!ignora) {
				fw.write(car);
			}
			car=fr.read();
			ignora=false;
		}
		fw.close();
		fr.close();
		return true;
	}

	public static void main(String[] args) throws IOException {
		DuplicadorValidado dv=new DuplicadorValidado("letras.txt");
		dv.duplicar("letras2.txt");
	
	}
	
}
