public enum Dzongkhag {
    BUMTHANG,
    CHUKHA,
    DAGANA,
    GASA,
    HAA,
    LHUNTSE,
    MONGAR,
    PARO,
    PEMAGATSHEL,
    PUNAKHA,
    SAMDRUP_JONGKHAR,
    SAMTSE,
    SARPANG,
    THIMPHU,
    TRASHIGANG,
    TRASHIYANGTSE,
    TRONGSA,
    TSIRANG,
    WANGDUE_PHODRANG,
    ZHEMGANG;

    @Override
    public String toString() {
        return switch (this) {
            case SAMDRUP_JONGKHAR -> "Samdrup Jongkhar";
            case TRASHIYANGTSE -> "Trashiyangtse";
            case WANGDUE_PHODRANG -> "Wangdue Phodrang";
            default -> {
                String name = name().toLowerCase();
                yield Character.toUpperCase(name.charAt(0)) + name.substring(1);
            }
        };
    }
}