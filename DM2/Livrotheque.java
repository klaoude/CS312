public class Livrotheque extends Bibliotheque {

	public Livrotheque(int capacite) {
		super(capacite);
	}

	@Override
	public boolean ajouter(Document doc) {
		if(!(doc instanceof Livre))
			return false;
		
		int indx = getNextFreeIndex();
		if(indx == -1)
			return false;
		documents[indx] = doc;
		return true;
	}
}