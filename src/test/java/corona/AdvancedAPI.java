package corona;

import corona.business.Employee;
import corona.business.Lab;
import corona.business.ReturnValue;
import corona.extensions.ExtensionMethods;
import org.junit.Test;

import java.util.ArrayList;

import static corona.business.ReturnValue.OK;
import static org.junit.Assert.*;

public class AdvancedAPI extends AbstractTest {
    @Test
    public void getCloseEmployeesBasicTest() {
        Employee employee = ExtensionMethods.createEmployee(1, "Roei", "Haifa");
        Employee employee_2 = ExtensionMethods.createEmployee(2, "Roei", "Haifa");
        Employee employee_3 = ExtensionMethods.createEmployee(3, "Roei", "TA");
        Employee employee_4 = ExtensionMethods.createEmployee(4, "Roei", "TA");
        Employee employee_5 = ExtensionMethods.createEmployee(5, "Roei", "TA");
        Employee employee_6 = ExtensionMethods.createEmployee(6, "Roei", "TA");
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        Lab lab_2 = ExtensionMethods.createLab(2, "TAUniv", "TA", true);
        Lab lab_3 = ExtensionMethods.createLab(3, "BUniv", "BEERSHEVA", true);
        Lab lab_4 = ExtensionMethods.createLab(4, "BARIlan", "Ramat-Gan", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addLab(lab_2);
        assertEquals(OK, res);
        res = Solution.addLab(lab_3);
        assertEquals(OK, res);
        res = Solution.addLab(lab_4);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee_2);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee_3);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee_4);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee_5);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee_6);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 1, 100);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(2, 1, 100);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(3, 2, 100);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(4, 2, 100);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(5, 2, 100);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(6, 3, 100);
        assertEquals(OK, res);
        ArrayList<Integer> al = Solution.getCloseEmployees(1);
        assertEquals(2, (int) al.get(0));
        assertEquals(1, al.size());
        al = Solution.getCloseEmployees(2);
        assertEquals(1, (int) al.get(0));
        assertEquals(1, al.size());
        al = Solution.getCloseEmployees(3);
        assertEquals(4, (int) al.get(0));
        assertEquals(5, (int) al.get(1));
        assertEquals(2, al.size());
        al = Solution.getCloseEmployees(4);
        assertEquals(3, (int) al.get(0));
        assertEquals(5, (int) al.get(1));
        al = Solution.getCloseEmployees(5);
        assertEquals(3, (int) al.get(0));
        assertEquals(4, (int) al.get(1));
        assertEquals(2, al.size());
        al = Solution.getCloseEmployees(6);
        assertEquals(0, al.size());

        res = Solution.employeeJoinLab(6, 1, 100);
        assertEquals(OK, res);

        res = Solution.employeeJoinLab(6, 2, 100);
        assertEquals(OK, res);

        res = Solution.employeeJoinLab(6, 4, 100);
        assertEquals(OK, res);

        al = Solution.getCloseEmployees(6);
        assertEquals(0, al.size());
    }
}