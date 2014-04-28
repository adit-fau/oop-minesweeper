/**
 * Minesweeper clone by Colby Kerwin and Aditya Burkule
 * Class that handles all of the game logic
 * Functions as the controller in MVC architecture
 */

package mineSweeper;


public class Game {
	
	/**
	 * Default constructor instanciates the board
	 */
	public Game()
	{
		gameOver=false;
		board=new Board();
		openSpaces=90;
		firstMove=true;
		minesLeft=board.getMines();
	}
	

	
	/**
	 * returns the size of the active board
	 * @return board.getBoardSize
	 */
	public int boardSize()
	{
		return board.getBoardSize();
	}
	
	
	
	/**
	 * Changes the difficulty of the board
	 * @param newState should be defined in redrawBoard
	 * @post difficulty has new value
	 */
	public void changeDifficulty(String newState)
	{
		board.changeDifficulty(newState);
	}
	
	/**
	 * Starts a new game
	 */
	public void startGame()
	{
		board.redrawBoard();
		openSpaces=board.getBoardSize()*board.getBoardSize()-board.getMines();
		gameOver=false;
		minesLeft=board.getMines();
	}
	
	/**
	 * getter for gameOver
	 * @return gameOver
	 */
	public Boolean gameOver()
	{
		return gameOver;
	}
	
	/**
	 * Method that makes moves
	 * @param row >0 and <board.MAXSIZE
	 * @param column >0 and <board.MAXSIZE
	 * @return number of surrounding mines
	 */
	public int makeMove(int row,int column)
	{
		if (gameOver)
		{
			System.out.println("Game Already Over, You had a time of Blah");
			return -1;
		}
		if(firstMove)
		{
			//time.start();
			firstMove=false;
		}

		int surMines=-1;
	
		MineButton temp=board.giveIndex(row, column);
		if(!temp.getFlagged()  && temp.getActive() )
		{
			if(!temp.getMine())
			{
				surMines=0;
				surMines=surMines+checkIndex(row-1,column-1);
				surMines=surMines+checkIndex(row-1,column);
				surMines=surMines+checkIndex(row-1,column+1);
				surMines=surMines+checkIndex(row,column-1);
				surMines=surMines+checkIndex(row,column+1);
				surMines=surMines+checkIndex(row+1,column-1);
				surMines=surMines+checkIndex(row+1,column);
				surMines=surMines+checkIndex(row+1,column+1);			
				if (temp.getClicked())
				{
					return surMines;
				}
				temp.changeClicked(true);
				openSpaces--;
			}
			else
			{
				gameOver=true;//you hit a mine
				//time.stopTime();
				System.out.println("You Lost");
			}
		}
		gameWon();
		return surMines;
	}
	
	/**
	 * Under construction
	 * @return will return the amount of time since game started
	 */
	public int getTimer()
	{
		//if(!gameOver)
			//return (int)(time.giveTime()/1000);
		//return (int)(time.giveStopTime()/1000); 
		return 0;
	}
	
	/**
	 * Private method that checks if an index contains a mine
	 * @param row >0 and <boardSize
	 * @param column >0 and <boardSize
	 * @return 1 if it is a mine, 0 if not or out of area
	 */
	private int checkIndex(int row,int column)
	{
		if(row<0||column<0||row>(board.getBoardSize()-1)||column>(board.getBoardSize()-1))
		{
			return 0;
		}
		
		MineButton temp=board.giveIndex(row, column);
		if (temp.getMine())
			return 1;
		return 0;
	}
	
	/**
	 * Places a flag at a given location
	 * @param row >0 and <boardSize
	 * @param column >0 and <boardSize
	 * @post flag is placed at an index
	 */
	public void placeFlag(int row, int column)
	{
		MineButton temp=board.giveIndex(row, column);
		if (!temp.getFlagged() && temp.getActive() && !temp.getClicked())
		{
			temp.changeFlag(true);
			minesLeft--;
		}
	}
	/**
	 * Removes a flag at an index
	 * @param row >0 and <boardSize
	 * @param column >0 and <boardSize
	 * @post flag is removed at an index
	 */
	public void removeFlag(int row, int column)
	{
		MineButton temp=board.giveIndex(row, column);
		if (temp.getFlagged() && temp.getActive() && !temp.getClicked())
		{
			temp.changeFlag(false);
			minesLeft++;
		}
		
	}
	
	/**
	 * getter for minesLeft
	 * @return minesLeft
	 */
	public int getMines()
	{
		return minesLeft;
	}	
	
	/**
	 * Sets the condition for a game being won
	 */
	public void gameWon()
	{
		if (openSpaces==0)
		{
			System.out.println("You Won");
			minesLeft=0;
			gameOver=true;
			//time.stopTime();
		}
	}
	
	/**
	 * Gives max board size
	 * @return max board size
	 */
	public int getMax()
	{
		return board.getMax();
	}
	
	/**
	 * Gives a specific mine button
	 * @param row must be >0 and <MAXSIZE
	 * @param collumn must be >0 and <MAXSIZE
	 * @return MineButton given
	 */
	public MineButton giveIndex(int row, int collumn)
	{
		return board.giveIndex(row, collumn);
	}
	
	private int minesLeft;
	private int openSpaces;
	private Board board;
	//private GameTimer time;
	private Boolean firstMove;
	private Boolean gameOver;

}
