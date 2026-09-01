/**
 * Author: Tshering Dorji
 * Unit: COSC120
 * Project: DiscoverDruk - Bhutan Experience Finder
 * GitHub: https://github.com/NADO1995/COSC120-Assignment3.git
 *
 * This enum was developed with reference to the COSC120 lecture notes,
 * tutorial materials, and sample code provided for the assignment.
 *
 * AI assistance:
 * ChatGPT was used to help understand the assignment requirements,
 * review the enum structure, and improve code clarity and documentation.
 */
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

    /**
     * Returns a readable name for each Dzongkhag.
     *
     * @return the readable Dzongkhag name
     */
    @Override
    public String toString() {

        return switch (this) {
            case SAMDRUP_JONGKHAR -> "Samdrup Jongkhar";
            case TRASHIYANGTSE -> "Trashiyangtse";
            case WANGDUE_PHODRANG -> "Wangdue Phodrang";

            default -> {
                String name = name().toLowerCase();

                yield Character.toUpperCase(name.charAt(0))
                        + name.substring(1);
            }
        };
    }
}