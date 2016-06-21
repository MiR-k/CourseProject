package regard.UI;

/**
 * Created by MiR_k on 19.12.2015.
 */
public enum Category {
    Brand("бренд"),
    Type("типам");

    public String value;

    Category(final String values) {
        value = values;
    }

    public String toString() {
        return value;
    }
}
