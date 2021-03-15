package museo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
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
		cantidad=cantidadHora.get(hora);
		if (cantidad>50) {
			return false;
		}
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
	
	public boolean actualizarHora(Visita v) throws IOException {
		System.out.println("Nueva hora");
		int hora=Consola.leeInt();
		System.out.println("Nuevos minutos");
		int minutos=Consola.leeInt();
		int horas[]=new int[] {hora,minutos};
		if (v.getHora()[0]==horas[0] && v.getHora()[1]==horas[1]) {
			return true;
		}
		String h=horas[0]+":"+horas[1];
		if (!Visita.horaBuena(hora, minutos)) {
			System.out.println("Hora erronea");
			return false;
		}
		int val3=v.getCantidad();
		int total3=0;
		if (cantidadHora.containsKey(horas[0]+":"+horas[1])) {
			total3=cantidadHora.get(horas[0]+":"+horas[1]);
			if (total3+val3>50) {
				System.out.println("Hay mas de 50 personas");
				return false;
			}
			cantidadHora.put(horas[0]+":"+horas[1], v.getCantidad()+val3);
		} else {
			cantidadHora.put(horas[0]+":"+horas[1], v.getCantidad());
		}
		v.setHora(horas);
		return true;
	}
	
	public boolean actualizarPersonas(Visita v) {
		System.out.println("Nueva cantidad de personas");
		int val=v.getCantidad();
		int ctd=Consola.leeInt();
		int total=0;
		if (cantidadHora.containsKey(v.getHora()[0]+":"+v.getHora()[1])) {
			total=cantidadHora.get(v.getHora()[0]+":"+v.getHora()[1]);
			if (total+ctd-val>50) {
				System.out.println("Hay mas de 50 personas");
				return false;
			}
			cantidadHora.put(v.getHora()[0]+":"+v.getHora()[1], v.getCantidad()+ctd);
		}
		cantidad=cantidadHora.get(v.getHora()[0]+":"+v.getHora()[1]);
		v.setCantidad(ctd);
		return true;
	}
	
	public boolean actualizarTodo(Visita v) throws IOException {
		System.out.println("Nueva hora");
		int hora=Consola.leeInt();
		System.out.println("Nuevos minutos");
		int minutos=Consola.leeInt();
		int horas[]=new int[] {hora,minutos};
		if (v.getHora()[0]==horas[0] && v.getHora()[1]==horas[1]) {
			return true;
		}
		String h=horas[0]+":"+horas[1];
		if (!Visita.horaBuena(hora, minutos)) {
			System.out.println("Hora erronea");
			return false;
		}
		System.out.println("Nueva cantidad de personas");
		int val=v.getCantidad();
		int ctd=Consola.leeInt();
		int total=0;
		if (cantidadHora.containsKey(horas[0]+":"+horas[1])) {
			total=cantidadHora.get(horas[0]+":"+horas[1]);
			if (total+ctd>50) {
				System.out.println("Hay mas de 50 personas");
				return false;
			}
			cantidadHora.put(horas[0]+":"+horas[1], v.getCantidad()+ctd);
		}
		cantidad=cantidadHora.get(horas[0]+":"+horas[1]);
		v.setCantidad(ctd);
		v.setHora(horas);
		return true;
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
				actualizarHora(v);
				break;
				
			case 'p':
				actualizarPersonas(v);
				break;
			case 'a':
				actualizarTodo(v);
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
	
	public HashMap<String, Integer> mapaLibres() throws IOException {
		HashMap<String, Integer> libres=new HashMap<String, Integer>();
		BufferedReader br=new BufferedReader(new FileReader("tiempos_visita.txt"));
		String linea=br.readLine();
		while (linea!=null) {
			int num=50;
			String separado[]=linea.split("\t");
			int horaBuena=Integer.parseInt(separado[0]);
			int minutosBuena=Integer.parseInt(separado[1]);
			String hora=horaBuena+":"+minutosBuena;
			if (cantidadHora.containsKey(hora)) {
				num-=cantidadHora.get(hora);
			}
			libres.put(hora, num);
			linea=br.readLine();
		}
		br.close();
		return libres;
	}
	
	public String tiempoVisitaMasCercano(int hora, int minutos) throws IOException {
		HashMap<String, Integer> libres=mapaLibres();
		Iterator<String> it=libres.keySet().iterator();
		int minHora=Integer.MAX_VALUE, minMinutos=Integer.MAX_VALUE, laHora=0, losMinutos=0;
		while (it.hasNext()) {
			String horas=it.next();
			int cpHora=hora;
			if (libres.get(horas)>0) {
				String separado[]=horas.split(":");
				int hor=Integer.parseInt(separado[0]);
				int min=Integer.parseInt(separado[1]);
				if (hor-cpHora<0) {
					cpHora--;
				}
				if (Math.abs(cpHora-hor)<=minHora) {
					minHora=Math.abs(cpHora-hor);
					if (Math.abs(minutos-min)<=minMinutos) {
						minMinutos=Math.abs(minutos-min);
						losMinutos=min;
						laHora=hor;
					}
				}
			}
		}
		return laHora+":"+losMinutos;
	}
	
	public int borrarVisitasPasadas() throws IOException {
		int cantidad=0;
		Date d=new Date();
		int horaActual=d.getHours();
		int minutosActual=d.getMinutes();
		Iterator<String> it= cantidadHora.keySet().iterator();
		while (it.hasNext()) {
			String hora=it.next();
			String[] separado=hora.split(":");
			int hora1=Integer.parseInt(separado[0]);
			int minutos1=Integer.parseInt(separado[1]);
			if (horaActual>=hora1) {
				if (minutosActual>minutos1) {
					cantidadHora.remove(hora);
					cantidad++;
				}
			}
		}
		FileOutputStream fos=new FileOutputStream(new File("visitasPasadas_"+horaActual+"_"+minutosActual+".bin"));
		fos.write(null);
		fos.close();
		return cantidad;
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		VisitasDia vd1=new VisitasDia(1,1);
		VisitasDia vd2=new VisitasDia(2,1);
		Visita v1=new Visita("Juan",2,9,30);
		Visita v2=new Visita("Carlos",47,9,30);
		Visita v3=new Visita("Almudena",40,11,20);
		Visita v4=new Visita("Jacinto",10,11,20);
		vd1.aniadeVisita(v1);
		vd1.aniadeVisita(v2);
		vd1.aniadeVisita(v3);
		vd1.aniadeVisita(v4);
		vd1.guardaAFichero("visitas.bin");
		vd2.cargarVisitas("visitas.bin");
		verFichero("visitas.bin");
		vd1.actualizaVisita("Juan");
		vd1.aniadeVisita(v4);
		vd1.crearInforme();
		System.out.println(vd1.tiempoVisitaMasCercano(11, 00));
		System.out.println(vd1.borrarVisitasPasadas());
	}
	
}
