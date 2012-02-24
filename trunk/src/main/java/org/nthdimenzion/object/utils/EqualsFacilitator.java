package org.nthdimenzion.object.utils;

public final class EqualsFacilitator {

    public static Boolean PROCEED_WITH_EQUALS = null;

    public static Boolean initialChecksPass(Object lhs, Object rhs) {
        if (rhs == null || lhs == null)
            return false;

        if (!rhs.getClass().equals(rhs.getClass()))
            return false;

        return true;
    }
}
