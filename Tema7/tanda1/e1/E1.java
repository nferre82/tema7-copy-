package e1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class E1 {

	public static void copiarImagen(String nomFich) throws IOException {
		String[] nombre=nomFich.split("\\.");
		FileInputStream fis=new FileInputStream(nomFich);
		FileOutputStream fos=new FileOutputStream(nombre[0]+"_CPY."+nombre[1]);
		while (fis.available()>0) {
			fos.write(fis.read());
		}
		fis.close();
		fos.close();
	}
	
	public static void copiarBloques(String nomFich) throws IOException {
		final int N=512;
		byte arr[]=new byte[N];
		String[] nombre=nomFich.split("\\.");
		FileInputStream fis=new FileInputStream(nomFich);
		FileOutputStream fos=new FileOutputStream(nombre[0]+"_CPY2."+nombre[1]);
		while (fis.available()>0) {
			int numero=fis.read(arr);
			fos.write(arr,0,numero);
		}
		fis.close();
		fos.close();
	}
	
	public static void copiarDeUna(String nomFich) throws IOException {
		String[] nombre=nomFich.split("\\.");
		FileInputStream fis=new FileInputStream(nomFich);
		FileOutputStream fos=new FileOutputStream(nombre[0]+"_CPY3."+nombre[1]);
		byte arr[]=new byte[fis.available()];
		fis.read(arr);
		fos.write(arr);
		fis.close();
		fos.close();
	}
	
	public static void main(String[] args) throws IOException {
		copiarImagen("img/img.jpg");
		copiarBloques("img/img.jpg");
		copiarDeUna("img/img.jpg");
	}
	
}
