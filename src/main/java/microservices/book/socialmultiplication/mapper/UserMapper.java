package microservices.book.socialmultiplication.mapper;

import microservices.book.socialmultiplication.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface allows us to save and retrieve Users
 */
@Repository
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE alias LIKE #{alias}")
    User findByAlias(@Param("alias") String alias);

    @Select("INSERT INTO users (alias) " +
            "SELECT * FROM (SELECT #{alias}) AS tmp " +
            "WHERE NOT EXISTS ( SELECT * FROM users WHERE alias LIKE #{alias} ); " +
            "SELECT * FROM users WHERE alias LIKE #{alias} LIMIT 1")
    User firstOrCreateByAlias(@Param("alias") String alias);
}
