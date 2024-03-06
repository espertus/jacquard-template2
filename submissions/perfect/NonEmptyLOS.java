package student;

/**
 * An immutable non-empty list of strings.
 */
public class NonEmptyLOS implements ILOS {
    private final String first;
    private final ILOS rest;

    /**
     * Creates a non-empty list of strings.
     *
     * @param first the first string
     * @param rest the rest of the list
     */
    public NonEmptyLOS(String first, ILOS rest) {
        this.first = first;
        this.rest = rest;
    }

    @Override
    public int size() {
        return 1 + rest.size();
    }

    @Override
    public String concat() {
        return first + rest.concat();
    }
}
