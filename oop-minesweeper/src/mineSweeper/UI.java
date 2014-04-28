/**
 * A class that creates a GUI for users
 * functions as the view in MVC architecture 
 */
package mineSweeper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


public class UI {
	/**
	 * default constructor, instanciates game and does everything
	 */
	public UI()//setting up the UI frame
	{

		frame=new JFrame("World's Best Minesweeper Rip");
		frame.setLayout(new BorderLayout());
		game=new Game();
		frame.add(BorderLayout.SOUTH,buildGrid());
		frame.add(BorderLayout.WEST,diffMenu());
		frame.add(BorderLayout.EAST,minesLeft());
		frame.add(BorderLayout.CENTER,restart());
	    frame.setSize(1000, 1000);
	    frame.setVisible(true);
	}
	
	/**Private Method
	 * creates the panel with the mines left
	 * @return a panel containing the # of mines left
	 */
	private JPanel minesLeft()
	{
		JPanel mines=new JPanel();
		mineLeft=new JTextField(((Integer)(game.getMines())).toString());
		mineLeft.setEditable(false);
		mines.add(mineLeft);
		JLabel temp=new JLabel("Mines Left");
		mines.add(temp);
		return mines;
	}
	/**Private Method
	 * Updates the minesLeft text field
	 */
	
	private void updateMines()
	{
		mineLeft.setText(((Integer)(game.getMines())).toString());
	}
	
	/**
	 * sets up the jbuttons that work as the mine field
	 * @return a panel containing the mine field
	 */
	private JPanel buildGrid()
	{
		int row=0;
		int collumn=0;
		JPanel mineField=new JPanel();
		mineField.setLayout(new GridLayout(0,game.getMax()));
		mineGrid=new JButton[game.getMax()][game.getMax()];
		for (row=0;row<game.getMax();row++)
		{
			for (collumn=0;collumn<game.getMax();collumn++)
			{
				mineGrid[row][collumn]=new JButton();
				mineGrid[row][collumn].setMargin(new Insets(0,0,0,0));
            	mineGrid[row][collumn].setText(" ");
            	mineGrid[row][collumn].setVisible(false);
            	mineGrid[row][collumn].setActionCommand(((Integer)(row)).toString()+ "/"+((Integer)(collumn)).toString() );
            	if(row<game.boardSize()&&collumn<game.boardSize())
            	{
            		mineGrid[row][collumn].setVisible(true);
            	}
            	mineGrid[row][collumn].setBackground(Color.gray);
            	mineGrid[row][collumn].setForeground(Color.black);
            	mineField.add(mineGrid[row][collumn]);     	
            	
            	mineGrid[row][collumn].addActionListener(new ActionListener() {
      		      public void actionPerformed(ActionEvent e) {//this is action listener for left click
      		    	  
      		    	  if(((JButton)(e.getSource())).getText()==" "&& !game.gameOver())
      		    	  {
      		    
      		    	  	int temp1=0;
      		    	  	int temp2=0;
      		    	  	String temp=((JButton) (e.getSource())).getActionCommand();
      		    	  	temp2=Integer.parseInt(temp.substring(temp.lastIndexOf("/") + 1));
      		    	  	temp1=Integer.parseInt(temp.substring(0,temp.lastIndexOf("/") ));
      					recButtonPress(temp1,temp2);
      					if (game.gameOver())
      					{
      						showMines();
      						updateMines();
      					}
      					formatText();
	
      		    	  }

      		      }//this is the action listener for left click
            	});
            	
            	
            	mineGrid[row][collumn].addMouseListener(new MouseAdapter(){
            		public void mouseClicked(MouseEvent e){//right click listener
            			 if (e.getButton() == 3)
            			 {
            			if (((JButton)(e.getSource())).getText()==" "&& !game.gameOver())
            			{
            				((JButton)e.getSource()).setText("F");
            				int temp1=0;
          		    	  	int temp2=0;
          		    	  	String temp=((JButton) (e.getSource())).getActionCommand();
          		    	  	temp2=Integer.parseInt(temp.substring(temp.lastIndexOf("/") + 1));
          		    	  	temp1=Integer.parseInt(temp.substring(0,temp.lastIndexOf("/") ));
            				game.placeFlag(temp1, temp2);
            				updateMines();
            				formatText();
            			}
            			else if (((JButton)(e.getSource())).getText()=="F"&& !game.gameOver())
            			{
            				((JButton)e.getSource()).setText(" ");
            				int temp1=0;
          		    	  	int temp2=0;
          		    	  	String temp=((JButton) (e.getSource())).getActionCommand();
          		    	  	temp2=Integer.parseInt(temp.substring(temp.lastIndexOf("/") + 1));
          		    	  	temp1=Integer.parseInt(temp.substring(0,temp.lastIndexOf("/") ));
            				game.removeFlag(temp1, temp2);
            				updateMines();
            				formatText();
            				
            			}
            			 }
            			
            			
            		}
            	});

            	
            	
            	
			}
		}

		return mineField;
	}
	/**Private Method
	 * shows the location of all of the mines in the mine field
	 */
	private void showMines()
	{
		int row=0;
		int collumn=0;
		int bSize=game.boardSize();
		for (row=0;row<bSize;row++)
		{
			for (collumn=0;collumn<bSize;collumn++)
			{
            	if (game.giveIndex(row, collumn).getMine())
            	{
            		mineGrid[row][collumn].setText("M");
            	}
			}
		}
	}
	/**Private Method
	 * rebuilds the minefield
	 */
	private void rebuildGrid()
	{
		int row=0;
		int collumn=0;
		game.startGame();
		updateMines();
		for (row=0;row<game.getMax();row++)
		{
			for (collumn=0;collumn<game.getMax();collumn++)
			{
            	mineGrid[row][collumn].setText(" ");
            	mineGrid[row][collumn].setVisible(false);
            	if(row<game.boardSize()&&collumn<game.boardSize())
            	{
            		mineGrid[row][collumn].setVisible(true);
            	}
            	mineGrid[row][collumn].setForeground(Color.black);
            	mineGrid[row][collumn].setBackground(Color.gray);
			}
		}
	}
	/**
	 * builds the restart button and makes it work
	 * @return a panel containing the restart button
	 */
	private JPanel restart()
	{
		JButton temp=new JButton("Restart Game");
		 temp.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		  		rebuildGrid();
		      }
		 });
		 JPanel plzNoJumboBtn=new JPanel();//before I put it into a JPanel, the button was HUGE
		 plzNoJumboBtn.add(temp);
		 game.startGame();
		return plzNoJumboBtn;
	}
	
	/**
	 * A recursive button press, it clears all surrounding spaces if the num of surrounding mines is 0
	 * @param row >0 and < board size
	 * @param collumn >0 and < board size
	 */
	private void recButtonPress(int row, int collumn)//recursive button pressing
	{
		int bSize=game.boardSize();
		if (row<0||row>=bSize||collumn<0||collumn>=bSize)//invalid move
			return ;
		
		int temp=game.makeMove(row,collumn);		
		
		mineGrid[row][collumn].setText(((Integer)(temp)).toString() );
		if (temp==0)
		{
			
			if(row>0 && mineGrid[row-1][collumn].getText()==" ")
				recButtonPress(row-1,collumn);
			
			if(row>0 &&(collumn+1<bSize) && mineGrid[row-1][collumn+1].getText()==" " )
				recButtonPress(row-1,collumn+1);
			
			if(row>0 &&(collumn>0) && mineGrid[row-1][collumn-1].getText()==" ")
				recButtonPress(row-1,collumn-1);
			
			if((collumn>0) && mineGrid[row][collumn-1].getText()==" " )
				recButtonPress(row,collumn-1);
			
			if((collumn+1<bSize) && mineGrid[row][collumn+1].getText()==" ")
				recButtonPress(row,collumn+1);
			
			if((row+1<bSize) &&(collumn>0) && mineGrid[row+1][collumn-1].getText()==" ")
				recButtonPress(row+1,collumn-1);	
			
			if((row+1<bSize)&& mineGrid[row+1][collumn].getText()==" ")
				recButtonPress(row+1,collumn);
			
			if( (row+1<bSize)&&(collumn+1<bSize) && mineGrid[row+1][collumn+1].getText()==" ")
				recButtonPress(row+1,collumn+1);
		}
	}
	/**
	 * sets colors and will add icons, adding icons under construction
	 */
	private void formatText()
	{
		int row=0;
		int collumn=0;
		int temp1=0;
		for (row=0;row<game.boardSize();row++)
		{
			for (collumn=0;collumn<game.boardSize();collumn++)
			{
				if (mineGrid[row][collumn].getText()=="M")
				{
					mineGrid[row][collumn].setForeground(Color.black);
					temp1=9;
				}
				else if(mineGrid[row][collumn].getText()==" ")
				{
					mineGrid[row][collumn].setBackground(Color.gray);
					temp1=9;
				}
				else if(mineGrid[row][collumn].getText()=="F")
				{
					mineGrid[row][collumn].setForeground(Color.cyan);
					temp1=9;
				}
				else
				{
					temp1=Integer.parseInt(mineGrid[row][collumn].getText());
					mineGrid[row][collumn].setBackground(Color.white);
				}
				if (temp1==0)
				{
					mineGrid[row][collumn].setForeground(Color.white);
				}
				else if (temp1==1)
				{
					mineGrid[row][collumn].setForeground(Color.blue);
				}
				else if (temp1==2)
				{
					mineGrid[row][collumn].setForeground(Color.green);
				}
				else if (temp1==3)
				{
					mineGrid[row][collumn].setForeground(Color.orange);
				}
				else if (temp1==4)
				{
					mineGrid[row][collumn].setForeground(Color.darkGray);
				}
				else if (temp1==5)
				{
					mineGrid[row][collumn].setForeground(Color.magenta);
				}
				else if (temp1==6)
				{
					mineGrid[row][collumn].setForeground(Color.pink);
				}
				else if (temp1==7)
				{
					mineGrid[row][collumn].setForeground(Color.red);
				}
				else if (temp1==8)
				{
					mineGrid[row][collumn].setForeground(Color.black);
				}
			}
		}
	}

	/**
	 * holds the panel that changes the difficulty and restarts a game if you hit the change difficulty button
	 * @return a panel containing the menu and a button to change the difficulty
	 */
	private JPanel diffMenu()
	{
		JPanel temp=new JPanel();
		final JComboBox<String>  c = new JComboBox<String>();
		JButton b=new JButton("Change Difficulty");
		String [] difficulties={"easy","medium","hard"};
		for (int i=0;i<difficulties.length;i++)
		{
			c.addItem(difficulties[i]);
		}
		temp.add(c);
		 b.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  game.changeDifficulty((String)(c.getSelectedItem()));
		  		rebuildGrid();
		      }
		 });
		temp.add(b);
		return temp;
	}

	
	private Game game;
	
	private JTextField mineLeft;
	private JButton [][] mineGrid;
	private JFrame frame;

}
