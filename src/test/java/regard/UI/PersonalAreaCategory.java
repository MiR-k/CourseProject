package regard.UI;

/**
 * Created by MiR_k on 19.12.2015.
 */
public enum PersonalAreaCategory {
    Order("Заказы"),
    Personal("Личные"),
    Adress("Доставки"),
    organization("Организаций");

    public String value;

    PersonalAreaCategory(final String values) {
        value = values;
    }

    public String toString() {
        return value;
    }
}
