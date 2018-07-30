package microservices.book.socialmultiplication.mapper;

import microservices.book.socialmultiplication.domain.Multiplication;
import microservices.book.socialmultiplication.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * This interface allows us to save and retrieve Multiplications
 */
@Mapper
@Repository
public interface MultiplicationMapper {
    @Select("INSERT INTO multiplication (factora, factorb) " +
            "SELECT * FROM (SELECT #{factorA}, #{factorB}) AS tmp " +
            "WHERE NOT EXISTS ( SELECT * FROM multiplication WHERE factorA = #{factorA} AND factorB = #{factorB} );" +
            "SELECT * FROM multiplication WHERE factora = #{factorA} AND factorb = #{factorB} LIMIT 1")
    @ResultMap("BaseResultMap")
    Multiplication firstOrCreate(Multiplication multiplication);
}
