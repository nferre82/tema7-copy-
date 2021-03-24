package gestorConciertos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GestorConciertos {

	private HashMap<Concierto, Integer> conciertos;

	public GestorConciertos(HashMap<Concierto, Integer> conciertos) {
		this.conciertos = conciertos;
	}
	
	public void guardarConciertosLlenos(String nomFich) throws IOException {
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(nomFich));
		Iterator<Concierto> it=conciertos.keySet().iterator();
		while (it.hasNext()) {
			Concierto concierto=it.next();
			if (conciertos.get(concierto)==concierto.getAforo()) {
				oos.writeObject(concierto);
			}
		}
		oos.writeObject(null);
		oos.close();
	}
	
	public static void verFich(String nomFich) throws IOException, ClassNotFoundException {
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(nomFich));
		Concierto concierto=(Concierto) ois.readObject();
		while (concierto!=null) {
			System.out.println(concierto.toString());
			concierto=(Concierto) ois.readObject();
		}
		ois.close();
	}
	
	public void depurarFichero(String nomFich) throws ClassNotFoundException, IOException {
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(nomFich));
		Concierto concierto=(Concierto) ois.readObject();
		ArrayList<Concierto> arr=new ArrayList<Concierto>();
		while (concierto!=null) {
			arr.add(concierto);
			concierto=(Concierto) ois.readObject();
		}
		ois.close();
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(nomFich));
		Iterator<Concierto> it=arr.iterator();
		while (it.hasNext()) {
			Concierto con=it.next();
			if (arr.contains(con)) {
				it.remove();
				it=arr.iterator();
			}
		}
		it=arr.iterator();
		while (it.hasNext()) {			
			Concierto concer=it.next();
			oos.writeObject(concer);		
		}
		oos.writeObject(null);
		oos.close();
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		HashMap<Concierto, Integer> mapa=new HashMap<Concierto, Integer>();
		Concierto c1=new Concierto("Canciones", 'a', 30);
		Concierto c2=new Concierto("Musicales", 'c', 20);
		Concierto c3=new Concierto("Top pop", 'x', 10);
		mapa.put(c1, 30);
		mapa.put(c2, 20);
		mapa.put(c3, 5);
		GestorConciertos gc=new GestorConciertos(mapa);
		gc.guardarConciertosLlenos("llenos.txt");
		GestorConciertos.verFich("llenos.txt");
	}
}
