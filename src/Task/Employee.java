package Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
*
    Class Employee provides an Object of type Employee. It has an id, Start Date, which shows when the given
    employee has started working on a Project and End Date, which shows when he has finished working on the Project
* @version 3/29/2020
* @author Nikolay Stoilov
 */

public class Employee{
    /*The employee's id */
    private long id;
    /*Starting Date*/
    private Date DateFrom;
    /*Ending Date*/
    private Date DateTo;

    /*Default Constructor*/
    public Employee (){
    }

    /*Constructor with parameters*/
    public Employee(long id, String dateFrom, String dateTo) throws ParseException {
        this.id = id;
        this.DateFrom = checkForNullDate(dateFrom);
        this.DateTo = checkForNullDate(dateTo);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        DateFrom = dateFrom;
    }

    public Date getDateTo() {
        return DateTo;
    }

    public void setDateTo(Date dateTo) {
        DateTo = dateTo;
    }
/*  Method checkForNullDate returns object of type Date. It provides a verification
    whether the given String is NULL. If so the method returns Date equal to the current DateTime, else
    returns the Date which is represented by the String in format "yyyy-MM-dd".
 */
    public Date checkForNullDate(String string) throws ParseException {
        Date date;
        if (string.equals("NULL")){
            return new Date();
        }
        return date=new SimpleDateFormat("yyyy-MM-dd").parse(string);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", DateFrom=" + DateFrom +
                ", DateTo=" + DateTo +
                '}';
    }
}
