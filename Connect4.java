import javax.swing.*;
import java.awt.*;

public class Connect4 extends JFrame {

	JPanel board;
	JButton[] buttons;
	JLabel[][] display;
	Dimension boardDim;
	
	public Connect4(){
		GameEngine ge = new GameEngine(this);
		
		buttons = new JButton[7];
		for( int i = 0; i < 7; i++ ){
			buttons[i] = new JButton(i+1+"");
			buttons[i].addActionListener(ge);
		}
		
		display = new JLabel[6][7];
		for( int i = 0; i < 6; i++ ){
			for( int j = 0; j < 7; j++ ){
				display[i][j] = new JLabel();
				display[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				display[i][j].setOpaque(true);
			}
		}
		
		//setup board
		board = new JPanel();
		boardDim = new Dimension(600,600);
		board.setPreferredSize(boardDim);
		board.setLayout(new GridLayout(7,7));
		for(int i = 0; i < 7; i++ ){
			board.add(buttons[i]);
		}
		for( int i = 0; i < 6; i++ ){
			for( int j = 0; j < 7; j++ ){
				board.add(display[i][j]);
			}
		}
		
		//setup window
		this.setTitle("Connect 4");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(board);
		this.getContentPane();
		this.pack();
		this.setVisible(true);
	}
	
	public void reset(){
		this.dispose();
		new Connect4();
	}
	
	public static void main(String[] args) {
		
		new Connect4();

	}

}
