
package corona;

import corona.extensions.ExtensionMethods;
import org.junit.Test;
import corona.business.*;

import static org.junit.Assert.assertEquals;
import static corona.business.ReturnValue.*;


public class BasicAPITests extends AbstractTest {

    @Test
    public void employeeJoinLab() {
        for (int i = 1; i < 10; i++) {
            Employee employee = ExtensionMethods.createEmployee(i, "Roei", "Haifa");
            Lab lab = ExtensionMethods.createLab(i, "Technion", "Haifa", true);
            ReturnValue res = Solution.addLab(lab);
            assertEquals(OK, res);
            res = Solution.addEmployee(employee);
            assertEquals(OK, res);
            res = Solution.employeeJoinLab(i, i, 100);
            assertEquals(OK, res);
        }
    }

    @Test
    public void TwoEmployeeJoinSameLab() {
        Employee employee = ExtensionMethods.createEmployee(1, "Roei", "Haifa");
        Employee employee_2 = ExtensionMethods.createEmployee(2, "Roei", "Haifa");
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee_2);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 1, 100);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(2, 1, 100);
        assertEquals(OK, res);
    }

    @Test
    public void EmployeeJoinTwoDifferentLabs() {
        Employee employee = ExtensionMethods.createEmployee(1, "Roei", "Haifa");
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        Lab lab_2 = ExtensionMethods.createLab(2, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addLab(lab_2);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 1, 100);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 2, 100);
        assertEquals(OK, res);
    }

    @Test
    public void nonExistingEmployeeJoinsLab() {
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 1, 100);
        assertEquals(NOT_EXISTS, res);
    }

    @Test
    public void employeeJoinNonExistingLab() {
        Employee employee = ExtensionMethods.createEmployee(1, "Roei", "Haifa");
        ReturnValue res = Solution.addEmployee(employee);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 1, 100);
        assertEquals(NOT_EXISTS, res);
    }

    @Test
    public void employeeJoinLabWithNegativeSalary() {
        Employee employee = ExtensionMethods.createEmployee(1, "Roei", "Haifa");
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 1, -1);
        assertEquals(BAD_PARAMS, res);
    }

    @Test
    public void employeeJoinLabWithZeroSalary() {
        Employee employee = ExtensionMethods.createEmployee(1, "Roei", "Haifa");
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 1, 0);
        assertEquals(OK, res);
    }

    @Test
    public void employeeJoinsSameLabTwice() {
        Employee employee = ExtensionMethods.createEmployee(1, "Roei", "Haifa");
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 1, 100);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 1, 100);
        assertEquals(ALREADY_EXISTS, res);
    }

    @Test
    public void employeeJoinsSameLabAfterBeingDeletedAndRecreated() {
        Employee employee = ExtensionMethods.createEmployee(1, "Roei", "Haifa");
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 1, 100);
        assertEquals(OK, res);
        res = Solution.deleteEmployee(employee);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 1, 100);
        assertEquals(OK, res);
    }

    @Test
    public void employeeJoinsSameLabAfterItBeingDeletedAndRecreated() {
        Employee employee = ExtensionMethods.createEmployee(1, "Roei", "Haifa");
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 1, 100);
        assertEquals(OK, res);
        res = Solution.deleteLab(lab);
        assertEquals(OK, res);
        res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 1, 100);
        assertEquals(OK, res);
    }

    @Test
    public void existingEmployeeLeftLab() {
        Employee employee = ExtensionMethods.createEmployee(1, "Roei", "Haifa");
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 1, 100);
        assertEquals(OK, res);
        res = Solution.employeeLeftLab(1, 1);
        assertEquals(OK, res);
    }

    @Test
    public void existingEmployeeLeftLabTwice() {
        Employee employee = ExtensionMethods.createEmployee(1, "Roei", "Haifa");
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 1, 100);
        assertEquals(OK, res);
        res = Solution.employeeLeftLab(1, 1);
        assertEquals(OK, res);
        res = Solution.employeeLeftLab(1, 1);
        assertEquals(NOT_EXISTS, res);
    }

    @Test
    public void NonExistingEmployeeLeftLab() {
        Employee employee = ExtensionMethods.createEmployee(1, "Roei", "Haifa");
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee);
        assertEquals(OK, res);
        res = Solution.employeeLeftLab(1, 1);
        assertEquals(NOT_EXISTS, res);
    }

    @Test
    public void ExistingEmployeeLeftNonExistingLab() {
        Employee employee = ExtensionMethods.createEmployee(1, "Roei", "Haifa");
        ReturnValue res = Solution.addEmployee(employee);
        assertEquals(OK, res);
        res = Solution.employeeLeftLab(1, 1);
        assertEquals(NOT_EXISTS, res);
    }

    @Test
    public void NonExistingEmployeeLeftExistingLab() {
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.employeeLeftLab(1, 1);
        assertEquals(NOT_EXISTS, res);
    }

    @Test
    public void existingEmployeeLeftDeletedLab() {
        Employee employee = ExtensionMethods.createEmployee(1, "Roei", "Haifa");
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 1, 100);
        assertEquals(OK, res);
        res = Solution.deleteLab(lab);
        assertEquals(OK, res);
        res = Solution.employeeLeftLab(1, 1);
        assertEquals(NOT_EXISTS, res);
    }

    @Test
    public void DeletedEmployeeLeftLab() {
        Employee employee = ExtensionMethods.createEmployee(1, "Roei", "Haifa");
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 1, 100);
        assertEquals(OK, res);
        res = Solution.deleteEmployee(employee);
        assertEquals(OK, res);
        res = Solution.employeeLeftLab(1, 1);
        assertEquals(NOT_EXISTS, res);
    }

    @Test
    public void labStartedProducingVaccine() {
        Vaccine vaccine = ExtensionMethods.createVaccine(1, "Vaccine", 0, 0, 0);
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addVaccine(vaccine);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(1, 1);
        assertEquals(OK, res);
    }

    @Test
    public void labStartedProducingSameVaccineTwice() {
        Vaccine vaccine = ExtensionMethods.createVaccine(1, "Vaccine", 0, 0, 0);
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addVaccine(vaccine);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(1, 1);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(1, 1);
        assertEquals(ALREADY_EXISTS, res);
    }

    @Test
    public void startProducingVaccineByNonExistingLab() {
        Vaccine vaccine = ExtensionMethods.createVaccine(1, "Vaccine", 0, 0, 0);
        ReturnValue res = Solution.addVaccine(vaccine);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(1, 1);
        assertEquals(NOT_EXISTS, res);
    }

    @Test
    public void labStartedProducingNonExistingVaccine() {
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(1, 1);
        assertEquals(NOT_EXISTS, res);
    }

    @Test
    public void labStartedProducingVaccineAgainAfterItWasDeletedAndCreated() {
        Vaccine vaccine = ExtensionMethods.createVaccine(1, "Vaccine", 0, 0, 0);
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addVaccine(vaccine);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(1, 1);
        assertEquals(OK, res);
        res = Solution.deleteVaccine(vaccine);
        assertEquals(OK, res);
        res = Solution.addVaccine(vaccine);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(1, 1);
        assertEquals(OK, res);
    }

    @Test
    public void labStartedProducingSameVaccineAfterLabWasRemovedAndCreatedAgain() {
        Vaccine vaccine = ExtensionMethods.createVaccine(1, "Vaccine", 0, 0, 0);
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addVaccine(vaccine);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(1, 1);
        assertEquals(OK, res);
        res = Solution.deleteLab(lab);
        assertEquals(OK, res);
        res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(1, 1);
        assertEquals(OK, res);
    }

    @Test
    public void TwoVaccinesProducedByOneLab() {
        Vaccine vaccine = ExtensionMethods.createVaccine(1, "Vaccine", 0, 0, 0);
        Vaccine vaccine_2 = ExtensionMethods.createVaccine(2, "Vaccine", 0, 0, 0);
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addVaccine(vaccine);
        assertEquals(OK, res);
        res = Solution.addVaccine(vaccine_2);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(1, 1);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(2, 1);
        assertEquals(OK, res);
    }

    @Test
    public void twoLabsProducingSameVaccine() {
        Vaccine vaccine = ExtensionMethods.createVaccine(1, "Vaccine", 0, 0, 0);
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        Lab lab_2 = ExtensionMethods.createLab(2, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addLab(lab_2);
        assertEquals(OK, res);
        res = Solution.addVaccine(vaccine);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(1, 1);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(1, 2);
        assertEquals(OK, res);
    }

    @Test
    public void labStoppedProducingVaccine() {
        Vaccine vaccine = ExtensionMethods.createVaccine(1, "Vaccine", 0, 0, 0);
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addVaccine(vaccine);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(1, 1);
        assertEquals(OK, res);
        res = Solution.labStoppedProducingVaccine(1,1);
        assertEquals(OK, res);
    }

    @Test
    public void labStoppedProducingSameVaccineTwice() {
        Vaccine vaccine = ExtensionMethods.createVaccine(1, "Vaccine", 0, 0, 0);
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addVaccine(vaccine);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(1, 1);
        assertEquals(OK, res);
        res = Solution.labStoppedProducingVaccine(1,1);
        assertEquals(OK, res);
        res = Solution.labStoppedProducingVaccine(1,1);
        assertEquals(NOT_EXISTS, res);
    }

    @Test
    public void labStoppedProducingNotExistingVaccine() {
        Vaccine vaccine = ExtensionMethods.createVaccine(1, "Vaccine", 0, 0, 0);
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addVaccine(vaccine);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(1, 1);
        assertEquals(OK, res);
        res = Solution.labStoppedProducingVaccine(1,2);
        assertEquals(NOT_EXISTS, res);
    }

    @Test
    public void NonExistingLabStoppedProducingProducedVaccine() {
        Vaccine vaccine = ExtensionMethods.createVaccine(1, "Vaccine", 0, 0, 0);
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addVaccine(vaccine);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(1, 1);
        assertEquals(OK, res);
        res = Solution.labStoppedProducingVaccine(2,1);
        assertEquals(NOT_EXISTS, res);
    }

    @Test
    public void getBestLabSimple(){
        Employee employee = ExtensionMethods.createEmployee(1, "Roei", "Haifa");
        Employee employee_2 = ExtensionMethods.createEmployee(2, "Roei", "Haifa");
        Employee employee_3 = ExtensionMethods.createEmployee(3, "Roei", "TA");
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        Lab lab_2 = ExtensionMethods.createLab(2, "TAUniv", "TA", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addLab(lab_2);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee_2);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee_3);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 1, 100);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(2, 1, 100);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(3, 2, 100);
        assertEquals(OK, res);
        assertEquals(1,Solution.getBestLab().intValue());
    }

    @Test
    public void getBestLabTwoLabsWithSameScoreReturnLowerLabID(){
        Employee employee = ExtensionMethods.createEmployee(1, "Roei", "Haifa");
        Employee employee_2 = ExtensionMethods.createEmployee(2, "Roei", "Haifa");
        Employee employee_3 = ExtensionMethods.createEmployee(3, "Roei", "TA");
        Employee employee_4 = ExtensionMethods.createEmployee(4, "Roei", "TA");
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        Lab lab_2 = ExtensionMethods.createLab(2, "TAUniv", "TA", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addLab(lab_2);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee_2);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee_3);
        assertEquals(OK, res);
        res = Solution.addEmployee(employee_4);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(1, 1, 100);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(2, 1, 100);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(3, 2, 100);
        assertEquals(OK, res);
        res = Solution.employeeJoinLab(4, 2, 100);
        assertEquals(OK, res);
        assertEquals(1,Solution.getBestLab().intValue());
    }

    @Test
    public void getBestLabCheckProperOrder(){
        Employee employee = ExtensionMethods.createEmployee(1, "Roei", "Haifa");
        Employee employee_2 = ExtensionMethods.createEmployee(2, "Roei", "Haifa");
        Employee employee_3 = ExtensionMethods.createEmployee(3, "Roei", "TA");
        Employee employee_4 = ExtensionMethods.createEmployee(4, "Roei", "TA");
        Employee employee_5 = ExtensionMethods.createEmployee(5, "Roei", "TA");
        Lab lab = ExtensionMethods.createLab(1, "Technion", "Haifa", true);
        Lab lab_2 = ExtensionMethods.createLab(2, "TAUniv", "TA", true);
        ReturnValue res = Solution.addLab(lab);
        assertEquals(OK, res);
        res = Solution.addLab(lab_2);
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
        assertEquals(2,Solution.getBestLab().intValue());
    }


    @Test
    public void Popular() {

        ReturnValue res;
        Lab s = new Lab();
        s.setId(1);
        s.setName("Technion");
        s.setCity("Haifa");
        s.setIsActive(true);


        res = Solution.addLab(s);
        assertEquals(OK, res);

        Vaccine v = new Vaccine();
        v.setId(5);
        v.setName("COVID-19");
        v.setCost(100);
        v.setProductivity(21);
        v.setUnits(30);
        res = Solution.addVaccine(v);
        assertEquals(OK, res);

        res = Solution.labProduceVaccine(5, 1);
        assertEquals(OK, res);

        Boolean ret = Solution.isLabPopular(1);
        assertEquals(true, ret);
        ret = Solution.isLabPopular(3);
        assertEquals(false, ret);
    }
}


