package e2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Descendente {

	private float[] n;
	private int total;

	public Descendente(int tamanio) {
		n=new float[tamanio];
		total=0;
	}
	
	public boolean aniadeNum(int num) {
		if (total==n.length) {
			return false;
		}
		for (int i = 0; i <= total ; i++) {
			if (n[i]<num) {
				for (int j = total; j > i; j--) {
					n[j]=n[j-1];
				}
				n[i]=(float) num;
				total++;
				return true;
			}
		}
		return false;
		
	}
	
	public void ver() {
		for (int i = 0; i < total && n[i]!=0; i++) {
			System.out.print(n[i]+" | ");
		}
		System.out.println();
	}
	
	public void borrarDuplicados() {
		boolean borrado=false;
		for (int i = 0; i < total && borrado==false; i++) {
			for (int j = i+1; j < total; j++) {
				if (n[i]==n[j]) {
					for (int j2 = j; j2 < total-1; j2++) {
						n[j2]=n[j2+1];
						borrado=true;
					}
					n[total-1]=(float) 0;
					total--;
				}
			}
		}
	}
	
	public void aniadeNumsArray(String nomFich) throws IOException {
		DataOutputStream dos=new DataOutputStream(new FileOutputStream(nomFich));
		for (int i = 0; i < total; i++) {
			dos.writeFloat(n[i]);
		}
		dos.close();
	}
	
	public void aniadeNumsRandom(int limInf, int limSup, int cant, String nomFich) throws IOException {
		DataOutputStream dos=new DataOutputStream(new FileOutputStream(nomFich));
		DataInputStream dis=new DataInputStream(new FileInputStream(nomFich));
		n=new float[dis.available()+cant];
		while (dis.available()>0) {
			aniadeNum(dis.read());
		}	
		for (int i = 0; i < total; i++) {
			int num=(int) (limInf+Math.random()*(limInf+limSup+1));
			aniadeNum(num);
			aniadeNumsArray(nomFich);
		}
		borrarDuplicados();
		aniadeNumsArray(nomFich);
		dos.close();
		dis.close();
	}
	
	public boolean buscarEnFichero(String nomFich, int p) throws IOException {
		DataInputStream dis=new DataInputStream(new FileInputStream(nomFich));
		
		if (p>dis.available()/Float.BYTES) {
			return false;
		}
		for (int i = 0; i < p-1; i++) {
			dis.readFloat();
		}
		System.out.println(dis.readFloat());
		dis.close();
		return true;
	}
	
	public void verFichero(String nomFich) throws IOException {
		DataInputStream dis=new DataInputStream(new FileInputStream(nomFich));
		Float n=dis.readFloat();
		while (dis.available()>0) {
			System.out.print(n+" | ");
			n=dis.readFloat();
		}
		
		dis.close();
	}
	
	public static void main(String[] args) throws IOException {
		Descendente d1=new Descendente(40);
		d1.aniadeNum(3);
		d1.aniadeNum(7);
		d1.aniadeNum(5);
		d1.aniadeNum(4);
		d1.aniadeNum(4);
		d1.ver();
		d1.borrarDuplicados();
		d1.ver();
		d1.aniadeNumsArray("num1.bin");
		d1.aniadeNumsArray("num2.bin");
		d1.aniadeNumsRandom(10, 20, 20, "num2.bin");
		System.out.println(d1.buscarEnFichero("num2.bin", 20));
		d1.buscarEnFichero("num2.bin", 5);
		d1.verFichero("num2.bin");
		
	}
	
}
