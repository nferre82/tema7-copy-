package e2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
		n[total]=Float.MAX_VALUE;
		for (int i = 0; i <= total; i++) {
			if (num<n[i]) {
				for (int j = total; j > i; j--) {
					n[j]=n[j-1];
				}
				n[i]=num;
				total++;
				return true;
			}
		}
		return false;
		
	}
	
	public void ver() {
		for (int i = 0; i < total; i++) {
			System.out.print(n[i]+" | ");
		}
		System.out.println();
	}
	
	public void borrarDuplicados() {
		for (int i = 0; i < total-1; i++) {
			if (n[i]==n[i+1]) {
				n[i+1]=Float.MAX_VALUE;
				total--;
				for (int j = i+1; j < total; j++) {
					n[j]=n[j+1];
				}
				i--;
			}
		}
	}
	
	public void aniadeNumsArray(String nomFich) throws IOException {
		borrarDuplicados();
		DataOutputStream dos=new DataOutputStream(new FileOutputStream(nomFich,true));
		for (int i = 0; i < total; i++) {
			dos.writeFloat(n[i]);
		}
		dos.close();
	}
	
	public void aniadeNumsRandom(int limInf, int limSup, int cant, String nomFich) throws IOException {
		DataInputStream dis=new DataInputStream(new FileInputStream(nomFich));
		float num;
		float numero;
		float[] numeros=new float[cant];
		boolean repe;
		for (int i = 0; i < cant && dis.available()>0;) {
			repe=false;
			num=(float) (limInf+Math.random()*(limSup-limInf));
			while (dis.available()>0 && repe==false) {
				numero=(Float)dis.readFloat();
				if (numero==num) {
					repe=true;
				}
			}
		}
		dis.close();
		DataOutputStream dos=new DataOutputStream(new FileOutputStream(nomFich, true));
		for (int i = 0; i < cant; i++) {
			dos.writeFloat(numeros[i]);
		}
		dos.close();
		/*
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
		*/
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
		System.out.println();
		while (dis.available()>0) {
			System.out.print(n+" | ");
			n=dis.readFloat();
		}
		System.out.print(n+" | ");
		dis.close();
	}
	
	public void copiarFichero(String nomFichOr, String nomFichDes) throws IOException {
		DataInputStream disDes=new DataInputStream(new FileInputStream(nomFichDes));
		char resp='s';
		if (disDes.available()>0) {
			System.out.println("El fichero ya existe, desea sobreescribir(s), a�adir(a) o no hacer nada(n)?");
			resp=Consola.leeChar();
		}
		DataInputStream dis=new DataInputStream(new FileInputStream(nomFichOr));
		switch (resp) {
			case 's':
				n=new float[dis.available()/Float.BYTES];
				total=0;
				while (dis.available()>0) {
					int num=(int) Math.round(dis.readFloat());
					if (num%2==1) {
						aniadeNum(num);
						borrarDuplicados();
					}
				}
				aniadeNumsArray(nomFichDes);
			break;
				
			case 'a':
				n=new float[disDes.available()/Float.BYTES+dis.available()/Float.BYTES];
				total=0;		
				int num;
				disDes=new DataInputStream(new FileInputStream(nomFichDes));
				while (disDes.available()/Float.BYTES>0) {
					num=(int) disDes.readFloat();
					aniadeNum(num);
					borrarDuplicados();
				}
				disDes.close();
				while (dis.available()>0) {
					num=(int) dis.readFloat();
					if (num%2==1) {
						aniadeNum(num);
						borrarDuplicados();
					}
				}
				aniadeNumsArray(nomFichDes);
				dis.close();
			break;
		}
		
		disDes.close();
		dis.close();
		
	}
	
	public static void main(String[] args) throws IOException {
		Descendente d1=new Descendente(40);
		d1.aniadeNum((float)3.444);
		d1.aniadeNum(-7);
		d1.aniadeNum(5);
		d1.aniadeNum(-4);
		d1.aniadeNum(4);
		d1.aniadeNum(4);
		d1.aniadeNum(4);
		d1.aniadeNum(4);
		d1.aniadeNum(5);
		d1.aniadeNum(5);
		d1.aniadeNum(0);
		d1.ver();
		d1.borrarDuplicados();
		d1.ver();
		d1.aniadeNumsArray("num1.bin");
		d1.verFichero("num1.bin");
		d1.aniadeNumsArray("num2.bin");
		d1.aniadeNumsRandom(10, 20, 30, "num2.bin");
		d1.verFichero("num2.bin");
		System.out.println();
		System.out.println(d1.buscarEnFichero("num2.bin", 2));
		d1.buscarEnFichero("num2.bin", 5);
		System.out.println();
		d1.buscarEnFichero("num2.bin", 20);
		System.out.println();
		System.out.println();
		d1.verFichero("num1.bin");
		System.out.println();
		d1.verFichero("num2.bin");
		System.out.println();
		System.out.println();
		d1.copiarFichero("num2.bin", "num1.bin");
		d1.verFichero("num1.bin");
	}
	
}
