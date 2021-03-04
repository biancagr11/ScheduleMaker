package repo;

import domain.Employee;
import domain.Job;

import java.util.List;

public class EmployeeRepository extends AbstractFileRepository<Integer, Employee> {

    public EmployeeRepository(String fileName) {
        super(fileName);
    }


    @Override
    public Employee save(Employee entity) {
        Integer id=super.entities.size();
        entity.setId(id);
        return super.save(entity);
    }

    @Override
    public Employee extractEntity(List<String> attributes) {
        Integer id=Integer.parseInt(attributes.get(0));
        String firstName=attributes.get(1);
        String lastName=attributes.get(2);
        Job job=Job.valueOf(attributes.get(3));
        return new Employee(id,firstName,lastName,job);
    }

    @Override
    protected String createEntityAsString(Employee entity) {
        return entity.getId()+";"+entity.getFirstName()+";"+entity.getLastName()+";"+entity.getJob();
    }
}
