import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class FSMIO<T1, T2> implements Serializable{
	private List<State> states;
	private TransitionFunction<T1, T2> tf ;
	private State currentState;	
	private State initialState;

	// Constructors
	public FSMIO(List<State> states, State init) {
		this.states = states;
		this.tf = new TransitionFunction<T1, T2>();
		this.currentState = this.initialState = init;
	}

	public FSMIO(State init){
		this.currentState = this.initialState = init;
		this.states = new ArrayList<State>();
		this.states.add(init);
		this.tf = new TransitionFunction<T1, T2>();
	}
	
	public FSMIO(String filePath)
	{
		ObjectInputStream ois = null;
		try{
			final FileInputStream fichier = new FileInputStream(filePath);
			ois = new ObjectInputStream(fichier);
			FSMIO fsmio = (FSMIO)ois.readObject();
			this.states = fsmio.states;
			this.tf = fsmio.tf;
			this.currentState = fsmio.currentState;
			this.initialState = fsmio.initialState;
		}
		catch(final java.io.IOException e) { e.printStackTrace(); }
		catch(final ClassNotFoundException e) {	e.printStackTrace(); }
		finally
		{
			try	{ if(ois !=null) ois.close(); }
			catch(final IOException ex) { ex.printStackTrace();	}
		}
	}

	public void saveObject(String filePath)
	{
		ObjectOutputStream oos = null;
		try
		{
			FileOutputStream fichier = new FileOutputStream(filePath);
			oos = new ObjectOutputStream(fichier);
			oos.writeObject(this);
			oos.flush();
		}
		catch(final java.io.IOException e) { e.printStackTrace(); }
		finally
		{
			try { if(oos !=null) { oos.flush(); oos.close(); } }
			catch(final IOException ex) { ex.printStackTrace(); }
		}
	}

	public void addTransition(State orig, T1 input, T2 output, State dest){
		if(states.contains(orig) && states.contains(dest))
		{
			try
			{
				tf.addTransition(orig, input, output, dest);
			}
			catch(Exception e)
			{
				System.out.println("Etat non ajoutes !");
				System.exit(0);	
			}			
		}
	}

	public List<String> getPossiblesInputs() {
        ArrayList<String> inputs = new ArrayList<>();

		for (Transition<T1, T2> transition : tf.getTransition()) 
		{
            String inputName = transition.getTag().getInput().toString();
            if (!inputs.contains(inputName))
				inputs.add(inputName);
        }

        return inputs;
    }

	public boolean addState(State s){		
		boolean done = false;
		if(!this.states.contains(s)){
			this.states.add(s);
			done = true;
		}
		return done;
	}

	public void reset(){
		currentState = initialState;
	}

	public T2 doTransition(T1 input){		
		try
		{
			Transition<T1, T2> nt = tf.getTransitions(currentState, input); 
			currentState = nt.getDest();
			return nt.getTag().getOutput();
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.exit(0);
		}
		return null;
	}

	public String toString(){
		return tf.toString();
	}

	public State getState() { return currentState; }
	public List<State> getStates() { return states; }
}