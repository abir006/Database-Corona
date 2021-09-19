package corona;


import corona.business.Employee;
import corona.business.Lab;
import corona.business.ReturnValue;
import corona.business.Vaccine;

import java.util.ArrayList;

import static corona.business.ReturnValue.OK;


public class Solution {
    public static void createTables() {
    }

    public static void clearTables() {
    }

    public static void dropTables() {
    }

    public static ReturnValue addLab(Lab lab) {
        return OK;
    }

    public static Lab getLabProfile(Integer labID) {
        return new Lab();
    }

    public static ReturnValue deleteLab(Lab lab) {
        return OK;
    }

    public static ReturnValue addEmployee(Employee employee) {
        return OK;
    }

    public static Employee getEmployeeProfile(Integer employeeID) {
        return new Employee();
    }

    public static ReturnValue deleteEmployee(Employee employee) {
        return OK;
    }

    public static ReturnValue addVaccine(Vaccine vaccine) {
        return OK;
    }

    public static Vaccine getVaccineProfile(Integer vaccineID) {
        return new Vaccine();
    }

    public static ReturnValue deleteVaccine(Vaccine vaccine) {
        return OK;
    }


    public static ReturnValue employeeJoinLab(Integer employeeID, Integer labID, Integer salary) {
        return OK;
    }

    public static ReturnValue employeeLeftLab(Integer labID, Integer employeeID) {
        return OK;
    }

    public static ReturnValue labProduceVaccine(Integer vaccineID, Integer labID) {
        return OK;
    }

    public static ReturnValue labStoppedProducingVaccine(Integer labID, Integer vaccineID) {
        return OK;
    }

    public static ReturnValue vaccineSold(Integer vaccineID, Integer amount) {
        return OK;
    }

    public static ReturnValue vaccineProduced(Integer vaccineID, Integer amount) {
        return OK;
    }

    public static Boolean isLabPopular(Integer labID) {
        return true;
    }

    public static Integer getIncomeFromVaccine(Integer vaccineID) {
        return 0;
    }

    public static Integer getTotalNumberOfWorkingVaccines() {
        return 0;
    }

    public static Integer getTotalWages(Integer labID) {
        return 0;
    }

    public static Integer getBestLab() {
        return 0;
    }

    public static String getMostPopularCity() {
        return "";
    }

    public static ArrayList<Integer> getPopularLabs() {
        return new ArrayList<>();
    }

    public static ArrayList<Integer> getMostRatedVaccines() {
        return new ArrayList<>();
    }

    public static ArrayList<Integer> getCloseEmployees(Integer employeeID) {
        return new ArrayList<>();
    }
}

