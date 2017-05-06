package squarefiller;

import java.util.ArrayList;

public class Player {

	private boolean turn;
	private ArrayList<Square> controlled = new ArrayList<Square>();

	public ArrayList<Square> getControlled() {
		return controlled;
	}
	
	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
	public int getPlayerScore() {
		return controlled.size();
	}
	
}
