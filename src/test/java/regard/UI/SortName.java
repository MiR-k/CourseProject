package regard.UI;

public enum SortName {

        Title("названию"),
        Price("цене"),
        Popular("популярности");
        public String value;

        SortName(final String values) {
            value = values;
        }

        public String toString() {
            return value;
        }
}

