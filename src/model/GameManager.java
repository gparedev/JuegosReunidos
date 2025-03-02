package model;

import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {

	private static ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	private int numeroDeJugadores;

	public GameManager(ArrayList<Jugador> jugadores, int numeroDeJugadores) {
		this.jugadores = new ArrayList<>();
		this.numeroDeJugadores = numeroDeJugadores;
	}

	public GameManager() {

	}

	public static void bienvenida() {
		System.out.println("Bienvnido a Yahtzee\n");
		mostrarMenu();
	}

	public static void mostrarMenu() {
		Scanner sc = new Scanner(System.in);
		int numeroMenu = 0;
		do {
			System.out.println("MENÚ DE OPCIONES:\n1.- Comenzar juego | 2.- Reglas | 3.- Salir del juego");
			numeroMenu = sc.nextInt();
		} while (numeroMenu > 3 || numeroMenu < 1);
		switch (numeroMenu) {
		case 1:
			crearJugadores(jugadores);
			System.out.println("¡Empieza el juego!");
			break;
		case 2:
			reglas();
			pulsarEnter();
			mostrarMenu();
			break;
		case 3:
			terminarJuego();
			break;

		default:
			break;
		}
	}

	public static void crearJugadores(ArrayList<Jugador> jugadores) {
		Scanner sc = new Scanner(System.in);
		int numeroDeJugadores = 0;
		String nombre = "";
		do {
			System.out.println("Número de jugadores que van a jugar (De 2 a 5 jugadores):");
			numeroDeJugadores = sc.nextInt();
			sc.nextLine();
		} while (numeroDeJugadores > 5 || numeroDeJugadores < 2);
		System.out.println("Jugaréis " + numeroDeJugadores + " jugadores.");
		for (int i = 0; i < numeroDeJugadores; i++) {
			System.out.println("Introduce el nombre del jugador " + (i+1) + "/" + numeroDeJugadores + ":");
			jugadores.add(new Jugador(sc.nextLine()));
		}
		System.out.println("Los jugadores serán:");
		for (int i = 0; i < jugadores.size(); i++) {
			System.out.println("#" + (i+1) + " " + jugadores.get(i).getNombre());
		}
	}

	public void mostrarJugadores() {
		for (Jugador jugador : jugadores) {
			jugador.getNombre();
		}
	}

	public static void pulsarEnter() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Pulsa ENTER para continuar");
		sc.nextLine();
	}

	public static void reglas() {
		System.out.println("REGLAS DEL YAHTZEE:\r\n"
				+ "Objetivo: Conseguir la mayor cantidad de puntos lanzando cinco dados y logrando combinaciones específicas.\r\n"
				+ "\r\n" + "Turno de juego:\r\n" + "\r\n"
				+ "Cada jugador puede lanzar los dados hasta tres veces por turno.\r\n"
				+ "Puede reservar algunos dados y relanzar los demás para intentar mejorar su combinación.\r\n"
				+ "Sección superior (sumar dados de un mismo número):\r\n" + "\r\n"
				+ "Se puntúa sumando los dados del número elegido (1, 2, 3, 4, 5 o 6).\r\n"
				+ "Si la suma total es 63 o más, se recibe un bono de 35 puntos.\r\n"
				+ "Sección inferior (combinaciones especiales):\r\n" + "\r\n"
				+ "Trío: Tres dados iguales, suma de todos los dados.\r\n"
				+ "Cuatro iguales: Cuatro dados iguales, suma de todos los dados.\r\n"
				+ "Full House: Un trío + un par (25 puntos).\r\n"
				+ "Escalera pequeña: Cuatro números consecutivos (30 puntos).\r\n"
				+ "Escalera grande: Cinco números consecutivos (40 puntos).\r\n"
				+ "Chance: Cualquier combinación, suma total de los dados.\r\n"
				+ "Yahtzee: Cinco dados iguales (50 puntos).\r\n"
				+ "Gana quien tenga la mayor puntuación al final de la partida.");
	}

	public static boolean terminarJuego() {
		boolean terminar = true;
		return terminar;
	}

}
