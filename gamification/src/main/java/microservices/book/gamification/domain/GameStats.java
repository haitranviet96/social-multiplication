package microservices.book.gamification.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This object contains the result of one or many iterations of the game.
 * It may contain any combination of {@link ScoreCard} objects and {@link BadgeCard} objects.
 * <p>
 * It can be used as a delta (as a single game iteration) or to represent the total amount of score / badges.
 */
public final class GameStats {
    private final Long userId;
    private final int score;
    private final List<Badge> badges;

    public GameStats() {
        this.userId = 0L;
        this.score = 0;
        this.badges = new ArrayList<>();
    }

    public GameStats(Long userId, int score, List<Badge> badges) {
        this.userId = userId;
        this.score = score;
        this.badges = badges;
    }

    public Long getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    /**
     * Factory method to build an empty instance (zero points and no badges)
     *
     * @param userId the user's id
     * @return a {@link GameStats} object with zero score and no badges
     */
    public static GameStats emptyStats(final Long userId) {
        return new GameStats(userId, 0, Collections.emptyList());
    }

    /**
     * @return an unmodifiable view of the badge cards list
     */
    public List<Badge> getBadges() {
        return Collections.unmodifiableList(badges);
    }
}
