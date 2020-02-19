public class Revue extends Document {
	private int mois;
	private int annee;

	//Constructor
	public Revue(int id, String title, String author, int nbPages, int mois, int annee) {
		super(id, title, author, nbPages);
		this.mois = mois;
		this.annee = annee;
	}

	//Setters
	public void setMois(int mois) {
		this.mois = mois;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	//Getters
	public int getMois() {
		return mois;
	}

	public int getAnnee() {
		return annee;
	}

	@Override
	public String toString() {
		return "Revue: {mois = " + mois + ", annee = " + annee + "}\n" + super.toString();
	}
}