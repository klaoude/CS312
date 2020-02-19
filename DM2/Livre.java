public class Livre extends Document {
	//Contructor
	public Livre(int id, String title, String author, int nbPages) {
		super(id, title, author, nbPages);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}