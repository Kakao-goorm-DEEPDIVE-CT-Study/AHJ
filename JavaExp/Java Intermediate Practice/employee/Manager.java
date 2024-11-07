package java_intermediate.employee;

public class Manager extends Employee {
    public String position;

    public Manager(String name, int salary, String position){
        super(name, salary);
        this.position = position;
    }
    @Override
    public void displayInfo() {
        System.out.println("이름 : " + name + " 급여 : " + salary + " 직책 : " + position);
    }
}
