package it.polito.tdp.totocalcio.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {
	private Pronostico pronostico;
	private int N;
	private List<Risultato> soluzione;

	public List<Risultato> cerca(Pronostico pronostico) {
		this.pronostico=pronostico;
		this.N=pronostico.size();
		this.soluzione=new ArrayList<>();
		List<RisultatoPartita> parziale=new ArrayList<>();
		int livello=0;
		ricorsiva(parziale,livello);
		return this.soluzione;
	}
	
	//hanno deciso 3 partite,tu decidi la 4
	private void ricorsiva(List<RisultatoPartita>parziale,int livello) {
		//caso terminale?
		if(livello==N) {
			//questa soluzione parziale, è una soluzione completa
			this.soluzione.add(new Risultato(parziale));
			//TODO restituire al chiamante
		}else {
			//caso generale
			// [parziale da 0 a livello -1] [livello] [livello+1 in poi]
			PronosticoPartita pp= this.pronostico.get(livello);
			//pp sono i sotto-problemi da provare
			for(RisultatoPartita ris: pp.getRisultati()) {
				//provo a mettere 'ris' nella posizione 'livello' della soluzioone parziale
				parziale.add(ris);
				ricorsiva(parziale,livello+1);
				parziale.remove(parziale.size()-1);
			}
		}
		
	}

}
