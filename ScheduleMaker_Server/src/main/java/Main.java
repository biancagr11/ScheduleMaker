import domain.Employee;
import domain.Job;
import repo.EmployeeDatabaseRepository;
import repo.EmployeeRepository;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        EmployeeDatabaseRepository employeeDatabaseRepository=new EmployeeDatabaseRepository("jdbc:mariadb://localhost:3306/ScheduleMaker","root","kalamaska23");
        //employeeDatabaseRepository.save(new Employee(1,"Carmen","Blanaru",Job.Asistent));
        employeeDatabaseRepository.findOne(2);
        System.out.println(employeeDatabaseRepository.findAll());

    }
}
