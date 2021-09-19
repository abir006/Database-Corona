package corona;

import corona.business.Lab;
import corona.business.ReturnValue;
import org.junit.Test;

import static corona.business.ReturnValue.NOT_EXISTS;
import static corona.business.ReturnValue.OK;
import static org.junit.Assert.assertEquals;

public class SimpleTest extends AbstractTest{

    @Test
    public void simpleTestCreateLab()
    {
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
        Lab a = new Lab();
        a.setId(10);
        a.setName("Technion");
        a.setCity("Haifa");
        a.setIsActive(true);

        ReturnValue ret = Solution.deleteLab(a);
        assertEquals(NOT_EXISTS , ret);

    }
}