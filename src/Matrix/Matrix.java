package Matrix;

import javax.management.MBeanAttributeInfo;
import javax.print.attribute.standard.Copies;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Matrix {
    private ArrayList<ArrayList<Integer>> matrix;
    // Dimensions
    private int rows;
    private int columns;
    public Matrix(){
        matrix = new ArrayList<>();
    }
    public Matrix(List<ArrayList<Integer>> matrix){
        this.rows = matrix.size();
        this.columns = matrix.get(0).size();
        for (int i = 0; i < matrix.size(); i++){
            for (int j = 0; j < matrix.get(0).size(); j++){
                this.matrix.get(i).set(j, matrix.get(i).get(j));
            }
        }
    }
    public Matrix(int[][] matrix) {
        this.rows = matrix.length;
        this.columns = matrix[0].length;
        this.matrix = new ArrayList<>(rows);
        for (ArrayList<Integer> row : this.matrix){
            row = new ArrayList<>(columns);
        }


        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                this.matrix.get(i).set(j, matrix[i][j]);
            }
        }
    }
    public Matrix(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        matrix = new ArrayList<>(rows);
        for (ArrayList<Integer> row : matrix){
            row = new ArrayList<>(columns);
        }
    }
    public void AddRow(){
        this.matrix.add(new ArrayList<>(columns));
    }
    public void AddRow(ArrayList<Integer> row) throws MatrixDimensionsException {
        if (row.size() != columns)
            throw new MatrixDimensionsException();
        ArrayList<Integer> tempRow = new ArrayList<>();
        for (int i = 0; i < this.columns; i++){
            tempRow.set(i, row.get(i));
        }
        this.matrix.add(tempRow);
    }
    public void AddColumn() {

    }
    public String toString() {
        String result = "";
        for (ArrayList<Integer> row : matrix) {
            result += row.toString();
            result += "\n";
        }
        return result;
    }
}
