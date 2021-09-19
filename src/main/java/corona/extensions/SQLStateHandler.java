package corona.extensions;

import corona.business.ReturnValue;
import corona.business.ReturnValue.*;

import java.util.HashMap;
import java.util.Map;

public class SQLStateHandler {
    public static final Map<String, ReturnValue> returnStatusTable = new HashMap<>();
    static {
        returnStatusTable.put("23502", ReturnValue.BAD_PARAMS);
        returnStatusTable.put("23503", ReturnValue.NOT_EXISTS);
        returnStatusTable.put("23505", ReturnValue.ALREADY_EXISTS);
        returnStatusTable.put("23514", ReturnValue.BAD_PARAMS);
    }
}