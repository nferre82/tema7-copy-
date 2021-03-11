package museo;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import e2.Consola;

public class VisitasDia implements Serializable{

	private int dia[];
	private ArrayList<Visita> visitas;
	private int cantidad;
	private HashMap<String, Integer> cantidadHora;
	
	public VisitasDia(int dia, int mes) {
		this.dia = new int[] {dia,mes};
		visitas=new ArrayList<Visita>();
		cantidad=0;
		cantidadHora=new HashMap<String, Integer>();
	}
	
	public boolean aniadeVisita(Visita v) {
		String hora=v.getHora()[0]+":"+v.getHora()[1];
		if (cantidadHora.containsKey(hora)) {
			int ctd=cantidadHora.get(hora);
			if (ctd+v.getCantidad()>50) {
				System.out.println("Hay mas de 50 personas");
				return false;
			}
			cantidadHora.put(hora, v.getCantidad()+ctd);
		} else {
			cantidadHora.put(hora, v.getCantidad());
		}
		
		if (cantidad+v.getCantidad()>50) {
			return false;
		}
		cantidad+=v.getCantidad();
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
	
	public static void verFichero(String fichero) throws IOException, ClassNotFoundException{
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(fichero));
		ArrayList<Visita> arr=(ArrayList<Visita>) ois.readObject();
		for (Visita v : arr) {
			System.out.println(v);
		}
		ois.close();
	}
	
	public boolean actualizaVisita(String nombre) throws IOException {
		Visita v=new Visita(nombre, 0, 0, 0);
		if (visitas.contains(v)) {
			int pos=visitas.indexOf(v);
			v=visitas.get(pos);
			System.out.println("Desea un cambio en tiempo de la visita(t), en el numero de personas(p) o en ambos(a)");
			char resp=Consola.leeChar();
			switch (resp) {
			case 't':
				System.out.println("Nueva hora");
				int hora=Consola.leeInt();
				System.out.println("Nuevos minutos");
				int minutos=Consola.leeInt();
				int horas[]=new int[] {hora,minutos};
				v.setHora(horas);
				break;
			case 'p':
				System.out.println("Nueva cantidad de personas");
				int ctd=Consola.leeInt();
				if (cantidad+ctd-v.getCantidad()>50) {
					System.out.println("No puede haber mas de 50 personas a esa hora");
					return false;
				}
				v.setCantidad(ctd);
				cantidad+=ctd;
				break;
			case 'a':
				System.out.println("Nueva hora");
				int hora2=Consola.leeInt();
				System.out.println("Nuevos minutos");
				int minutos2=Consola.leeInt();
				int horas2[]=new int[] {hora2,minutos2};
				v.setHora(horas2);
				System.out.println("Nueva cantidad de personas");
				int ctd2=Consola.leeInt();
				if (cantidad+ctd2-v.getCantidad()>50) {
					System.out.println("No puede haber mas de 50 personas a esa hora");
					return false;
				}
				cantidad+=ctd2;
				v.setCantidad(ctd2);
				break;
			default:
				return false;
			}
			return true;
		}
		return false;
	}
	
	public void crearInforme() throws IOException {
		BufferedWriter bw=new BufferedWriter(new FileWriter("report_"+dia[1]+"_"+dia[0]));
		Iterator<Visita> it=visitas.iterator();
		while (it.hasNext()) {
			Visita v=it.next();
			bw.write(v.getNombre()+"\t"+v.getHora()[0]+":"+v.getHora()[1]+"\t"+v.getCantidad()+" persona(s)");
			bw.newLine();
		}
		bw.close();
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		VisitasDia vd1=new VisitasDia(1,1);
		VisitasDia vd2=new VisitasDia(2,1);
		Visita v1=new Visita("Juan",2,10,5);
		Visita v2=new Visita("Carlos",48,5,20);
		Visita v3=new Visita("Almudena",1,11,20);
		Visita v4=new Visita("Jacinto",3,11,20);
		vd1.aniadeVisita(v1);
		vd1.aniadeVisita(v2);
		vd1.aniadeVisita(v3);
		vd1.guardaAFichero("visitas.bin");
		vd2.cargarVisitas("visitas.bin");
		verFichero("visitas.bin");
		vd1.actualizaVisita("Juan");
		vd1.aniadeVisita(v4);
		vd1.crearInforme();
	}
	
}
