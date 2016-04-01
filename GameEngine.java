import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class GameEngine implements ActionListener {

	Connect4 parent;
	JButton buttonClicked;
	int[][] gameState;
	int player;
	int tileCount = 0;
	
	public GameEngine( Connect4 parent ){
		this.parent = parent;
		player = 1;
		
		//initialize gameState
		gameState = new int[6][7];
		for( int i = 0; i < 6; i++ ){
			for( int j = 0; j < 7; j++ ){
				gameState[i][j] = 0;
			}
		}
	}
	
	public void actionPerformed( ActionEvent ae ){
		buttonClicked = (JButton)ae.getSource();
		for( int i = 0; i < 7; i++ ){
			if( buttonClicked == parent.buttons[i] ){
				for( int j = 5; j >= 0; j-- ){
					Color background = parent.display[j][i].getBackground();
					if( background != Color.red && background != Color.blue ){
						gameTurn(j,i);
						break;
					}
				}
			}
		}
	}

	public void gameTurn( int row, int col ){
		tileCount = 0;
		if( player == 1 ){
			parent.display[row][col].setBackground(Color.red);
			gameWin(row,col);
			player = 2;
		}
		else{
			parent.display[row][col].setBackground(Color.blue);
			gameWin(row,col);
			player = 1;
		}
	}
	
	public void gameWin( int row, int col ){
		Color playerColor;
		if( player == 1 )
			playerColor = Color.red;
		else
			playerColor = Color.blue;

		//horizontal
		for( int i = 0; i < 4; i++ ){
			if( getTileColor(row,i) == playerColor && getTileColor(row,i+1) == playerColor && getTileColor(row,i+2) == playerColor && getTileColor(row,i+3) == playerColor )
				endGame();
		}

		//vertical
		for( int i = 5; i >=3; i-- ){
			if( getTileColor(i,col) == playerColor && getTileColor(i-1,col) == playerColor && getTileColor(i-2,col) == playerColor && getTileColor(i-3,col) == playerColor )
				endGame();
		}

		//diag1
		for( int i = 0; i < 4; i++ ){
			for( int j = 5; j >= 3; j-- ){
				 if( getTileColor(j,i) == playerColor && getTileColor(j-1,i+1) == playerColor && getTileColor(j-2,i+2) == playerColor && getTileColor(j-3,i+3) == playerColor )
					 endGame();
			}
		}
		//diag2
		for( int i = 3; i < 7; i++ ){
			for( int j = 5; j >= 3; j-- ){
				 if( getTileColor(j,i) == playerColor && getTileColor(j-1,i-1) == playerColor && getTileColor(j-2,i-2) == playerColor && getTileColor(j-3,i-3) == playerColor )
					 endGame();
			}
		}

	}
	
	public Color getTileColor( int row, int col ){
		return parent.display[row][col].getBackground();
	}
	
	public void endGame(){
		int dialogResult = JOptionPane.showConfirmDialog(null, "Player "+player+" has won the game! \nPlay again?");
		if( dialogResult == JOptionPane.NO_OPTION )
			System.exit(0);
		else if( dialogResult == JOptionPane.YES_OPTION )
			parent.reset();
	}

}
