package microservices.book.socialmultiplication.controller;

import microservices.book.socialmultiplication.domain.Multiplication;
import microservices.book.socialmultiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class implements a REST API for our Multiplication application.
 */
@RestController
@RequestMapping("/multiplications")
final class MultiplicationController {

    private final MultiplicationService multiplicationService;

    @Autowired
    public MultiplicationController(final MultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @GetMapping("/random")
    Multiplication getRandomMultiplication() {
        return multiplicationService.createRandomMultiplication();
    }

}