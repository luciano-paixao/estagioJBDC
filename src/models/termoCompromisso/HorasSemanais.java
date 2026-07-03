package models.termoCompromisso;

public enum HorasSemanais {
    QUATRO(4),
    SEIS(6),
    OITO(8);

    private final int horas;

    HorasSemanais(int horas) {
        this.horas = horas;
    }

    public int getHoras() {
        return horas;
    }

    public static HorasSemanais fromHoras(int horas) {
        for (HorasSemanais h : values()) {
            if (h.horas == horas) {
                return h;
            }
        }
        throw new IllegalArgumentException("Valor invalido para horas semanais: " + horas);
    }
}
