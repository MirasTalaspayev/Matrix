import Matrix.Matrix;

public class Main {
    public static void main(String[] args) throws Exception {
        double[][] arr = new double[2][2];
        arr[0][0] = 1;
        arr[0][1] = 3;
        arr[1][0] = 3;
        arr[1][1] = 10;
        Matrix m = new Matrix(arr);
        System.out.println(m);
        System.out.println(m.UpperTriangleMatrix());
        System.out.println(m.Determinant());
    }
}
