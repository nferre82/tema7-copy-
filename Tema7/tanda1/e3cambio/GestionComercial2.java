package e3cambio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class GestionComercial2 {

	private String nomfich;

	public GestionComercial2(String nomfich) {
		super();
		this.nomfich = nomfich;
	}
	
	public void guardaComerciales(ArrayList<Comercial> comerciales) throws IOException {
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(nomfich));
		oos.writeObject(comerciales);
		oos.close();
	}
	
	public void verComerciales() throws IOException, ClassNotFoundException {
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(nomfich));
		ArrayList<Comercial> c=(ArrayList<Comercial>) ois.readObject();
		Iterator<Comercial> it=c.iterator();
		while (it.hasNext()) {
			it.next().ver();
		}
		ois.close();
	}
	
	public Comercial buscaComercial(String nomComer) throws IOException, ClassNotFoundException {
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(nomfich));
		ArrayList<Comercial> c=(ArrayList<Comercial>) ois.readObject();
		Iterator<Comercial> it=c.iterator();
		while (it.hasNext()) {
			Comercial com=it.next();
			if (com.getNombre().equals(nomComer)) {
				return com;
			}
		}
		ois.close();
		return null;
	}
	
	public void generarFichMoviles(String nomfichero) throws IOException, ClassNotFoundException {
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(nomfichero));
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(nomfich));
		ArrayList<Comercial> c=(ArrayList<Comercial>) ois.readObject();
		Iterator<Comercial> it=c.iterator();
		while (it.hasNext()) {
			Comercial com=it.next();
			TelefonoMovil m=com.getMovil();
			m.cargar(10);
			oos.writeObject(m);
		}
		oos.writeObject(null);
		ois.close();
		oos.close();
	}
	
	public void trabajarTodos() throws IOException, ClassNotFoundException {
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(nomfich));
		ArrayList<Comercial> arr=new ArrayList<Comercial>();
		ArrayList<Comercial> carr=(ArrayList<Comercial>) ois.readObject();
		Iterator<Comercial> it=carr.iterator();
		while (it.hasNext()) {
			Comercial com=it.next();
			com.trabajar();
			arr.add(com);		
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
		GestionComercial2 g1=new GestionComercial2("Comerciales.bin");
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
