package corona;

import corona.business.Employee;
import corona.business.Lab;
import corona.business.ReturnValue;
import org.junit.Test;

import static corona.extensions.ExtensionMethods.*;
import static corona.business.ReturnValue.*;
import static org.junit.Assert.assertEquals;

public class LabCRUDTests extends AbstractTest {

    @Test
    public void addValidLab() {
        Lab lab = createLab(1, "Technion", "Haifa", true);
        ReturnValue ret = Solution.addLab(lab);
        assertEquals(OK, ret);
    }

    @Test
    public void addInvalidLabWithNegativeID() {
        Lab lab = createLab(-1, "Technion", "Haifa", true);
        ReturnValue ret = Solution.addLab(lab);
        assertEquals(BAD_PARAMS, ret);
    }

    @Test
    public void errorStatusORderCheck() {
        Lab lab = createLab(1, "Technion", "Haifa", true);
        ReturnValue ret = Solution.addLab(lab);
        assertEquals(OK, ret);
        lab = createLab(1, null, "Haifa", true);
        ret = Solution.addLab(lab);
        assertEquals(BAD_PARAMS, ret);
        lab = createLab(1, "Technion", null, true);
        ret = Solution.addLab(lab);
        assertEquals(BAD_PARAMS, ret);
    }

    @Test
    public void addInvalidLabWithZeroID() {
        Lab lab = createLab(0, "Technion", "Haifa", true);
        ReturnValue ret = Solution.addLab(lab);
        assertEquals(BAD_PARAMS, ret);
    }

    @Test
    public void addInvalidLabWithNullName() {
        Lab lab = createLab(1, null, "Haifa", true);
        ReturnValue ret = Solution.addLab(lab);
        assertEquals(BAD_PARAMS, ret);
    }

    @Test
    public void addInvalidLabWithNullCity() {
        Lab lab = createLab(1, "Technion", null, true);
        ReturnValue ret = Solution.addLab(lab);
        assertEquals(BAD_PARAMS, ret);
    }

    @Test
    public void addAlreadyExistingLab() {
        Lab lab = createLab(1, "Technion", "Haifa", true);
        ReturnValue ret = Solution.addLab(lab);
        assertEquals(OK, ret);
        ret = Solution.addLab(lab);
        assertEquals(ALREADY_EXISTS, ret);
    }

    @Test
    public void getExistingLab() {
        int labID = 1;
        Lab lab = createLab(labID, "Technion", "Haifa", true);
        ReturnValue ret = Solution.addLab(lab);
        assertEquals(OK, ret);
        assertEquals(Solution.getLabProfile(labID), lab);
    }

    @Test
    public void getNonExistingLab() {
        assertEquals(Lab.badLab(), Solution.getLabProfile(-1));
    }

    @Test
    public void deleteExistingLab() {
        Lab lab = createLab(1, "Technion", "Haifa", true);
        ReturnValue ret = Solution.addLab(lab);
        assertEquals(OK, ret);
        ret = Solution.deleteLab(lab);
        assertEquals(OK, ret);
    }

    @Test
    public void deleteNonExistingLab() {
        Lab lab = createLab(1, "Technion", "Haifa", true);
        ReturnValue ret = Solution.deleteLab(lab);
        assertEquals(NOT_EXISTS, ret);
    }
}