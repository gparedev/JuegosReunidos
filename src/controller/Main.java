package controller;

import model.Dados;
import model.Jugador;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dados dado1 = new Dados();
		System.out.println(dado1.getValorDado());
		dado1.tirarDado();

		Jugador jugador1 = new Jugador("Paco");
		jugador1.tirarDados();
	}

}
