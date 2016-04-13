package ma.cy.joun.core.data.filter;

/**
 * Created by Mohamed EZ-ZARGHILI <mohamed.ezzarghili@gmail.com> on 2016.
 */

import ma.cy.joun.core.data.parser.TypeParser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @param <T> the used for the filter, as far as version 1.0 supported types are:
 *           <code>Integer</code>
 *           <code>Long</code>
 *           <code>String</code>
 *           <code>Calendar</code>
 *           <code>Boolean</code>
 */
public class Filter<T> {
  /**
   * used to hold the name of the filterd property
   */
  private String property;
  /**
   * used to hold the operation used for the filtering
   */
  private Operation operation;
  /**
   * used to hold value/values of the filter by which a property is filtered
   */
  private List<T> values;

  /**
   * Class constructor initialize values using an ArrayList with 1 element default size
   * Example:
   * <code>
   *         filter Filter<Integer> = new Filter<>();
   *         filter.setProperty("someProperty");
   *         filter.setOperation(Operation.EQ); // EQ, LT, GT, LIKE, IN
   *         filter.setValue(10);
   * </code>
   */
  public Filter() {
    values = new ArrayList<>(1);
  }
  /**
   *
   * @param property the name of the filter property
   * @param operation the compare operation to use for the filter
   */
  public Filter(String property, Operation operation) {
    this();
    this.setProperty(property);
    this.setOperation(operation);
  }
  /**
   *
   * @param property the name of the filter property
   * @param operation the compare operation to use for the filter
   * @param values a list for values of with the same type as the filter to compare to when using Operation.IN operation
   */
  public Filter(String property, Operation operation, List<T> values) {
    this(property,operation);
    this.values = values;
  }

  /**
   *
   * @param property the name of the filter property
   * @param operation the compare operation to use for the filter
   * @param value a value with the same type as the filter to use for comparison
   */
  public Filter(String property, Operation operation, T value) {
    this(property,operation);
    this.values.add(value);
  }

  /**
   * used to covert a GenericFilter object to a typed Filter based on type
   * @param genericFilter the source GenericFilter object to use
   * @param type the type for the new Filter object
   * @see GenericFilter
   */
  public Filter(GenericFilter genericFilter, Class<T> type) {
    this(genericFilter.getProperty(),genericFilter.getOperation());
    this.values = getGenericFilterValues(genericFilter.getValue(), type);
  }

  /**
   *
   * @return <code>String</code> the property used by the filter
   */
  public String getProperty() {
    return property;
  }

  /**
   *
   * @param property the property used by the filter
   */
  public void setProperty(String property) {
    this.property = property;
  }
  /**
   *
   * @return <code>Operation</code> the operation used by the filter
   */
  public Operation getOperation() {
    return operation;
  }

  /**
   *
   * @param operation the operation used by the filter
   */
  public void setOperation(Operation operation) {
    this.operation = operation;
  }

  /**
   * returns the first value in the values list or null if empty list
   *
   * @return <code>Class<T></code>
   */
  public T getValue() {
    if (values.isEmpty())
      return null;
    return values.get(0);
  }

  /**
   * set the value of the filter and remove any existing values in the internal list
   *
   * @param value of same type as the Filter
   */
  public void setValue(T value) {
    if (!values.isEmpty())
      values.clear();
    this.values.add(value);
  }

  /**
   * appends the value to the internal list of values of the filter
   *
   * @param value of same type as the filter
   */
  public void addValue(T value) {
    if (!values.contains(value))
      this.values.add(value);
  }

  /**
   * returns the internal list of values of the filter
   * @return <Code>List</Code> a typed list with the same type as the filter
   */
  public List<T> getValues() {
    return values;
  }

  /**
   * replace the internal values list with the values list
   *
   * @param values a list containing values with same time as the filter
   */
  public void setValues(List<T> values) {
    this.values = values;
  }

  /**
   *
   * provides information about the internal values list
   * @return <code>true</code> if the list contains more than one value
   *         <code>false</code> otherwise
   */
  public boolean isList() {
    return values.size() > 1;
  }

  /**
   * wrap the current filter inside a typed filter ArrayList and returns it
   *
   * @return the wrapped filter ArrayList
   */
  public List<Filter<?>> asList() {
    List<Filter<?>> list = new ArrayList<>(1);
    list.add(this);
    return list;
  }

  /**
   * internal use method for GenericFilter conversion
   * @param value the value of GenericFilter object
   * @param type the target class of the filter
   * @return a typed list of values to use in as internal values list of the filter
   */
  private List<T> getGenericFilterValues(Object value, Class<T> type) {
    List<T> values = new ArrayList<>();
    if (value instanceof List) { // check if the value is a list and iterate over the values the list
      for (Object item : (List) value) {
        if (item.getClass() == type){
          values.add(type.cast(item));
        } else {
          values.add(type.cast(TypeParser.getSafeObject(item, type)));
        }
      }
    } else {
      if (value.getClass() == type){
        values.add(type.cast(value));
      } else {
        values.add(type.cast(TypeParser.getSafeObject(value, type)));
      }
    }
    return values;
  }
//
}
