/**
 * A class that holds the board that functions 
 * as the Model in a MVC architecture
 */




package mineSweeper;

import java.util.Random;

public class Board {
	private final int MAXSIZE=25;
	
	/**
	 * Default Constructor that creates the board
	 */
	public Board()
	{
		gameBoard=new MineButton[MAXSIZE][MAXSIZE];
		for(int i=0;i<MAXSIZE;i++)
		{
			for(int j=0;j<MAXSIZE;j++)
			{
				gameBoard[i][j]=new MineButton();
			}
		}		
		difficulty="easy";
		redrawBoard();
	}
	/**
	 * a method that redraws the board to whatever
	 * size the game is set to
	 */
	public void redrawBoard()
	{

		if(difficulty=="easy")
		{
			helpDrawBoard(10,10);
		}
		else if (difficulty=="medium")
		{
			helpDrawBoard(25,15);
		}
		else if (difficulty=="hard")
		{
			helpDrawBoard(50,25);
		}
		else
		{
			//don't know how we got here
		}
	}
	
	/**
	 * Private method that helps improve readibility of code
	 * @param mineCount must be <activeSize
	 * @param activeSize must be <MAXSIZE
	 * @post Board is drawn
	 */
	private void helpDrawBoard(int mineCount,int activeSize)
	{
		Random r = new Random();
		
		numMines=0;
		for (int i=0;i<MAXSIZE;i++)
		{
			for (int j=0;j<MAXSIZE;j++)
			{
				if (i<activeSize && j<activeSize)//active area
				{
					gameBoard[i][j].changeActive(true);
					gameBoard[i][j].changeFlag(false);
					gameBoard[i][j].changeClicked(false);
					if(numMines<mineCount)//need more mines
					{
						if (r.nextInt(activeSize)==1)//1 in activeSize chance of getting a mine
						{
							numMines++;
							gameBoard[i][j].changeMine(true);
						}
						else//not getting a mine
							{
							gameBoard[i][j].changeMine(false);
							}
					}
					else//got max mines in this step
						{
						gameBoard[i][j].changeMine(false);
						}
					
				}
				else//inactive area
				{
					gameBoard[i][j].changeActive(false);
					gameBoard[i][j].changeFlag(false);
					gameBoard[i][j].changeMine(false);
					gameBoard[i][j].changeClicked(false);
				}
					
			}
		}
		while(numMines<mineCount)//might not have enough mines
		{
			int temp1=r.nextInt(activeSize);
			int temp2=r.nextInt(activeSize);
			if (!gameBoard[temp1][temp2].getMine())
			{
				numMines++;
				gameBoard[temp1][temp2].changeMine(true);
			}
		}
	}
	/**
	 * Setter for difficulty
	 * @param newState should be defined in redrawBoard
	 * @post difficulty has new value
	 */
	public void changeDifficulty(String newState)
	{
		difficulty=newState;
		redrawBoard();
	}
	
	/**
	 * Getter for difficulty
	 * @post difficulty has been returned
	 */
	public String getDifficulty()
	{
		return difficulty;
	}
	
	/**
	 * Getter for numMines
	 * @post numMines has been returned
	 */
	public int getMines()
	{
		return numMines;
	}
	
	 /** 
	 * Setter for numMines
	 * @param newNumMines must be 0> and <MAXSIZE
	 * @post numMines has a new value
	 */
	public void changeNumMines(Integer newNumMines)
	{
		numMines=newNumMines;
	}
	
	/**
	 * Gives a specific mine button
	 * @param row must be >0 and <MAXSIZE
	 * @param collumn must be >0 and <MAXSIZE
	 * @return MineButton given
	 */
	public MineButton giveIndex(int row,int collumn)
	{
		return gameBoard[row][collumn];
	}
	
	/**
	 * Getter for MAXSIZE
	 * @return MAXSIZE
	 */
	public int getMax()
	{
		return MAXSIZE;
	}
	
	/**
	 * Gives the size of the board
	 * @return size of active board
	 */
	public int getBoardSize()
	{
		if (difficulty=="easy")
			return 10;
		else if (difficulty =="medium")
			return 15;
		else
			return 25;
	}
		
	private String difficulty;
	private MineButton [][] gameBoard;
	private int numMines;
}
