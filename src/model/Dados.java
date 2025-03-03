package model;

import java.util.Random;

public class Dados {

	private int valorDado;
	private boolean cambiado;

	// CONSTRUCTOR COMPLETO
	public Dados(int valorDado) {
		this.valorDado = valorDado;
	}

	// Constructor vacío
	public Dados() {
		valorDado = 1;
		cambiado = false;
	}

	// SETTERS & GETTERS
	public int getValorDado() {
		return valorDado;
	}

	public void setValorDado(int valorDado) {
		this.valorDado = valorDado;
	}
	
	public boolean getCambiado() {
		return cambiado;
	}

	public void setCambiado(boolean cambiado) {
		this.cambiado = cambiado;
	}

	// MÉTODOS VARIOS

	// Devuelve un valor aleatorio entre 1 y 6
	public int generateRandom(int min, int max) {
		Random rand = new Random();
		return rand.nextInt(max - min + 1) + min;

	}

	public void tirarDado() {
		valorDado = generateRandom(1, 6);
		System.out.println("Tiras el dado y sacas un: " + valorDado);
	}

}
