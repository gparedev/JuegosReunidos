package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Jugador {

	private String nombre;
	private int numLanzamientos;
	private ArrayList<Dados> dadosFinalesJugador = new ArrayList<Dados>();
	private ArrayList<Dados> dadosATirarJugador = new ArrayList<Dados>();
	private final int NUMERO_DADOS_JUGADOR = 5;
	private final int MAXIMO_NUMERO_LANZAMIENTOS = 3;
	Puntuaciones puntuacionesJugador = new Puntuaciones();

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

	public int getNumLanzamientos() {
		return numLanzamientos;
	}

	public void setNumLanzamientos(int numLanzamientos) {
		this.numLanzamientos = numLanzamientos;
	}

	// MÉTODOS VARIOS

	// Creación de Objetos Dados
	private void crearDados() {
		for (int i = 0; i < NUMERO_DADOS_JUGADOR; i++) {
			dadosATirarJugador.add(new Dados());
			dadosATirarJugador.get(i).setNumeroDado(i + 1);
		}
	}

	public void tirarDados() {

		if (numLanzamientos > 0) {

			System.out.println("Tirando dados...");
			for (Dados d : dadosATirarJugador) {
				System.out.print(d.getNumeroDado() + ".- ");
				// Cada vez que tiro los dados su estado de cambiado pasa a falso.
				d.setCambiado(false);
				d.tirarDado();
				dadosFinalesJugador.add(d);
			}

			// Una vez añadidos los dados "Finales" Limpiamos la lista de dados a tirar por
			// si el jugador quiere volver a tirar algunos dados
			dadosATirarJugador.clear();

			numLanzamientos--;
			puntuacionesJugador.setDadosFinalesJugador(dadosFinalesJugador);
			puntuacionesJugador.imprimirPuntuacion();

		} else {
			System.out.println("No te quedan lanzamientos, campeón");
		}

	}

	public void opcionesJugador() {
		boolean check = false;

		while (!check) {
			int index = 0;
			do {

				System.out.println("\n1.- Confirmar Dados | 2.- Cambiar Dados | 3.- Tirar Dados Seleccionados |"
						+ " 4.- Tirar todos los dados | 5.- Mostrar Dados");
				index = sc.nextInt();
				sc.nextLine();

			} while (index < 1 || index > 5);

			switch (index) {
			case 1:
				if (dadosFinalesJugador.size() != NUMERO_DADOS_JUGADOR) {
					System.out.println(
							"No puedes confirmar, tira los dados seleccionados para cambiar antes de confirmar");
					printDadosFinales();
				} else {
					System.out.println("Has confirmado los siguientes dados: ");
					numLanzamientos = MAXIMO_NUMERO_LANZAMIENTOS;
					printDadosFinales();
					puntuacionesJugador.setDadosFinalesJugador(dadosFinalesJugador);
					puntuacionesJugador.imprimirPuntuacion();
					puntuacionesJugador.setPuntuacion();
					check = true;
				}
				break;

			case 2:
				if (numLanzamientos > 0) {
					selecDadosACambiar();
				} else {
					System.out.println("Sin lanzamientos no puedes cambiar dados, crack.");
				}

				break;

			case 3:
				if (!dadosATirarJugador.isEmpty()) {
					tirarDados();
				} else {
					System.out.println("Me da a mí que no has seleccionado ningún dado, tontito.");
				}

				break;

			case 4:
				if (numLanzamientos > 0) {
					dadosATirarJugador.clear();
					dadosFinalesJugador.clear();
					crearDados();
					tirarDados();
				} else {
					System.out.println("No te quedan lanzamientos, crack.");
				}

				break;

			case 5:
				printDadosFinales();
				break;

			default:
				System.out.println("Error");

			}
		}

	}

	public void selecDadosACambiar() {
		int index = 0;
		boolean check = false;

		while (!check) {

			do {

				System.out.println("Selecciona qué dado deseas cambiar o pulsa 6 para confirmar la selección");
				printDadosCambiado();
				index = sc.nextInt();

			} while (index < 1 || index > 6);

			if (index == 6) {
				System.out.println("Sales de la opción cambiar dados");

				// Borro aquellos dados que tengan el atributo cambiado a True
				// Dados::getCambiado es una referencia al meotdo getCambiado de la clase Dados
				dadosFinalesJugador.removeIf(Dados::getCambiado);

				check = true;
			} else {
				// Referencia al objeto de la lista
				Dados d = dadosFinalesJugador.get(index - 1);

				// Si el dado seleccionado no ha sido metido al grupo de cambiar...
				if (!d.getCambiado()) {
					System.out.println("Añades el dado " + index + " para cambiar, esta acción no se puede deshacer");

					d.setCambiado(true);
					dadosATirarJugador.add(d);

				} else {
					System.out.println("Este dado ya ha sido seleccionado para cambiar.");
				}
			}

		}
	}

	public void jugar() {
		tirarDados();
		opcionesJugador();
	}

	// MÉTODOS DE IMPRESIÓN

	// Imprime los dados finales (los que van a puntuar) además de comprobar si hay
	// dados en la lista de tirar, si los hay tambien los imprime
	public void printDadosFinales() {
		// Ordena los dados de menor a mayor en función de su atributo número.
		// d -> d.getNumeroDado()) esta parte del código se llama expresión lambda, se
		// encarga de tomar un objeto referenciado como d y accedemos al atributo número
		// del dado. Ira iterando por cada objeto y a partir de esta condición lo
		// ordena.
		dadosFinalesJugador.sort(Comparator.comparingInt(d -> d.getNumeroDado()));
		System.out.println("Dados a puntuar: ");
		for (Dados d : dadosFinalesJugador) {

			System.out.println("Dado " + d.getNumeroDado() + ".- " + d.getValorDado());
		}

		if (!dadosATirarJugador.isEmpty()) {
			System.out.println("Dados a cambiar: ");

			for (Dados d : dadosATirarJugador) {
				System.out.println("Dado " + d.getNumeroDado() + ".- " + d.getValorDado());
			}
		}
	}

	// Esta función se usa en el menu de selecDadosACambiar para dar feedback al
	// usuario de qué dados son cambiados
	public void printDadosCambiado() {

		dadosFinalesJugador.sort(Comparator.comparingInt(d -> d.getNumeroDado()));

		for (Dados d : dadosFinalesJugador) {

			if (!d.getCambiado()) {
				System.out.println("Dado " + d.getNumeroDado() + ".- " + d.getValorDado());
			} else {
				System.out.println("Dado " + d.getNumeroDado() + ".- " + d.getValorDado() + " | CAMBIADO");
			}
		}
		System.out.println("6.- CONFIRMAR");
	}

}
