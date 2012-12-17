package org.nthdimenzion.object.utils;

public final class StringUtils {

    public static String replaceNullWithEmptyString(String str){
        return str == null ? "" : str;
    }

    public static String nullSafeCopy(String str){
        return str!=null ? new String(str) : null;
    }
}
