package microservices.book.gamification.mapper;

import microservices.book.gamification.domain.BadgeCard;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Handles data operations with BadgeCards
 */
@Mapper
@Repository
public interface BadgeCardMapper {
    /**
     * Retrieves all BadgeCards for a given user.
     *
     * @param userId the id of the user to look for BadgeCards
     * @return the list of BadgeCards, sorted by most recent.
     */
    @Select("SELECT * FROM badge_card WHERE user_id = #{userId} ORDER BY badge_ts DESC")
    @ResultMap("BaseResultMap")
    List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(@Param("userId") final Long userId);

    @Insert("INSERT INTO badge_card (user_id, badge_ts, badge) " +
            "VALUES(#{userId},#{badgeTimestamp},#{badge}::badge)")
    void save(BadgeCard badgeCard);
}
