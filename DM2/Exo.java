public class Exo {
	public static void main(String[] args) {
		Revue rev = new Revue(0, "Cazou", "Yano", 420, 2, 2121);
		Roman rom = new Roman(1, "Pissine", "Sundy", 1337, PrixLiterraire.GONCOUR);

		System.out.println(rev);
		System.out.println(rom);

		System.out.println("----------------");

		Roman r1 = new Roman(2, "Pistache", "Tiens", 21, PrixLiterraire.INTERALLIE);
		Roman r2 = new Roman(4, "ytu", "rtyu", 69, PrixLiterraire.AUCUN);
		Manuel m1 = new Manuel(3, "aze", "eza", 42, 5);
		Revue rev1 = new Revue(5, "Revue 1", "Moi", 420, 2, 2121);

		Bibliotheque bibli = new Bibliotheque(2);
		bibli.ajouter(rev);
		bibli.ajouter(rom);
		bibli.afficherDocuments();
		System.out.println("----------------");

		Bibliotheque livresBibli = new Bibliotheque(4);
		Document[] docs = {r1, r2, rev1, m1};
		livresBibli.ajouterLivres(docs);
		livresBibli.afficherDocuments();
		System.out.println("----------------");

		Livrotheque livri = new Livrotheque(4);
		livri.ajouter(r1);
		livri.ajouter(m1);
		livri.ajouter(rev1);
		livri.ajouter(r2);
		livri.afficherDocuments();
	}
}