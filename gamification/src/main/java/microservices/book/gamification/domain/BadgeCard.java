package microservices.book.gamification.domain;

/**
 * This class links a Badge to a User. Contains also a
 * timestamp with the moment in which the user got it.
 */
public final class BadgeCard {
    private final Long badgeId;
    private final Long userId;
    private final long badgeTimestamp;
    private final Badge badge;

    public Long getBadgeId() {
        return badgeId;
    }

    public Long getUserId() {
        return userId;
    }

    public long getBadgeTimestamp() {
        return badgeTimestamp;
    }

    public Badge getBadge() {
        return badge;
    }

    public BadgeCard(Long badgeId, Long userId, long badgeTimestamp, Badge badge) {
        this.badgeId = badgeId;
        this.userId = userId;
        this.badgeTimestamp = badgeTimestamp;
        this.badge = badge;
    }

    public BadgeCard() {
        this(null, null, 0, null);
    }

    public BadgeCard(final Long userId, final Badge badge) {
        this(null, userId, System.currentTimeMillis(), badge);
    }
}
