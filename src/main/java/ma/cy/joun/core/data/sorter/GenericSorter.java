package ma.cy.joun.core.data.sorter;
/**
 * Created by Mohamed EZ-ZARGHILI <mohamed.ezzarghili@gmail.com> on 2016.
 */
public class GenericSorter {
  /**
   * used to hold the name of the sorted property
   */
    private String property;
  /**
   * used to hold the sorting direction of the sorter
   */
    private Direction direction;

  /**
   * Get the sorting property of the sorter
   * @return <code>String</code> property name used by the sorter
   */
    public String getProperty() {
        return property;
    }

  /**
   * Set the property to used for sorting
   * @param property the property to use for sorting
     */
    public void setProperty(String property) {
        this.property = property;
    }

  /**
   * Get the sorting direction of the sorter
   * @return <code>Direction.ASC</code> if sorting ascendant
   *         <code>Direction.DESC</code> if sorting descendant
     */
    public Direction getDirection() {
        return direction;
    }

  /**
   * Set the sorting direction of the sorter
   * @param direction the sorting direction for the sorter
   */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
