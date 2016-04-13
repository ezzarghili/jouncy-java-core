package ma.cy.joun.core.data.filter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Mohamed EZ-ZARGHILI <mohamed.ezzarghili@gmail.com> on 2016.
 */
public class FilterTest {

  @Test
  public void testProperty() {
    Filter<Long> filter = new Filter<>();
    filter.setProperty("anyProperty");
    assertThat(filter.getProperty(), is(equalTo("anyProperty")));
  }

  @Test
  public void testOperation() {
    Filter<Long> filter = new Filter<>();
    filter.setOperation(Operation.GT);
    assertThat(filter.getOperation(), is(equalTo(Operation.GT)));
  }

  @Test
  public void testValue() {
    Filter<Long> filter = new Filter<>();
    filter.setValue(10L);
    assertThat(filter.getValue(), is(equalTo(10L)));
    filter.setValue(10L);
    assertThat(filter.getValues().size(), is(equalTo(1)));
  }

  @Test
  public void testAddValue() {
    Filter<Long> filter = new Filter<>();
    filter.addValue(10L);
    assertThat(filter.getValue(), is(equalTo(10L)));
    filter.addValue(11L);
    assertThat(filter.getValues().get(1), is(equalTo(11L)));
  }

  @Test
  public void testValues() {
    Filter<Long> filter = new Filter<>();
    List<Long> values = new ArrayList<>();
    values.add(10L);
    values.add(11L);
    filter.setValues(values);
    assertThat(filter.getValues().size(), is(equalTo(2)));
    assertThat(filter.getValues().get(0), is(equalTo(10L)));
    assertThat(filter.getValues().get(1), is(equalTo(11L)));
  }

  @Test
  public void testIsList() throws Exception {
    Filter<Long> filter = new Filter<>();
    List<Long> values = new ArrayList<>();
    values.add(10L);
    values.add(11L);
    filter.setValues(values);
    assertThat(filter.isList(), is(equalTo(true)));
  }

  @Test
  public void testAsList() throws Exception {
    Filter<Long> filter = new Filter<>();
    filter.setValue(10L);
    assertThat(filter.asList(), is(instanceOf(List.class)));
  }

  @Test
  public void testGenericConstructor() throws Exception {
    GenericFilter genericFilter = new GenericFilter();
    genericFilter.setProperty("anyProperty");
    genericFilter.setOperation(Operation.EQ);
    genericFilter.setValue(11L);
    // normal test
    Filter<?> filter = new Filter<>(genericFilter, Long.class);
    assertThat(filter.getValue(), is(instanceOf(Long.class)));
    assertThat(filter.getValue(), is(equalTo(11L)));
    assertThat(filter.getProperty(), is(equalTo("anyProperty")));
    assertThat(filter.getOperation(), is(equalTo(Operation.EQ)));

    genericFilter.setValue("11"); // test safe object casting
    filter = new Filter<>(genericFilter, Long.class);

    // testing GenericFilter with list values
    ArrayList<Object> values = new ArrayList<>();
    values.add(10L);
    values.add("11"); // test safe object casting
    genericFilter.setValue(values);
    Filter<?> filterList = new Filter<>(genericFilter, Long.class);
    assertThat(filterList.isList(), is(true));
  }

  @Test
  public void testConstructors() throws Exception {
    Filter<?> filter = new Filter<>("anyProperty", Operation.EQ);
    assertThat(filter.getValue(), is(nullValue()));
    assertThat(filter.getProperty(), is(equalTo("anyProperty")));
    assertThat(filter.getOperation(), is(equalTo(Operation.EQ)));

    Filter<?> filter2 = new Filter<>("anyProperty2", Operation.GT, 11L);
    assertThat(filter2.getValue(), is(instanceOf(Long.class)));
    assertThat(filter2.getValue(), is(equalTo(11L)));
    assertThat(filter2.getProperty(), is(equalTo("anyProperty2")));
    assertThat(filter2.getOperation(), is(equalTo(Operation.GT)));

    List<Long> values = new ArrayList<>();
    values.add(10L);
    values.add(11L);
    Filter<?> filter3 = new Filter<>("anyProperty3", Operation.IN, values);
    assertThat(filter3.getProperty(), is(equalTo("anyProperty3")));
    assertThat(filter3.getOperation(), is(equalTo(Operation.IN)));
    assertThat(filter3.getValues().size(), is(equalTo(2)));
    assertThat(filter3.getValues().get(0), is(equalTo(10L)));
    assertThat(filter3.getValues().get(1), is(equalTo(11L)));
  }
}
