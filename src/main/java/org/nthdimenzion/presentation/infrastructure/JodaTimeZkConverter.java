package org.nthdimenzion.presentation.infrastructure;

import org.joda.time.DateTime;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zkplus.databind.TypeConverter;

import java.util.Date;

public class JodaTimeZkConverter implements TypeConverter {
    @Override
    public Object coerceToUi(Object val, Component comp) {
        if (val == null)
            return val;
        if (val instanceof DateTime) {
            return ((DateTime) val).toDate();
        }
        throw new UiException("Converter expects a Date/DateTime object");
    }

    @Override
    public Object coerceToBean(Object val, Component comp) {

        if (val == null)
            return val;

        if (val instanceof Date) {
            return new DateTime(val);
        }
        throw new UiException("Converter expects a Date/DateTime object");
    }
}
