import Matrix.Matrix;

public class Main {
    public static void main(String[] args) {
        int[][] arr = new int[3][];
        for (int i = 0; i < 3; i++) {
            arr[i] = new int[2];
            for (int j = 0; j < 2; j++) {
                arr[i][j] = i + j;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }
}
