package rust.tinyspring;

import java.util.Arrays;
import java.util.Iterator;

public interface PropertyValues extends Iterable<PropertyValue> {

    @Override
    default Iterator<PropertyValue> iterator() {
        return Arrays.asList(this.getPropertyValues()).iterator();
    }

    PropertyValue[] getPropertyValues();

    PropertyValue getPropertyValue(String propertyName);
}
