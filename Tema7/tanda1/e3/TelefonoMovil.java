package e3;

import java.io.Serializable;

public class TelefonoMovil implements Serializable{

	private int saldo;
	private String numero;

	public TelefonoMovil(String numero, int saldo) {
		super();
		this.numero = numero;
		this.saldo = saldo;
	}
	
	public void ver() {
		System.out.println("Numero: "+numero+", saldo: "+saldo);
	}
	
	public void cargar(int s) {
		saldo+=s;
	}
	
	public void llamar(int minutos) {
		saldo-=minutos*2;
		if (saldo<=0) {
			saldo=0;
		}
	}

	public int getSaldo() {
		return saldo;
	}
	
	
}
