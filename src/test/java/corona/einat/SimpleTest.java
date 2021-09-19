package corona.einat;

import corona.AbstractTest;
import corona.Solution;
import corona.business.Employee;
import corona.business.Lab;
import corona.business.ReturnValue;
import corona.business.Vaccine;
import org.junit.Test;

import static corona.business.ReturnValue.*;
import static org.junit.Assert.assertEquals;

// Written by: Einat
public class SimpleTest extends AbstractTest {

    @Test
    public void simpleTestCreateLab()
    {
        System.out.println("Test: simpleTestCreateLab\n");

        Lab a = new Lab();
        a.setId(1);
        a.setName("Technion");
        a.setCity("Haifa");
        a.setIsActive(true);
        ReturnValue ret = Solution.addLab(a);
        assertEquals(OK, ret);

    }

    @Test
    public void testDeleteLab(){
        System.out.println("Test: testDeleteLab\n");

        Lab a = new Lab();
        a.setId(10);
        a.setName("Technion");
        a.setCity("Haifa");
        a.setIsActive(true);

        ReturnValue ret = Solution.deleteLab(a);
        assertEquals(NOT_EXISTS , ret);

    }

    @Test
    public void AddToTables()
    {
        System.out.println("Test: AddToTables\n");

        Lab[] lab = new Lab[10];
        for(int i=0; i< lab.length; i++)
        {
            lab[i] = new Lab();
            lab[i].setId(i+1);
            lab[i].setName("Technion");
            lab[i].setCity("Haifa");
            lab[i].setIsActive(true);
            ReturnValue ret = Solution.addLab(lab[i]);
            assertEquals(OK, ret);
        }

        Employee[] empl = new Employee[10];
        for(int i=0; i< empl.length; i++)
        {
            empl[i] = new Employee();
            empl[i].setId(i+1);
            empl[i].setName("Ido" + i);
            empl[i].setCity("Haifa");
            ReturnValue ret = Solution.addEmployee(empl[i]);
            assertEquals(OK, ret);
        }

        Vaccine[] vacc = new Vaccine[10];
        for(int i=0; i< vacc.length; i++)
        {
            vacc[i] = new Vaccine();
            vacc[i].setId(i+1);
            vacc[i].setName("Ido" + i);
            vacc[i].setCost(2*i);
            vacc[i].setUnits(i*10);
            vacc[i].setProductivity(i+23);
            ReturnValue ret = Solution.addVaccine(vacc[i]);
            assertEquals(OK, ret);
        }

        System.out.println("Test: AddToTableWithFailure\n");

        Lab labs[] = new Lab[6];
        for(int i=0; i< labs.length; i++) {
            labs[i] = new Lab();
        }

        //adding object with the same ID
        labs[0].setId(2);
        labs[0].setName("Tech");
        labs[0].setCity("Hai");
        labs[0].setIsActive(true);
        ReturnValue ret = Solution.addLab(labs[0]);
        assertEquals(ALREADY_EXISTS, ret);

        //adding object with negative ID
        labs[1].setId(-17);
        labs[1].setName("Technion");
        labs[1].setCity("Haifa");
        labs[1].setIsActive(true);
        ret = Solution.addLab(labs[1]);
        assertEquals(BAD_PARAMS, ret);

        //adding object already exists
        labs[2].setId(2);
        labs[2].setName("Technion");
        labs[2].setCity("Haifa");
        labs[2].setIsActive(true);
        ret = Solution.addLab(labs[2]);
        assertEquals(ALREADY_EXISTS, ret);

        //adding object with NULL name
        labs[3].setId(11);
        labs[3].setName(null);
        labs[3].setCity("Haifa");
        labs[3].setIsActive(true);
        ret = Solution.addLab(labs[3]);
        assertEquals(BAD_PARAMS, ret);

        //adding object with null city
        labs[4].setId(2);
        labs[4].setName("Technion");
        labs[4].setCity(null);
        labs[4].setIsActive(true);
        ret = Solution.addLab(labs[4]);
        assertEquals(BAD_PARAMS, ret);


        Employee emp[] = new Employee[5];
        for(int i=0; i< emp.length; i++)
        {
            emp[i] = new Employee();
        }

        //adding object already exists
        emp[0].setId(3);
        emp[0].setName("Ido" + 2);
        emp[0].setCity("Haifa");
        ret = Solution.addEmployee(emp[0]);
        assertEquals(ALREADY_EXISTS, ret);

        //adding object with the same id
        emp[1].setId(2);
        emp[1].setName("Ido " + "ido");
        emp[1].setCity("Haifa");
        ret = Solution.addEmployee(emp[1]);
        assertEquals(ALREADY_EXISTS, ret);

        //adding object with negative id
        emp[2].setId(-2);
        emp[2].setName("Ido " + "ido");
        emp[2].setCity("Haifa");
        ret = Solution.addEmployee(emp[2]);
        assertEquals(BAD_PARAMS, ret);

        //adding object with null name
        emp[3].setId(-2);
        emp[3].setName(null);
        emp[3].setCity("Haifa");
        ret = Solution.addEmployee(emp[3]);
        assertEquals(BAD_PARAMS, ret);

        //adding object with null city
        emp[4].setId(-2);
        emp[4].setName("ido");
        emp[4].setCity(null);
        ret = Solution.addEmployee(emp[4]);
        assertEquals(BAD_PARAMS, ret);


        Vaccine vac[] = new Vaccine[10];
        for(int i=0; i< vac.length; i++)
        {
            vac[i] = new Vaccine();
        }

        //adding object already exists
        vac[0].setId(3);
        vac[0].setName("Ido" + 2);
        vac[0].setCost(2*2);
        vac[0].setUnits(2*10);
        vac[0].setProductivity(2+23);
        ret = Solution.addVaccine(vac[0]);
        assertEquals(ALREADY_EXISTS, ret);

        //adding object with id exists
        vac[1].setId(2);
        vac[1].setName("Ido");
        vac[1].setCost(2*2);
        vac[1].setUnits(2*10);
        vac[1].setProductivity(2+23);
        ret = Solution.addVaccine(vac[1]);
        assertEquals(ALREADY_EXISTS, ret);

        //adding object with negative id
        vac[2].setId(-2);
        vac[2].setName("Ido");
        vac[2].setCost(2*2);
        vac[2].setUnits(2*10);
        vac[2].setProductivity(2+23);
        ret = Solution.addVaccine(vac[2]);
        assertEquals(BAD_PARAMS, ret);

        //adding object with negative cost
        vac[3].setId(12);
        vac[3].setName("Ido");
        vac[3].setCost(-2*2);
        vac[3].setUnits(2*10);
        vac[3].setProductivity(2+23);
        ret = Solution.addVaccine(vac[3]);
        assertEquals(BAD_PARAMS, ret);

        //adding object with negative unit
        vac[4].setId(12);
        vac[4].setName("Ido");
        vac[4].setCost(2*2);
        vac[4].setUnits(-2*10);
        vac[4].setProductivity(2+23);
        ret = Solution.addVaccine(vac[4]);
        assertEquals(BAD_PARAMS, ret);

        //adding object with negative prod
        vac[5].setId(12);
        vac[5].setName("Ido");
        vac[5].setCost(2*2);
        vac[5].setUnits(2*10);
        vac[5].setProductivity(-223);
        ret = Solution.addVaccine(vac[5]);
        assertEquals(BAD_PARAMS, ret);

        //adding object with null name
        vac[6].setId(12);
        vac[6].setName(null);
        vac[6].setCost(2*2);
        vac[6].setUnits(2*10);
        vac[6].setProductivity(5);
        ret = Solution.addVaccine(vac[6]);
        assertEquals(BAD_PARAMS, ret);

    }

    @Test
    public void deleteFromTables()
    {
        System.out.println("Test: deleteFromTables\n");

        Lab[] lab = new Lab[4];
        for(int i=0; i< lab.length; i++)
        {
            lab[i] = new Lab();
            lab[i].setId(i+1);
            lab[i].setName("Technion");
            lab[i].setCity("Haifa");
            lab[i].setIsActive(true);
            ReturnValue ret = Solution.addLab(lab[i]);
            assertEquals(OK, ret);
        }

        Employee[] empl = new Employee[4];
        for(int i=0; i< empl.length; i++)
        {
            empl[i] = new Employee();
            empl[i].setId(i+1);
            empl[i].setName("Ido" + i);
            empl[i].setCity("Haifa");
            ReturnValue ret = Solution.addEmployee(empl[i]);
            assertEquals(OK, ret);
        }

        Vaccine[] vacc = new Vaccine[4];
        for(int i=0; i< vacc.length; i++)
        {
            vacc[i] = new Vaccine();
            vacc[i].setId(i+1);
            vacc[i].setName("Ido" + i);
            vacc[i].setCost(2*i);
            vacc[i].setUnits(i*10);
            vacc[i].setProductivity(i+23);
            ReturnValue ret = Solution.addVaccine(vacc[i]);
            assertEquals(OK, ret);
        }


        Lab labs[] = new Lab[6];
        for(int i=0; i< labs.length; i++) {
            labs[i] = new Lab();
        }

        //deleteLab object with negative ID
        labs[1].setId(-17);
        labs[1].setName("Technion");
        labs[1].setCity("Haifa");
        labs[1].setIsActive(true);
        ReturnValue ret = Solution.deleteLab(labs[1]);
        assertEquals(NOT_EXISTS, ret);

        //deleteLab object already exists
        labs[2].setId(2);
        labs[2].setName("Technion");
        labs[2].setCity("Haifa");
        labs[2].setIsActive(true);
        ret = Solution.deleteLab(labs[2]);
        assertEquals(OK, ret);

        //deleteLab object with NULL name
        labs[3].setId(11);
        labs[3].setName(null);
        labs[3].setCity("Haifa");
        labs[3].setIsActive(true);
        ret = Solution.deleteLab(labs[3]);
        assertEquals(NOT_EXISTS, ret);

        //deleteLab object with null city
        labs[4].setId(2);
        labs[4].setName("Technion");
        labs[4].setCity(null);
        labs[4].setIsActive(true);
        ret = Solution.deleteLab(labs[4]);
        assertEquals(NOT_EXISTS, ret);


        Employee emp[] = new Employee[5];
        for(int i=0; i< emp.length; i++)
        {
            emp[i] = new Employee();
        }

        //deleteEmployee object already exists
        emp[0].setId(3);
        emp[0].setName("Ido" + 2);
        emp[0].setCity("Haifa");
        ret = Solution.deleteEmployee(emp[0]);
        assertEquals(OK, ret);

        //deleteEmployee object with the same id
        emp[1].setId(2);
        emp[1].setName("Ido " + "ido");
        emp[1].setCity("Haifa");
        ret = Solution.deleteEmployee(emp[1]);
        assertEquals(OK, ret);

        //deleteEmployee object with negative id
        emp[2].setId(-2);
        emp[2].setName("Ido " + "ido");
        emp[2].setCity("Haifa");
        ret = Solution.deleteEmployee(emp[2]);
        assertEquals(NOT_EXISTS, ret);

        //deleteEmployee object with null name
        emp[3].setId(-2);
        emp[3].setName(null);
        emp[3].setCity("Haifa");
        ret = Solution.deleteEmployee(emp[3]);
        assertEquals(NOT_EXISTS, ret);

        //deleteEmployee object with null city
        emp[4].setId(-2);
        emp[4].setName("ido");
        emp[4].setCity(null);
        ret = Solution.deleteEmployee(emp[4]);
        assertEquals(NOT_EXISTS, ret);


        Vaccine vac[] = new Vaccine[10];
        for(int i=0; i< vac.length; i++)
        {
            vac[i] = new Vaccine();
        }

        //deleteVaccine object already exists
        vac[0].setId(3);
        vac[0].setName("Ido" + 2);
        vac[0].setCost(2*2);
        vac[0].setUnits(2*10);
        vac[0].setProductivity(2+23);
        ret = Solution.deleteVaccine(vac[0]);
        assertEquals(OK, ret);

        //deleteVaccine object with id exists
        vac[1].setId(2);
        vac[1].setName("Ido");
        vac[1].setCost(2*2);
        vac[1].setUnits(2*10);
        vac[1].setProductivity(2+23);
        ret = Solution.deleteVaccine(vac[1]);
        assertEquals(OK, ret);

        //deleteVaccine object with negative id
        vac[2].setId(-2);
        vac[2].setName("Ido");
        vac[2].setCost(2*2);
        vac[2].setUnits(2*10);
        vac[2].setProductivity(2+23);
        ret = Solution.deleteVaccine(vac[2]);
        assertEquals(NOT_EXISTS, ret);

        //deleteVaccine object with negative cost
        vac[3].setId(12);
        vac[3].setName("Ido");
        vac[3].setCost(-2*2);
        vac[3].setUnits(2*10);
        vac[3].setProductivity(2+23);
        ret = Solution.deleteVaccine(vac[3]);
        assertEquals(NOT_EXISTS, ret);

        //deleteVaccine object with negative unit
        vac[4].setId(12);
        vac[4].setName("Ido");
        vac[4].setCost(2*2);
        vac[4].setUnits(-2*10);
        vac[4].setProductivity(2+23);
        ret = Solution.deleteVaccine(vac[4]);
        assertEquals(NOT_EXISTS, ret);

        //deleteVaccine object with negative prod
        vac[5].setId(12);
        vac[5].setName("Ido");
        vac[5].setCost(2*2);
        vac[5].setUnits(2*10);
        vac[5].setProductivity(-223);
        ret = Solution.deleteVaccine(vac[5]);
        assertEquals(NOT_EXISTS, ret);

        //deleteVaccine object with null name
        vac[6].setId(12);
        vac[6].setName(null);
        vac[6].setCost(2*2);
        vac[6].setUnits(2*10);
        vac[6].setProductivity(5);
        ret = Solution.deleteVaccine(vac[6]);
        assertEquals(NOT_EXISTS, ret);

    }


    @Test
    public void getProfiles() {
        System.out.println("Test: getProfiles\n");

        Lab[] lab = new Lab[2];
        for (int i = 0; i < lab.length; i++) {
            lab[i] = new Lab();
            lab[i].setId(i + 1);
            lab[i].setName("Technion");
            lab[i].setCity("Haifa");
            lab[i].setIsActive(true);
            ReturnValue ret = Solution.addLab(lab[i]);
            assertEquals(OK, ret);
        }

        Employee[] empl = new Employee[2];
        for (int i = 0; i < empl.length; i++) {
            empl[i] = new Employee();
            empl[i].setId(i + 1);
            empl[i].setName("Ido" + i);
            empl[i].setCity("Haifa");
            ReturnValue ret = Solution.addEmployee(empl[i]);
            assertEquals(OK, ret);
        }

        Vaccine[] vacc = new Vaccine[2];
        for (int i = 0; i < vacc.length; i++) {
            vacc[i] = new Vaccine();
            vacc[i].setId(i + 1);
            vacc[i].setName("Ido" + i);
            vacc[i].setCost(2 * i);
            vacc[i].setUnits(i * 10);
            vacc[i].setProductivity(i + 23);
            ReturnValue ret = Solution.addVaccine(vacc[i]);
            assertEquals(OK, ret);
        }
        Solution.getLabProfile(1).getName();
        assertEquals(lab[0].getName(),Solution.getLabProfile(1).getName());
        assertEquals(lab[1].getName(),Solution.getLabProfile(2).getName());
        assertEquals(lab[0].getCity(),Solution.getLabProfile(1).getCity());
        assertEquals(lab[1].getCity(),Solution.getLabProfile(2).getCity());
        //trying to get profile of lab that dont exist
        assertEquals(Lab.badLab().getName(),Solution.getLabProfile(3).getName());
        assertEquals(Lab.badLab().getCity(),Solution.getLabProfile(3).getCity());

        assertEquals(vacc[0].getName(),Solution.getVaccineProfile(1).getName());
        assertEquals(vacc[1].getName(),Solution.getVaccineProfile(2).getName());
        assertEquals(vacc[0].getCost(),Solution.getVaccineProfile(1).getCost());
        assertEquals(vacc[1].getCost(),Solution.getVaccineProfile(2).getCost());
        assertEquals(vacc[0].getProductivity(),Solution.getVaccineProfile(1).getProductivity());
        assertEquals(vacc[1].getProductivity(),Solution.getVaccineProfile(2).getProductivity());
        assertEquals(vacc[0].getId(),Solution.getVaccineProfile(1).getId());
        assertEquals(vacc[1].getId(),Solution.getVaccineProfile(2).getId());
        assertEquals(vacc[0].getUnits(),Solution.getVaccineProfile(1).getUnits());
        assertEquals(vacc[1].getUnits(),Solution.getVaccineProfile(2).getUnits());
        //trying to get profile of vaccine that dont exist
        assertEquals(Vaccine.badVaccine().getName(),Solution.getVaccineProfile(3).getName());
        assertEquals(Vaccine.badVaccine().getUnits(),Solution.getVaccineProfile(3).getUnits());
        assertEquals(Vaccine.badVaccine().getId(),Solution.getVaccineProfile(3).getId());
        assertEquals(Vaccine.badVaccine().getProductivity(),Solution.getVaccineProfile(3).getProductivity());
        assertEquals(Vaccine.badVaccine().getCost(),Solution.getVaccineProfile(3).getCost());

        assertEquals(empl[0].getName(),Solution.getEmployeeProfile(1).getName());
        assertEquals(empl[1].getName(),Solution.getEmployeeProfile(2).getName());
        assertEquals(empl[0].getCity(),Solution.getEmployeeProfile(1).getCity());
        assertEquals(empl[1].getCity(),Solution.getEmployeeProfile(2).getCity());
        assertEquals(empl[0].getId(),Solution.getEmployeeProfile(1).getId());
        assertEquals(empl[1].getId(),Solution.getEmployeeProfile(2).getId());
        //trying to get profile of lab that dont exist
        assertEquals(Employee.badEmployee().getName(),Solution.getEmployeeProfile(3).getName());
        assertEquals(Employee.badEmployee().getCity(),Solution.getEmployeeProfile(3).getCity());
        assertEquals(Employee.badEmployee().getId(),Solution.getEmployeeProfile(3).getId());

    }
}