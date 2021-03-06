package e2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Descendente {

	private float[] n;
	private int total;

	public Descendente(int tamanio) {
		n=new float[tamanio];
		total=0;
	}
	
	public boolean aniadeNum(float num) {
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
		DataInputStream dis=new DataInputStream(new FileInputStream(nomFich));
		int nums=dis.available()/Float.BYTES;
		n=new float[nums];	
		total=0;
		Float num;
		while (dis.available()>0) {
			num=dis.readFloat();
			aniadeNum(num);
			borrarDuplicados();
		}
		float m1[]=n;
		dis.close();
		DataOutputStream dos=new DataOutputStream(new FileOutputStream(nomFich));
		total=0;
		n=new float[cant-nums];
		while(total<cant-nums) {
			num=(float) (limInf+Math.random()*(limSup-limInf));
			borrarDuplicados();
			aniadeNum(num);
		}
		float m2[]=n;
		n=new float[m1.length+m2.length];
		int cont=0;
		while (cont<m1.length) {
			n[cont]=m1[cont];
			cont++;
		}
		int cont2=0;
		while (cont<n.length) {
			n[cont]=m2[cont2];
			cont++;
			cont2++;
		}
		total=n.length;
		aniadeNumsArray(nomFich);
		dos.close();
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
		System.out.print(n+" | ");
		dis.close();
	}
	
	public static void main(String[] args) throws IOException {
		Descendente d1=new Descendente(40);
		d1.aniadeNum((float)3.444);
		d1.aniadeNum(7);
		d1.aniadeNum(5);
		d1.aniadeNum(4);
		d1.aniadeNum(4);
		d1.ver();
		d1.borrarDuplicados();
		d1.ver();
		d1.aniadeNumsArray("num1.bin");
		d1.verFichero("num1.bin");
		d1.aniadeNumsArray("num2.bin");
		d1.aniadeNumsRandom(10, 20, 15, "num2.bin");
		System.out.println();
		System.out.println(d1.buscarEnFichero("num2.bin", 2));
		d1.buscarEnFichero("num2.bin", 5);
		d1.verFichero("num2.bin");
		System.out.println();
		d1.buscarEnFichero("num2.bin", 20);
		
	}
	
}
