import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.Serializable;

public class TransitionFunction <T1, T2> implements Serializable{
	private List<Transition<T1, T2>> transitions;

	public TransitionFunction( ) {
		this.transitions = new ArrayList<Transition<T1, T2>>();
	}

	public void addTransition(State orig, T1 input, T2 output, State dest){
		this.transitions.add(new Transition<T1, T2>(orig,
                        new Tag<T1, T2>(input, output), dest));
	}
	
	// Retourne la transition correspondant à l'état orig et à l'entrée input
	public Transition<T1, T2> getTransitions(State orig, T1 input) throws Exception 
	{
		Iterator<Transition<T1, T2>> iter = this.transitions.iterator();
		while(iter.hasNext())
		{
			Transition<T1, T2> t = iter.next();
			if(t.getOrig().equals(orig) && t.getTag().getInput().equals(input))
				return t; 
		}
		throw new Exception("Transition not found !");
	}

	public List<Transition<T1, T2>> getTransition() { return transitions; }

	public String toString()
	{
		String s = "";
		for(Transition<T1, T2> t : transitions)
			s += "\n\t" + t.toString();
		return s;
	}
}
