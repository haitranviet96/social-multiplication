package microservices.book.gamification.mapper;

import microservices.book.gamification.domain.LeaderBoardRow;
import microservices.book.gamification.domain.ScoreCard;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ScoreCardMapper {
    /**
     * Gets the total score for a given user, being the sum of the scores of all his ScoreCards.
     *
     * @param userId the id of the user for which the total score should be retrieved
     * @return the total score for the given user
     */
    @Select("SELECT SUM(score) FROM score_card WHERE user_id = #{userId} GROUP BY user_id")
    int getTotalScoreForUser(@Param("userId") final Long userId);

    /**
     * Retrieves a list of {@link LeaderBoardRow}s representing the Leader Board of users and their total score.
     *
     * @return the leader board, sorted by highest score first.
     */
    @Select("SELECT user_id,SUM(score) " +
            "FROM score_card GROUP BY user_id ORDER BY SUM(score) DESC")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "totalScore", column = "sum")
    })
    List<LeaderBoardRow> findFirst10();

    @Insert("INSERT INTO score_card (attempt_id, score, score_ts, user_id) " +
            "VALUES(#{attemptId},#{score},#{scoreTimestamp},#{userId})")
    void save(ScoreCard scoreCard);

    /**
     * Retrieves all the ScoreCards for a given user, identified by his user id.
     *
     * @param userId the id of the user
     * @return a list containing all the ScoreCards for the given user, sorted by most recent.
     */
    @Select("SELECT * FROM score_card WHERE user_id = #{userId} ORDER BY score_ts DESC")
    @ResultMap("BaseResultMap")
    List<ScoreCard> findByUserIdOrderByScoreTimestampDesc(@Param("userId") final Long userId);
}
