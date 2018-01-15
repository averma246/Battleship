//==============================================================================================================================

// Class Player()

//Author: Ana Verma

//Player object -- represents one of the two players playing the game 
//Contains the enemy of the player, the board on which the player has his/her ships, the board which keeps tracks of the attacks 
//that have been made, and an array of all the ships that the player has (which contains their locations)
//==============================================================================================================================
public class Player{
	private Player enemy;
	private Board playerBoard;
	private Board attacks;
	private Ship[] ships;

	//constructor method
	//each player must have only five ships, and each ship must be of a certain length (depending on the type of ship)
	public Player(){
		playerBoard = new Board();
		attacks = new Board();
		ships = new Ship[5];

		ships[0] = new Ship("carrier",5);
		ships[1] = new Ship("battleship",4);
		ships[2] = new Ship("cruiser",3);
		ships[3] = new Ship("submarine",3);
		ships[4] = new Ship("destroyer",2);

	}

	//getter methods
	public Ship[] getShips(){
		return ships;
	}

	public Board getPlayerBoard(){
		return playerBoard;
	}

	public Board getAttacks(){
		return attacks;
	}

	//sets the opponent of the player
	public void setEnemy(Player enemy){
		this.enemy = enemy;
	}


	//method to add a ship onto the board -- arguments:
	//shipName -- the type of ship that is being added
	//start -- the coordinate of one end of the ship
	//end -- the coordinate of the other end of the ship
	public void addShip(String shipName, Coordinate start, Coordinate end){
//==============================================DEBUG======================================================//
System.out.println("\nDEBUG: Entering addShip.");
System.out.println("\nDEBUG: ShipName = " + shipName);
//==============================================DEBUG======================================================//

		if(shipName.equals("carrier")){
			ships[0].setLocation(start,end,playerBoard);
			ships[0].setAdded();
		}

		else if(shipName.equals("battleship")){
			ships[1].setLocation(start,end,playerBoard);
			ships[1].setAdded();
		}

		else if(shipName.equals("cruiser")){
			ships[2].setLocation(start, end,playerBoard);
			ships[2].setAdded();
		}

		else if(shipName.equals("submarine")){
			ships[3].setLocation(start, end,playerBoard);
			ships[3].setAdded();
		}

		else if(shipName.equals("destoryer")){
			ships[4].setLocation(start, end,playerBoard);
			ships[4].setAdded();
		}

		playerBoard.print();
	}

	//before the game begins, the player is allowed to move his/her ships
	public void changeShip(String shipName, Coordinate start, Coordinate end){
		addShip(shipName,start,end);
	}

	//checks whether a ship is already present at a certain coordinate -- can be used to check the enemy board
	//while attacking, or to check while changing ship locations on the player's own board 
	//PARAMETERS:
	//ships -- the ships on the board you are checking (could be the ships of the opponent or of the player)
	//attack -- the coordinate the player is attacking
	//reason -- why is this method being called -- if it is for attack, then if a ship is found, the number of hits
	//of the player who's ship has been hit is incremented 
	public boolean shipPresent(Ship[] ships, Coordinate attack, String reason){
		int indexOfAttackedShip = 0;
		boolean shipPresent = false;

		for(int i = 0; i < ships.length; i++){
			if(ships[i].hasCoordinate(attack)){
				indexOfAttackedShip = i;
				shipPresent = true;
			}
		}

		if(reason.equals("attack")){
			ships[indexOfAttackedShip].addToHits();
		}

		return shipPresent;
	}

	//type = type of place you are trying to avoid 
	public boolean validMove(Board board, Coordinate coordinate, int type){
		return (!(board.getValue(coordinate) == type));
	}

	//make changes to the board from outside, without direct access to the board
	public void boardChange(Coordinate attack, int type){
		playerBoard.playerMove(attack, type);
	}

	//method which allows the player to attack the enemy board
	public void attack(Coordinate attackCoord){
		Ship[] enemyShips = enemy.getShips();

		if(shipPresent(enemyShips, attackCoord, "attack")){
			System.out.println("Hit!");

			attacks.playerMove(attackCoord, 2);
			enemy.boardChange(attackCoord,3);
		}

		else{
			System.out.println("Miss.");

			attacks.playerMove(attackCoord, 1);
			enemy.boardChange(attackCoord,1);
		}

		playerBoard.print();
		attacks.print();
	}

	//prints the player's own board
	public void printPlayerBoard(){
		playerBoard.print();
	}

	//prints the attacks the player has made and their status 
	//(whether or not the attacks were hits or misses)
	public void printAttacks(){
		attacks.print();
	}
}