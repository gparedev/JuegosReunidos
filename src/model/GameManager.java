package model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;

public class GameManager {

	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	private ArrayList<Jugador> ordenDeJuego = new ArrayList<Jugador>();

	public void mostrarMenu() {
		System.out.println("Bienvenido a Yahtzee\n");
		// menu de inicio
		Scanner sc = new Scanner(System.in);
		int numeroMenu = 0;
		do {
			System.out.println("MENÚ DE OPCIONES:\n1.- Comenzar juego | 2.- Reglas | 3.- Salir del juego");
			numeroMenu = sc.nextInt();
		} while (numeroMenu > 3 || numeroMenu < 1);
		switch (numeroMenu) {
		case 1:
			juego();
			System.out.println("¡Empieza el juego!");
			break;
		case 2:
			reglas();
			pulsarEnter();
			mostrarMenu();
			break;
		case 3:
			System.out.println("Otra vez será");
			terminarJuego();
			break;

		default:
			break;
		}
	}

	public void crearJugadores(ArrayList<Jugador> jugadores) {
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
			System.out.println("Introduce el nombre del jugador " + (i + 1) + "/" + numeroDeJugadores + ":");
			jugadores.add(new Jugador(sc.nextLine()));
		}
		System.out.println("Los jugadores serán:");
		for (int i = 0; i < jugadores.size(); i++) {
			System.out.println("#" + (i + 1) + " " + jugadores.get(i).getNombre());
		}
		empiezaJugador(ordenDeJuego);
	}

	public void empiezaJugador(ArrayList<Jugador> ordenDeJuego) {
		// número al azar que define la posición de el array jugadores que empezará
		int empiezaJugadorNum = (int) (Math.random() * jugadores.size()); // número aleatorio de 0 a 4
		System.out.println(
				"\nEmpezará el jugador número " + (empiezaJugadorNum + 1) + " por lo tanto el orden del juego será:");
		if (empiezaJugadorNum == 0) {
			// añade jugadores de la forma normal pero para el juego usaremos el arraylist
			for (int i = 0; i < jugadores.size(); i++) {
				ordenDeJuego.add(jugadores.get(i));
			}
			// empieza a añadir desde la posición(numero aleatorio) del array
		} else if (empiezaJugadorNum == 1 || empiezaJugadorNum == 2 || empiezaJugadorNum == 3
				|| empiezaJugadorNum == 4) {
			for (int i = empiezaJugadorNum; i < jugadores.size(); i++) {
				ordenDeJuego.add(jugadores.get(i));
			}
			// añade desde 0 hasta el jugador que empieza para añadirlo en el nuevo orden
			for (int i = 0; i < empiezaJugadorNum; i++) {
				ordenDeJuego.add(jugadores.get(i));
			}
		}
		// imprimir nuevo orden de juego
		for (int i = 0; i < ordenDeJuego.size(); i++) {
			System.out.println("#" + (i + 1) + " " + ordenDeJuego.get(i).getNombre());
		}

	}

	public void mostrarJugadores() {
		for (Jugador jugador : jugadores) {
			jugador.getNombre();
		}
	}

	public void pulsarEnter() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nPulsa ENTER para continuar");
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

	public boolean terminarJuego() {
		boolean terminar = true;
		return terminar;
	}

	public void juego() {
		Scanner sc = new Scanner(System.in);

		boolean estadoJuego = terminarJuego(); // permite acabar el juego
		int turno = 0; // jugador que juega (turno)
		boolean jugada = true; // jugada nº x del jugador
		int turnoGlobal = 0;

		int jugarOtraPartida;

		crearJugadores(jugadores);
		while (estadoJuego == true) {// juego
			while (turno < ordenDeJuego.size()) {
				System.out.println("-------------------------------------");
				System.out.println("\nTURNO DE " + ordenDeJuego.get(turno).getNombre() + ":");
				jugada = true;
				while (jugada) {
					ordenDeJuego.get(turno).jugar(); // en jugar están los 3 turnos
					jugada = false;
				}
				turno++;
			}
			turno = 0; // vuelve al turno del primer jugador
			// estadoJuego = false; // con esto solo hay una ronda
			turnoGlobal++;
			if (turnoGlobal == 1) { // CAMBIAR A 13 PARA JUEGO COMPLETO
				estadoJuego = false;
			}
		}

		if (!estadoJuego) { // cuando acaba una partida, para empezar un juego nuevo con nuevos jugadores
			mostrarClasificacionOrdenada();
			jugadores.clear();
			ordenDeJuego.clear();
			System.out.println("\nEL JUEGO HA TERMINADO");
		}
		do {
			System.out.println("\n\n¿Deseas jugar otra partida? \n1.- Si | 2.- No");
			jugarOtraPartida = sc.nextInt();
		} while (jugarOtraPartida > 2 || jugarOtraPartida < 1);

		if (jugarOtraPartida == 1) {
			mostrarMenu(); // reinicio del juego
		} else {
			System.out.println("Otra vez será");
		}

	}

	// clasificación
	public void mostrarClasificacionOrdenada() {
		for (int i = 0; i < ordenDeJuego.size(); i++) {
			System.out.println("\nEl jugador " + ordenDeJuego.get(i).getNombre() + " ha puntuado: "
					+ ordenDeJuego.get(i).puntuacionesJugador.getSumaPuntuacionFinal() + " puntos.");
		}

		System.out.println("\nClasificación final:");
		// ordenDeJuego.sort(Comparator.comparingInt(jugador ->
		// jugador.puntuacionesJugador.getSumaPuntuacionFinal()));
		ordenDeJuego.sort(Comparator.comparing(jugador -> jugador.puntuacionesJugador.getSumaPuntuacionFinal(),
				Comparator.reverseOrder()));

		int posicion = 1;
		for (Jugador jugador : ordenDeJuego) {
			System.out.println(posicion + "ª posición " + jugador.getNombre());
			posicion++;
		}

	}

}
