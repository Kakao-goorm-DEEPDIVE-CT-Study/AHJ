package java_intermediate.employee;

public class Company {
    public static void main(String[] args) {
        Employee employee = new Employee("Hong Gil Dong",1000);
        Manager manager = new Manager("Kim Chul Soo",10000,"CEO");

        employee.displayInfo();
        manager.displayInfo();
    }
}
