
public class Move implements Comparable<Move> {
	Integer row;
	Integer col;
	char colChar;
	Integer numCaps; 
	
	
	public Integer getNumCaps() {
		return numCaps;
	}

	public void setNumCaps(Integer numCaps) {
		this.numCaps = numCaps;
	}

	public Move(int row, char colChar) {

		this.row = row;
		// Covert from char to int
		switch (colChar) {

		case 'a':
			this.col = 0;
			break;
		case 'b':
			this.col = 1;
			break;
		case 'c':
			this.col = 2;
			break;
		case 'd':
			this.col = 3;
			break;
		case 'e':
			this.col = 4;
			break;
		case 'f':
			this.col = 5;
			break;
		case 'g':
			this.col = 6;
			break;
		case 'h':
			this.col = 7;
			break;

		}

	}

	@Override
	public String toString() {
		String s = "";

		s = "( Row:"+this.row + ", Col:"+this.col+" )";
		
		return s;
	}

	public Move(int row, int col) {

		this.row = row;
		this.col = col;
	}

	public Move() {

	}

	public int getRow() {
		return row;
	}

	public char getColChar() {
		return colChar;
	}

	public void setColChar(char colChar) {
		this.colChar = colChar;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;

	}

	@Override
	public int compareTo(Move o) {
		if (this.numCaps>o.numCaps) {
			return 1;
		}else if (this.numCaps<o.numCaps) {
			return -1;
			
		}else {
		
		return 0;}
	}

}
