package microservices.book.socialmultiplication.controller;

import microservices.book.socialmultiplication.domain.Multiplication;
import microservices.book.socialmultiplication.domain.MultiplicationResultAttempt;
import microservices.book.socialmultiplication.domain.User;
import microservices.book.socialmultiplication.mapper.MultiplicationMapper;
import microservices.book.socialmultiplication.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class implements a REST API for our Multiplication application.
 */
@RestController
@RequestMapping("/test")
final class TestController {

    private final UserMapper userMapper;
    private final MultiplicationMapper multiplicationMapper;

    @Autowired
    public TestController(final MultiplicationMapper multiplicationMapper,
                          final UserMapper userMapper) {
        this.userMapper = userMapper;
        this.multiplicationMapper = multiplicationMapper;
    }

    @GetMapping
    User getUserByAlias(@RequestParam("alias") String alias) {
        return userMapper.findByAlias(alias);
    }

    @GetMapping("/create")
    ResponseEntity<Multiplication> createMulti(@RequestParam("factora") int factorA, @RequestParam("factorb") int factorB) {
        return ResponseEntity.ok(multiplicationMapper.firstOrCreate(new Multiplication(factorA,factorB)));
    }



}