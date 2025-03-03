package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Jugador {

	private String nombre;
	private int numLanzamientos;
	private ArrayList<Dados> dadosFinalesJugador = new ArrayList<Dados>();
	private ArrayList<Dados> dadosACambiar = new ArrayList<Dados>();
	private ArrayList<Dados> dadosATirarJugador = new ArrayList<Dados>();
	private final int NUMERO_DADOS_JUGADOR = 5;
	private final int MAXIMO_NUMERO_LANZAMIENTOS = 3;

	private Scanner sc = new Scanner(System.in);

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

	// MÉTODOS VARIOS

	public void tirarDados() {

		int contador = 1;
		for (Dados d : dadosATirarJugador) {
			System.out.print(contador + ".- ");
			d.tirarDado();
			contador++;
		}
		// Una vez hemos tirado los dados los asignamos a la fase "Final"
		setDadosFinalesJugador(dadosATirarJugador);
		numLanzamientos--;

		// Una vez asignados los dados "Finales" Limpiamos la lista de dados a tirar por
		// si el jugador quiere volver a tirar algunos dados
		dadosATirarJugador.clear();

	}

	public void crearDados() {
		for (int i = 0; i < NUMERO_DADOS_JUGADOR; i++) {
			dadosATirarJugador.add(new Dados());
		}
	}

	// Aquí hacer la logica en la que se pregunta que dados quiere cambiar.
	public void cambioDados() {
		if (numLanzamientos > 0) {
			int index = 0;
			switch (index) {
			case 1:
				break;

			case 2:
				break;

			default:
				System.out.println("Error");
			}
		} else {
			System.out.println("No te quedan Lanzamientos");
		}
	}

	public int preguntarCambio() {
		int index = 0;
		do {
			System.out.println("¿Quieres volver a tirar algún dado?");
			System.out.println("1.- Sí 2.- No");
			index = sc.nextInt();
		} while (index < 1 || index > 2);

		return index;

	}

	public void selecDadosACambiar() {
		Dados d;
		int index = 0;
		boolean check = false;
		do {
			System.out.println("Selecciona qué dado deseas cambiar");
			index = sc.nextInt();
			// Asignamos qué dado es el que vamos a cambiar
			d = dadosFinalesJugador.get(index);
			dadosACambiar.add(d);

		} while (!check);

	}

}
