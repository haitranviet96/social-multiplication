package microservices.book.socialmultiplication.mapper;

import microservices.book.socialmultiplication.domain.MultiplicationResultAttempt;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This interface allow us to store and retrieve attempts
 */
@Mapper
@Repository
public interface MultiplicationResultAttemptMapper {
    /**
     * @return the latest 5 attempts for a given user, identified by their alias.
     */
    @Select("SELECT mra.*,u.alias,m.factora,m.factorb " +
            "FROM multiplication_result_attempt as mra,users as u,multiplication as m " +
            "WHERE u.alias LIKE #{userAlias} AND u.id = mra.user_id AND mra.multiplication_id = m.id " +
            "ORDER BY id DESC LIMIT 5")
    @ResultMap("BaseResultMap")
    List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(@Param("userAlias") String userAlias);

    /**
     * @param resultId id of the attempt
     * @return the attempt identified by resultId.
     */
    @Select("SELECT mra.*,u.alias,m.factora,m.factorb " +
            "FROM multiplication_result_attempt as mra,users as u,multiplication as m " +
            "WHERE mra.id = #{resultId} AND u.id = mra.user_id AND mra.multiplication_id = m.id LIMIT 1")
    @ResultMap("BaseResultMap")
    MultiplicationResultAttempt findById(@Param("resultId") Long resultId);

    /**
     * save new attempt
     * @param checkedAttempt attempt need to be saved
     * @return id after saved
     */
    @Select("INSERT INTO multiplication_result_attempt(user_id,multiplication_id,result_attempt,correct) " +
            "VALUES(#{user.id},#{multiplication.id},#{resultAttempt},#{correct}) RETURNING id;")
    int save(MultiplicationResultAttempt checkedAttempt);
}