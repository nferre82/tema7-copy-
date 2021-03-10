package museo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class VisitasDia implements Serializable{

	private int dia[];
	private ArrayList<Visita> visitas;
	
	public VisitasDia(int dia, int mes) {
		this.dia = new int[] {dia,mes};
		visitas=new ArrayList<Visita>();
	}
	
	public boolean aniadeVisita(Visita v) {
		if (visitas.contains(v)) {
			return false;
		}
		visitas.add(v);
		ver();
		return true;
	}
	
	public void ver() {
		System.out.println("Dia= "+dia[0]+", mes="+dia[1]+":");
		for (Visita v : visitas) {
			System.out.println("-- "+v);
		}
		System.out.println();
	}
	
	public void guardaAFichero(String fichero) throws IOException {
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(fichero));
		oos.writeObject(visitas);
		oos.writeObject(null);
		oos.close();
	}
	
	public void cargarVisitas(String fichero) throws IOException, ClassNotFoundException {
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(fichero));
		visitas=(ArrayList<Visita>) ois.readObject();
		ois.close();
		ver();
	}
	
	public void verFichero(String fichero) {
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(fichero));

		ois.close();
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		VisitasDia vd1=new VisitasDia(1,1);
		VisitasDia vd2=new VisitasDia(2,1);
		Visita v=new Visita("Juan",2,10,5);
		vd1.aniadeVisita(v);
		vd1.guardaAFichero("visitas.bin");
		vd2.cargarVisitas("visitas.bin");
	}
	
}
