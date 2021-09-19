package corona.extra;

import corona.business.Employee;
import corona.business.Lab;
import corona.business.ReturnValue;
import corona.business.Vaccine;
import corona.Solution;
import org.junit.Test;

import java.util.ArrayList;

import static corona.business.ReturnValue.*;
import static org.junit.Assert.assertEquals;


// Written by: Joseph Hakim
// Extended/modified by Tzuriel Mazuz
public class LargeTest extends AbstractTest{
	@Test
	public void SimpleUnitsTest() {
		Solution.clearTables();
    	Vaccine a = new Vaccine();
    	a.setCost(200);
    	a.setId(8788);
    	a.setName("corona Vaccine");
    	a.setProductivity(30);
    	a.setUnits(100);
    	ReturnValue ret = Solution.addVaccine(a);
        assertEquals(OK, ret);
        
    	Vaccine b = new Vaccine();
    	b.setCost(200);
    	b.setId(15357);
    	b.setName("corona Vaccine");
    	b.setProductivity(30);
    	b.setUnits(250);
    	ret = Solution.addVaccine(b);
        assertEquals(OK, ret);
        
        Integer ExpectedResult = 350;
        Integer ReceivedResult = 0;
        ReceivedResult = Solution.getTotalNumberOfWorkingVaccines();
        assertEquals(ExpectedResult, ReceivedResult);
        
        ret = Solution.vaccineSold(8788, 50);
        assertEquals(OK, ret);
        ExpectedResult = 300;
        ReceivedResult = Solution.getTotalNumberOfWorkingVaccines();
        assertEquals(ExpectedResult, ReceivedResult);
        
        Solution.vaccineProduced(15357, 700);
        assertEquals(OK, ret);
        
        ExpectedResult = 50;
        ReceivedResult = Solution.getTotalNumberOfWorkingVaccines();
        assertEquals(ExpectedResult, ReceivedResult);
        
        Solution.vaccineProduced(8788, 700);
        assertEquals(OK, ret);
        
        ExpectedResult = 750;
        ReceivedResult = Solution.getTotalNumberOfWorkingVaccines();
        assertEquals(ExpectedResult, ReceivedResult);
        
        Solution.vaccineProduced(8788, 900);
        assertEquals(OK, ret);
        
        ExpectedResult = 0;
        ReceivedResult = Solution.getTotalNumberOfWorkingVaccines();
        assertEquals(ExpectedResult, ReceivedResult);
        
        Solution.deleteVaccine(a);
        assertEquals(OK, ret);
        
        Solution.deleteVaccine(b);
        assertEquals(OK, ret);
        
        
	}
    @Test
    public void simpleTestLab()
    {
		Solution.clearTables();
        Lab a = new Lab();
        a.setId(1);
        a.setName("Technion");
        a.setCity("Haifa");
        a.setIsActive(true);
        ReturnValue ret = Solution.addLab(a);
        assertEquals(OK, ret);
        
    	Lab resultLab = Solution.getLabProfile(1);
        
        Lab b = new Lab();
        b.setId(100);
        b.setName("Technion");
        b.setCity("Haifa");
        b.setIsActive(true);
        ret = Solution.addLab(b);
        assertEquals(OK, ret);
        
        Lab c = new Lab();
        c.setId(200);
        c.setName("Technion");
        c.setCity("Haifa");
        c.setIsActive(true);
        ret = Solution.addLab(c);
        assertEquals(OK, ret);
        
        ret = Solution.deleteLab(a);
        assertEquals(OK, ret);
        
        ret = Solution.deleteLab(b);
        assertEquals(OK, ret);
        
        ret = Solution.deleteLab(c);
        assertEquals(OK, ret);
        
        ret = Solution.deleteLab(a);
        assertEquals(NOT_EXISTS, ret);
         
    }
    
    @Test
    public void EmployeeTest()
    {
		Solution.clearTables();
    	Employee a = new Employee();
    	a.setId(1);
    	a.setName("joseph");
    	a.setCity("Nazareth");
        ReturnValue ret = Solution.addEmployee(a);
        assertEquals(OK, ret);
    	Employee b = new Employee();
    	b.setId(2);
    	b.setName("Waseem");
    	b.setCity("Israel");
        ret = Solution.addEmployee(b);
        assertEquals(OK, ret);
        
        ret = Solution.deleteEmployee(a);
        assertEquals(OK, ret);
        
        ret = Solution.deleteEmployee(b);
        assertEquals(OK, ret);
    }
    
    @Test
    public void AddWorkToEmployee() {
		Solution.clearTables();
    	Employee a = new Employee();
    	a.setId(1);
    	a.setName("joseph");
    	a.setCity("Nazareth");
        ReturnValue ret = Solution.addEmployee(a);
        assertEquals(OK, ret);
    	Employee b = new Employee();
    	b.setId(2);
    	b.setName("Waseem");
    	b.setCity("Israel");
        ret = Solution.addEmployee(b);
        assertEquals(OK, ret);
        
        Lab bl = new Lab();
        bl.setId(100);
        bl.setName("Technion");
        bl.setCity("Haifa");
        bl.setIsActive(true);
        ret = Solution.addLab(bl);
        assertEquals(OK, ret);
        
        Lab cl = new Lab();
        cl.setId(200);
        cl.setName("Technion");
        cl.setCity("Haifa");
        cl.setIsActive(true);
        ret = Solution.addLab(cl);
        assertEquals(OK, ret);
        
        
    	ret = Solution.employeeJoinLab(1,100,500);
        assertEquals(OK, ret);
    	ret = Solution.employeeJoinLab(1,200,500);
        assertEquals(OK, ret);
    	ret = Solution.employeeJoinLab(2,200,7000);
        assertEquals(OK, ret);
    	ret = Solution.employeeLeftLab(200,2);
        assertEquals(OK, ret);
    	ret = Solution.employeeLeftLab(200,2);
        assertEquals(NOT_EXISTS, ret);
        
        ret = Solution.deleteLab(cl);
        assertEquals(OK, ret);
        
    	ret = Solution.employeeLeftLab(200,1);
        assertEquals(NOT_EXISTS, ret);
        
    	ret = Solution.employeeLeftLab(100,1);
        assertEquals(OK, ret);
        
        ret = Solution.deleteLab(bl);
        assertEquals(OK, ret);
        
        ret = Solution.deleteEmployee(a);
        assertEquals(OK, ret);
        
        ret = Solution.deleteEmployee(b);
        assertEquals(OK, ret);
    }

    
    @Test 
    public void CascadeTestForLab() {
		Solution.clearTables();
        Lab a = new Lab();
        a.setId(50);
        a.setName("Technion");
        a.setCity("Haifa");
        a.setIsActive(true);
        ReturnValue ret = Solution.addLab(a);
        assertEquals(OK, ret);
        
    	Employee empa = new Employee();
    	empa.setId(40);
    	empa.setName("Joseph");
    	empa.setCity("Israel");
        ret = Solution.addEmployee(empa);
        assertEquals(OK, ret);
        
    	ret = Solution.employeeJoinLab(40,50,6000);
        assertEquals(OK, ret);
        
    	ret = Solution.employeeLeftLab(50,40);
        assertEquals(OK, ret);
        
    	ret = Solution.employeeJoinLab(40,50,6000);
        assertEquals(OK, ret);
        
        ret = Solution.deleteLab(a);
        assertEquals(OK, ret);  
        
        ret = Solution.deleteEmployee(empa);
        assertEquals(OK, ret);
        
    	ret = Solution.employeeLeftLab(50,40);
        assertEquals(NOT_EXISTS, ret);
        
    }
    
    
    @Test
    public void VaccineSellingTest() {
		Solution.clearTables();
    	Vaccine a = new Vaccine();
    	a.setCost(200);
    	a.setId(1);
    	a.setName("corona Vaccine");
    	a.setProductivity(30);
    	a.setUnits(100);
    	ReturnValue ret = Solution.addVaccine(a);
        assertEquals(OK, ret);
        
        Vaccine ReturnedVaccine = Solution.getVaccineProfile(1);
        assertEquals(true,a.equals(ReturnedVaccine));

        ret = Solution.vaccineSold(1, 50);
        assertEquals(OK, ret);
        System.out.println("We just sold 50 unit of Vaccine ID = 1 , ret = " + ret);
        
        ReturnedVaccine = Solution.getVaccineProfile(1);
        a.setUnits(50);
        a.setProductivity(45);
        a.setCost(400);
        assertEquals(true,a.equals(ReturnedVaccine));
        
        ReturnedVaccine =  Solution.getVaccineProfile(5878784); //non existant vaccine
        
        Vaccine badvaccine = Vaccine.badVaccine();
        assertEquals(true,badvaccine.equals(ReturnedVaccine));
        
        Solution.getIncomeFromVaccine(1);
        
        ret = Solution.vaccineSold(2, 50);
        System.out.println("We just sold 50 unit of Vaccine ID = 2 (not available) , ret = " + ret);
        assertEquals(NOT_EXISTS, ret);
        
        ret = Solution.vaccineSold(1, 51);
        System.out.println("We just sold 51 unit of Vaccine ID = 1 (not enough stock) , ret = " + ret);
        assertEquals(BAD_PARAMS, ret);
        
        Integer CurrIncome = Solution.getIncomeFromVaccine(1);
        Integer CurrExpectedResult = 10000;
        assertEquals(CurrExpectedResult, CurrIncome);
        ret = Solution.vaccineSold(1, 5);
        System.out.println("We just sold 5 unit of Vaccine ID = 1 (enough stock) , ret = " + ret);
        Solution.getVaccineProfile(1);
        assertEquals(OK, ret);
        
        CurrIncome = Solution.getIncomeFromVaccine(1);
        CurrExpectedResult = 10000 + 400*5;
        assertEquals(CurrExpectedResult, CurrIncome);
        ret = Solution.vaccineSold(1, 5);
        System.out.println("We just sold 5 unit of Vaccine ID = 1 (enough stock) , ret = " + ret);
        Solution.getVaccineProfile(1);
        assertEquals(OK, ret);
        
        ret = Solution.vaccineSold(1, 5);
        System.out.println("We just sold 5 unit of Vaccine ID = 1 (enough stock) , ret = " + ret);
        Solution.getVaccineProfile(1);
        assertEquals(OK, ret);
        
        ret = Solution.vaccineSold(1, 5);
        System.out.println("We just sold 5 unit of Vaccine ID = 1 (enough stock) , ret = " + ret);
        Solution.getVaccineProfile(1);
        assertEquals(OK, ret);
        
        ret = Solution.vaccineProduced(1, 5);
        System.out.println("We just produced 5 unit of Vaccine ID = 1 , ret = " + ret);
        Solution.getVaccineProfile(1);
        
        ret = Solution.vaccineProduced(3, 5);
        System.out.println("We just produced 5 unit of Vaccine ID = 3 (doesn't exist) , ret = " + ret);
        Solution.getVaccineProfile(1);
        
        ret = Solution.deleteVaccine(a);
        assertEquals(OK, ret);
        ret = Solution.deleteVaccine(a);
        assertEquals(NOT_EXISTS, ret);
        
    }
    
    @Test 
    public void isLabPopularTest(){
		Solution.clearTables();
    	Vaccine a = new Vaccine();
    	a.setCost(200);
    	a.setId(700);
    	a.setName("corona Vaccine");
    	a.setProductivity(30);
    	a.setUnits(100);
    	ReturnValue ret = Solution.addVaccine(a);
        assertEquals(OK, ret);
        
        Lab b = new Lab();
        b.setId(90);
        b.setName("Technion");
        b.setCity("Haifa");
        b.setIsActive(true);
        ret = Solution.addLab(b);
        assertEquals(OK, ret);
        
        ret = Solution.labProduceVaccine(700, 90);
        assertEquals(OK, ret);

        Boolean CurrExpectedResult = true;
        Boolean CurrReceivedResult;
        CurrReceivedResult = Solution.isLabPopular(90);
        //assertEquals(CurrExpectedResult, CurrReceivedResult);
        
    	Vaccine c = new Vaccine();
    	c.setCost(200);
    	c.setId(720);
    	c.setName("corona Vaccine");
    	c.setProductivity(10);
    	c.setUnits(100);
    	ret = Solution.addVaccine(c);
        assertEquals(OK, ret);
        
        
        
        ret = Solution.labProduceVaccine(720, 90);
        assertEquals(OK, ret);
        
        CurrExpectedResult = false;
        CurrReceivedResult = Solution.isLabPopular(90);
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        ret = Solution.labStoppedProducingVaccine(90, 720);
        assertEquals(OK, ret);
        
        CurrExpectedResult = true;
        CurrReceivedResult = Solution.isLabPopular(90);
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        ret = Solution.labProduceVaccine(720, 90);
        assertEquals(OK, ret);
        
        CurrExpectedResult = false;
        CurrReceivedResult = Solution.isLabPopular(90);
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        ret = Solution.labStoppedProducingVaccine(90, 720);
        assertEquals(OK, ret);
        
        CurrExpectedResult = true;
        CurrReceivedResult = Solution.isLabPopular(90);
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        ret = Solution.labProduceVaccine(720, 90);
        assertEquals(OK, ret);
        
        CurrExpectedResult = false;
        CurrReceivedResult = Solution.isLabPopular(90);
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        ret = Solution.deleteVaccine(c);
        assertEquals(OK, ret);
        
        CurrExpectedResult = true;
        CurrReceivedResult = Solution.isLabPopular(90);
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        ret = Solution.addVaccine(c);
        assertEquals(OK, ret);
       
        
        CurrExpectedResult = true;
        CurrReceivedResult = Solution.isLabPopular(90);
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        ret = Solution.labProduceVaccine(720, 90);
        assertEquals(OK, ret);
        
        CurrExpectedResult = false;
        CurrReceivedResult = Solution.isLabPopular(90);
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        ret = Solution.deleteLab(b);
        assertEquals(OK, ret);
        
        CurrExpectedResult = false;
        CurrReceivedResult = Solution.isLabPopular(90);
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        ret = Solution.addLab(b);
        assertEquals(OK, ret);
        
        CurrExpectedResult = true;
        CurrReceivedResult = Solution.isLabPopular(90);
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        ret = Solution.deleteLab(b);
        assertEquals(OK, ret);
        
        ret = Solution.deleteVaccine(c);
        assertEquals(OK, ret);
        
        ret = Solution.deleteVaccine(a);
        assertEquals(OK, ret);
    }
    
    @Test
    public void ClearTables() {
    	Solution.clearTables();
    }
    
    @Test
    public void WagesTest() {
		Solution.clearTables();
        Lab b = new Lab();
        b.setId(222);
        b.setName("Technion");
        b.setCity("Haifa");
        b.setIsActive(true);
        ReturnValue ret = Solution.addLab(b);
        assertEquals(OK, ret);
        
    	Employee a = new Employee();
    	a.setId(1700);
    	a.setName("joseph");
    	a.setCity("Nazareth");
        ret = Solution.addEmployee(a);
        assertEquals(OK, ret);
        
    	Employee c = new Employee();
    	c.setId(1600);
    	c.setName("Waseem");
    	c.setCity("Israel");
        ret = Solution.addEmployee(c);
        assertEquals(OK, ret);
        
        
        Integer CurrExpectedResult = 0;
        Integer CurrReceivedResult;
        CurrReceivedResult = Solution.getTotalWages(222);
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
    	ret = Solution.employeeJoinLab(1700,222,6000);
        assertEquals(OK, ret);
        
        CurrExpectedResult = 0;
        CurrReceivedResult = Solution.getTotalWages(222);
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
    	ret = Solution.employeeJoinLab(1600,222,1000);
        assertEquals(OK, ret);
        
        CurrExpectedResult = 6000+1000;
        CurrReceivedResult = Solution.getTotalWages(222);
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        Lab d = new Lab();
        d.setId(333);
        d.setName("Technion");
        d.setCity("Haifa");
        d.setIsActive(true);
        ret = Solution.addLab(d);
        assertEquals(OK, ret);
        
    	ret = Solution.employeeJoinLab(1600,333,1000);
        assertEquals(OK, ret);
        
        CurrExpectedResult = 6000+1000;
        CurrReceivedResult = Solution.getTotalWages(222);
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        CurrExpectedResult = 0;
        CurrReceivedResult = Solution.getTotalWages(333);
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
    	ret = Solution.employeeJoinLab(1700,333,6000);
        assertEquals(OK, ret);
        
        CurrExpectedResult = 6000+1000;
        CurrReceivedResult = Solution.getTotalWages(333);
        assertEquals(CurrExpectedResult, CurrReceivedResult);
            
    	ret = Solution.employeeLeftLab(222,1600);
        assertEquals(OK, ret);
        
        CurrExpectedResult = 0;
        CurrReceivedResult = Solution.getTotalWages(222);
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        CurrExpectedResult = 6000+1000;
        CurrReceivedResult = Solution.getTotalWages(333);
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        ret = Solution.deleteLab(d);
        assertEquals(OK, ret);
        
        CurrExpectedResult = 0;
        CurrReceivedResult = Solution.getTotalWages(333);
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        ret = Solution.deleteLab(b);
        assertEquals(OK, ret);
        
        ret = Solution.deleteEmployee(a);
        assertEquals(OK, ret);
        
        ret = Solution.deleteEmployee(c);
        assertEquals(OK, ret);

    }
    
    @Test
    public void BestLabCheck() {
		Solution.clearTables();
        Lab b = new Lab();
        b.setId(9785);
        b.setName("Technion");
        b.setCity("Haifa");
        b.setIsActive(true);
        ReturnValue ret = Solution.addLab(b);
        assertEquals(OK, ret);        
        
        Integer CurrExpectedResult = 0;
        Integer CurrReceivedResult;
        CurrReceivedResult = Solution.getBestLab();
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        CurrExpectedResult = 0;
        CurrReceivedResult = Solution.getBestLab();
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
    	Employee a = new Employee();
    	a.setId(1700);
    	a.setName("joseph");
    	a.setCity("Nazareth");
        ret = Solution.addEmployee(a);
        assertEquals(OK, ret);
        
        CurrExpectedResult = 0;
        CurrReceivedResult = Solution.getBestLab();
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
    	Employee c = new Employee();
    	c.setId(1600);
    	c.setName("waseem");
    	c.setCity("Haifa");
        ret = Solution.addEmployee(c);
        assertEquals(OK, ret);
        
        CurrExpectedResult = 0;
        CurrReceivedResult = Solution.getBestLab();
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        Lab d = new Lab();
        d.setId(9000);
        d.setName("Technion");
        d.setCity("Karmel");
        d.setIsActive(true);
        ret = Solution.addLab(d);
        assertEquals(OK, ret);  
        
    	Employee e = new Employee();
    	e.setId(1800);
    	e.setName("george");
    	e.setCity("Karmel");
        ret = Solution.addEmployee(e);
        assertEquals(OK, ret);

        Solution.employeeJoinLab(1800,9000,1000);
        CurrExpectedResult = 9000;
        CurrReceivedResult = Solution.getBestLab();
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
    	Employee f = new Employee();
    	f.setId(1900);
    	f.setName("george");
    	f.setCity("Haifa");
        ret = Solution.addEmployee(f);
        assertEquals(OK, ret);

        Solution.employeeJoinLab(1600,9785,1000);
        Solution.employeeJoinLab(1900,9785,1000);
        CurrExpectedResult = 9785;
        CurrReceivedResult = Solution.getBestLab();
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        Solution.deleteEmployee(f);
        assertEquals(OK, ret);
        
        CurrExpectedResult = 9000;
        CurrReceivedResult = Solution.getBestLab();
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        ret = Solution.addEmployee(f);
        assertEquals(OK, ret);
        
        CurrExpectedResult = 9000;
        CurrReceivedResult = Solution.getBestLab();
        assertEquals(CurrExpectedResult, CurrReceivedResult);

        Solution.employeeJoinLab(1900,9785,1000);
        CurrExpectedResult = 9785;
        CurrReceivedResult = Solution.getBestLab();
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        Solution.deleteLab(b);
        assertEquals(OK, ret);
        
        CurrExpectedResult = 9000;
        CurrReceivedResult = Solution.getBestLab();
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        Solution.deleteLab(d);
        assertEquals(OK, ret);
        
        CurrExpectedResult = 0;
        CurrReceivedResult = Solution.getBestLab();
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        ret = Solution.deleteEmployee(f);
        assertEquals(OK, ret);
        
        ret = Solution.deleteEmployee(e);
        assertEquals(OK, ret);
        
        ret = Solution.deleteEmployee(c);
        assertEquals(OK, ret);
        
        ret = Solution.deleteEmployee(a);
        assertEquals(OK, ret);
        
        CurrExpectedResult = 0;
        CurrReceivedResult = Solution.getBestLab();
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
    }
    
    @Test 
    public void mostpopularcitytest() {
		Solution.clearTables();
        Lab b = new Lab();
        b.setId(9785);
        b.setName("Technion");
        b.setCity("Nazareth");
        b.setIsActive(true);
        ReturnValue ret = Solution.addLab(b);
        assertEquals(OK, ret);     
        
        Lab d = new Lab();
        d.setId(9000);
        d.setName("Technion");
        d.setCity("Nesher");
        d.setIsActive(true);
        ret = Solution.addLab(d);
        assertEquals(OK, ret);  
        
    	Employee f = new Employee();
    	f.setId(1900);
    	f.setName("george");
    	f.setCity("Haifa");
        ret = Solution.addEmployee(f);
        assertEquals(OK, ret);
        
    	Employee e = new Employee();
    	e.setId(1800);
    	e.setName("george");
    	e.setCity("Karmel");
        ret = Solution.addEmployee(e);
        assertEquals(OK, ret);
        
        
    	Employee c = new Employee();
    	c.setId(1600);
    	c.setName("waseem");
    	c.setCity("Haifa");
        ret = Solution.addEmployee(c);
        assertEquals(OK, ret);
        
        String CurrExpectedResult = "";
        String CurrReceivedResult;
        CurrReceivedResult = Solution.getMostPopularCity();
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        ret = Solution.employeeJoinLab(1800, 9000, 50);
        assertEquals(OK, ret);
        
        CurrExpectedResult = "Karmel";
        CurrReceivedResult = Solution.getMostPopularCity();
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        ret = Solution.employeeJoinLab(1600, 9785, 50);
        assertEquals(OK, ret);
        
        CurrExpectedResult = "Karmel";
        CurrReceivedResult = Solution.getMostPopularCity();
        assertEquals(CurrExpectedResult, CurrReceivedResult);
        
        ret = Solution.employeeJoinLab(1600, 9000, 50);
        assertEquals(OK, ret);
        
        CurrExpectedResult = "Haifa";
        CurrReceivedResult = Solution.getMostPopularCity();
        assertEquals(CurrExpectedResult, CurrReceivedResult);
    }
    
    @Test
    public void popularlabstest() {
		Solution.clearTables();
        Lab b = new Lab();
        b.setId(9785);
        b.setName("Technion");
        b.setCity("Haifa");
        b.setIsActive(true);
        ReturnValue ret = Solution.addLab(b);
        assertEquals(OK, ret);
        
    	Vaccine a = new Vaccine();
    	a.setCost(200);
    	a.setId(8788);
    	a.setName("corona Vaccine");
    	a.setProductivity(15);
    	a.setUnits(100);
    	ret = Solution.addVaccine(a);
        assertEquals(OK, ret);
        
    	Vaccine c = new Vaccine();
    	c.setCost(200);
    	c.setId(15357);
    	c.setName("corona Vaccine");
    	c.setProductivity(30);
    	c.setUnits(250);
    	ret = Solution.addVaccine(c);
        assertEquals(OK, ret);
        
        Lab d = new Lab();
        d.setId(9358);
        d.setName("Technion");
        d.setCity("Haifa");
        d.setIsActive(true);
        ret = Solution.addLab(d);
        assertEquals(OK, ret);
        
        Lab f = new Lab();
        f.setId(6589);
        f.setName("Technion");
        f.setCity("Haifa");
        f.setIsActive(true);
        ret = Solution.addLab(f);
        assertEquals(OK, ret);
        
        Lab g = new Lab();
        g.setId(2222);
        g.setName("Technion");
        g.setCity("Haifa");
        g.setIsActive(true);
        ret = Solution.addLab(g);
        assertEquals(OK, ret);
        
        ArrayList<Integer> ExpectedArray = new ArrayList<>() ;
        ArrayList<Integer> ReceivedArray;
        
        ReceivedArray = Solution.getPopularLabs();
        assertEquals(ExpectedArray, ReceivedArray);
        
    	ret = Solution.labProduceVaccine(15357, 9785);
        assertEquals(OK, ret);
       
        
        ExpectedArray.add(9785);
        ReceivedArray = Solution.getPopularLabs();
        assertEquals(ExpectedArray, ReceivedArray);
        
    	ret = Solution.labProduceVaccine(8788, 9785);
        assertEquals(OK, ret);
        
        ExpectedArray.remove(0);
        ReceivedArray = Solution.getPopularLabs();
        assertEquals(ExpectedArray, ReceivedArray);
        
    	ret = Solution.labStoppedProducingVaccine(9785, 8788);
        assertEquals(OK, ret);
        
        ExpectedArray.add(9785);
        ReceivedArray = Solution.getPopularLabs();
        assertEquals(ExpectedArray, ReceivedArray);
        
    	ret = Solution.labProduceVaccine(8788, 9785);
        assertEquals(OK, ret);
        
        ret = Solution.vaccineSold(8788, 20);
        assertEquals(OK, ret);
        
        ReceivedArray = Solution.getPopularLabs();
        assertEquals(ExpectedArray, ReceivedArray);
        
    	ret = Solution.labProduceVaccine(8788, 15357);
        assertEquals(NOT_EXISTS, ret);
        
    	ret = Solution.labProduceVaccine(8788, 9358);
        assertEquals(OK, ret);
        
    	ret = Solution.labProduceVaccine(8788, 6589);
        assertEquals(OK, ret);
        
    	ret = Solution.labProduceVaccine(8788, 2222);
        assertEquals(OK, ret);
        
        ExpectedArray.remove(0);
        ExpectedArray.add(2222);
        ExpectedArray.add(6589);
        ExpectedArray.add(9358);
        ReceivedArray = Solution.getPopularLabs();
        assertEquals(ExpectedArray, ReceivedArray);
        
    	ret = Solution.labStoppedProducingVaccine(9358, 8788);
        assertEquals(OK, ret);
        
        ExpectedArray.remove(2);
        ExpectedArray.add(9785);
        ReceivedArray = Solution.getPopularLabs();
        assertEquals(ExpectedArray, ReceivedArray);

        ret = Solution.deleteLab(g);
        assertEquals(OK, ret);
        ret = Solution.deleteLab(f);
        assertEquals(OK, ret);
        ret = Solution.deleteLab(d);
        assertEquals(OK, ret);
        ret = Solution.deleteLab(b);
        assertEquals(OK, ret);
        
        ret = Solution.deleteVaccine(a);
        assertEquals(OK, ret);
        
        ret = Solution.deleteVaccine(c);
        assertEquals(OK, ret);
        
    }
    
    @Test 
    public void getMostRatedVaccinesTest() {
		Solution.clearTables();
    	Vaccine a = new Vaccine();
    	a.setCost(200);
    	a.setId(12000);
    	a.setName("corona Vaccine");
    	a.setProductivity(15);
    	a.setUnits(100);
        
        ArrayList<Integer> ExpectedArray = new ArrayList<>() ;
        ArrayList<Integer> ReceivedArray;
        ReceivedArray = Solution.getMostRatedVaccines();
        assertEquals(ExpectedArray, ReceivedArray);
        
    	ReturnValue ret = Solution.addVaccine(a);
        assertEquals(OK, ret);
        
        ExpectedArray.add(12000);
        ReceivedArray = Solution.getMostRatedVaccines();
        assertEquals(ExpectedArray, ReceivedArray);
        
        ret = Solution.deleteVaccine(a);
        assertEquals(OK, ret);
        
    }
    
    @Test
    public void CloseEmployeeTest() {
		Solution.clearTables();
    	Employee a = new Employee();
    	a.setId(2500);
    	a.setName("joseph");
    	a.setCity("Nazareth");
        ReturnValue ret = Solution.addEmployee(a);
        assertEquals(OK, ret);
        
        Lab b = new Lab();
        b.setId(3500);
        b.setName("Technion");
        b.setCity("Haifa");
        b.setIsActive(true);
        ret = Solution.addLab(b);
        assertEquals(OK, ret);
        
    	Employee c = new Employee();
    	c.setId(2600);
    	c.setName("waseem");
    	c.setCity("Haifa");
        ret = Solution.addEmployee(c);
        assertEquals(OK, ret);
        
        Lab d = new Lab();
        d.setId(3600);
        d.setName("Technion");
        d.setCity("Haifa");
        d.setIsActive(true);
        ret = Solution.addLab(d);
        assertEquals(OK, ret);
        
        Lab e = new Lab();
        e.setId(3700);
        e.setName("Technion");
        e.setCity("Haifa");
        e.setIsActive(true);
        ret = Solution.addLab(e);
        assertEquals(OK, ret);
        
    	Employee f = new Employee();
    	f.setId(2700);
    	f.setName("waseem");
    	f.setCity("Haifa");
        ret = Solution.addEmployee(f);
        assertEquals(OK, ret);
        
        
        ArrayList<Integer> ExpectedArray = new ArrayList<>() ;
        ArrayList<Integer> ReceivedArray;
        ReceivedArray = Solution.getCloseEmployees(2501);
        assertEquals(ExpectedArray, ReceivedArray);

        ReceivedArray = Solution.getCloseEmployees(2500);
        ExpectedArray.add(2600);
        ExpectedArray.add(2700);
        assertEquals(ExpectedArray, ReceivedArray);
        

        ret = Solution.employeeJoinLab(2600, 3500, 2000); //2600 joined lab 3500
        assertEquals(OK,ret);

        ExpectedArray.clear();
        ReceivedArray = Solution.getCloseEmployees(2600);
        assertEquals(ExpectedArray, ReceivedArray);

        ret = Solution.employeeJoinLab(2500, 3600, 2000); //2500 joined lab 3600
        assertEquals(OK,ret);

        ExpectedArray.clear();
        ReceivedArray = Solution.getCloseEmployees(2500);
        assertEquals(ExpectedArray, ReceivedArray);
        
        ReceivedArray = Solution.getCloseEmployees(2600);
        assertEquals(ExpectedArray, ReceivedArray);
        
        
        ret = Solution.employeeJoinLab(2500, 3500, 2000); //2500 joined lab 3500
        assertEquals(OK,ret);
        
        ExpectedArray.add(2500);
        ReceivedArray = Solution.getCloseEmployees(2600);
        assertEquals(ExpectedArray, ReceivedArray);
        
        ExpectedArray.remove(0);
        ExpectedArray.add(2600);
        ReceivedArray = Solution.getCloseEmployees(2500);
        assertEquals(ExpectedArray, ReceivedArray);
        
        ret = Solution.employeeJoinLab(2700, 3600, 2000); //2700 joined lab 3600
        assertEquals(OK,ret);
        
        ExpectedArray.remove(0);
        ExpectedArray.add(2600);
        ExpectedArray.add(2700);
        ReceivedArray = Solution.getCloseEmployees(2500);
        assertEquals(ExpectedArray, ReceivedArray);
        
        
        ExpectedArray.remove(0);
        ExpectedArray.remove(0);
        
        //ExpectedArray is empty from here.
        ret = Solution.employeeJoinLab(2500, 3700, 2000); //2500 joined lab 3700
        assertEquals(OK,ret);
        
        ReceivedArray = Solution.getCloseEmployees(2500);
        assertEquals(ExpectedArray, ReceivedArray);
        
        ExpectedArray.add(2500);
        ReceivedArray = Solution.getCloseEmployees(2600);
        assertEquals(ExpectedArray, ReceivedArray);
        
        ReceivedArray = Solution.getCloseEmployees(2700);
        assertEquals(ExpectedArray, ReceivedArray);
        
    }
    
    @Test 
    public void ErrorCasesTest() {
		Solution.clearTables();
    	Vaccine a = new Vaccine();
    	a.setCost(200);
    	a.setId(8788);
    	a.setName("corona Vaccine");
    	a.setProductivity(30);
    	a.setUnits(-100);
    	ReturnValue ret = Solution.addVaccine(a);
        assertEquals(BAD_PARAMS, ret);
        
        a.setUnits(100);
        ret = Solution.addVaccine(a);
        assertEquals(OK, ret);
        
        ret = Solution.vaccineSold(8788, 10);
        assertEquals(OK, ret);

        ret = Solution.vaccineSold(8788, 5);
        assertEquals(OK, ret);
        
        ret = Solution.vaccineProduced(8788, 5);
        assertEquals(OK, ret);
        
        ret = Solution.vaccineProduced(8788, -1);
        assertEquals(BAD_PARAMS, ret);
        
        ret = Solution.vaccineProduced(8788, 0);
        assertEquals(OK, ret);
        
        Boolean BoolRet = Solution.isLabPopular(-1);
        assertEquals(false, BoolRet);
        
        BoolRet = Solution.isLabPopular(99998);
        assertEquals(false, BoolRet);
        
        Integer expectedInt = 0;
        Integer IntRet = Solution.getIncomeFromVaccine(-1);
        assertEquals(expectedInt, IntRet);
    }

}