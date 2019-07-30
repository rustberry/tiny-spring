package rust.tinyspring.factory;

import rust.tinyspring.PropertyValue;
import rust.tinyspring.PropertyValues;

import java.util.ArrayList;
import java.util.List;

public class MutablePropertyValues implements PropertyValues {

    private final List<PropertyValue> propertyValueList;

    public MutablePropertyValues() {
        this.propertyValueList = new ArrayList<>(0);
    }

    public MutablePropertyValues(List<PropertyValue> propertyValueList) {
        this.propertyValueList =
                (propertyValueList == null ? new ArrayList<>(0) : propertyValueList);
    }

    @Override
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    @Override
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }
}
