public class TestFSMIO {
    public TestFSMIO()
    {
        ArrayList states = new ArrayList<State>();
		states.add(new State("a"));
		states.add(new State("b"));
		states.add(new State("c"));
		
		FSMIO fsmio = new FSMIO<String, String>(states, (State)states.get(0));
		
		fsmio.saveObject("test.ser");
		FSMIO newFsmio = new FSMIO("test.ser");
		System.out.println(fsmio);
    }
}