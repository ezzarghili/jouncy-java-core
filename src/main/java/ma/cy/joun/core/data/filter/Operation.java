package ma.cy.joun.core.data.filter;

/**
 * Created by Mohamed EZ-ZARGHILI <mohamed.ezzarghili@gmail.com> on 2016.
 */

/**
 * Enum used for providing compare operations
 * <code>EQ</code> for = equal
 * <code>LT</code> for < less then
 * <code>GT</code> for > greater then
 * <code>LIKE</code> for .* like
 * <code>IN</code> for in sql operation
 */
public enum Operation {
  /**
   * Equal operation ( = value )
   */
  EQ,
  /**
   * Less than operation ( &lt; value )
   */
  LT,
  /**
   * Greater than operation ( &gt; value )
   */
  GT,
  /**
   * Like operation ( grep ^.*value.*$ ) for strings
   */
  LIKE,
  /**
   * IN operation ( used mainly when filter has multiple values to test against)
   */
  IN
}
