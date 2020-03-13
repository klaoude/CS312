import java.util.*;

public class Activity {
	private String nom;
	private ArrayList<Groupe> groupes;
	private ArrayList<Salle> sallesAppropriees;
	
	public Activity(String nom){
		this.nom = new String(nom);
		groupes = new ArrayList<Groupe>();
		sallesAppropriees = new ArrayList<Salle>();
	}
	
	public void addGroupe(Groupe groupe){
		groupes.add(groupe);
	}
	
	protected void addSalle(Salle s){ 
		sallesAppropriees.add(s); 
	}
	
	public ArrayList<Salle> getSalles(){
		return sallesAppropriees;
	}
	
	public ArrayList<Groupe> getGroupes(){
		return groupes;
	}
	
	public String toString(){
		return "Activity " + nom + " (" + groupes +")";
	}
}
