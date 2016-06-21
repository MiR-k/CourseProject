package regard.UI;

public enum TopMenuElement {

    Main("Главная"),
    Configurator("Конфигуратор"),
    About("компании"),
    Contacts("Контакты"),
    Guarantee("Гарантия"),
    Pickup("Самовывоз"),
    payment("Оплата"),
    delivery("Доставка");

    public String value;

    TopMenuElement(final String values) {
        value = values;
    }

    public String toString() {
        return value;
    }

}
