package org.generation.italy;

import java.util.Scanner;

public class Bevanda {
	
	Scanner sc = new Scanner(System.in);
	
	private int codice, quantitàDisponibile;
	private String nome;
	private Float prezzo;

	public Bevanda(int codice, String nome, Float prezzo)
	{
		this.codice = codice;
		this.nome = nome;
		this.prezzo = prezzo;
		quantitàDisponibile = 5;
		
	}
	
	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
			this.codice = codice;
	}

	public int getQuantitàDisponibile() {
		return quantitàDisponibile;
	}

	public void setQuantitàDisponibile(int quantitàDisponibile) {
		if(quantitàDisponibile > 0)
			this.quantitàDisponibile = quantitàDisponibile;
	}

	public String getNome() {
			return nome;
	}

	public void setNome(String nome) {
		if(!nome.isEmpty())
			this.nome = nome;
	}

	public Float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Float prezzo) {
		if(prezzo > 0)
			this.prezzo = prezzo;
	}



	public void eroga() //questo metodo riduce la quantità disponibile di 1
	{
		if(quantitàDisponibile > 0)
			quantitàDisponibile--;
	}
	
	public void carica(int quantità) //questo metodo permette di caricare la quantità desiderata di bevande
	{
		System.out.println("Inserire il numero di unità da aggiungere: ");
		quantità = Integer.parseInt(sc.nextLine());
		if(quantità > 0 && quantità < 50-quantitàDisponibile)
			quantitàDisponibile += quantità;
	}
}
