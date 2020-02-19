public class Bibliotheque {
	protected Document[] documents;

	protected int getNextFreeIndex() {
		int ret = 0;
		for(Document doc : documents) {
			if(doc == null)
				return ret;
			ret++;
		}
		return -1;
	}

	public Bibliotheque(int capacite) {
		documents = new Document[capacite];
	}

	public boolean ajouter(Document doc) {
		int indx = getNextFreeIndex();
		if(indx == -1)
			return false;
		documents[indx] = doc;
		return true;
	}

	public void afficherDocuments() {
		for(Document doc : documents) {
			System.out.println(doc);
			System.out.println("");
		}
	}

	public void afficherAuteurs() {
		for(Document doc : documents) {
			System.out.println(doc.getAuthor());
			System.out.println("");
		}
	}

	public int ajouterLivres(Document[] docs) {
		int ret = 0;
		for(Document doc : docs) {
			if(doc instanceof Livre) {
				if(ajouter(doc))
					ret++;
				else
					return ret;
			}
		}
		return ret;
	}
}