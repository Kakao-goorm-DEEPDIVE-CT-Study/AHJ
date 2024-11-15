package java_intermediate.diagraminterface;

public class DiagramMain {
    public static void main(String[] args) {

        DiagramInterface[] diagrams = new DiagramInterface[3];
        diagrams[0] = new Circle(10);
        diagrams[1] = new Rectangle(10,20);
        diagrams[2] = new Triangle(10,20);

        for(DiagramInterface diagram : diagrams) {
            diagram.calculateArea();
            diagram.display();
        }
    }
}
