package squarefiller;

import java.awt.Color;

import javax.swing.JButton;

public class Square extends JButton {

	private static final long serialVersionUID = 1L;
		
	private int x;//square's X coordinate
	private int y;//square's Y coordinate
	
	//square's pool of possible colors
	private Color[] colorPool = new Color[]{Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.YELLOW, Color.ORANGE};
	
	//square's initial color
	private Color color = colorPool[(int) Math.round(Math.floor(Math.random()*6))];

	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Color getColor() {
		return color;
	}

	public POSITION getPosition() {
		if(this.getX() == 0 && this.getY() == 0) {
			return POSITION.FIRST_CORNER;
		}
		if(this.getX() == 384 && this.getY() == 0) {
			return POSITION.SECOND_CORNER;
		}
		if(this.getX() == 384 && this.getY() == 384) {
			return POSITION.THIRD_CORNER;
		}
		if(this.getX() == 0 && this.getY() == 384) {
			return POSITION.FOURTH_CORNER;
		}
		if(this.getX() % 32 == 0 && this.getY() == 0) {
			return POSITION.FIRST_EDGE;
		}
		if(this.getX() == 384 && this.getY() % 32 == 0) {
			return POSITION.SECOND_EDGE;
		}
		if(this.getX() % 32 == 0 && this.getY() == 384) {
			return POSITION.THIRD_EDGE;
		}
		if(this.getX() == 0 && this.getY() % 32 == 0) {
			return POSITION.FOURTH_EDGE;
		}
		return POSITION.MIDDLE;		
	}
	
	public boolean borders(JButton square) {
		if(this.getPosition() == POSITION.FIRST_CORNER) {
			return(this.getX() + 32 == square.getX() && this.getY() == square.getY() || this.getX() == square.getX() && this.getY() + 32 == square.getY());
		}
		if(this.getPosition() == POSITION.SECOND_CORNER) {
			return(this.getX() - 32 == square.getX() && this.getY() == square.getY() || this.getX() == square.getX() && this.getY() + 32 == square.getY());
		}
		if(this.getPosition() == POSITION.THIRD_CORNER) {
			return(this.getX() - 32 == square.getX() && this.getY() == square.getY() || this.getX() == square.getX() && this.getY() - 32 == square.getY());
		}
		if(this.getPosition() == POSITION.FOURTH_CORNER) {
			return(this.getX() + 32 == square.getX() && this.getY() == square.getY() || this.getX() == square.getX() && this.getY() - 32 == square.getY());
		}
		if(this.getPosition() == POSITION.FIRST_EDGE) {
			return(this.getX() - 32 == square.getX() && this.getY() == square.getY() || this.getX() + 32 == square.getX() && this.getY() == square.getY() || this.getX() == square.getX() && this.getY() + 32 == square.getY());
		}
		if(this.getPosition() == POSITION.SECOND_EDGE) {
			return(this.getX() - 32 == square.getX() && this.getY() == square.getY() || this.getX() == square.getX() && this.getY() + 32 == square.getY() || this.getX() == square.getX() && this.getY() - 32 == square.getY());
		}
		if(this.getPosition() == POSITION.THIRD_EDGE) {
			return(this.getX() - 32 == square.getX() && this.getY() == square.getY() || this.getX() == square.getX() && this.getY() - 32 == square.getY() || this.getX() + 32 == square.getX() && this.getY() == square.getY());
		}
		if(this.getPosition() == POSITION.FOURTH_EDGE) {
			return(this.getX() + 32 == square.getX() && this.getY() == square.getY() || this.getX() == square.getX() && this.getY() + 32 == square.getY() || this.getX() == square.getX() && this.getY() - 32 == square.getY());
		}
		if(this.getPosition() == POSITION.MIDDLE) {
			return(this.getX() - 32 == square.getX() && this.getY() == square.getY() ||this.getX() + 32 == square.getX() && this.getY() == square.getY() || this.getX() == square.getX() && this.getY() + 32 == square.getY() || this.getX() == square.getX() && this.getY() - 32 == square.getY());
		}
		return false;
	}


}
