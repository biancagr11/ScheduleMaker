package repo;
import domain.Employee;
import domain.Entity;

import java.io.*;
import java.util.Arrays;
import java.util.List;

//EmployeeDatabaseRepositoryInterface
public abstract class EmployeeDatabaseRepositoryInterface<ID, E extends Entity<ID>> extends InMemoryRepository<ID,E> {

    public EmployeeDatabaseRepositoryInterface(){}

}


