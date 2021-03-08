package e1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import e2.Consola;

public class EJ1 {

	public static void main(String[] args) throws IOException {

		int resp=0;
		FileWriter fw=new FileWriter("nums.bin");
		do {
			System.out.println("Introduce numeros");
			resp=Consola.leeInt();
			if (resp!=0) {
				fw.write(String.valueOf(resp));
			}
		} while (resp!=0);
		fw.close();
		
		FileReader fr=new FileReader("nums.bin");
		BufferedWriter bw=new BufferedWriter(new FileWriter("nums.txt"));
		int num=fr.read();
		while (num>0) {
			bw.write(num);
			bw.newLine();
			num=fr.read();
		}
		bw.close();
		fr.close();
	}

}
