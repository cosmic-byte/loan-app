package thecrevance.enums;

/**
 * Created by Greg on 10/31/17.
 */
public enum Sex {
    M, F;

    public static Sex valueOf(char s) {
        switch (s) {
            case  'M': return M;
            case 'F': return F;
            default:
                throw new IllegalArgumentException("Sex should be M or F.");
        }
    }
}
