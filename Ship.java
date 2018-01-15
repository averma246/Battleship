//==============================================================================================================================

// Class Ship()

// Author: Ana Verma

//Each ship that a user places on the board is a ship object. This object contains the length of the ship, the coordinates which
//the ship occupies, the number of times it has been hit, and a boolean indicating whether or not it has been added to the board

//At the moment contains a lot of Debugging code, but once the bugs have been fixed, the debugging statements will be removed
//==============================================================================================================================

public class Ship{
	//name of the ship
	private String shipName;
	//length of the ship can be checked to see whether the correct length ship is being added
	private int length;
	//numberHits can be used to check whether the ship has been sunk, because if numberHits == length, the ship has been sunk
	private int numberHits;
	//private boolean sunk;
	//false if the ship hasn't been added yet, true if it has been
	private boolean added;
	//an array of all the coordinates that this ship occupies
	private Coordinate[] location;

	//constructor method for the Ship class
	public Ship(String shipName, int length){
		this.shipName = shipName;
		this.length = length;
		location = new Coordinate[length];
		numberHits = 0;
		added = false;
	}

	//returns the name of the ship
	public String getName(){
		return shipName;
	}

	//places the ship on the board 
	public void setLocation(Coordinate start, Coordinate end, Board playerBoard){
//==============================================DEBUG======================================================//
System.out.println("\nDEBUG: Entering setLocation.");
//==============================================DEBUG======================================================//

		int col = start.getCol();
		int row = start.getRow();
		
//==============================================DEBUG======================================================//
System.out.println("\nDEBUG: col = " + col + " row = " + row);
//==============================================DEBUG======================================================//

		if(row == end.getRow()){
			for(int i  = 0; i < length; i++){
				location[i] = new Coordinate(row,col);

//==============================================DEBUG======================================================//
System.out.println("\nDEBUG: coordinate = " + location[i] + "    Col = " + col);
//==============================================DEBUG======================================================//

				playerBoard.playerMove(location[i], 2);
				col++;

			}
		}

		else{
			for(int i = 0; i < length; i++){
				location[i] = new Coordinate(row,col);

//==============================================DEBUG======================================================//
System.out.println("\nDEBUG: coordinate = " + location[i]);
//==============================================DEBUG======================================================//

				playerBoard.playerMove(location[i],2);
				row++;
			}
		}
	}

	//called outside of this class to check whether this ship has been placed on the board -- false if it hasn't been, and true if it has been
	public boolean isAdded(){
		return added;
	}

	//this method is called when an instance of a ship is added to the board 
	public void setAdded(){
		added = true;
	}

	//if the number of hits equals the number of hits, then the ship has been sunk 
	public boolean sunk(){
		return (length == numberHits);
	}

	//checks whether this ship is located at the coordinate that is passed in 
	public boolean hasCoordinate(Coordinate a){
		for(int i = 0; i < length; i++){
			if(location[i].equals(a)){
				return true;
			}
		}
		return false;
	}

	//if this ship has been hit, the number of hits is incremented 
	public void addToHits(){
		numberHits++;
	}
}