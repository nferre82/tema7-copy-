package e2;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DuplicarPorBloques extends DuplicadorDeArchivos{

	public DuplicarPorBloques(String nombre) {
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
		FileReader fr=new FileReader(getNombre());
		FileWriter fw=new FileWriter(destino);
		char[] car=new char[20];
		int n=fr.read(car);
		while (n>0) {
			fw.write(car,0,n);
			n=fr.read(car);
		}
		fw.close();
		fr.close();
		return true;
	}

	public static void main(String[] args) throws IOException {
		DuplicarPorBloques db=new DuplicarPorBloques("letras.txt");
		db.duplicar("letras2.txt");
	
	}

	
	
}
