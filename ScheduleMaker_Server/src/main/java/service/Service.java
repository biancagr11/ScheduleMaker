package service;

import domain.*;
import observer.Observable;
import observer.Observer;
import repo.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Service implements Observable {
    private EmployeeRepository employeeRepo;
    private List<Employee> involvedEmployees=new ArrayList<>();
    private List<Employee> avoidedEmployees=new ArrayList<>();


    public Service(EmployeeRepository employeeRepo){
        this.employeeRepo =employeeRepo;

    }

    @Override
    public void addObserver(Observer e){
        observers.add(e);
    }
    @Override
    public void removeObserver(Observer e){
    }

    @Override
    public void notifyObservers(){
        observers.forEach(x->x.update());
    }

    public List<Employee> findAllEmployes(){
        List<Employee> list=new ArrayList<>();
        employeeRepo.findAll().forEach(list::add);
        return list;

    }

    public Employee deleteEmploye(Integer id){
        Employee employee=employeeRepo.delete(id);
        notifyObservers();
        return employee;
    }

    public Employee addEmployee(String firstName, String lastName, String job){
        Employee employee=new Employee(0,firstName,lastName,Job.valueOf(job));
        Employee response= employeeRepo.save(employee);
        notifyObservers();
        return response;
    }
    public List<Employee> allDoctors(){
        avoidedEmployees.clear();
        involvedEmployees=findAllEmployes().stream().filter(x->x.getJob()==Job.Medic).collect(Collectors.toList());
        return involvedEmployees;
    }
    public List<Employee> allNurses(){
        avoidedEmployees.clear();
        involvedEmployees=findAllEmployes().stream().filter(x->x.getJob()==Job.Asistent).collect(Collectors.toList());
        return involvedEmployees;
    }
    public List<Employee> allOrderlies(){
        avoidedEmployees.clear();
        involvedEmployees=findAllEmployes().stream().filter(x->x.getJob()==Job.Infirmier).collect(Collectors.toList());
        return involvedEmployees;
    }
    public void avoidEmployee(Employee employee){
        involvedEmployees.remove(employee);
        avoidedEmployees.add(employee);
    }
    public boolean involveEmployee(){
        if(avoidedEmployees.size()==0){
            return false;
        }
        involvedEmployees.add(avoidedEmployees.get(avoidedEmployees.size()-1));
        avoidedEmployees.remove(avoidedEmployees.size()-1);
        return true;
    }
    public List<Employee> allInvolvedEmployees(){
        return involvedEmployees;
    }
}
