public class Document {
	private int id;
	private String title;
	private String author;
	private int pages;

	//Constructor
	public Document(int id, String title, String author, int nbPages) {
		this.id      = id;
		this.title   = title;
		this.author  = author;
		this.pages   = nbPages;
	}

	//Setters
	public void setID(int id) {
		this.id = id;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPages(int nbPages) {
		this.pages = nbPages;
	}

	//Getters
	public int getID() {
		return this.id;
	}

	public String getAuthor() {
		return this.author;
	}

	public String getTitle() {
		return this.title;
	}

	public int getPages() {
		return this.pages;
	}

	@Override
	public String toString() {
		return "Document: {id = " + id + ", title = " + title + ", auteur = " + author + ", nombres de pages = " + pages + "}";
	}
}