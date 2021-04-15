package Matrix;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private double[][] matrix;
    // Dimensions
    private int rows;
    private int columns;
    public Matrix(List<ArrayList<Double>> matrix) throws Exception {
        this.rows = matrix.size();
        this.columns = matrix.get(0).size();
        for (int i = 0; i < matrix.size(); i++){
            if (matrix.get(i).size() != this.columns){
                throw new Exception(String.format("Parameter is not matrix type"));
            }
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
    public int getRows(){
        return rows;
    }
    public int getColumns() {
        return columns;
    }

    public double valueAtIndex(int row, int column) throws Exception {
        if (row >= rows || row < 0 || column >= columns || column < 0)
            throw new Exception("Out of Range.");
        return matrix[row][column];
    }
    public Matrix Add(Matrix other) throws Exception {
        if (other.columns != columns || other.rows != rows) {
            throw new Exception("Can not add matrices with diferent dimensions");
        }
        double[][] newMatrix = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                newMatrix[i][j] = matrix[i][j] + other.matrix[i][j];
            }
        }
        return new Matrix(newMatrix);
    }
    public Matrix Subtract(Matrix other) throws Exception {
        if (other.columns != columns || other.rows != rows) {
            throw new Exception("Can not add matrices with diferent dimensions");
        }
        double[][] newMatrix = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                newMatrix[i][j] = matrix[i][j] - other.matrix[i][j];
            }
        }
        return new Matrix(newMatrix);
    }
    public Matrix Multiply(Matrix other) throws Exception {
        if (columns != other.rows) {
            throw new Exception("Multiplication is allowed only if the number of first's matrix columns equal to other's rows");
        }
        double[][] newMatrix = new double[rows][other.columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

            }
        }
        return new Matrix(newMatrix);
    }
    public void CheckWithSymbolab(){
        String link_begin = "https://www.symbolab.com/solver/step-by-step/%5Cbegin%7Bpmatrix%7D";
        String link_end = "%5Cend%7Bpmatrix%7D";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++){
                link_begin += matrix[i][j];
                if (j != columns - 1) {
                    link_begin += "%26";
                }
            }
            if (i != rows - 1) {
                link_begin += "%5C%5C";
            }
        }
        String url = link_begin + link_end;

        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
