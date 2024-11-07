package java_intermediate.employee;

public class Employee {
    public String name;
    public int salary;
    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
    public void displayInfo() {
        System.out.println("이름 : " + name + " 급여 : " + salary);
    }
}
