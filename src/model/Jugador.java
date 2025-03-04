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

			for (Dados d : dadosATirarJugador) {
				System.out.print(d.getNumeroDado() + ".- ");
				// Cada vez que tiro los dados su estado de cambiado pasa a falso.
				d.setCambiado(false);
				d.tirarDado();
				dadosFinalesJugador.add(d);
			}
			// Una vez hemos tirado los dados los asignamos a la fase "Final"
			// Aunque poner dadosATirarjugador parece lo mas evidente si no ponemos el new
			// ArrayList... las dos listan apuntarian al mismo lugar haciendo que una sea
			// igual a la otra para evitar esto debemos de crear un nuevo ArrayList

			// Una vez asignados los dados "Finales" Limpiamos la lista de dados a tirar por
			// si el jugador quiere volver a tirar algunos dados
			dadosATirarJugador.clear();

			numLanzamientos--;
		} else {
			System.out.println("No te quedan lanzamientos, campeón");
		}

	}

	public void opcionesJugador() {
		boolean check = false;

		while (!check) {
			int index = 0;
			System.out.println("Te quedan: " + numLanzamientos + " lanzamientos.");
			do {

				System.out.println("1.- Confirmar 2.- Cambiar Dados 3.- Tirar Dados Seleccionados"
						+ " 4.- Tirar todos los dados 5.- Mostrar Dados");
				index = sc.nextInt();
				sc.nextLine();

			} while (index < 1 || index > 5);

			switch (index) {
			case 1:
				System.out.println("Has confirmado los siguientes dados: ");
				printDadosFinales();
				check = true;
				break;

			case 2:
				selecDadosACambiar();
				break;

			case 3:
				if (!dadosATirarJugador.isEmpty()) {
					tirarDados();
				} else {
					System.out.println("Me da a mí que no has seleccionado ningún dado, tontito.");
				}

				break;

			case 4:
				setDadosATirarJugador(new ArrayList<Dados>(dadosFinalesJugador));
				tirarDados();
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
				index = sc.nextInt();

			} while (index < 1 || index > 6);

			if (index == 6) {
				System.out.println("Sales de la opción cambiar dados");
				check = true;
			} else {
				// Referencia al objeto de la lista
				Dados d = dadosFinalesJugador.get(index - 1);

				// Si el dado seleccionado no ha sido metido al grupo de cambiar...
				if (!d.getCambiado()) {
					System.out.println("Añades el dado " + index + " para cambiar, esta acción no se puede deshacer");

					d.setCambiado(true);
					dadosATirarJugador.add(d);
					dadosFinalesJugador.remove(d);
				} else {
					System.out.println("Este dado ya ha sido seleccionado para cambiar.");
				}
			}

		}

	}

	// MÉTODOS DE IMPRESIÓN

	public void printDadosFinales() {
		// Ordena los dados de menor a mayor en función de su atributo número.
		// d -> d.getNumeroDado()) esta parte del código se llama expresión lambda, se
		// encarga de tomar un objeto referenciado como d y accedemos al atributo número
		// del dado. Ira iterando por cada objeto y a partir de esta condición lo ordena.
		dadosFinalesJugador.sort(Comparator.comparingInt(d -> d.getNumeroDado()));

		for (Dados d : dadosFinalesJugador) {

			System.out.println("Dado " + d.getNumeroDado() + ".- " + d.getValorDado());
		}
	}

}
