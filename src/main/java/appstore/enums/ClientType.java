package appstore.enums;

public enum ClientType {
    LAIDBACK(1),
    DEMANDING(2),
    MTHRFCKR(3);

    ClientType(int i) {
    }

    public static ClientType getClientType(int number) {
        switch (number) {
            case 1:
                return ClientType.LAIDBACK;
            case 2:
                return ClientType.DEMANDING;
            case 3:
                return ClientType.MTHRFCKR;
            default:
                return null;
        }
    }
}
