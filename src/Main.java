import Task.Employee;
import Task.Project;

import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/*
*
    Class Main is used as starting point of the Application. The main method is used to instantiate
    Objects, load data from TextFile into the objects and fulfill different types of functionality
* @version 3/29/2020
* @author Nikolay Stoilov
 */

public class Main {
    /*
    This boolean method makes sure that we don't have similar projects added to the main set of projects.
    It uses Set of Projects as parameter1 and the id of the currently checked Project.
     */
    public static boolean checkIfProjectAlreadyExists(Set<Project> projects, long id) {
        boolean isIn = false;
        for (Project project : projects) {
            if (project.getId() == id) {
                isIn = true;
                break;
            }
        }
        return isIn;
    }
    /*
    Method isOverlaping returns true if a pair of employees have worked together and false
    if the pair hasn't worked together on a same project.
     */
    public static boolean isOverlaping(Date d1, Date d2, Date d3, Date d4) {
        return (d1.before(d4)) && (d2.after(d3));
    }
    /* CalculateStartDate is used to give us the first Date that will be used for calculating the
        common working time of two employees if it exists. The method takes two Employees
        as parameters.
     */
    public static Date calculateStartDate(Employee employee1, Employee employee2){
        if(employee1.getDateFrom().compareTo(employee2.getDateFrom())>0) {
            return employee1.getDateFrom();
        }else {
            return employee2.getDateFrom();
        }
    }
    /*
       CalculateEndDate is used to give us the second Date that will be used for calculating the
       common working time of two employees if it exists. The method takes two Employees
        as parameters.
     */
    public static Date calculateEndDate(Employee employee1, Employee employee2){
        if(employee1.getDateTo().compareTo(employee2.getDateTo())>0) {
            return employee2.getDateTo();
        }else {
            return employee1.getDateTo();
        }
    }
    /*
    CalculateOverlapPeriod is used to give us the overlap period between two employees in days so we can further compare which is the longest together
    working pair of employees. CalculationOfOverlapPeriodInDays is used in the return statement.
     */
    public static long calculateOverlapPeriod(Employee employee1, Employee employee2) {
        return calculationOfOverlapPeriodInDays(calculateStartDate(employee1,employee2),calculateEndDate(employee1,employee2));
    }

    /*
    Simly returns the overlap period between two dates in DAYS.
     */
    public static long calculationOfOverlapPeriodInDays(Date d1, Date d2) {
       return Math.abs(TimeUnit.DAYS.convert(d1.getTime()-d2.getTime(),TimeUnit.MILLISECONDS));
   }
   /*
    Void method used for printing the final result which is the longest together working pair of employees
    that has worked on a given project. The method has 3 for-each loops and 3 local variables maxWorkingTime,
    emp1 and emp2. If a project indeed has pair of employees, the method prints the pair to the console.
    The method iterates through the set of projects and comapres every two employees whether they have
    overlaping period between them and calculates the duration if such period exists.
    */
    public static void printPairOfEmployeesWithLongestWorkingTime(Set<Project>projects) {
        for (Project project : projects) {
            long maxWorkingTime = 0;
            Employee emp1 = new Employee();
            Employee emp2 = new Employee();
            for (Employee employee1 : project.getEmployeesSet()) {
                for (Employee employee2 : project.getEmployeesSet()) {
                    if (employee1.getId() != employee2.getId()) {
                        if (isOverlaping(employee1.getDateFrom(), employee1.getDateTo(), employee2.getDateFrom(), employee2.getDateTo())) {
                            if (maxWorkingTime < calculateOverlapPeriod(employee1, employee2)) {
                                maxWorkingTime = calculateOverlapPeriod(employee1, employee2);
                                emp1 = employee1;
                                emp2 = employee2;
                            }
                        }
                    }
                }
            }
            if (maxWorkingTime > 0) {
                System.out.println("The max working time for project with ID " + project.getId() + " is " + maxWorkingTime +
                        " days with PAIR OF EMPLOYEES " + emp1.toString() + emp2.toString());
            } else {
                System.out.println("The project doesn't have a pair of employees that have worked together on a same project");
            }
        }
    }
    public static void main(String[] args) throws IOException {
        String st;
        Set<Project> projects = new LinkedHashSet<>();
        try {
            File file = new File("text.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((st = br.readLine()) != null) {
                String[] splited = st.split(", ");
                Employee employee = new Employee(Long.parseLong(splited[0]),splited[2],splited[3]);
                Project buffProject = new Project();
                if (checkIfProjectAlreadyExists(projects, Long.parseLong(splited[1]))) {
                    buffProject = projects.stream().filter(project -> project.getId() == Long.parseLong(splited[1])).findFirst().get();
                } else {
                    buffProject.setId(Long.parseLong(splited[1]));
                    projects.add(buffProject);
                }
                buffProject.getEmployeesSet().add(employee);
            }
            printPairOfEmployeesWithLongestWorkingTime(projects);
        }catch (IOException | ParseException ex){
            System.out.println("Error ocurred");
            ex.printStackTrace();
        }
    }
}


