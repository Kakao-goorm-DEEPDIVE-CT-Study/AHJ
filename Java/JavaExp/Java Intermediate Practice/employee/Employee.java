package java_intermediate.employee;

public class Employee {
    private String name;
    private int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public void displayInfo() {
        System.out.println("이름 : " + name + " 급여 : " + salary);
    }
}
