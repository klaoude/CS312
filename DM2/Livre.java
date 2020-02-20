public class Livre extends Document {
	private String author;
	private int pages;

	//Contructor
	public Livre(int id, String title, String author, int nbPages) {
		super(id, title);

		this.author  = author;
		this.pages   = nbPages;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPages(int nbPages) {
		this.pages = nbPages;
	}

	public String getAuthor() {
		return this.author;
	}

	public int getPages() {
		return this.pages;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}