package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Jugador {

	private String nombre;
	private ArrayList<Dados> dadosFinalesJugador = new ArrayList<Dados>();
	private ArrayList<Dados> dadosATirarJugador = new ArrayList<Dados>();
	private ArrayList<Dados> dadosToChangeJugador = new ArrayList<Dados>();
	private final int NUMERO_DADOS_JUGADOR = 5;

	public Jugador(String nombre) {
		this.nombre = nombre;

		// Cuando llamamos al constructor se ejecuta todo el código.
		// En este caso cuando creamos al jugador ademas llamamos a la función
		// Donde se instancian 5 dados de valor 1 por defecto.
		crearDados();

	}

	// MÉTODOS VARIOS

	public void tirarDados() {

		int contador = 1;
		for (Dados d : dadosATirarJugador) {
			System.out.print(contador + ".- ");
			d.tirarDado();
			contador++;
		}

	}

	public void crearDados() {
		for (int i = 0; i < NUMERO_DADOS_JUGADOR; i++) {
			dadosATirarJugador.add(new Dados());
		}
	}

}
