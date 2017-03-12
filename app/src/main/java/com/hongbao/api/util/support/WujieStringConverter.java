package com.hongbao.api.util.support;

import org.apache.commons.beanutils.converters.AbstractConverter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * other --> String
 */
public class WujieStringConverter extends AbstractConverter {
    private String defaultFmt_yyyy_mm_dd = "yyyy-MM-dd";
    private String defaultFmt_yyyy_mm_dd_hh_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public WujieStringConverter() {
        super(null);
    }

    public WujieStringConverter(String defaultFmt_yyyy_mm_dd, String defaultFmt_yyyy_mm_dd_hh_mm_ss) {
        this();
        this.defaultFmt_yyyy_mm_dd = defaultFmt_yyyy_mm_dd;
        this.defaultFmt_yyyy_mm_dd_hh_mm_ss = defaultFmt_yyyy_mm_dd_hh_mm_ss;
    }

    /**
     * Return the default type this <code>Converter</code> handles.
     *
     * @return The default type this <code>Converter</code> handles.
     * @since 1.8.0
     */
    protected Class getDefaultType() {
        return String.class;
    }

    /**
     * Convert the specified input object into an output object of the
     * specified type.
     *
     * @param type  Data type to which this value should be converted.
     * @param value The input value to be converted.
     * @return The converted value.
     * @throws Throwable if an error occurs converting to the specified type
     * @since 1.8.0
     */
    protected Object convertToType(Class type, Object value) throws Throwable {
        return convertToString(value);
    }

    /**
     * Convert the input object into a String.
     * <p/>
     * <b>N.B.</b>This implementation simply uses the value's
     * <code>toString()</code> method and should be overriden if a
     * more sophisticated mechanism for <i>conversion to a String</i>
     * is required.
     *
     * @param value The input value to be converted.
     * @return the converted String value.
     * @throws Throwable if an error occurs converting to a String
     */
    protected String convertToString(Object value) throws Throwable {
        if (value instanceof Date) {
            Date date = (Date) value;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if (calendar.get(Calendar.MILLISECOND) == 0 && calendar.get(Calendar.SECOND) == 0 && calendar.get(Calendar.MINUTE) == 0 && calendar.get(Calendar.HOUR_OF_DAY) == 0) {
                return new SimpleDateFormat(this.defaultFmt_yyyy_mm_dd).format(date);
            } else {
                return new SimpleDateFormat(this.defaultFmt_yyyy_mm_dd_hh_mm_ss).format(date);
            }
        } else {
            return value.toString();
        }
    }

}
