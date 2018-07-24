package microservices.book.socialmultiplication.service;

import microservices.book.socialmultiplication.domain.*;
import microservices.book.socialmultiplication.mapper.MultiplicationMapper;
import microservices.book.socialmultiplication.mapper.MultiplicationResultAttemptMapper;
import microservices.book.socialmultiplication.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
class MultiplicationServiceImpl implements MultiplicationService {

    private RandomGeneratorService randomGeneratorService;
    private MultiplicationMapper multiplicationMapper;
    private MultiplicationResultAttemptMapper attemptMapper;
    private UserMapper userMapper;

    @Autowired
    public MultiplicationServiceImpl(final RandomGeneratorService randomGeneratorService,
                                     final MultiplicationMapper multiplicationMapper,
                                     final MultiplicationResultAttemptMapper attemptMapper,
                                     final UserMapper userMapper) {
        this.randomGeneratorService = randomGeneratorService;
        this.multiplicationMapper = multiplicationMapper;
        this.attemptMapper = attemptMapper;
        this.userMapper = userMapper;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }

    @Transactional
    @Override
    public boolean checkAttempt(MultiplicationResultAttempt attempt) {
        // Check if the user already exists for that alias
        User user = userMapper.firstOrCreateByAlias(attempt.getUser().getAlias());

        // Check if the multiplication already exists
        Multiplication multiplication = multiplicationMapper.firstOrCreate(attempt.getMultiplication());

        // Avoids 'hack' attempts
        Assert.isTrue(!attempt.isCorrect(), "You can't send an attempt marked as correct!!");

        // Check if the attempt is correct
        boolean isCorrect = attempt.getResultAttempt() ==
                attempt.getMultiplication().getFactorA() * attempt.getMultiplication().getFactorB();

        MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(
                user, multiplication, attempt.getResultAttempt(),isCorrect);

        // Stores the attempt
        attemptMapper.save(checkedAttempt);

        return isCorrect;
    }

    @Override
    public List<MultiplicationResultAttempt> getStatsForUser(String userAlias) {
        return attemptMapper.findTop5ByUserAliasOrderByIdDesc(userAlias);
    }
}