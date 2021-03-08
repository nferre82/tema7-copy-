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
		oos.writeObject(null);
		ois.close();
		oos.close();
	}
	
	public void trabajarTodos() throws IOException, ClassNotFoundException {
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(nomfich));
		Comercial c=(Comercial) ois.readObject();
		ArrayList<Comercial> arr=new ArrayList<Comercial>();
		while (c!=null) {
			c.trabajar();
			arr.add(c);
			c=(Comercial) ois.readObject();
		}
		ois.close();
		guardaComerciales(arr);
	}
	
	public void visualizarMoviles(String nomfichero) throws IOException, ClassNotFoundException {
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(nomfichero));
		TelefonoMovil c=(TelefonoMovil) ois.readObject();
		while (c!=null) {
			c.ver();
			c=(TelefonoMovil) ois.readObject();
		}
		ois.close();
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		Comercial c1=new Comercial("Juan", 1000,new TelefonoMovil(666666666, 10));
		Comercial c2=new Comercial("Ana", 2000,new TelefonoMovil(677777777, 20));
		Comercial c3=new Comercial("Jon", 4000,new TelefonoMovil(677837777, 200));
		Comercial c4=new Comercial("Alberto", 200,new TelefonoMovil(683877777, 2000));
		Comercial c5=new Comercial("Javi", 20,new TelefonoMovil(677774377, 20));
		ArrayList<Comercial> a1=new ArrayList<Comercial>();
		a1.add(c1);
		a1.add(c2);
		a1.add(c3);
		a1.add(c4);
		a1.add(c5);
		GestionComercial g1=new GestionComercial("Comerciales.bin");
		g1.guardaComerciales(a1);
		g1.verComerciales();
		System.out.println();
		if (g1.buscaComercial("Javi")!=null) {
			g1.buscaComercial("Javi").ver();
		}else {
			System.out.println(g1.buscaComercial("Javi"));
		}
		if (g1.buscaComercial("Lorena")!=null) {
			g1.buscaComercial("Lorena").ver();
		} else {
			System.out.println(g1.buscaComercial("Lorena"));
		}
		System.out.println();
		g1.generarFichMoviles("moviles.bin");
		g1.visualizarMoviles("moviles.bin");
		System.out.println();
		g1.trabajarTodos();
		g1.verComerciales();
	}
}
