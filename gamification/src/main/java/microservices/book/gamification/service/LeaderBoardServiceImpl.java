package microservices.book.gamification.service;

import microservices.book.gamification.domain.LeaderBoardRow;
import microservices.book.gamification.mapper.ScoreCardMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {
    private ScoreCardMapper scoreCardMapper;

    LeaderBoardServiceImpl(final ScoreCardMapper scoreCardMapper) {
        this.scoreCardMapper = scoreCardMapper;
    }

    @Override
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        return scoreCardMapper.findFirst10();
    }
}
