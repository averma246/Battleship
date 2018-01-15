//==============================================================================================================================

//Class Game()

//Author: Ana Verma

//The class where the game is actually played 
//==============================================================================================================================
import java.util.Scanner;
import java.lang.Math;
public class Game{
	//turns is used to figure out which player's turn it is to attack
	private static int turns;

	public static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args){
		System.out.println("\n\nWelcome to a game of Battleship!");
		//This is where the rules will be printed out

		Game newGame = new Game();

		Player player1 = new Player();
		Player player2 = new Player();

		System.out.println("Here is what your board and attacks look like: \n");
		System.out.println("YOUR BOARD:");
		player1.printPlayerBoard();

		System.out.println("\n\nYOUR ATTACKS:");
		player1.printAttacks();

		System.out.println("\n\nLet's add some ships!");

		addShips(player1);

	}

	public static void addShips(Player player){


//==============================================DEBUG======================================================//
		System.out.println("\nDEBUG: ENTERING addShips");
//==============================================DEBUG======================================================//


		int shipCount = 0;
		//only five ships can be added 
		while(shipCount < 5){
			//which ships has the player not added yet
			String[] shipsRemainingToBeAdded = shipsRemainingToBeAdded(player);
			int indexOfShip = 0;

//==============================================DEBUG======================================================//
			System.out.println("\nDEBUG: indexOfShip = " + indexOfShip);
//==============================================DEBUG======================================================//

			//this boolean is used to check whether the player has entered a valid number indicating the 
			//the type of ship s/he wants to add
			boolean validNumber = false;

			while(!validNumber){

//==============================================DEBUG======================================================//
System.out.println("\nDEBUG: Enterning validNumber while loop.");
//==============================================DEBUG======================================================//

				System.out.println("Enter the number of the ship you would like to add: ");
				
				for(int i = 0; i < shipsRemainingToBeAdded.length; i++){
					System.out.println((i+1) + " " + shipsRemainingToBeAdded[i]);
				}

				indexOfShip = keyboard.nextInt() - 1;

//==============================================DEBUG======================================================//
			System.out.println("\nDEBUG: indexOfShip = " + indexOfShip);
//==============================================DEBUG======================================================//
				if(indexOfShip < 0 || indexOfShip >= shipsRemainingToBeAdded.length){
					System.out.println("Please enter a valid number:");
				}

				else validNumber = true;
			}


//==============================================DEBUG======================================================//
System.out.println("\nDEBUG: validNumber = " + validNumber);
//==============================================DEBUG======================================================//


			//validCoordinate is used to see whether the coordinate that the player entered is valid 
			boolean validCoordinate = false;
			String coordinate1 = "";
			String coordinate2 = "";

			while(!validCoordinate){
				System.out.println("\nEnter the start coordinate of the ship: ");

				coordinate1 = keyboard.next();

				System.out.println("\nEnter the end coordinate of the ship: ");

				coordinate2 = keyboard.next();

				validCoordinate = valid(player,coordinate1,coordinate2,shipsRemainingToBeAdded[indexOfShip]);

//==============================================DEBUG======================================================//
System.out.println("\nDEBUG: validCoordinate = " + validCoordinate);
//==============================================DEBUG======================================================//

			}
	
			String start = "";
			String end = "";

			//the player may have entered the coordinates in an incorrect order, so this is measures against that 
			//possiblity 
			if((coordinate1.charAt(0) < coordinate2.charAt(0)) || (coordinate1.charAt(1) < coordinate2.charAt(1))){
				start = coordinate1;
				end = coordinate2;
			}
			else{
				start = coordinate2;
				end = coordinate1;
			}

			

			System.out.println("Ship is being added from " + start + " to " + end);
			player.addShip(shipsRemainingToBeAdded[indexOfShip], new Coordinate(start), new Coordinate(end));
			shipCount++;

		}
	}

	//checks which ships have not been added to the board yet
	public static String[] shipsRemainingToBeAdded(Player player){
		int shipsRemaining = 0;

		for(int i = 0; i < player.getShips().length; i++){
			if(!player.getShips()[i].isAdded()){
				shipsRemaining++;
			}
		}

		String[] shipsRemainingToBeAdded = new String[shipsRemaining];

		int j = 0;
		for(int i = 0; i < player.getShips().length && j < shipsRemainingToBeAdded.length; i++){
			if(!player.getShips()[i].isAdded()){
				shipsRemainingToBeAdded[j] = player.getShips()[i].getName();
				j++;
			}
		}

		return shipsRemainingToBeAdded;
	}

	//checks whether the coordinates entered are valid 
	public static boolean valid(Player player,String coordinate1,String coordinate2, String shipName){

//==============================================DEBUG======================================================//
System.out.println("DEBUG: Entering valid");
//==============================================DEBUG======================================================//

		boolean valid = true;
		int length = shipLength(shipName);

//==============================================DEBUG======================================================//
System.out.println("\n length = " + length);
//==============================================DEBUG======================================================//

		char rowOne = coordinate1.charAt(0);
		char rowTwo = coordinate2.charAt(0);

		char colOne = coordinate1.charAt(1);
		char colTwo = coordinate2.charAt(1);


		if((rowOne < 'A') || (rowOne > 'J')){
			System.out.println("Please check the letter of the first coordinate.");
			valid = false;
		}
		if((rowTwo < 'A') || (rowTwo > 'J')){
			System.out.println("Please check the letter of the second coordinate.");
			valid = false;
		}
		if((colOne < '0') || (colOne > '9')){
			System.out.println("Please check the first digit of the first coordinate.");
			valid = false;
		}
		if((colTwo < '0') || (colTwo > '9')){
			System.out.println("Please check the first digit of the second coordinate.");
			valid = false;
		}

		if((rowOne == rowTwo) && (colOne == colTwo)){
			valid = false;
		}

		else if((rowOne != rowTwo) && (colOne != colTwo)){
			System.out.println("The ship cannot be placed diagonally.");
			valid = false;
		}

		else if(Math.abs(rowOne - rowTwo) != 0){
			if((Math.abs(rowOne - rowTwo) + 1) != length){
				valid = false;
				System.out.println("The ship is not the correct length.");
			}
		}

		else if(Math.abs(colOne - colTwo) != 0){
			if((Math.abs(colOne - colTwo) + 1) != length){
				valid = false;
				System.out.println("The ship is not the correct length.");				
			}
		}

		if(valid){
			if(rowOne == rowTwo){
				for(int i = colOne; i < colTwo; i++){
					if(!player.validMove(player.getPlayerBoard(),new Coordinate("" + rowOne + i),2))
						return false;
				}
			}
			else{
				for(int i = rowOne; i < rowTwo; i++){
					if(!player.validMove(player.getPlayerBoard(),new Coordinate("" + colOne + i),2))
						return false;
				}
			}
		}
		/*if(player.valiMove(player.getPlayerBoard(), coordinate1, 2)){
			valid = (player.shipPresent(player.getShips(),new Coordinate(coordinate1),"check") && player.shipPresent(player.getShips(),new Coordinate(coordinate2),"check"));
		}*/

		return true;
	}

	//returns the length of a ship depending on the name 
	public static int shipLength(String shipName){
		if(shipName == "carrier")
			return 5;
		else if(shipName == "battleship")
			return 4;
		else if(shipName.equals("cruiser") || shipName.equals("submarine"))
			return 3;
		else if(shipName == "destroyer")
			return 2;
		else return 0;
	}



	//will be used at the end of each turn to check whether any player has 
	//sunk all the ships of the opponent 
	public static boolean checkWinner(Player player){
		Ship[] ships = player.getShips();

		for(int i = 0; i < ships.length; i++){
			if(!ships[i].sunk()){
				return false;
			}
		}
		return true;
	}
}


	