package microservices.book.socialmultiplication.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
class RandomGeneratorServiceImpl implements RandomGeneratorService {
    private final static int MINIMUM_FACTOR = 11;
    private final static int MAXIMUM_FACTOR = 99;

    @Override
    public int generateRandomFactor() {
        return new Random().nextInt((MAXIMUM_FACTOR - MINIMUM_FACTOR) + 1) + MINIMUM_FACTOR;
    }
}