package java_intermediate;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

class Rectangle {
    private int height;
    private int width;

    public Rectangle(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int calculateArea() {
        return height * width;
    }

    public int calculatePerimeter() {
        return 2 * (height + width);
    }
}

public class Rect {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        java_intermediate.Rectangle r = new java_intermediate.Rectangle(10, 20);

        bw.write("넓이 : " + r.calculateArea());
        bw.newLine();
        bw.write("둘레 : " + r.calculatePerimeter());
        bw.flush();
        bw.close();
    }

}
