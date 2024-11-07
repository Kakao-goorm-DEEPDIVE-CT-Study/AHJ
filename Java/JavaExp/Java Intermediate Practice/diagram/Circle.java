package java_intermediate.diagram;


public class Circle extends Diagram{

    int radius;

    public Circle(int radius) {
        this.diagramName = "circle";
        this.radius = radius;
    }

    @Override
    public void calculateArea(){
        this.area = this.radius * this.radius * 3.14;
    }

    @Override
    public void display() {
        super.display();
    }
}
