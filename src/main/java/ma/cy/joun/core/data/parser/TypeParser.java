package ma.cy.joun.core.data.parser;

/**
 * Created by Mohamed EZ-ZARGHILI <mohamed.ezzarghili@gmail.com> on 2016.
 */


import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * utility class for object conversion.
 */
public abstract class TypeParser {

  /**
   * a static logger to log class events
   */
  private static final Logger log = Logger.getLogger(TypeParser.class.getName());

  /**
   * @param object the object to cast
   * @param type   the type to cast to
   * @return if requested type has a casting strategy returns the casted object and is castable
   * otherwise will return null
   */
  public static Object getSafeObject(Object object, Class type) {
    Object internalObject;
    if (Integer.class == type) {
      internalObject = parseInteger(object);
    } else if (Long.class == type) {
      internalObject = parseLong(object);
    } else if (Boolean.class == type) {
      internalObject = parseBoolean(object);
    } else if (Calendar.class == type) {
      internalObject = parseCalendar(object);
    } else if (LocalDate.class == type) {
      internalObject = parseLocalDate(object);
    } else if (String.class == type) {
      internalObject = parseString(object);
    } else {
      return null;
    }
    return internalObject;
  }

  /**
   *
   * @param object  the object to cast
   * @return <code>LocalDate</code> the casted object
   */
  private static Object parseLocalDate(Object object) {
    return LocalDate.parse(object.toString());
  }

  /**
   * @param object the object to cast
   * @return <code>Integer</code> the casted object
   */
  private static Integer parseInteger(Object object) {
    return Integer.parseInt(object.toString());
  }

  /**
   * @param object the object to cast
   * @return <code>Long</code> the casted object
   */
  private static Long parseLong(Object object) {
    return Long.parseLong(object.toString());
  }

  /**
   * @param object the object to cast
   * @return <code>Boolean</code> the casted object
   */
  private static Boolean parseBoolean(Object object) {
    return Boolean.parseBoolean(object.toString());
  }

  /**
   * @param object the object to cast should use ISO 8601 Date Format
   * @return <code>Calendar</code> the casted object
   */
  public static Calendar parseCalendar(Object object) {
    Calendar calendar = Calendar.getInstance();
    String dateString = object.toString();
    try {
      LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);
      calendar.setTime(Date.valueOf(localDate));
    } catch (DateTimeParseException e){
      log.log(Level.WARNING,"Invalid date format.");
      return null;
    }
    return calendar;
  }

  /**
   * @param object the object to cast
   * @return <code>String</code> the casted object
   */
  public static String parseString(Object object) {
    return object.toString();
  }

}
