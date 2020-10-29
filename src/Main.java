import Matrix.Matrix;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

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

        System.out.print("Do you want to check the answer with symbolab.com? (yes/no): ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        answer = answer.toLowerCase();

        if (answer.equals("yes")) {
            m.CheckWithSymbolab();
        }
    }
}
