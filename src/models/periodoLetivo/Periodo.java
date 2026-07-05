package models.periodoLetivo;

public enum Periodo {
    MODULO_1(1),
    MODULO_2(2),
    MODULO_3(3),
    MODULO_4(4);

    private final int modulo;

    Periodo(int modulo) {
        this.modulo = modulo;
    }

    public int getModulo() {
        return modulo;
    }
}
