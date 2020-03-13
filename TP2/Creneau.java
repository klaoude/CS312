import java.util.*;

public class Creneau {
	private int annee;
	private int mois; // 1 e 12
	private int jour; // 1 e 31
	private int heure; // 0 e 23
	private int minute; // 0 e 59
	private int duree; // en minutes, maximum 210
	
	private Salle salle;
	private Activity activity;
	
	public Creneau(int a, int m, int j, int h, int min, int d, Salle s, Activity ac) {
		
		annee = a; mois = m; jour = j;
		heure = h; minute = min; duree = d;
		salle = s;
		activity = ac;
		
		if(!verifCapacite()){
			System.exit(1);
		}
		if(!verifDuree()){
			System.exit(1);
		}
		if(!verifSalle()){
			System.exit(1);
		}
	}
	
	// Verifie l'adequation de la salle : la salle affectee doit etre une des salles appropriees de l'activity
	private boolean verifSalle() {
		return salle instanceof SalleTP && activity instanceof TP || salle instanceof SalleCTD && activity instanceof TD;
	}
	
	// Verifie que la taille de la salle convient e la promo
	private boolean verifCapacite() {
		int numEtu = 0;
		for(Groupe g : activity.getGroupes())
			numEtu += g.getEffectif();
		return salle.getCapacite() >= numEtu;
	}
	
	// Verifie que le debut et la fin du Creneau sont dans la meme journee, entre 8h et 19h
	private boolean verifDuree() {
		return heure >= 8 && heure + (int)(this.duree / 60) < 19; 
	}
	
	public Salle getSalle() {
		return salle;
	}
	
	public Activity getactivity() {
		return activity;
	}
	
	public int getDuree() {
		return duree;
	}

	public int getHeure() {
		return heure;
	}

	public int getMinutes() {
		return minute;
	}
	
	public String toString(){
		return jour + "/" + mois + "/" + annee + " " + heure + ":" + minute +" (" + duree +") : " + 
				activity + " " + salle;
	}
	
	public boolean intersection(Creneau c) {
		return heure * 60 + minute + duree >= c.getHeure() * 60 + c.getMinutes();
	}	
}