package java_intermediate.employee;

public class Manager extends Employee {
    private String position;

    public Manager(String name, int salary, String position){
        super(name, salary);
        this.position = position;
    }
    @Override
    public void displayInfo() {
        System.out.println("이름 : " + getName() + " 급여 : " + getSalary() + " 직책 : " + position);
    }
}
