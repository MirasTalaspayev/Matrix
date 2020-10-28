package Matrix;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private double[][] matrix;
    // Dimensions
    private int rows;
    private int columns;
    public Matrix(List<ArrayList<Double>> matrix){
        this.rows = matrix.size();
        this.columns = matrix.get(0).size();
        for (int i = 0; i < matrix.size(); i++){
            for (int j = 0; j < matrix.get(0).size(); j++){
                this.matrix[i][j] = matrix.get(i).get(j);
            }
        }
    }
    public Matrix(double[][] matrix) {
        this.rows = matrix.length;
        this.columns = matrix[0].length;
        this.matrix = new double[rows][columns];


        for (int i = 0; i < rows; i++){
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, columns);
        }
    }
    public Matrix(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        matrix = new double[rows][columns];
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < rows; i++) {
            result += "|";
            for (int j = 0; j < columns - 1; j++) {
                result += matrix[i][j];
                result += " ";
            }
            result += matrix[i][columns - 1];
            result += "|\n";
        }
        return result;
    }
    public Matrix UpperTriangleMatrix() {
        return new Matrix(UpperArrayTriangle());
    }
    private double[][] UpperArrayTriangle() {
        double[][] newMatrix = CopyMatrix();

        int row = 0;
        int column = 0;
        while (row < rows && column < columns) {
            if (newMatrix[row][column] == 0) {
                for (int i = row + 1; i < rows; i++) {
                    if (newMatrix[i][column] != 0) {
                        ExchangeRows(i, row);
                    }
                }
            }
            if (newMatrix[row][column] == 0) {
                column++;
                continue;
            }
            for (int i = row + 1; i < rows; i++) {
                double factor = newMatrix[i][column] / newMatrix[row][column];

                for (int j = column; j < columns; j++) {
                    newMatrix[i][j] = newMatrix[i][j] - newMatrix[row][j] * factor;
                }
            }
            row++;
            column++;
        }
        return newMatrix;
    }
    public Matrix RowReduce(){
        double[][] newMatrix = UpperArrayTriangle();

        return new Matrix(newMatrix);
    }
    public void ExchangeRows(int rowIndex1, int rowIndex2) {
        double[] temp = new double[columns];
        for (int i = 0; i < columns; i++) {
            temp[i] = matrix[rowIndex1][i];
        }
        matrix[rowIndex1] = matrix[rowIndex2];
        matrix[rowIndex2] = temp;
    }
    public double[][] CopyMatrix() {
        double[][] newMatrix = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++){
                newMatrix[i][j] = matrix[i][j];
            }
        }
        return newMatrix;
    }
    // It is implemented by multiplying diagonal entries (only invertible matrix)
    public double Determinant() throws Exception {
        if (rows != columns) {
            throw new Exception("Determinants are only for Invertible Matrix"); //  Specific Exception could be created ??
        }
        double[][] upperTriangleMatrix = UpperArrayTriangle();
        double determinant = 1;
        for (int i = 0; i < rows; i++) {
            determinant *= upperTriangleMatrix[i][i];
        }
        return determinant;
    }
}
