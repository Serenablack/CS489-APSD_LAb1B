package edu.miu.cs.cs489appsd.lab1bemployeepensionplan.model;

import java.time.LocalDate;

public class Employee {
    private long employeeId;
    private String firstName;
    private String lastName;
    private LocalDate employmentDate;
    private double yearlySalary;
    private PensionPlan pensionPlan;
    public Employee(long employeeId, String firstName, String lastName, String employmentDate, double yearlySalary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearlySalary = yearlySalary;
        this.employmentDate = LocalDate.parse(employmentDate);
    }

    public long getEmployeeId() { return employeeId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public double getYearlySalary() { return yearlySalary; }
    public LocalDate getEmploymentDate() { return employmentDate; }
    public PensionPlan getPensionPlan() { return pensionPlan; }

    public void enrollToPension(PensionPlan pensionPlan) {
        this.pensionPlan = pensionPlan;
    }

    public boolean isEnrolledInPension() {
        return pensionPlan != null;
    }

    public long getYearsWorked() {
        return java.time.temporal.ChronoUnit.YEARS.between(employmentDate, LocalDate.now());
    }

    @Override
    public String toString() {
        return "{ \"Employee ID\": " + employeeId +
                ", \"Name\": \"" + firstName + " " + lastName + "\"" +
                ", \"Yearly Salary\": " + yearlySalary +
                ", \"Employment Date\": \"" + employmentDate + "\"" +
                (pensionPlan != null ? ", \"Pension Plan\": " + pensionPlan : "") +
                " }";
    }
}
