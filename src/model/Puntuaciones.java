package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Puntuaciones {
	// Método que recibe el arrayList de los dados de una tirada
	public static void numerosDados(ArrayList<Dados> dados) {
		// Valores para almacenar cuantos valores de cada numero hay
		int unos = 0;
		int doses = 0;
		int treses = 0;
		int cuatros = 0;
		int cincos = 0;
		int seises = 0;
		// For each para recorrer el arrayList de los dados
		for (Dados dado : dados) {
			// Variable numero que almacena el valor del dado que se está analizando
			int numero = dado.getValorDado();
			// switch que dependiendo de que numero contenga el dado suma un valor
			switch (numero) {
			case 1:
				unos++;
				break;
			case 2:
				doses++;
				break;
			case 3:
				treses++;
				break;
			case 4:
				cuatros++;
				break;
			case 5:
				cincos++;
				break;
			case 6:
				seises++;
				break;
			}
		}
		// Llama a la función combinaciones enviando la cantidad de cada numero que hay
		// en la tirada
		combinaciones(unos, doses, treses, cuatros, cincos, seises);
	}

	// Función que analiza las combinaciones existentes
	static void combinaciones(int unos, int doses, int treses, int cuatros, int cincos, int seises) {
		// Variables que determinan la puntuación que tiene cada casilla
		int trio = 0;
		int cuatroIguales = 0;
		int fullHouse = 0;
		int escaleraPeque = 0;
		int escaleraGrande = 0;
		int chance = 0;
		int yahtzee = 0;
		// Valores booleanos para comprobar combinaciones
		boolean parBoolean = false;
		boolean trioBoolean = false;
		boolean boolUno = false;
		boolean boolDos = false;
		boolean boolTres = false;
		boolean boolCuatro = false;
		boolean boolCinco = false;
		boolean boolSeis = false;
		// Valores para combinaciones
		int parFullHouse = 0;
		int trioFullHouse = 0;
		// Arrays que definen números y cuantos dados hay con ese número
		// Se podría haber realizado con un array cuadrado
		int[] Array = { unos, doses, treses, cuatros, cincos, seises };
		int[] ArrayNumero = { 1, 2, 3, 4, 5, 6 };
		// Se llama a la función calculoSimple, para calcular cada uno de los 6 valores
		// de los dados
		int uno = calculoSimple(unos, 1);
		int dos = calculoSimple(doses, 2);
		int tres = calculoSimple(treses, 3);
		int cuatro = calculoSimple(cuatros, 4);
		int cinco = calculoSimple(cincos, 5);
		int seis = calculoSimple(seises, 6);
		// Se llama a la función calcChance para calcular el chance, que es la suma de todos los numeros de los dados
		chance = calcChance(uno, dos, tres, cuatro, cinco, seis);
		// for que valora combinaciones simples
		for (int i = 0; i < Array.length; i++) {
			if (Array [i] >= 1) {
				// switch que anota que número existen en los dados para usar en las escaleras
				switch (ArrayNumero[i]) {
				case 1:
					boolUno = true;
					break;
				case 2:
					boolDos = true;
					break;
				case 3:
					boolTres = true;
					break;
				case 4:
					boolCuatro = true;
					break;
				case 5:
					boolCinco = true;
					break;
				case 6:
					boolSeis = true;
					break;
				}
			}
			if (Array[i] == 2) {
				parBoolean = true;
				parFullHouse = ArrayNumero[i];
			}
			if (Array[i] >= 3) {
				trio = calculoSimple(3, ArrayNumero[i]);
				trioBoolean = true;
				trioFullHouse = ArrayNumero[i];
			}
			if (Array[i] >= 4) {
				cuatroIguales = calculoSimple(4, ArrayNumero[i]);
			}
			if (Array[i] == 5) {
				yahtzee = calcYahtzee(ArrayNumero[i]);
			}
		}
		if (trioBoolean && parBoolean) {
			fullHouse = calcFullHouse(parFullHouse, trioFullHouse);
		}
		// LLamada a las funciones que calcula las escaleras y su puntuación
		escaleraPeque = calcEscaleraPeque(boolUno, boolDos, boolTres, boolCuatro, boolCinco, boolSeis);
		escaleraGrande = calcEscaleraGrande(boolUno, boolDos, boolTres, boolCuatro, boolCinco, boolSeis);
		// Se almacenan las puntuaciones en otra función
		imprimirPuntuacion(uno, dos, tres, cuatro, cinco, seis, trio, cuatroIguales, fullHouse, escaleraPeque, escaleraGrande, chance, yahtzee);
	}

	// Función que realiza cálculos simples
	static int calculoSimple(int cantidad, int numero) {
		int num = 0;
		num = cantidad * numero;
		return num;
	}
	// Función que suma todos los valores
	static int calcChance(int uno, int dos, int tres, int cuatro, int cinco, int seis) {
		int chance = (uno + dos + tres + cuatro + cinco + seis);
		return chance;
	}

	// Función que calcula el valor de Yahtzee
	static int calcYahtzee(int numero) {
		int yahtzee = 0;
		yahtzee = 50;
		return yahtzee;
	}

	// Función que calcula el fullHouse
	static int calcFullHouse(int par, int trio) {
		int fullHouse = 25;
		return fullHouse;
	}
	
	// Función que calcula la escalera pequeña
	static int calcEscaleraPeque(boolean uno, boolean dos, boolean tres, boolean cuatro, boolean cinco, boolean seis) {
		int resultado = 0;
		boolean escalera = false;
		if (uno && dos && tres && cuatro) {
			escalera = true;
		} else if (dos && tres && cuatro && cinco) {
			escalera = true;
		} else if (tres && cuatro && cinco && seis) {
			escalera = true;
		}
		if (escalera) {
			resultado = 30;
		}
		return resultado;
	}
	
	// Función que calcula la escalera grande
	static int calcEscaleraGrande(boolean uno, boolean dos, boolean tres, boolean cuatro, boolean cinco, boolean seis) {
		int resultado = 0;
		boolean escalera = false;
		if (uno && dos && tres && cuatro && cinco) {
			escalera = true;
		} else if (dos && tres && cuatro && cinco && seis) {
			escalera = true;
		}
		if (escalera) {
			resultado = 40;
		}
		return resultado;
	}

	// Imprimir la puntuación
	static void imprimirPuntuacion(int uno, int dos, int tres, int cuatro, int cinco, int seis, int trio,
			int cuatroIguales, int fullHouse, int escaleraPeque, int escaleraGrande, int chance, int yahtzee) {
		System.out.println("Uno: " + uno + " Dos: " + dos + " Tres:" + tres + " cuatro: " + cuatro + " cinco: " + cinco
				+ " seis: " + seis + " trio: " + trio + " Cuatro Iguales: " + cuatroIguales + " FullHouse: " + fullHouse
				+ " Escalera Pequeña: " + escaleraPeque + " Escalera Grande: " + escaleraGrande + " Chance: " + chance + " Yahtzee: " + yahtzee);
	}
}
