package e2;

import java.io.File;
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
		if (!new File(getNombre()).exists()) {
			return false;
		}
		final char[] ignorar=new char[] {'a','b'};
		FileReader fr=new FileReader(getNombre());
		FileWriter fw=new FileWriter(destino);
		int car=fr.read();
		boolean ignora;
		while (car>0) {
			ignora=false;
			for (int i = 0; i < ignorar.length; i++) {
				if (ignorar[i]==car) {
					ignora=true;
				}
			}
			if (!ignora) {
				fw.write(car);
			}
			car=fr.read();
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
