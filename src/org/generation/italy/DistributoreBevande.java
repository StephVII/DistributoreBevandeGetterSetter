package org.generation.italy;

import java.util.HashMap;
import java.util.Scanner;

public class DistributoreBevande {
	
	Scanner sc = new Scanner(System.in);
	
	float credito, inserisciMoneta; //credito contiene il credito attuale del distributore, inserisciMoneta è la variabile in cui l'utente inserisce i valori delle monete
	String moneta, risposta, rifornitura, decisione, rifornimentoSI_NO; //risposta permette all'utente di aggiungere ulteriori monete
	boolean check = true, check1 = false, check3 = true;
	int scelta, ricarica, password; //scelta permette di scegliere una delle bevande
	
	HashMap<Integer, Bevanda> bevande = new HashMap<>(); //questa hashmap contiene tutte le bevande

	public DistributoreBevande() { //questo costruttore imposta la lista delle bevande e il valore del credito iniziale del distributore
		bevande.put(1, new Bevanda(1, "Acqua Naturale", 0.5f));
		bevande.put(2, new Bevanda(2, "Acqua Frizzante", 0.75f));
		bevande.put(3, new Bevanda(3, "Coca Cola", 1.5f));
		bevande.put(4, new Bevanda(4, "Fanta", 1.5f));
		bevande.put(5, new Bevanda(5, "Sprite", 1.5f));
		bevande.put(6, new Bevanda(6, "Chinotto", 1.5f));
		bevande.put(7, new Bevanda(7, "Limonata", 1.25f));
		bevande.put(8, new Bevanda(8, "Estathè Pesca", 1.25f));
		bevande.put(9, new Bevanda(9, "Estathè Limone", 1.25f));
		credito = 0;
	}

	public void credito() {
		
		do
		{
			System.out.print("Inserisci una moneta (€0.10/€0.20/€0.50/€1/€2): ");
			moneta = sc.nextLine();
			
			if(moneta.equals("0.1")||moneta.equals("0.2")||moneta.equals("0.5")||moneta.equals("1")||moneta.equals("2"))
			{
				inserisciMoneta = Float.parseFloat(moneta);
				credito = credito+inserisciMoneta;
				System.out.println("Vuoi inserire un'altra moneta?");
				risposta = sc.nextLine();
				risposta = risposta.toLowerCase();
				check1 = true;
			}
			else
			{
				System.out.println("La moneta inserita non è valida.");
				check1 = false;
			}
			
		} while(risposta.equals("si")||check1 == false);
	}
	
	public void erogaBevanda() {
		
		for(Integer bevanda : bevande.keySet())
		{
			System.out.println("[0"+bevande.get(bevanda).getCodice()+"]"+" - €"+bevande.get(bevanda).getPrezzo()+" - "+bevande.get(bevanda).getNome());
		}
		
		do 
		{	
				do
				{
					System.out.println("\n\nScegli una bevanda da bere:");
					scelta = Integer.parseInt(sc.nextLine());
					
					if(bevande.containsKey(scelta))
					{
						System.out.println("\n"+bevande.get(scelta).getNome()+" costa €"+bevande.get(scelta).getPrezzo()+"\n");
						check = true;
					}
					else
					{
						System.out.println("La bevanda scelta non è disponibile.");
						check = false;
					}
				} while(check == false);
				
				do
				{
					if(credito < bevande.get(scelta).getPrezzo())
					{
						System.out.println("Il credito attuale non è sufficiente per acquistare la bevanda scelta. (€"+credito+")\n");
						credito();
					}
				} while(credito < bevande.get(scelta).getPrezzo());
				
				credito = credito-bevande.get(scelta).getPrezzo();
				bevande.get(scelta).eroga();
				System.out.println("Hai acquistato un'unità di "+bevande.get(scelta).getNome()+".\nSono stati detratti €"+bevande.get(scelta).getPrezzo()+" dal credito. Il credito attuale è €"+String.format("%.2f", credito));
				System.out.println("Rimangono "+bevande.get(scelta).getQuantitàDisponibile()+" unità di "+bevande.get(scelta).getNome());
				
				System.out.println("\n\nVuoi acquistare un'altra bevanda?");
				decisione = sc.nextLine();
				decisione = decisione.toLowerCase();
			
		} 
		while(decisione.equals("si"));
		
		
		System.out.println("Grazie per aver usato il distributore. Il tuo resto è "+credito);
		credito = 0;
		
		System.out.println("Vuoi rifornire il distributore? (si/no)");
		rifornimentoSI_NO = sc.nextLine();
		rifornimentoSI_NO = rifornimentoSI_NO.toLowerCase();
		if(rifornimentoSI_NO.equals("si"))
		{
			do
			{
				System.out.print("\ninserire la password per rifornire il distributore: ");
				password = Integer.parseInt(sc.nextLine());
				
				if(password == 12345)
				{
					check3 = true;
					System.out.println("Quale bevanda vuoi rifornire? [01/02/03/04/05/06/07/08/09]");
					ricarica = Integer.parseInt(sc.nextLine());
					bevande.get(ricarica).carica(ricarica);
				}
				else
				{
					System.out.println("Password errata.\n");
					check3 = false;
				}
			} while(check3 == false);
			System.out.println("Quantità di "+bevande.get(ricarica).getNome()+": "+bevande.get(ricarica).getQuantitàDisponibile());
		}
		else
			System.out.println("\nArrivederci.");
		
		
	}
	
	
}
