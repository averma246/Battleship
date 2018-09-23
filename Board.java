//=================================================================================================================
// Class Board()

// Author: Ana Verma


//Can be used to hold the attacks which a player has made on the enemy board, and also to hold
//the ships of the player him/herself 
//=================================================================================================================


public class Board{
	private int[][] board;
	private int test;

	//creates a 10 x 10 board
	public Board(){
		board = new int[10][10];
	}

	//the variable type contains the nature of the move:
	//type = 0 --> coordinate has water (~)
	//type = 1 --> coordinate has a miss (x)
	//type = 2 --> coordinate has a hit (represented on the attacks board) (O)
	//type = 3 --> coordinate has a hit (represented on the playerBoard board) (*)
	public void playerMove(Coordinate c, int type){
		int row = c.getRow();
		int col = c.getCol();

		board[row][col] = type;
	}


	//returns the value in the matrix -- the "types" key is commented above the playerMove method
	public int getValue(Coordinate coordinate){
		return board[coordinate.getRow()][coordinate.getCol()];
	}

	//prints out the board with the rows labeled as: A B C D E F G H I J 
	// and the columns labeled as: 0 1 2 3 4 5 6 7 8 9 
	public void print(){
		for(int i = -1; i < board.length; i++){
			for(int j = -1; j < board.length; j++){
				if(i == -1){
					if(j == -1){
						System.out.print("  ");
					}

					else{
						System.out.print((j) + " ");
					}
				}

				else if (j == -1 && i != -1){
					System.out.print((char)(i + (int)'A') + " ");
				}
				else{
					int coordType = board[i][j];

					if(coordType == 0){
						System.out.print("~ ");
					}
					else if(coordType == 1){
						System.out.print("X ");
					}
					else if(coordType == 2){
						System.out.print("O ");
					}
					else if(coordType == 3){
						System.out.print("* ");
					}

				}

			}
			System.out.println();
		}
	}
}
