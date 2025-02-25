package model;

import java.util.ArrayList;

public class Jugador {

	private String nombre;
	private int numLanzamientos;
	private ArrayList<Dados> dadosFinalesJugador = new ArrayList<Dados>();
	private ArrayList<Dados> dadosATirarJugador = new ArrayList<Dados>();
	private ArrayList<Dados> dadosToChangeJugador = new ArrayList<Dados>();
	private final int NUMERO_DADOS_JUGADOR = 5;
	private final int MAXIMO_NUMERO_LANZAMIENTOS = 3;
	
	public Jugador(String nombre) {
		this.nombre = nombre;
		numLanzamientos = MAXIMO_NUMERO_LANZAMIENTOS;

		// Cuando llamamos al constructor se ejecuta todo el código.
		// En este caso cuando creamos al jugador ademas llamamos a la función
		// Donde se instancian 5 dados de valor 1 por defecto.
		crearDados();

	}

	// SETTERS AND GETTERS

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Dados> getDadosFinalesJugador() {
		return dadosFinalesJugador;
	}

	public void setDadosFinalesJugador(ArrayList<Dados> dadosFinalesJugador) {
		this.dadosFinalesJugador = dadosFinalesJugador;
	}

	public ArrayList<Dados> getDadosATirarJugador() {
		return dadosATirarJugador;
	}

	public void setDadosATirarJugador(ArrayList<Dados> dadosATirarJugador) {
		this.dadosATirarJugador = dadosATirarJugador;
	}

	public ArrayList<Dados> getDadosToChangeJugador() {
		return dadosToChangeJugador;
	}

	public void setDadosToChangeJugador(ArrayList<Dados> dadosToChangeJugador) {
		this.dadosToChangeJugador = dadosToChangeJugador;
	}

	// MÉTODOS VARIOS

	public void tirarDados() {

		int contador = 1;
		for (Dados d : dadosATirarJugador) {
			System.out.print(contador + ".- ");
			d.tirarDado();
			contador++;
		}
		// Una bez hemos tirado los dados los asignamos a la fase "Final"
		setDadosFinalesJugador(dadosATirarJugador);
		numLanzamientos--;

	}

	public void crearDados() {
		for (int i = 0; i < NUMERO_DADOS_JUGADOR; i++) {
			dadosATirarJugador.add(new Dados());
		}
	}
	// Aquí hacer la logica en la que se pregunta que dados quiere cambiar, hasta un máximo de 3.
	public void preguntarDadosACambiar() {
		boolean check = false;
		while (!check) {
			
		}
	}

}
