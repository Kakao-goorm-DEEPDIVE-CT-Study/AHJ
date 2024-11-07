package java_intermediate.diagram;

public class DiagramArray {
    public static void main(String[] args) {

        Diagram[] diagrams = new Diagram[3];

        diagrams[0] = new Triangle(10,10);
        diagrams[1] = new Circle(10);
        diagrams[2] = new Rectangle(10,10);

        for(Diagram diagram : diagrams) {
            diagram.calculateArea();
            diagram.display();
        }
    }

}
