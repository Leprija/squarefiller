package squarefiller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class Tile implements ActionListener{
	
	private static final JFrame window = new JFrame("Square Filler");
	private static final JPanel panel = new JPanel();
	private static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	private static final Square[] buttons = new Square[169];
		
	private static final Player p1 = new Player();
	private static final Player p2 = new Player();
	
	private int squaresLeft = buttons.length-2;
	
	public Tile() {
		window.setSize(421, 442);
		window.setResizable(false);
		window.setLocation(dim.width/2 - window.getWidth()/2, dim.height/2 - window.getHeight()/2);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		panel.setLayout(null);		
	}
		
	public Square[] getButtons() {
		return buttons;
	}
					
	public int getSquaresLeft() {
		return squaresLeft;
	}

	public void setSquaresLeft(int added) {
		squaresLeft-=added;
	}
	
	public void populate() {
		for(int i = 0; i < buttons.length; i++) {
			if(i == 0) {
				buttons[i] = new Square(0, 0);
			}else {
				if(buttons[i-1].getX() == Math.sqrt(buttons.length)*32-32) {					
					buttons[i] = new Square(0, buttons[i-1].getY() + 32);
				}else {
					buttons[i] = new Square(buttons[i-1].getX() + 32, buttons[i-1].getY());				
				}
			}
			buttons[i].setBounds(buttons[i].getX(), buttons[i].getY(), 31, 31);	
			buttons[i].setBackground(buttons[i].getColor());
			buttons[i].addActionListener(this);
			panel.add(buttons[i]);
		}
		
		p1.getControlled().add(buttons[0]);
		buttons[0].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		buttons[0].setText("M");
				
		p2.getControlled().add(buttons[buttons.length-1]);
		buttons[buttons.length-1].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE));
		
		p1.setTurn(true);
				
		window.add(panel);
		window.setVisible(true);
	}

	public void checkScore() {
		if(p2.getPlayerScore() + squaresLeft < p1.getPlayerScore()) {
			JOptionPane.showMessageDialog(null, p1.getPlayerScore() + " : " + p2.getPlayerScore() + "\nLeft player wins!");
			restartGame();
			return;
		}
		if(p1.getPlayerScore() + squaresLeft < p2.getPlayerScore()) {
			JOptionPane.showMessageDialog(null, p1.getPlayerScore() + " : " + p2.getPlayerScore() + "\nRight player wins!");
			restartGame();
			return;
		}
	}
	
	public void restartGame() {		
		p1.getControlled().removeAll(p1.getControlled());
		p2.getControlled().removeAll(p2.getControlled());
		squaresLeft = 167;
		for(int i = 0; i < buttons.length; i++) {
			panel.remove(buttons[i]);
		}
		new Tile().populate();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		int p1Added = 0;
		int p2Added = 0;
		for(int i = 0; i < getButtons().length; i++) {
			if(ae.getSource() == getButtons()[i]) {
				if(p1.isTurn()) {
					getButtons()[0].setText("");
					getButtons()[getButtons().length-1].setText("M");
					for(int j = 0; j < p1.getControlled().size(); j++) {
						if(getButtons()[i].getBackground() != p2.getControlled().get(0).getBackground()) {
							p1.getControlled().get(j).setBackground(getButtons()[i].getBackground());							
						}
						for(int k = 0; k < getButtons().length; k++) {
							if(p1.getControlled().contains(getButtons()[k])) {
								getButtons()[k].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
							}
							if(!p1.getControlled().contains(getButtons()[k]) && !p2.getControlled().contains(getButtons()[k])) {
								if(p1.getControlled().get(j).borders(getButtons()[k]) && p1.getControlled().get(j).getBackground() == getButtons()[k].getBackground()) {
									p1Added++;
									p1.getControlled().add(getButtons()[k]);	
								}
							}
						}
					}
					setSquaresLeft(p1Added);
					checkScore();
					p1.setTurn(false);
					p2.setTurn(true);
					getButtons()[0].setText("");
					getButtons()[getButtons().length-1].setText("M");
					return;
				}
				if(p2.isTurn()) {
					getButtons()[0].setText("M");
					getButtons()[getButtons().length-1].setText("");
					for(int j = 0; j < p2.getControlled().size(); j++) {
						if(getButtons()[i].getBackground() != p1.getControlled().get(0).getBackground()) {
							p2.getControlled().get(j).setBackground(getButtons()[i].getBackground());
						}
						for(int k = 0; k < getButtons().length; k++) {
							if(p2.getControlled().contains(getButtons()[k])) {
								getButtons()[k].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE));
							}
							if(!p2.getControlled().contains(getButtons()[k]) && !p1.getControlled().contains(getButtons()[k])) {
								if(p2.getControlled().get(j).borders(getButtons()[k]) && p2.getControlled().get(j).getBackground() == getButtons()[k].getBackground()) {
									p2Added++;
									p2.getControlled().add(getButtons()[k]);
								}
							}
						}
					}
					setSquaresLeft(p2Added);
					checkScore();
					p1.setTurn(true);
					p2.setTurn(false);
					getButtons()[0].setText("M");
					getButtons()[getButtons().length-1].setText("");
					return;
				}
			}
		}
	}

}
