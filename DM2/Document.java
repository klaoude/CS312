public class Document {
	private int id;
	private String title;

	//Constructor
	public Document(int id, String title) {
		this.id      = id;
		this.title   = title;
	}

	//Setters
	public void setID(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	//Getters
	public int getID() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	@Override
	public String toString() {
		return "Document: {id = " + id + ", title = " + title + "}";
	}
}