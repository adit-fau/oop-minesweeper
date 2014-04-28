/**Class that holds state of a node in the grid
 * 
 */
package mineSweeper;

public class MineButton {
	/**
	 * Default constructor, sets all states to false
	 */
	public MineButton()
	{
		isFlagged=false;
		isMine=false;
		isActive=false;
		isClicked=false;
	}
	/**
	 * getter for isFlagged
	 * @return state of isFlagged
	 */
	public Boolean getFlagged()
	{
		return isFlagged;
	}
	/**
	 * getter for isMine
	 * @return state of isMine
	 */
	public Boolean getMine()
	{
		return isMine;
	}
	/**
	 * getter for isActive
	 * @return state of isActive
	 */
	public Boolean getActive()
	{
		return isActive;
	}
	/**
	 * setter for isFlagged
	 * @param newState true or false
	 */
	public void changeFlag(Boolean newState)
	{
		isFlagged=newState;
	}
	/**
	 * getter for isClicked
	 * @return state of isClicked
	 */
	public Boolean getClicked()
	{
		return isClicked;
	}
	
	/**
	 * setter for isClicked
	 * @param newState true or false
	 */
	public void changeClicked(Boolean newState)
	{
		isClicked=newState;
	}
	/**
	 * setter for isActive
	 * @param newState true or false
	 */
	public void changeActive(Boolean newState)
	{
		isActive=newState;
	}
	/**
	 * setter for isMine
	 * @param newState true or false
	 */
	public void changeMine(Boolean newState)
	{
		isMine=newState;
	}
	
	private Boolean isFlagged;
	private Boolean isMine;
	private Boolean isActive;
	private Boolean isClicked;
}
