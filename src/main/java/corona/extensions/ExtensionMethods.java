package corona.extensions;

import corona.business.Employee;
import corona.business.Lab;
import corona.business.Vaccine;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ExtensionMethods {
    public static Lab createLab(int id, String name, String city, boolean isActive) {
        Lab lab = new Lab();
        lab.setId(id);
        lab.setName(name);
        lab.setCity(city);
        lab.setIsActive(isActive);
        return lab;
    }

    public static Lab createLabFromResultSet(ResultSet results) throws SQLException {
        return createLab(results.getInt("id"),
                results.getString("name"),
                results.getString("city"),
                results.getBoolean("active"));
    }

    public static Vaccine createVaccine(int id, String name, int cost, int units, int productivity) {
        Vaccine vaccine = new Vaccine();
        vaccine.setId(id);
        vaccine.setName(name);
        vaccine.setCost(cost);
        vaccine.setUnits(units);
        vaccine.setProductivity(productivity);
        return vaccine;
    }

    public static Vaccine createVaccineFromResultSet(ResultSet rs) throws SQLException {
        return createVaccine(rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("cost"),
                rs.getInt("units"),
                rs.getInt("productivity"));
    }

    public static Employee createEmployee(int id, String name, String city) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setCity(city);
        return employee;
    }

    public static Employee createEmployeeFromResultSet(ResultSet results) throws SQLException {
        return createEmployee(results.getInt("id"),
                results.getString("name"),
                results.getString("city"));
    }

}