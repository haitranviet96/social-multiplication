package microservices.book.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * This class represents the Score linked to an attempt in the game,
 * with an associated user and the timestamp in which the score
 * is registered.
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class ScoreCard {
    // The default score assigned to this card, if notspecified.
    public static final int DEFAULT_SCORE = 10;

    private final Long cardId;
    private final Long userId;
    private final Long attemptId;
    private final long scoreTimestamp;
    private final int score;

    // Empty constructor for JSON / JPA
    public ScoreCard() {
        this(null, null, null, 0, 0);
    }

    public ScoreCard(final Long userId, final Long attemptId) {
        this(null, userId, attemptId, System.currentTimeMillis(), DEFAULT_SCORE);
    }
}
