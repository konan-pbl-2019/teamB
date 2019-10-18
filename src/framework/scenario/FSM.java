package framework.scenario;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

public class FSM {
	private State initialState = null;
	protected State currentState = null;
	protected State prevState = null;
	private Hashtable<String, State> states = new Hashtable<String, State>();

	public FSM(State initialState, Hashtable<String, State> states) {
		this.initialState = initialState;
		this.states = states;
		currentState = initialState;
		prevState = initialState;
		Collection<State> allStates = states.values();
		Iterator<State> it = allStates.iterator();
		while (it.hasNext()) {
			State s = it.next();
			s.setOwner(this);
		}
	}

	public void addState(String stateName, State s) {
		states.put(stateName, s);
		s.setOwner(this);
	}

	public boolean trans(Event e) {
		prevState = currentState;
		currentState = currentState.getSuccessor(e);
		if (currentState == null) return false;
		return true;
	}

	public boolean go(State newState) {
		currentState = newState;
		return true;
	}

	public State getCurrentState() {
		return currentState;
	}
}
