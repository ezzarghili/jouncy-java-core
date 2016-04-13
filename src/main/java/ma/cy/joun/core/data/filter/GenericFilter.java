package ma.cy.joun.core.data.filter;

/**
 * Created by Mohamed EZ-ZARGHILI <mohamed.ezzarghili@gmail.com> on 2016.
 */

/**
 * a generic filtering class to be used when target filter type is not known
 */
public class GenericFilter {

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
   * the value should be cast to the target filter before usage
   */
  private Object value;

  /**
   * returns the property of the filter
   * @return <code>String</code> the property of the filter
   */
  public String getProperty() {
    return property;
  }

  /**
   * define the property used by the filter
   * @param property the property used by the filter
   */
  public void setProperty(String property) {
    this.property = property;
  }

  /**
   * returns the operation used by the filter
   * @return <code>Operation</code>
   * @see Operation for available options
   */
  public Operation getOperation() {
    return operation;
  }

  /**
   * define the operation used by the filter
   * @param operation the filtering operation used to filter
   */
  public void setOperation(Operation operation) {
    this.operation = operation;
  }

  /**
   * returns the value used in the filter
   * @return object <code>Object</code>
   */
  public Object getValue() {
    return value;
  }

  /**
   * define the value used by the filter
   * @param value the value object of the filter
   */
  public void setValue(Object value) {
    this.value = value;
  }

}
