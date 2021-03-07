package e3;

public class TelefonoMovil {

	private int numero, saldo;

	public TelefonoMovil(int numero, int saldo) {
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
	}
}
