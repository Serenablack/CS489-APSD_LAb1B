package edu.miu.cs.cs489appsd.lab1bemployeepensionplan;

import edu.miu.cs.cs489appsd.lab1bemployeepensionplan.model.Employee;
import edu.miu.cs.cs489appsd.lab1bemployeepensionplan.model.PensionPlan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class Lab1bemployeePensionPlanApplication {

    private static final List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        initializeData();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEmployee Pension System - CLI");
            System.out.println("1. Show All Employees (Sorted by Salary Desc, Last Name Asc)");
            System.out.println("2. Show Quarterly Upcoming Enrollees");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showAllEmployees();
                    break;
                case 2:
                    showUpcomingEnrollees();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void initializeData() {
        employees.add(new Employee(1, "Daniel", "Agar", "2018-01-17", 105945.50));
        employees.add(new Employee(2, "Benard", "Shaw", "2022-09-03", 197750.00));
        employees.add(new Employee(3, "Carly", "Agar", "2014-05-16", 842000.75));
        employees.add(new Employee(4, "Wesley", "Schneider", "2022-07-21", 74500.00));
        employees.add(new Employee(5, "Anna", "Wiltord", "2022-06-15", 85750.00));
        employees.add(new Employee(6, "Yosef", "Tesfalem", "2022-10-31", 100000.00));

        employees.get(0).enrollToPension(new PensionPlan("EX1089", "2024-06-01", 500.00)); // Daniel
        employees.get(2).enrollToPension(new PensionPlan("SM2307", "2024-06-01", 800.00)); // Carly
        employees.get(4).enrollToPension(new PensionPlan("PL1452", "2024-06-01", 450.00)); // Anna

    }

    private static void showAllEmployees() {
        employees.sort(Comparator.comparing(Employee::getYearlySalary).reversed().thenComparing(Employee::getLastName));
        System.out.println("\nAll Employees:");
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    private static void showUpcomingEnrollees() {
        LocalDate today = LocalDate.now();

        int currentQuarter = (today.getMonthValue() - 1) / 3 + 1;
        int nextQuarter = currentQuarter == 4 ? 1 : currentQuarter + 1;
        int startMonth = (nextQuarter - 1) * 3 + 1;
        int year = (nextQuarter == 1) ? today.getYear() + 1 : today.getYear();

        LocalDate nextQuarterStart = LocalDate.of(year, startMonth, 1);
        LocalDate nextQuarterEnd = nextQuarterStart.plusMonths(3).minusDays(1);

        List<Employee> upcoming = employees.stream()
                .filter(e -> !e.isEnrolledInPension())
                .filter(e -> {
                    LocalDate employmentDate = e.getEmploymentDate();
                    LocalDate threeYearMark = employmentDate.plusYears(3);
                    return !threeYearMark.isAfter(nextQuarterEnd);
                })
                .sorted(Comparator.comparing(Employee::getEmploymentDate).reversed())
                .toList();

//        LocalDate eligibilityCutoff = nextQuarterEnd.minusYears(3);
//
//        List<Employee> upcoming = employees.stream()
//                .filter(e -> !e.isEnrolledInPension())
//                .filter(e -> !e.getEmploymentDate().isAfter(eligibilityCutoff))
//                .sorted(Comparator.comparing(Employee::getEmploymentDate).reversed())
//                .toList();

        System.out.println("\nQuarterly Upcoming Enrollees:");
        for (Employee e : upcoming) {
            System.out.println(e);
        }
    }


}





