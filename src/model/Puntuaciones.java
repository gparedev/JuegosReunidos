package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Puntuaciones {

	void NumerosDados(ArrayList<Dados> dados) {

		int unos;
		int doses;
		int treses;
		int cuatros;
		int cincos;
		int seises;

		for (Dados dado : dados) {
			int numero = dado.getNumero();

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
		combinaciones(unos, doses, treses, cuatros, cincos, seises);
	}

	void combinaciones(int unos, int doses, int treses, int cuatros, int cincos, int seises) {
		int trio = 0;
		int cuatroIguales = 0;
		int fullHouse = 0;
		int yahtzee = 0;
		
		boolean parBoolean = false;
		boolean trioBoolean = false;
		
		int parFullHouse = 0;
		int trioFullHouse = 0;
		
		int[] Array = { unos, doses, treses, cuatros, cincos, seises };
		int[] ArrayNumero = { 1, 2, 3, 4, 5, 6 };

		for (int i = 0; i < Array.length; i++) {
			if (Array[i] == 2) {
				parBoolean = true;
				parFullHouse = ArrayNumero[i];
			}
			if (Array[i] >= 3) {
				trio = CalcTrio(ArrayNumero[i]);
				trioBoolean = true;
				trioFullHouse = ArrayNumero[i];
			}
			if (Array[i] >= 4) {
				cuatroIguales = CalcCuatroIguales(ArrayNumero[i]);
			}
			if (Array[i] == 5) {
				yahtzee = CalcYahtzee(ArrayNumero[i]);
			}
		}
		if (trioBoolean && parBoolean) {
			fullHouse = CalcFullHouse(parFullHouse, trioFullHouse);
		}
	}

	int CalcTrio(int numero) {
		int trio = 0;
		trio = 3 * numero;
		return trio;
	}

	int CalcCuatroIguales(int numero) {
		int Cuatro = 0;
		Cuatro = 4 * numero;
		return Cuatro;
	}

	int CalcYahtzee(int numero) {
		int yahtzee = 0;
		yahtzee = 5 * numero;
		return yahtzee;
	}

	int CalcFullHouse(int par, int trio) {
		int fullHouse = 25;
		return fullHouse;
	}
}
