package microservices.book.socialmultiplication.domain;

/**
 * Stores information to identify the user.
 */
public final class User {
    private Long id;

    private final String alias;

    // Empty constructor for JSON (de)serialization
    protected User() {
        alias = null;
    }

    public User(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public Long getId() {
        return id;
    }
}