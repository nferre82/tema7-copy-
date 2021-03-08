package e1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class E1 {

	public static void copiarImagen(String nomFich) throws IOException {
		FileInputStream fis=new FileInputStream(nomFich+".jpg");
		FileOutputStream fos=new FileOutputStream(nomFich+"_CPY.jpg");
		while (fis.available()>0) {
			fos.write(fis.read());
		}
		fis.close();
		fos.close();
	}
	
	public static void copiarBloques(String nomFich) throws IOException {
		final int N=512;
		byte arr[]=new byte[N];
		FileInputStream fis=new FileInputStream(nomFich+".jpg");
		FileOutputStream fos=new FileOutputStream(nomFich+"_CPY2.jpg");
		while (fis.available()>0) {
			fis.read(arr);
			fos.write(arr);
			if (fis.available()<N) {
				arr=new byte[fis.available()];
			}
		}
		fis.close();
		fos.close();
	}
	
	public static void copiarDeUna(String nomFich) throws IOException {
		FileInputStream fis=new FileInputStream(nomFich+".jpg");
		FileOutputStream fos=new FileOutputStream(nomFich+"_CPY3.jpg");
		byte arr[]=new byte[fis.available()];
		while (fis.available()>0) {
			fis.read(arr);
			fos.write(arr);
		}
		fis.close();
		fos.close();
	}
	
	public static void main(String[] args) throws IOException {
		copiarImagen("img/img");
		copiarBloques("img/img");
		copiarDeUna("img/img");
	}
	
}
