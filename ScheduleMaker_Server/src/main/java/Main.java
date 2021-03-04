import domain.Employee;
import domain.Job;
import repo.EmployeeRepository;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Employee employee=new Employee(99,"Ana","Maria", Job.Asistent);
        EmployeeRepository employeeRepository=new EmployeeRepository("employees.csv");
        System.out.println(employeeRepository.findAll());
    }
}
