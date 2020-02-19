enum PrixLiterraire {
	GONCOUR,
	MEDICIS,
	INTERALLIE,
	AUCUN
}

public class Roman extends Livre {	
	private PrixLiterraire prixLiterraire;

	private String prixToString(PrixLiterraire prix) {
		String ret = "";
		switch(prix) {
		case GONCOUR:
			ret = "Goncour";
			break;
		case MEDICIS:
			ret = "Medicis";
			break;
		case INTERALLIE:
			ret = "Interallie";
			break;
		default:
			ret = "aucun";
			break;
		}
		return ret;
	}

	//Contructor
	public Roman(int id, String title, String author, int nbPages, PrixLiterraire prix) {
		super(id, title, author, nbPages);
		this.prixLiterraire = prix;
	}

	//Getters
	public PrixLiterraire getPrix() {
		return this.prixLiterraire;
	}

	//Setters
	public void setPrix(PrixLiterraire prix) {
		this.prixLiterraire = prix;
	}

	@Override
	public String toString() {
		return "Roman: {prix = " + prixToString(prixLiterraire) + "}\n" + super.toString();
	}
}