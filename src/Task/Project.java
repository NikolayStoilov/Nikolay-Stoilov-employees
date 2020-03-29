package Task;

import java.util.HashSet;
import java.util.Set;

/*
*
    Class Project provides an Object of type Project. It has an id and Set of Employees, which are
    belonging to the given Project.
* @version 3/29/2020
* @author Nikolay Stoilov
 */

public class Project {
    //The Project's id
    private long id;
    //The Project's set of employees. Set is being used because there cannot be two equal employees
    private Set<Employee> employeesSet;

    public Project (){
        this.employeesSet = new HashSet<>();
    }

    public Project(long id) {
        this.id = id;
        this.employeesSet = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public Set<Employee> getEmployeesSet() {
        return employeesSet;
    }

    public void setEmployeesSet(Set<Employee> employeesSet) {
        this.employeesSet = employeesSet;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", employeesSet=" + employeesSet +
                '}';
    }
}
