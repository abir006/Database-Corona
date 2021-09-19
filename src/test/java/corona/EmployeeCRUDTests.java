package corona;

import com.sun.prism.shader.Solid_ImagePattern_AlphaTest_Loader;
import corona.business.Employee;
import corona.business.Lab;
import corona.business.ReturnValue;
import org.junit.Test;

import static corona.business.ReturnValue.*;
import static corona.extensions.ExtensionMethods.createEmployee;
import static org.junit.Assert.assertEquals;

public class EmployeeCRUDTests extends AbstractTest {

    @Test
    public void addValidEmployee() {
        Employee employee = createEmployee(1, "Name", "City");
        ReturnValue ret = Solution.addEmployee(employee);
        assertEquals(OK, ret);
    }

    @Test
    public void addAlreadyExistingEmployee() {
        Employee employee = createEmployee(1, "Name", "City");
        ReturnValue ret = Solution.addEmployee(employee);
        assertEquals(OK, ret);
        ret = Solution.addEmployee(employee);
        assertEquals(ALREADY_EXISTS, ret);
    }

    @Test
    public void addInvalidEmployeeWithNullName() {
        Employee employee = createEmployee(1, null, "City");
        ReturnValue ret = Solution.addEmployee(employee);
        assertEquals(BAD_PARAMS, ret);
    }

    @Test
    public void addInvalidEmployeeWithNullCity() {
        Employee employee = createEmployee(1, "Name", null);
        ReturnValue ret = Solution.addEmployee(employee);
        assertEquals(BAD_PARAMS, ret);
    }

    @Test
    public void addInvalidEmployeeWithNegativeID() {
        Employee employee = createEmployee(-1, "Name", "City");
        ReturnValue ret = Solution.addEmployee(employee);
        assertEquals(BAD_PARAMS, ret);
    }

    @Test
    public void addInvalidEmployeeWithZeroID() {
        Employee employee = createEmployee(0, "Name", "City");
        ReturnValue ret = Solution.addEmployee(employee);
        assertEquals(BAD_PARAMS, ret);
    }

    @Test
    public void getEmployee() {
        Employee employee = createEmployee(1, "Name", "City");
        ReturnValue ret = Solution.addEmployee(employee);
        assertEquals(OK, ret);
        assertEquals(employee, Solution.getEmployeeProfile(1));
    }

    @Test
    public void getNonExistingEmployee() {
        assertEquals(Solution.getEmployeeProfile(1), Employee.badEmployee());
    }

    @Test
    public void deleteEmployee() {
        Employee employee = createEmployee(1, "Name", "City");
        ReturnValue ret = Solution.addEmployee(employee);
        assertEquals(OK, ret);
        ret = Solution.deleteEmployee(employee);
        assertEquals(OK, ret);
    }

    @Test
    public void deleteNonExistingEmployee() {
        Employee employee = createEmployee(1, "Name", "City");
        ReturnValue ret = Solution.deleteEmployee(employee);
        assertEquals(NOT_EXISTS, ret);
    }

}
