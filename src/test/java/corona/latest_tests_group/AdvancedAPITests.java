
package corona.latest_tests_group;

import corona.AbstractTest;
import corona.Solution;
import corona.business.Employee;
import corona.business.Lab;
import corona.business.ReturnValue;
import corona.business.Vaccine;
import org.junit.Test;

import java.util.ArrayList;

import static corona.business.ReturnValue.*;
import static org.junit.Assert.assertEquals;


public class AdvancedAPITests extends AbstractTest {
    @Test
    public void popularLab() {

        System.out.println("Test: popularLab\n");
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
            v[i].setProductivity(23);
            v[i].setUnits(20*(i+1));
            res = Solution.addVaccine(v[i]);
            assertEquals(OK, res);
        }

        //no vaccines in all labs should return empty list
        ArrayList<Integer> retlist = Solution.getPopularLabs();
        assertEquals(0, retlist.size());

        res = Solution.labProduceVaccine(1,5);
        assertEquals(OK, res);
        //only one lab is popular
        retlist = Solution.getPopularLabs();
        assertEquals(1, retlist.size());
        assertEquals(5,(int)retlist.get(0));

        res = Solution.labProduceVaccine(2,3);
        assertEquals(OK, res);
        //only two lab is popular
        retlist = Solution.getPopularLabs();
        assertEquals(2, retlist.size());
        assertEquals(3,(int)retlist.get(0));
        assertEquals(5,(int)retlist.get(1));

        res = Solution.labProduceVaccine(3,4);
        assertEquals(OK, res);
        //only three lab is popular
        retlist = Solution.getPopularLabs();
        assertEquals(3, retlist.size());
        assertEquals(3,(int)retlist.get(0));
        assertEquals(4,(int)retlist.get(1));
        assertEquals(5,(int)retlist.get(2));

        res = Solution.vaccineProduced(3,1);
        assertEquals(OK, res);

        //now lab 4 have vaccine with prod under 20
        retlist = Solution.getPopularLabs();
        assertEquals(2, retlist.size());
        assertEquals(3,(int)retlist.get(0));
        assertEquals(5,(int)retlist.get(1));

        //adding vaccines to labs
        res = Solution.labProduceVaccine(1,4);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(3,1);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(2,2);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(5,2);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(4,5);
        assertEquals(OK, res);
        res = Solution.labProduceVaccine(3,5);
        assertEquals(OK, res);

        //now lab 4,1, 5 have vaccine with prod under 20
        retlist = Solution.getPopularLabs();
        assertEquals(2, retlist.size());
        assertEquals(2,(int)retlist.get(0));
        assertEquals(3,(int)retlist.get(1));

        res = Solution.vaccineSold(3,1);
        assertEquals(OK, res);

        //now all the vaccines with prod above 20
        retlist = Solution.getPopularLabs();
        assertEquals(3, retlist.size());
        assertEquals(1,(int)retlist.get(0));
        assertEquals(2,(int)retlist.get(1));
        assertEquals(3,(int)retlist.get(2));

        res = Solution.vaccineProduced(2,1);
        assertEquals(OK, res);

        //now lab 3,2 have vaccine with prod under 20
        retlist = Solution.getPopularLabs();
        assertEquals(3, retlist.size());
        assertEquals(1,(int)retlist.get(0));
        assertEquals(4,(int)retlist.get(1));
        assertEquals(5,(int)retlist.get(2));

        res = Solution.labStoppedProducingVaccine(1,3);
        assertEquals(OK, res);

        //now lab 3,2 have vaccine with prod under 20 and lab 1 have no vaccines
        retlist = Solution.getPopularLabs();
        assertEquals(2, retlist.size());
        assertEquals(4,(int)retlist.get(0));
        assertEquals(5,(int)retlist.get(1));

        res = Solution.labStoppedProducingVaccine(2,2);
        assertEquals(OK, res);

        //now lab 3 have vaccine with prod under 20 and lab 1 have no vaccines
        retlist = Solution.getPopularLabs();
        assertEquals(3, retlist.size());
        assertEquals(2,(int)retlist.get(0));
        assertEquals(4,(int)retlist.get(1));
        assertEquals(5,(int)retlist.get(2));

    }

    @Test
    public void mostRatedVaccine() {

        System.out.println("Test: mostRatedVaccine\n");
        ReturnValue res;

        //no vaccines should return empty list
        ArrayList<Integer> retlist = Solution.getMostRatedVaccines();
        assertEquals(0, retlist.size());

        Vaccine v[] = new Vaccine[5];
        for(int i=0; i<5; i++)
        {
            v[i]= new Vaccine();
            v[i].setId(i+1);
            v[i].setName("COVID-"+(i+1));
            v[i].setCost(10*(i+1));
            v[i].setProductivity(20);
            v[i].setUnits(20*(i+1));
            res = Solution.addVaccine(v[i]);
            assertEquals(OK, res);
        }

        retlist = Solution.getMostRatedVaccines();
        assertEquals(5, retlist.size());
        assertEquals(5,(int)retlist.get(0));
        assertEquals(4,(int)retlist.get(1));
        assertEquals(3,(int)retlist.get(2));
        assertEquals(2,(int)retlist.get(3));
        assertEquals(1,(int)retlist.get(4));

        res = Solution.deleteVaccine(v[3]);
        assertEquals(OK, res);

        retlist = Solution.getMostRatedVaccines();
        assertEquals(4, retlist.size());
        assertEquals(5,(int)retlist.get(0));
        assertEquals(3,(int)retlist.get(1));
        assertEquals(2,(int)retlist.get(2));
        assertEquals(1,(int)retlist.get(3));

        res = Solution.vaccineSold(2,10);
        assertEquals(OK, res);

        retlist = Solution.getMostRatedVaccines();
        assertEquals(4, retlist.size());
        assertEquals(5,(int)retlist.get(0));
        assertEquals(3,(int)retlist.get(1));
        assertEquals(1,(int)retlist.get(2));
        assertEquals(2,(int)retlist.get(3));

        res = Solution.vaccineProduced(3,40);
        assertEquals(OK, res);

        retlist = Solution.getMostRatedVaccines();
        assertEquals(4, retlist.size());
        assertEquals(3,(int)retlist.get(0));
        assertEquals(5,(int)retlist.get(1));
        assertEquals(1,(int)retlist.get(2));
        assertEquals(2,(int)retlist.get(3));

        Vaccine v1[] = new Vaccine[5];
        for(int i=0; i<5; i++)
        {
            v1[i]= new Vaccine();
            v1[i].setId(i+6);
            v1[i].setName("COVID-"+(i+1));
            v1[i].setCost(10*(i+1));
            v1[i].setProductivity(20);
            v1[i].setUnits(20*(i+1));
            res = Solution.addVaccine(v1[i]);
            assertEquals(OK, res);
        }

        retlist = Solution.getMostRatedVaccines();
        assertEquals(9, retlist.size());
        assertEquals(3,(int)retlist.get(0));
        assertEquals(5,(int)retlist.get(1));
        assertEquals(10,(int)retlist.get(2));
        assertEquals(9,(int)retlist.get(3));
        assertEquals(8,(int)retlist.get(4));
        assertEquals(7,(int)retlist.get(5));
        assertEquals(1,(int)retlist.get(6));
        assertEquals(6,(int)retlist.get(7));
        assertEquals(2,(int)retlist.get(8));

        Vaccine v2[] = new Vaccine[3];
        for(int i=0; i<v2.length; i++)
        {
            v2[i]= new Vaccine();
            v2[i].setId(i+11);
            v2[i].setName("COVID-"+(i+1));
            v2[i].setCost(2);
            v2[i].setProductivity(2);
            v2[i].setUnits(0);
            res = Solution.addVaccine(v2[i]);
            assertEquals(OK, res);
        }

        retlist = Solution.getMostRatedVaccines();
        assertEquals(10, retlist.size());
        assertEquals(3,(int)retlist.get(0));
        assertEquals(5,(int)retlist.get(1));
        assertEquals(10,(int)retlist.get(2));
        assertEquals(9,(int)retlist.get(3));
        assertEquals(8,(int)retlist.get(4));
        assertEquals(7,(int)retlist.get(5));
        assertEquals(1,(int)retlist.get(6));
        assertEquals(6,(int)retlist.get(7));
        assertEquals(2,(int)retlist.get(8));
        assertEquals(11,(int)retlist.get(9));

        res = Solution.vaccineProduced(9,20);
        assertEquals(OK, res);

        retlist = Solution.getMostRatedVaccines();
        assertEquals(10, retlist.size());
        assertEquals(3,(int)retlist.get(0));
        assertEquals(9,(int)retlist.get(1));
        assertEquals(5,(int)retlist.get(2));
        assertEquals(10,(int)retlist.get(3));
        assertEquals(8,(int)retlist.get(4));
        assertEquals(7,(int)retlist.get(5));
        assertEquals(1,(int)retlist.get(6));
        assertEquals(6,(int)retlist.get(7));
        assertEquals(2,(int)retlist.get(8));
        assertEquals(11,(int)retlist.get(9));

        res = Solution.vaccineProduced(11,51);
        assertEquals(OK, res);

        retlist = Solution.getMostRatedVaccines();
        assertEquals(10, retlist.size());
        assertEquals(3,(int)retlist.get(0));
        assertEquals(9,(int)retlist.get(1));
        assertEquals(5,(int)retlist.get(2));
        assertEquals(10,(int)retlist.get(3));
        assertEquals(8,(int)retlist.get(4));
        assertEquals(11,(int)retlist.get(5));
        assertEquals(7,(int)retlist.get(6));
        assertEquals(1,(int)retlist.get(7));
        assertEquals(6,(int)retlist.get(8));
        assertEquals(2,(int)retlist.get(9));
    }

    @Test
    public void closeEmployee() {

        System.out.println("Test: closeEmployee\n");
        ReturnValue res;

        //no employee
        ArrayList<Integer> retlist = Solution.getCloseEmployees(1);
        assertEquals(0, retlist.size());

        Lab s[] = new Lab[5];
        for(int i=0; i<s.length; i++)
        {
            s[i] = new Lab();
            s[i].setId(i+1);
            s[i].setName("Technion"+i);
            s[i].setCity("Haifa");
            s[i].setIsActive(true);
            res = Solution.addLab(s[i]);
            assertEquals(OK, res);
        }

        Employee e [] = new Employee[10];
        for(int i=0; i<e.length;i++)
        {
            e[i] = new Employee();
            e[i].setId(i+1);
            e[i].setName("Ido");
            e[i].setCity("Petach-Tikva");
            res = Solution.addEmployee(e[i]);
            assertEquals(OK, res);
        }

        //employee dont work in any lab close by empty way to all
        retlist = Solution.getCloseEmployees(1);
        assertEquals(9, retlist.size());
        assertEquals(2,(int)retlist.get(0));
        assertEquals(3,(int)retlist.get(1));
        assertEquals(4,(int)retlist.get(2));
        assertEquals(5,(int)retlist.get(3));
        assertEquals(6,(int)retlist.get(4));
        assertEquals(7,(int)retlist.get(5));
        assertEquals(8,(int)retlist.get(6));
        assertEquals(9,(int)retlist.get(7));
        assertEquals(10,(int)retlist.get(8));

        res = Solution.employeeJoinLab(1, 3, 100);
        assertEquals(OK, res);

        //no employees beside 1 in this lab
        retlist = Solution.getCloseEmployees(1);
        assertEquals(0, retlist.size());

        res = Solution.employeeJoinLab(6, 3, 100);
        assertEquals(OK, res);

        retlist = Solution.getCloseEmployees(1);
        assertEquals(1, retlist.size());
        assertEquals(6,(int)retlist.get(0));


        res = Solution.employeeJoinLab(1, 4, 100);
        assertEquals(OK, res);

        retlist = Solution.getCloseEmployees(1);
        assertEquals(1, retlist.size());
        assertEquals(6,(int)retlist.get(0));

        res = Solution.employeeJoinLab(1, 2, 100);
        assertEquals(OK, res);

        retlist = Solution.getCloseEmployees(1);
        assertEquals(0, retlist.size());

        res = Solution.employeeJoinLab(6, 5, 100);
        assertEquals(OK, res);

        retlist = Solution.getCloseEmployees(6);
        assertEquals(1, retlist.size());
        assertEquals(1,(int)retlist.get(0));


        res = Solution.employeeJoinLab(2, 1, 100);
        assertEquals(OK, res);

        res = Solution.employeeJoinLab(3, 1, 100);
        assertEquals(OK, res);

        res = Solution.employeeJoinLab(8, 4, 100);
        assertEquals(OK, res);

        res = Solution.employeeJoinLab(10, 1, 100);
        assertEquals(OK, res);

        retlist = Solution.getCloseEmployees(3);
        assertEquals(2, retlist.size());
        assertEquals(2,(int)retlist.get(0));
        assertEquals(10,(int)retlist.get(1));

        res = Solution.employeeJoinLab(7, 2, 100);
        assertEquals(OK, res);

        res = Solution.employeeJoinLab(6, 2, 100);
        assertEquals(OK, res);

        res = Solution.employeeJoinLab(5, 5, 100);
        assertEquals(OK, res);

        res = Solution.employeeJoinLab(10, 2, 100);
        assertEquals(OK, res);

        retlist = Solution.getCloseEmployees(6);
        assertEquals(1, retlist.size());
        assertEquals(1,(int)retlist.get(0));

        retlist = Solution.getCloseEmployees(10);
        assertEquals(5, retlist.size());
        assertEquals(1,(int)retlist.get(0));
        assertEquals(2,(int)retlist.get(1));
        assertEquals(3,(int)retlist.get(2));
        assertEquals(6,(int)retlist.get(3));
        assertEquals(7,(int)retlist.get(4));

        retlist = Solution.getCloseEmployees(8);
        assertEquals(1, retlist.size());
        assertEquals(1,(int)retlist.get(0));

        retlist = Solution.getCloseEmployees(4);
        assertEquals(9, retlist.size());
        assertEquals(1,(int)retlist.get(0));
        assertEquals(2,(int)retlist.get(1));
        assertEquals(3,(int)retlist.get(2));
        assertEquals(5,(int)retlist.get(3));
        assertEquals(6,(int)retlist.get(4));
        assertEquals(7,(int)retlist.get(5));
        assertEquals(8,(int)retlist.get(6));
        assertEquals(9,(int)retlist.get(7));
        assertEquals(10,(int)retlist.get(8));

        res = Solution.employeeJoinLab(4, 1, 100);
        assertEquals(OK, res);

        res = Solution.employeeJoinLab(4, 5, 100);
        assertEquals(OK, res);

        res = Solution.employeeJoinLab(4, 3, 100);
        assertEquals(OK, res);

        res = Solution.employeeJoinLab(4, 4, 100);
        assertEquals(OK, res);

        retlist = Solution.getCloseEmployees(4);
        assertEquals(2, retlist.size());
        assertEquals(1,(int)retlist.get(0));
        assertEquals(6,(int)retlist.get(1));

        res = Solution.employeeLeftLab(4, 1);
        assertEquals(OK, res);

        res = Solution.employeeJoinLab(4, 2, 100);
        assertEquals(OK, res);

        retlist = Solution.getCloseEmployees(4);
        assertEquals(1, retlist.size());
        assertEquals(6,(int)retlist.get(0));

        retlist = Solution.getCloseEmployees(3);
        assertEquals(3, retlist.size());
        assertEquals(2,(int)retlist.get(0));
        assertEquals(4,(int)retlist.get(1));
        assertEquals(10,(int)retlist.get(2));

        res = Solution.employeeLeftLab(1, 4);
        assertEquals(OK, res);

        retlist = Solution.getCloseEmployees(3);
        assertEquals(2, retlist.size());
        assertEquals(2,(int)retlist.get(0));
        assertEquals(10,(int)retlist.get(1));

        retlist = Solution.getCloseEmployees(17);
        assertEquals(0, retlist.size());
    }
}