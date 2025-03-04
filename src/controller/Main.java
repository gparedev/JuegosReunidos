package controller;

import model.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
<<<<<<< Updated upstream
//		Dados dado1 = new Dados();
//		System.out.println(dado1.getValorDado());
//		dado1.tirarDado();
=======
		Dados dado1 = new Dados();
		System.out.println(dado1.getValorDado());
		dado1.tirarDado();
		
		
>>>>>>> Stashed changes

		Jugador jugador1 = new Jugador("Paco");
		
		jugador1.tirarDados();
		
<<<<<<< Updated upstream
		GameManager.bienvenida();
		jugador1.jugar();
=======
		
		Puntuaciones.numerosDados(jugador1.getDadosFinalesJugador());
		
>>>>>>> Stashed changes
	}

}
