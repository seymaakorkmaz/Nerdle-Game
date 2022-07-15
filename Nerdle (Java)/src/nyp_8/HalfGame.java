package nyp_8;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JToggleButton;

public class HalfGame implements Serializable{
	//Yarým kalan oyunun bilgileri tutulur.
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int row;
	private int column;
	private JToggleButton[][] matrix;
	private ArrayList<String> equation;
	private int time;
	
	public HalfGame(int x, int y) {
		this.setRow(row);
		this.setColumn(column);
		 setMatrix(new JToggleButton[x][y]);
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public JToggleButton[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(JToggleButton[][] matrix) {
		this.matrix = matrix;
	}

	public ArrayList<String> getEquation() {
		return equation;
	}

	public void setEquation(ArrayList<String> equation) {
		this.equation = equation;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	
	
	
	
}
