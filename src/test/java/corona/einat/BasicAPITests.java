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
public class BasicAPITests extends AbstractTest {
    @Test
    public void employeeJoinLeftLabTest() {

        System.out.println("Test: employeeJoinLeftLabTest\n");
        ReturnValue res;
        Lab s = new Lab();
        s.setId(1);
        s.setName("Technion");
        s.setCity("Haifa");
        s.setIsActive(true);

        Lab s1 = new Lab();
        s1.setId(2);
        s1.setName("CS");
        s1.setCity("Haifa");
        s1.setIsActive(true);

        res = Solution.addLab(s);
        assertEquals(OK, res);

        res = Solution.addLab(s1);
        assertEquals(OK, res);

        Employee a = new Employee();
        a.setId(2);
        a.setName("Roei");
        a.setCity("Haifa");
        ReturnValue ret = Solution.addEmployee(a);
        assertEquals(OK, ret);

        res = Solution.employeeJoinLab(2, 1, 100);
        assertEquals(OK, res);

        res = Solution.employeeJoinLab(1, -19, 100);
        assertEquals(NOT_EXISTS, res);

        res = Solution.employeeJoinLab(2, 1, 100);
        assertEquals(ALREADY_EXISTS, res);

        res = Solution.employeeJoinLab(2, 2, -10);
        assertEquals(BAD_PARAMS, res);

        //adding employ to more than oneLab
        res = Solution.employeeJoinLab(2, 2, 50);
        assertEquals(OK, res);

        //Leave Lab
        res = Solution.employeeLeftLab(2, 2);
        assertEquals(OK, res);

        res = Solution.employeeLeftLab(2, 2);
        assertEquals(NOT_EXISTS, res);

        res = Solution.employeeLeftLab(3, 2);
        assertEquals(NOT_EXISTS, res);

        res = Solution.employeeLeftLab(1, 5);
        assertEquals(NOT_EXISTS, res);

        res = Solution.employeeLeftLab(-2, 2);
        assertEquals(NOT_EXISTS, res);

        res = Solution.employeeLeftLab(1, -2);
        assertEquals(NOT_EXISTS, res);

    }

    @Test
    public void PopularTest() {

        System.out.println("Test: PopularTest\n");
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

        res = Solution.labProduceVaccine(5,1);
        assertEquals(OK, res);

        Boolean ret = Solution.isLabPopular(1);
        assertEquals(true, ret);

        res = Solution.vaccineProduced(5,1);
        assertEquals(OK, res);

        //prod now under 20
         ret = Solution.isLabPopular(1);
        assertEquals(false, ret);

        // lab that dont exist
        ret = Solution.isLabPopular(17);
        assertEquals(false, ret);

        Vaccine v1 = new Vaccine();
        v1.setId(4);
        v1.setName("COVID-19");
        v1.setCost(100);
        v1.setProductivity(21);
        v1.setUnits(30);
        res = Solution.addVaccine(v1);
        assertEquals(OK, res);

        res = Solution.labProduceVaccine(4,1);
        assertEquals(OK, res);

        //there is 1 vaccine with prod less then 20
        ret = Solution.isLabPopular(1);
        assertEquals(false, ret);

        //change that vaccine to prod over 20
        res = Solution.vaccineSold(5,1);
        assertEquals(OK, res);

        ret = Solution.isLabPopular(1);
        assertEquals(true, ret);

        ReturnValue result = Solution.labStoppedProducingVaccine(1,5);
        assertEquals(OK, result);

        result = Solution.labStoppedProducingVaccine(1,4);
        assertEquals(OK, result);

        //case the lab dont have vaccines the condition satisfies by empty way
        ret = Solution.isLabPopular(1);
        assertEquals(true, ret);

        Lab s1 = new Lab();
        s1.setId(2);
        s1.setName("CS");
        s1.setCity("Haifa");
        s1.setIsActive(false);

        res = Solution.addLab(s1);
        assertEquals(OK, res);

        Vaccine v2 = new Vaccine();
        v2.setId(1);
        v2.setName("COVID-19");
        v2.setCost(100);
        v2.setProductivity(30);
        v2.setUnits(30);
        res = Solution.addVaccine(v2);
        assertEquals(OK, res);

        ret = Solution.isLabPopular(2);
        assertEquals(true, ret);

        res = Solution.labProduceVaccine(1,2);
        assertEquals(OK, res);

        ret = Solution.isLabPopular(2);
        assertEquals(true, ret);

        res = Solution.vaccineProduced(1,1);
        assertEquals(OK, res);

        ret = Solution.isLabPopular(2);
        assertEquals(false, ret);

        res = Solution.vaccineSold(1,1);
        assertEquals(OK, res);

        ret = Solution.isLabPopular(2);
        assertEquals(true, ret);

        res = Solution.labProduceVaccine(5,2);
        assertEquals(OK, res);

        res = Solution.vaccineProduced(5,1);
        assertEquals(OK, res);

        ret = Solution.isLabPopular(2);
        assertEquals(false, ret);

        res = Solution.vaccineSold(5,1);
        assertEquals(OK, res);

        ret = Solution.isLabPopular(2);
        assertEquals(true, ret);
    }

    @Test
    public void ProduceVaccineTest() {

        System.out.println("Test: ProduceVaccineTest\n");
        ReturnValue res;
        Lab s[] = new Lab[5];
        for(int i=0; i<5; i++)
        {
            s[i] = new Lab();
            s[i].setId(i+1);
            s[i].setName("Technion"+i);
            s[i].setCity("Haifa");
            s[i].setIsActive(true);
            res = Solution.addLab(s[i]);
            assertEquals(OK, res);
        }

        Vaccine v[] = new Vaccine[5];
        for(int i=0; i<5; i++)
        {
            v[i]= new Vaccine();
            v[i].setId(i+1);
            v[i].setName("COVID-"+(i+1));
            v[i].setCost(10*(i+1));
            v[i].setProductivity(17+(i+1));
            v[i].setUnits(20*(i+1));
            res = Solution.addVaccine(v[i]);
            assertEquals(OK, res);
        }

        res = Solution.labProduceVaccine(5,1);
        assertEquals(OK, res);

        res = Solution.labProduceVaccine(17,1);
        assertEquals(NOT_EXISTS, res);

        res = Solution.labProduceVaccine(1,17);
        assertEquals(NOT_EXISTS, res);

        res = Solution.labProduceVaccine(18,17);
        assertEquals(NOT_EXISTS, res);

        res = Solution.labProduceVaccine(5,1);
        assertEquals(ALREADY_EXISTS, res);

        res = Solution.labProduceVaccine(2,4);
        assertEquals(OK, res);

        res = Solution.labProduceVaccine(3,4);
        assertEquals(OK, res);

        res = Solution.labProduceVaccine(3,3);
        assertEquals(OK, res);

        res = Solution.labProduceVaccine(3,2);
        assertEquals(OK, res);

        res = Solution.labProduceVaccine(2,1);
        assertEquals(OK, res);

        res = Solution.labProduceVaccine(3,1);
        assertEquals(OK, res);

        res = Solution.labProduceVaccine(3,1);
        assertEquals(ALREADY_EXISTS, res);

        //stop producing vaccine

        System.out.println("Test: StopProduceVaccineTest\n");

        res = Solution.labStoppedProducingVaccine(1,3);
        assertEquals(OK, res);

        res = Solution.labStoppedProducingVaccine(4,3);
        assertEquals(OK, res);

        res = Solution.labStoppedProducingVaccine(2,1);
        assertEquals(NOT_EXISTS, res);

        res = Solution.labStoppedProducingVaccine(12,1);
        assertEquals(NOT_EXISTS, res);

        res = Solution.labStoppedProducingVaccine(2,11);
        assertEquals(NOT_EXISTS, res);

        res = Solution.labStoppedProducingVaccine(21,31);
        assertEquals(NOT_EXISTS, res);

    }


    @Test
    public void VaccineProduceSoldTest() {

        System.out.println("Test: VaccineProduceSoldTest\n");
        ReturnValue res;

        Vaccine v[] = new Vaccine[5];
        for(int i=0; i<5; i++)
        {
            v[i]= new Vaccine();
            v[i].setId(i+1);
            v[i].setName("COVID-"+(i+1));
            v[i].setCost(10*(i+1));
            v[i].setProductivity(80+(i+1));
            v[i].setUnits(20*(i+1));
            res = Solution.addVaccine(v[i]);
            assertEquals(OK, res);
        }

        res = Solution.vaccineSold(6,10);
        assertEquals(NOT_EXISTS, res);

        res = Solution.vaccineSold(2,100);
        assertEquals(BAD_PARAMS, res);

        res = Solution.vaccineSold(1,10);
        assertEquals(OK, res);

        res = Solution.vaccineSold(1,10);
        assertEquals(OK, res);

        //now there not supposed to be vaccine from type 1
        res = Solution.vaccineSold(1,1);
        assertEquals(BAD_PARAMS, res);

        res = Solution.vaccineSold(3,10);
        assertEquals(OK, res);

        Vaccine vac = Solution.getVaccineProfile(3);
        assertEquals(98,vac.getProductivity());
        assertEquals(60,vac.getCost());
        assertEquals(50,vac.getUnits());

        res = Solution.vaccineSold(4,10);
        assertEquals(OK, res);

        vac = Solution.getVaccineProfile(4);
        assertEquals(99,vac.getProductivity());
        assertEquals(80,vac.getCost());
        assertEquals(70,vac.getUnits());

        //check the case productivity over 100
        res = Solution.vaccineSold(5,10);
        assertEquals(OK, res);
        //this should not fail - if fails there exception caused by trying to increase prod over 100
        res = Solution.vaccineSold(5,10);
        assertEquals(OK, res);
        vac = Solution.getVaccineProfile(5);
        assertEquals(100,vac.getProductivity());

        //vaccine produced tests

        res = Solution.vaccineProduced(1,10);
        assertEquals(OK, res);

        res = Solution.vaccineProduced(6,10);
        assertEquals(NOT_EXISTS, res);

        //amount should not be negative
        res = Solution.vaccineProduced(2,-10);
        assertEquals(BAD_PARAMS, res);

        res = Solution.vaccineProduced(200,-100);
        assertEquals(BAD_PARAMS, res);

        res = Solution.vaccineProduced(2,10);
        assertEquals(OK, res);
        vac = Solution.getVaccineProfile(2);
        assertEquals(67,vac.getProductivity());
        assertEquals(10,vac.getCost());
        assertEquals(50,vac.getUnits());

        //checking that prod dont get under 0
        res = Solution.vaccineProduced(2,10);
        assertEquals(OK, res);
        res = Solution.vaccineProduced(2,10);
        assertEquals(OK, res);
        res = Solution.vaccineProduced(2,10);
        assertEquals(OK, res);
        res = Solution.vaccineProduced(2,10);
        assertEquals(OK, res);
        res = Solution.vaccineProduced(2,10);
        assertEquals(OK, res);
        vac = Solution.getVaccineProfile(2);
        assertEquals(0,vac.getProductivity());

        Vaccine va = new Vaccine();
        va.setId(6);
        va.setName("COVID-19");
        va.setCost(7);
        va.setProductivity(2);
        va.setUnits(1);
        res = Solution.addVaccine(va);
        assertEquals(OK, res);
        res = Solution.vaccineProduced(6,10);
        assertEquals(OK, res);
        vac = Solution.getVaccineProfile(6);
        assertEquals(0,vac.getProductivity());
        assertEquals(3,vac.getCost());
        assertEquals(11,vac.getUnits());

        //produce and sold together


        Vaccine v2 = new Vaccine();
        v2.setId(8);
        v2.setName("COVID-19");
        v2.setCost(10);
        v2.setProductivity(50);
        v2.setUnits(300);
        res = Solution.addVaccine(v2);
        assertEquals(OK, res);

        vac = Solution.getVaccineProfile(8);
        assertEquals(10,vac.getCost());

        res = Solution.vaccineProduced(8,10);
        assertEquals(OK, res);

        vac = Solution.getVaccineProfile(8);
        assertEquals(5,vac.getCost());

        res = Solution.vaccineSold(8,10);
        assertEquals(OK, res);

        vac = Solution.getVaccineProfile(8);
        assertEquals(10,vac.getCost());

        res = Solution.vaccineSold(8,10);
        assertEquals(OK, res);

        vac = Solution.getVaccineProfile(8);
        assertEquals(20,vac.getCost());

        res = Solution.vaccineProduced(8,10);
        assertEquals(OK, res);

        vac = Solution.getVaccineProfile(8);
        assertEquals(10,vac.getCost());


    }

    @Test
    public void NumberOfWorkingVaccinesTest() {

        System.out.println("Test: NumberOfWorkingVaccinesTest\n");

        //before there are any vaccines
        int result = Solution.getTotalNumberOfWorkingVaccines();
        assertEquals(0, result);

        ReturnValue res;

        Vaccine v = new Vaccine();
        v.setId(5);
        v.setName("COVID-19");
        v.setCost(100);
        v.setProductivity(21);
        v.setUnits(30);
        res = Solution.addVaccine(v);
        assertEquals(OK, res);

        result = Solution.getTotalNumberOfWorkingVaccines();
        assertEquals(30, result);

        res = Solution.vaccineSold(5, 10);
        assertEquals(OK, res);

        result = Solution.getTotalNumberOfWorkingVaccines();
        assertEquals(20, result);

        Vaccine v1 = new Vaccine();
        v1.setId(2);
        v1.setName("COVID-19");
        v1.setCost(100);
        v1.setProductivity(20);
        v1.setUnits(100);
        res = Solution.addVaccine(v1);
        assertEquals(OK, res);

        result = Solution.getTotalNumberOfWorkingVaccines();
        assertEquals(20, result);

        res = Solution.vaccineProduced(5, 10);
        assertEquals(OK, res);

        res = Solution.vaccineProduced(5, 10);
        assertEquals(OK, res);

        //the productivity of vacc 5 is now under 20
        result = Solution.getTotalNumberOfWorkingVaccines();
        assertEquals(0, result);

        //the productivity of vacc 2 is now over 20
        res = Solution.vaccineSold(2, 30);
        assertEquals(OK, res);

        result = Solution.getTotalNumberOfWorkingVaccines();
        assertEquals(70, result);

    }


    @Test
    public void TotalWagesTest() {

        System.out.println("Test: TotalWagesTest\n");

        //before there are any labs
        int result = Solution.getTotalWages(1);
        assertEquals(0, result);

        Lab s1 = new Lab();
        s1.setId(2);
        s1.setName("CS");
        s1.setCity("Haifa");
        s1.setIsActive(true);

        ReturnValue res = Solution.addLab(s1);
        assertEquals(OK, res);

        Employee a = new Employee();
        a.setId(2);
        a.setName("Roei");
        a.setCity("Haifa");
        ReturnValue ret = Solution.addEmployee(a);
        assertEquals(OK, ret);

        res = Solution.employeeJoinLab(2, 2, 100);
        assertEquals(OK, res);

        //only 1 employee in lab
        result = Solution.getTotalWages(2);
        assertEquals(0, result);

        Employee a1 = new Employee();
        a1.setId(1);
        a1.setName("Ido");
        a1.setCity("Haifa");
        ret = Solution.addEmployee(a1);
        assertEquals(OK, ret);

        res = Solution.employeeJoinLab(1, 2, 50);
        assertEquals(OK, res);

        result = Solution.getTotalWages(2);
        assertEquals(150, result);

        res = Solution.employeeLeftLab(2, 2);
        assertEquals(OK, res);

        //only 1 employee
        result = Solution.getTotalWages(2);
        assertEquals(0, result);

        res = Solution.employeeJoinLab(2, 2, 0);
        assertEquals(OK, res);

        result = Solution.getTotalWages(2);
        assertEquals(50, result);

        res = Solution.employeeLeftLab(2, 1);
        assertEquals(OK, res);

        res = Solution.employeeLeftLab(2, 2);
        assertEquals(OK, res);

        //there are no more workers in the lab
        result = Solution.getTotalWages(2);
        assertEquals(0, result);

        //check inactive lab
        Lab l = new Lab();
        l.setId(17);
        l.setName("CS");
        l.setCity("Haifa");
        l.setIsActive(false);

        res = Solution.addLab(l);
        assertEquals(OK, res);

        Employee a2 = new Employee();
        a2.setId(17);
        a2.setName("Roei");
        a2.setCity("Haifa");
        ret = Solution.addEmployee(a2);
        assertEquals(OK, ret);

        res = Solution.employeeJoinLab(17, 17, 100);
        assertEquals(OK, res);

        res = Solution.employeeJoinLab(1, 17, 100);
        assertEquals(OK, res);

        result = Solution.getTotalWages(17);
        assertEquals(0, result);

    }


    @Test
    public void BestLabTest() {

        System.out.println("Test: BestLabTest\n");

        //before there are any labs and employees
        int result = Solution.getBestLab();
        assertEquals(0, result);

        Lab s1 = new Lab();
        s1.setId(2);
        s1.setName("CS");
        s1.setCity("Haifa");
        s1.setIsActive(true);

        ReturnValue res = Solution.addLab(s1);
        assertEquals(OK, res);

        Employee a = new Employee();
        a.setId(2);
        a.setName("Roei");
        a.setCity("Haifa");
        ReturnValue ret = Solution.addEmployee(a);
        assertEquals(OK, ret);

        res = Solution.employeeJoinLab(2, 2, 100);
        assertEquals(OK, res);

        Employee a1 = new Employee();
        a1.setId(1);
        a1.setName("Ido");
        a1.setCity("Petach-Tikva");
        ret = Solution.addEmployee(a1);
        assertEquals(OK, ret);

        res = Solution.employeeJoinLab(1, 2, 50);
        assertEquals(OK, res);

        //now there are 1 employee with same city in lab 2
        result = Solution.getBestLab();
        assertEquals(2, result);

        Lab l = new Lab();
        l.setId(1);
        l.setName("EE");
        l.setCity("Tel-Aviv");
        l.setIsActive(false);

        res = Solution.addLab(l);
        assertEquals(OK, res);

        Employee a2 = new Employee();
        a2.setId(17);
        a2.setName("Ido");
        a2.setCity("Tel-Aviv");
        ret = Solution.addEmployee(a2);
        assertEquals(OK, ret);

        res = Solution.employeeJoinLab(17, 1, 100);
        assertEquals(OK, res);

        //case of eqality smallest Id should return
        result = Solution.getBestLab();
        assertEquals(1, result);

        res = Solution.employeeLeftLab(1, 17);
        assertEquals(OK, res);

        //now only in lab 2 the condition exists
        result = Solution.getBestLab();
        assertEquals(2, result);

        Lab l3 = new Lab();
        l3.setId(3);
        l3.setName("TheCoolLab");
        l3.setCity("Petach-Tikva");
        l3.setIsActive(false);

        res = Solution.addLab(l3);
        assertEquals(OK, res);

        Employee e [] = new Employee[2];
        for(int i=0; i<2;i++)
        {
            e[i] = new Employee();
            e[i].setId(18+i);
            e[i].setName("Ido");
            e[i].setCity("Petach-Tikva");
            ret = Solution.addEmployee(e[i]);
            assertEquals(OK, ret);
        }
        res = Solution.employeeJoinLab(18, 3, 100);
        assertEquals(OK, res);

        res = Solution.employeeJoinLab(19, 3, 20);
        assertEquals(OK, res);

        result = Solution.getBestLab();
        assertEquals(3, result);

        res = Solution.employeeLeftLab(3, 19);
        assertEquals(OK, res);

        //now a case of equality between lab 3 and lab 2
        result = Solution.getBestLab();
        assertEquals(2, result);

    }


    @Test
    public void IncomeFromVaccineTest() {

        System.out.println("Test: IncomeFromVaccineTest\n");
        ReturnValue res;

        Vaccine v[] = new Vaccine[3];
        for(int i=0; i<v.length; i++)
        {
            v[i]= new Vaccine();
            v[i].setId(i+1);
            v[i].setName("COVID-"+(i+1));
            v[i].setCost(10*(i+1));
            v[i].setProductivity(10);
            v[i].setUnits(100*(i+1));
            res = Solution.addVaccine(v[i]);
            assertEquals(OK, res);
        }

        int result = Solution.getIncomeFromVaccine(1);
        assertEquals(0, result);

        res = Solution.vaccineSold(1,10);
        assertEquals(OK, res);


        result = Solution.getIncomeFromVaccine(1);
        assertEquals(100, result);

        res = Solution.vaccineSold(1,10);
        assertEquals(OK, res);

        result = Solution.getIncomeFromVaccine(1);
        assertEquals(300, result);

        res = Solution.vaccineProduced(1,100);
        assertEquals(OK, res);

        res = Solution.vaccineSold(1,10);
        assertEquals(OK, res);

        result = Solution.getIncomeFromVaccine(1);
        assertEquals(500, result);

        result = Solution.getIncomeFromVaccine(2);
        assertEquals(0, result);

        res = Solution.vaccineProduced(2,10);
        assertEquals(OK, res);

        result = Solution.getIncomeFromVaccine(2);
        assertEquals(0, result);

        res = Solution.vaccineSold(2,50);
        assertEquals(OK, res);

        result = Solution.getIncomeFromVaccine(2);
        assertEquals(500, result);

        result = Solution.getIncomeFromVaccine(1);
        assertEquals(500, result);

        res = Solution.vaccineSold(3,10);
        assertEquals(OK, res);

        result = Solution.getIncomeFromVaccine(3);
        assertEquals(300, result);

        res = Solution.vaccineSold(3,10);
        assertEquals(OK, res);

        result = Solution.getIncomeFromVaccine(3);
        assertEquals(900, result);


    }

    @Test
    public void MostPopularCity() {

        System.out.println("Test: MostPopularCity\n");

        //before there are any labs and employees
        String result = Solution.getMostPopularCity();
        assertEquals("", result);

        Lab l = new Lab();
        l.setId(1);
        l.setName("EE");
        l.setCity("Tel-Aviv");
        l.setIsActive(false);
        ReturnValue res = Solution.addLab(l);
        assertEquals(OK, res);

        Lab s1 = new Lab();
        s1.setId(2);
        s1.setName("CS");
        s1.setCity("Haifa");
        s1.setIsActive(true);
        res = Solution.addLab(s1);
        assertEquals(OK, res);

        Employee a = new Employee();
        a.setId(2);
        a.setName("Ido");
        a.setCity("Petach-Tikva");
        ReturnValue ret = Solution.addEmployee(a);
        assertEquals(OK, ret);

        res = Solution.employeeJoinLab(2, 2, 100);
        assertEquals(OK, res);

        result = Solution.getMostPopularCity();
        assertEquals("Petach-Tikva", result);

        Employee a1 = new Employee();
        a1.setId(1);
        a1.setName("Moriya");
        a1.setCity("Ashdod");
        ret = Solution.addEmployee(a1);
        assertEquals(OK, ret);

        res = Solution.employeeJoinLab(1, 2, 50);
        assertEquals(OK, res);

        //case of equality return by last lex order
        result = Solution.getMostPopularCity();
        assertEquals("Petach-Tikva", result);

        res = Solution.employeeJoinLab(1, 1, 100);
        assertEquals(OK, res);

        //employee 1 works in two labs so need to be count twice
        result = Solution.getMostPopularCity();
        assertEquals("Ashdod", result);

        res = Solution.employeeLeftLab(1, 1);
        assertEquals(OK, res);
        res = Solution.employeeLeftLab(2, 1);
        assertEquals(OK, res);

        //only from petach tikva there are employees
        result = Solution.getMostPopularCity();
        assertEquals("Petach-Tikva", result);

        res = Solution.employeeLeftLab(2, 2);
        assertEquals(OK, res);

        //no more working employees
        result = Solution.getMostPopularCity();
        assertEquals("", result);

        Employee e1 = new Employee();
        e1.setId(3);
        e1.setName("Moriya");
        e1.setCity("Bat-Yam");
        ret = Solution.addEmployee(e1);
        assertEquals(OK, ret);

        res = Solution.employeeJoinLab(1, 2, 50);
        assertEquals(OK, res);

        //only from ashdod
        result = Solution.getMostPopularCity();
        assertEquals("Ashdod", result);

        res = Solution.employeeJoinLab(3, 2, 35);
        assertEquals(OK, res);

        //tie ashdod vs bat yam
        result = Solution.getMostPopularCity();
        assertEquals("Bat-Yam", result);

        Employee a2 = new Employee();
        a2.setId(17);
        a2.setName("Lia");
        a2.setCity("Ashdod");
        ret = Solution.addEmployee(a2);
        assertEquals(OK, ret);

        res = Solution.employeeJoinLab(17, 1, 100);
        assertEquals(OK, res);

        result = Solution.getMostPopularCity();
        assertEquals("Ashdod", result);

        Employee e [] = new Employee[3];
        for(int i=0; i<3;i++)
        {
            e[i] = new Employee();
            e[i].setId(18+i);
            e[i].setName("yarden");
            e[i].setCity("Shoam");
            ret = Solution.addEmployee(e[i]);
            assertEquals(OK, ret);
            res = Solution.employeeJoinLab(18+i, 1, 100);
            assertEquals(OK, res);
        }

        result = Solution.getMostPopularCity();
        assertEquals("Shoam", result);

    }

      /*  @Test
    public void MostPopularCity() {
        THIS IS ACCORDING TO THE NONUPDATE
        System.out.println("Test: MostPopularCity\n");

        //before there are any labs and employees
        String result = Solution.getMostPopularCity();
        assertEquals("", result);

        Lab l = new Lab();
        l.setId(1);
        l.setName("EE");
        l.setCity("Tel-Aviv");
        l.setIsActive(false);
        ReturnValue res = Solution.addLab(l);
        assertEquals(OK, res);

        Lab s1 = new Lab();
        s1.setId(2);
        s1.setName("CS");
        s1.setCity("Haifa");
        s1.setIsActive(true);
        res = Solution.addLab(s1);
        assertEquals(OK, res);

        Lab s2 = new Lab();
        s2.setId(3);
        s2.setName("CE");
        s2.setCity("Ashdod");
        s2.setIsActive(true);
        res = Solution.addLab(s2);
        assertEquals(OK, res);

        ReturnValue ret;

        Employee e [] = new Employee[10];
        for(int i=0; i<e.length;i++)
        {
            e[i] = new Employee();
            e[i].setId(i+1);
            e[i].setName("Ido"+i);
            e[i].setCity("Shoam");
            ret = Solution.addEmployee(e[i]);
            assertEquals(OK, ret);
        }

        //before there are any labs and employees
        result = Solution.getMostPopularCity();
        assertEquals("", result);

        res = Solution.employeeJoinLab(1, 2, 100);
        assertEquals(OK, res);

        result = Solution.getMostPopularCity();
        assertEquals("Haifa", result);

        res = Solution.employeeJoinLab(1, 3, 100);
        assertEquals(OK, res);

        //case of equality return by lex order
        result = Solution.getMostPopularCity();
        assertEquals("Ashdod", result);

        res = Solution.employeeJoinLab(2, 2, 100);
        assertEquals(OK, res);

        result = Solution.getMostPopularCity();
        assertEquals("Haifa", result);

        res = Solution.employeeJoinLab(3, 1, 100);
        assertEquals(OK, res);

        result = Solution.getMostPopularCity();
        assertEquals("Haifa", result);

        res = Solution.employeeJoinLab(4, 1, 100);
        assertEquals(OK, res);

        result = Solution.getMostPopularCity();
        assertEquals("Haifa", result);

        res = Solution.employeeJoinLab(5, 1, 100);
        assertEquals(OK, res);

        result = Solution.getMostPopularCity();
        assertEquals("Tel-Aviv", result);

        res = Solution.employeeJoinLab(6, 2, 100);
        assertEquals(OK, res);

        result = Solution.getMostPopularCity();
        assertEquals("Haifa", result);

        res = Solution.employeeJoinLab(6, 3, 100);
        assertEquals(OK, res);

        result = Solution.getMostPopularCity();
        assertEquals("Haifa", result);

        res = Solution.employeeJoinLab(5, 3, 100);
        assertEquals(OK, res);

        result = Solution.getMostPopularCity();
        assertEquals("Ashdod", result);

        res = Solution.employeeLeftLab(3, 6);
        assertEquals(OK, res);

        result = Solution.getMostPopularCity();
        assertEquals("Haifa", result);

        res = Solution.employeeLeftLab(2, 6);
        assertEquals(OK, res);

        result = Solution.getMostPopularCity();
        assertEquals("Tel-Aviv", result);

    } */
}


