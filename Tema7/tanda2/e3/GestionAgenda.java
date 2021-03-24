package e3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GestionAgenda {

	private String nomFich;

	public GestionAgenda(String nomFich) {
		this.nomFich = nomFich;
	}
	
	public void ver() throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(nomFich));
		String linea=br.readLine();
		while (linea!=null) {
			System.out.println(linea);
			linea=br.readLine();
		}
		br.close();
	}
	
	public void aniadePersona(Persona persona) throws IOException {
		BufferedWriter bw=new BufferedWriter(new FileWriter(nomFich, true));
		bw.write(persona.getTelefono()+"\t"+persona.getEdad()+"\t"+persona.getNombre()+"\t"+persona.getLugar());
		bw.newLine();
		bw.close();
	}
	
	public boolean nombreEnLinea(String linea, String nombre) {
		String[] persona=linea.split("\t");
		if (persona[2].equals(nombre)) {
			return true;
		}
		return false;
	}
	
	public boolean telefonoEnLinea(String linea, String nombre) {
		String[] persona=linea.split("\t");
		if (persona[1].equals(nombre)) {
			return true;
		}
		return false;
	}
	
	public Persona transforma(String linea) {
		String[] persona=linea.split("\t");
		Persona p=new Persona(persona[2], persona[0], persona[3], Integer.parseInt(persona[1]));
		return p;
	}
	
	public Persona buscarPersona(String nombre) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(nomFich));
		String linea=br.readLine();
		while (linea!=null) {
			if (nombreEnLinea(linea, nombre)) {
				br.close();
				return transforma(linea);
			}
			linea=br.readLine();
		}
		br.close();
		return null;
	}
	
	public Persona buscarTelefono(String nombre) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(nomFich));
		String linea=br.readLine();
		while (linea!=null) {
			if (telefonoEnLinea(linea, nombre)) {
				br.close();
				return transforma(linea);
			}
			linea=br.readLine();
		}
		br.close();
		return null;
	}
	
	public void aniadePersonaChecked(Persona persona) throws IOException {
		if (buscarPersona(persona.getNombre())==null && buscarTelefono(persona.getTelefono())==null) {
			aniadePersona(persona);
		}
	}
	
	public void eliminaPersona(Persona persona) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(nomFich));
		ArrayList<String> p=new ArrayList<String>();
		String linea=br.readLine();
		while (linea!=null) {
			if (!nombreEnLinea(linea, persona.getNombre())) {
				p.add(linea);
			}
			linea=br.readLine();
		}
		br.close();
		BufferedWriter bw=new BufferedWriter(new FileWriter(nomFich));
		Iterator<String> it=p.iterator();
		while (it.hasNext()) {
			bw.write(it.next());
			bw.newLine();
		}
		bw.close();
	}
	
	public int edadMasFrecuente() throws IOException {
		int edad=0, max=0;
		HashMap<Integer, Integer> cant=new HashMap<Integer, Integer>();
		BufferedReader br=new BufferedReader(new FileReader(nomFich));
		String linea=br.readLine();
		int cantidad;
		while (linea!=null) {
			String[] persona=linea.split("\t");
			int edad1=Integer.parseInt(persona[1]);
			if (cant.containsKey(edad1)) {
				cantidad=cant.get(edad1);
				cantidad++;
				cant.put(edad1, cantidad);
				if (cantidad>max) {
					max=cantidad;
					edad=edad1;
				}
			} else {
				cant.put(edad1, 1);
			}
			linea=br.readLine();
		}
		br.close();
		return edad;
	}
	
	public static void main(String[] args) throws IOException {
		GestionAgenda g=new GestionAgenda("personas.txt");
		g.ver();
		Persona p=new Persona("Javi", "661685247", "Madrid", 40);
		System.out.println();
		g.aniadePersona(p);
		g.aniadePersona(p);
		g.ver();
		System.out.println();
		System.out.println(g.buscarPersona("Javi"));
		System.out.println();
		g.aniadePersonaChecked(p);
		g.eliminaPersona(p);
		g.ver();
		System.out.println();
		System.out.println(g.edadMasFrecuente());
		
	}
}
