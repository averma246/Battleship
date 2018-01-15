//==============================================================================================================================================

// Class Coordinate()

//Author: Ana Verma

//Holds the row and column of a certain attack or ship placement 

//==============================================================================================================================================

public class Coordinate{
	private int row;
	private int col;

	//the coordinate is passed in this format: "letter number" -- this constructor changes the format of the user input into usable cooridnates 
	public Coordinate(String coordinate){ 

		row = (int)(coordinate.charAt(0) - 'A');
		col = (int)(coordinate.charAt(1) - '0');

	}

	//a coordinates row and col can be passed into the Coordinate object
	public Coordinate(int r, int c){
		row = r;
		col = r;
	}

	//getter methods 
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	//checks whether a coordinate is equal to the current coordinate
	public boolean equals(Coordinate b){
		return ((row == b.getRow()) && (col == b.getCol()));
	}

	public String toString(){
		return("(" + row + ", " + col + ")");
	}


}