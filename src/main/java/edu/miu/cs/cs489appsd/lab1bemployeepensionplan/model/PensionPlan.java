package edu.miu.cs.cs489appsd.lab1bemployeepensionplan.model;

import java.time.LocalDate;

public class PensionPlan {
    String planReferenceNumber;
    LocalDate enrollmentDate;
    double monthlyContribution;

    public PensionPlan(String planReferenceNumber, String enrollmentDate, double monthlyContribution) {
        this.planReferenceNumber = planReferenceNumber;
        this.enrollmentDate = LocalDate.parse(enrollmentDate);
        this.monthlyContribution = monthlyContribution;
    }

    @Override
    public String toString() {
        return "{ \"Plan Reference\": \"" + planReferenceNumber + "\"" +
                ", \"Enrollment Date\": \"" + enrollmentDate + "\"" +
                ", \"Monthly Contribution\": " + monthlyContribution +
                " }";
    }

}


