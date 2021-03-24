package gestorConciertos;

import java.io.Serializable;

public class Concierto implements Serializable{

	private int aforo;
	private Artista artista;
	
	public Concierto(String nombre, char categoria, int aforo) {
		artista=new Artista(nombre, categoria);
		this.aforo = aforo;
	}

	public int getAforo() {
		return aforo;
	}

	@Override
	public String toString() {
		return "Concierto [aforo=" + aforo + ", artista= " + artista + "]";
	}
	
	
}
