package e3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class GestionComercial {

	private String nomfich;

	public GestionComercial(String nomfich) {
		super();
		this.nomfich = nomfich;
	}
	
	public void guardaComerciales(ArrayList<Comercial> comerciales) throws IOException {
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(nomfich));
		Iterator<Comercial> it=comerciales.iterator();
		while (it.hasNext()) {
			oos.writeObject(it.next());
		}
		oos.writeObject(null);
		oos.close();
	}
	
	public void verComerciales() throws IOException, ClassNotFoundException {
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(nomfich));
		Comercial c=(Comercial) ois.readObject();
		while (c!=null) {
			c.ver();
			c=(Comercial) ois.readObject();
		}
		ois.close();
	}
	
	public Comercial buscaComercial(String nomComer) throws IOException, ClassNotFoundException {
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(nomfich));
		Comercial c=(Comercial) ois.readObject();
		while (c!=null) {
			if (c.getNombre().equals(nomComer)) {
				return c;
			}
			c=(Comercial) ois.readObject();
		}
		ois.close();
		return null;
	}
	
	public void generarFichMoviles(String nomfichero) throws IOException, ClassNotFoundException {
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(nomfichero));
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(nomfich));
		Comercial c=(Comercial) ois.readObject();
		while (c!=null) {
			TelefonoMovil m=c.getMovil();
			if (m!=null) {
				m.cargar(10);
				oos.writeObject(m);
			}
			c=(Comercial) ois.readObject();
		}
		ois.close();
		oos.close();
	}
	
	public void trabajarTodos() {

	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		Comercial c1=new Comercial("Juan", 1000);
		Comercial c2=new Comercial("Ana", 2000);
		ArrayList<Comercial> a1=new ArrayList<Comercial>();
		a1.add(c1);
		a1.add(c2);
		GestionComercial g1=new GestionComercial("Comerciales.bin");
		g1.guardaComerciales(a1);
		g1.verComerciales();
		System.out.println();
		System.out.println(g1.buscaComercial("Juan"));
		g1.buscaComercial("Ana").ver();
		System.out.println();
		
	}
}
