import java.util.ArrayList;

public class TestFSMIO {
    public TestFSMIO()
    {
        ArrayList states = new ArrayList<State>();
		states.add(new State("cazou"));
		states.add(new State("yano"));
		states.add(new State("suntzu"));
		states.add(new State("piscine"));

		
		FSMIO fsmio = new FSMIO<String, String>(states, (State)states.get(0));
		
		try{
			fsmio.addTransition((State)states.get(0), 'a', 0, (State)states.get(1));
			fsmio.addTransition((State)states.get(0), 'b', 1, (State)states.get(3));
			fsmio.addTransition((State)states.get(0), 'b', 0, (State)states.get(2));
			fsmio.addTransition((State)states.get(1), 'a', 0, (State)states.get(2));
			fsmio.addTransition((State)states.get(2), 'a', 1, (State)states.get(3));
		}
		catch( Exception ex)
		{
			System.exit(0);
		}


		fsmio.saveObject("../test.ser");
		FSMIO newFsmio = new FSMIO("../test.ser");
		System.out.println(fsmio);
    }
}