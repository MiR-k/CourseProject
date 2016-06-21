package regard.UI;

public enum CatalogFilter {
    Price("Цена"),
    ManufBrand("Производитель"),
    Type("Тип"),
    Color("Цвет");

    public String value;

    CatalogFilter(final String values) {
        value = values;
    }

    public String toString() {
        return value;
    }
}
