package microservices.book.socialmultiplication.mapper;

import microservices.book.socialmultiplication.domain.MultiplicationResultAttempt;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Insert("INSERT INTO multiplication_result_attempt(user_id,multiplication_id,result_attempt,correct) " +
            "VALUES(#{user.id},#{multiplication.id},#{resultAttempt},#{correct})")
    int save(MultiplicationResultAttempt checkedAttempt);
}