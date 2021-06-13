package appstore.enums;

public enum Technology {
    FRONTEND(1),
    BACKEND(2),
    DATABASE(3),
    MOBILE(4),
    WORDPRESS(5),
    PRESTASHOP(6)
    ;

    private int value;

    Technology(int i) {
        this.value = i;
    }

    public int getNumber() {
        return value;
    }

    public static Technology getTechnology(int number) {
        for (Technology t : values()) {
            if (t.value == number) {
                return t;
            }
        }
        return null;
    }
}
