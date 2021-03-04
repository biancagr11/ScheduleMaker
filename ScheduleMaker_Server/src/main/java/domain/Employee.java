package domain;

public class Employee extends Entity<Integer>{
    private String firstName;
    private String lastName;
    private Job job;

    public Employee(Integer id, String firstName, String lastName, Job job){
        super.setId(id);
        this.firstName=firstName;
        this.lastName=lastName;
        this.job=job;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Job getJob() {
        return job;
    }

    @Override
    public String toString() {
        String string="";
        if(job==Job.Asistent)
            string="As. ";
        else {
            if (job == Job.Medic)
                string = "Dr. ";
        }
        string=string+lastName+" "+firstName;
        return string;

    }
}
