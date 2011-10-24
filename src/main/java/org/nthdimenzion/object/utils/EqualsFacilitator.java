package org.nthdimenzion.object.utils;

public final class EqualsFacilitator {

    public static Boolean PROCEED_WITH_EQUALS = null;

    public static Boolean initialChecks(Object lhs, Object rhs) {
        if (rhs == null || lhs == null)
            return false;

        if (!rhs.getClass().equals(rhs.getClass()))
            return false;

        if (lhs == rhs)
            return true;

        return PROCEED_WITH_EQUALS;
    }
}
