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
            String link_begin = "https://www.symbolab.com/solver/step-by-step/%5Cbegin%7Bpmatrix%7D";
            String link_end = "%5Cend%7Bpmatrix%7D";
            for (int i = 0; i < m.getRows(); i++) {
                for (int j = 0; j < m.getColumns(); j++){
                    link_begin += m.valueAtIndex(i, j);
                    if (j != m.getColumns() - 1) {
                        link_begin += "%26";
                    }
                }
                if (i != m.getRows() - 1) {
                    link_begin += "%5C%5C";
                }
            }
            String link = link_begin + link_end;

            String url = link;

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
}
