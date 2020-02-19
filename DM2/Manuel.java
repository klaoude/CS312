public class Manuel extends Livre {
	private int niveau;

	//Contructor
	public Manuel(int id, String title, String author, int nbPages, int niveau) {
		super(id, title, author, nbPages);
		this.niveau = niveau;
	}

	//Getters
	public int getNiveau() {
		return this.niveau;
	}

	//Setters
	public void setPrix(int niveau) {
		this.niveau = niveau;
	}

	@Override
	public String toString() {
		return "Manuel: {niveau = " + niveau + "}\n" + super.toString();
	}
}