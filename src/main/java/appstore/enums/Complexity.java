package appstore.enums;

public enum Complexity {
    EASY(1),
    MEDIUM(2),
    COMPLEX(3);

    private int value;

    Complexity(int i) {
        this.value = i;
    }

    public int getNumber() {
        return value;
    }

    public static Complexity getComplexity(int number) {
        switch (number) {
            case 1:
                return EASY;
            case 2:
                return MEDIUM;
            case 3:
                return COMPLEX;
            default:
                return null;
        }
    }
}
