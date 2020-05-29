public class TestFSMIOViewer {

	public static void main(String argv[]) {
		if(argv.length != 1)
		{
			System.out.println("Input args !\n\t1 = Exo1\n\t2 = Exo2\n\t3 = Exo3\n\ts = test serialisation");
			System.exit(0);
		}
		if(argv[0].equals("s"))
			new TestFSMIO();
		else if(argv[0].equals("3"))
			new Test();
		else if(argv[0].equals("1"))
			new FSMIOStringviewer();
		else if(argv[0].equals("2"))
			new FSMIOViewer();
	}
}