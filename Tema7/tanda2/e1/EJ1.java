package e1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import e2.Consola;

public class EJ1 {

	public static void main(String[] args) throws IOException {

		int resp=0;
		DataOutputStream dos=new DataOutputStream(new FileOutputStream("nums.bin"));
		do {
			System.out.println("Introduce numeros");
			resp=Consola.leeInt();
			if (resp!=0) {
				dos.writeInt(resp);
			}
		} while (resp!=0);
		dos.close();
		
		DataInputStream dis=new DataInputStream(new FileInputStream("nums.bin"));
		BufferedWriter bw=new BufferedWriter(new FileWriter("nums.txt"));
		while (dis.available()>0) {
			int num=dis.readInt();
			bw.write(String.valueOf(num));
			bw.newLine();
		}
		bw.close();
		dis.close();
	}

}
