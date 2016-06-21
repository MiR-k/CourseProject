package regard.UI;

public enum LeftMenuTypes {
    Car("Автомобильная"),
    Accessories("Аксессуары"),
    Audio("Аудио"),
    UnitsPower("Блоки"),
    Home("Бытовая"),
    VideoCards("Видеокарты"),
    HDD("Жесткие"),
    UPS("ИБП"),
    Cables("Кабели"),
    Keyboard("Клавиатуры"),
    Columns("Колонки"),
    Corps("Корпуса"),
    Handlers("Манипуляторы"),
    MotherBoard("Материнские"),
    Mobile("Мобильная"),
    Monitor("Мониторы"),
    Monoblock("Моноблоки"),
    Multimedia("Мультимедиа"),
    Mouse("Мыши"),
    Board("Настольные"),
    Headphones("Наушники"),
    Nettops("Неттопы"),
    Notebook("Ноутбуки"),
    Equipment("Оборудование"),
    RAM("Оперативная"),
    Office("Офисная"),
    Memory("Память"),
    Tablet("Планшетные"),
    Plotters("Плоттеры"),
    Drive("Приводы"),
    Printer("Принтеры"),
    Software("Программное"),
    CPU("Процессоры"),
    Other("Прочие"),
    Sale("Распродажа"),
    Consumables("Расходные"),
    Server("Серверное"),
    Network("Сетевое"),
    Systems("Системы"),
    Scanners("Сканеры"),
    Telecom("Телеком"),
    Discount("Уцененный"),
    Camera("Фотоаппараты");

    public String value;

    LeftMenuTypes(final String values) {
        value = values;
    }

    public String toString() {
        return value;
    }
}
