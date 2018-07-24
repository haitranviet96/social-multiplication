package microservices.book.socialmultiplication.domain;

/**
 * Identifies the attempt from a {@link User} to solve a
 * {@link Multiplication}.
 */
public final class MultiplicationResultAttempt {
    private Long id;

    private final User user;

    private final Multiplication multiplication;
    private final int resultAttempt;

    private final boolean correct;

    // Empty constructor for JSON (de)serialization
    MultiplicationResultAttempt() {
        user = null;
        multiplication = null;
        resultAttempt = -1;
        correct = false;
    }

    public MultiplicationResultAttempt(User user, Multiplication multiplication, int resultAttempt, boolean correct) {
        this.user = user;
        this.multiplication = multiplication;
        this.resultAttempt = resultAttempt;
        this.correct = correct;
    }

    public User getUser() {
        return user;
    }

    public Multiplication getMultiplication() {
        return multiplication;
    }

    public int getResultAttempt() {
        return resultAttempt;
    }

    public boolean isCorrect() {
        return correct;
    }

    public Long getId() {
        return id;
    }
}