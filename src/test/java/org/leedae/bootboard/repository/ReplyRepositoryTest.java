package org.leedae.bootboard.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.leedae.bootboard.domain.Board;
import org.leedae.bootboard.domain.Reply;
import org.leedae.bootboard.dto.BoardListReplyCountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Log4j2
class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private BoardRepository boardRepository;


    @Test
    public void testInsert(){

        //실제 DB에 있는 bno
        Long bno = 101L;

        Board board = Board.builder()
                .bno(bno)
                .build();

        Reply reply = Reply.builder()
                .board(board)
                .replyText("댓글")
                .replyer("replyer1")
                .build();

        replyRepository.save(reply);
    }

    @Test
    public void testBoardReplies(){
        Long bno = 101L;

        Pageable pageable = PageRequest.of(0,10, Sort.by("rno").descending());

        Page<Reply> result = replyRepository.listOfBoard(bno,pageable);

        result.getContent().forEach(reply ->{
            log.info(reply);
        });

    }

    @Test
    public void testSearchReplyCount(){

        String []types = {"t","c","w"};

        String keyword = "1";

        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());

        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types,keyword,pageable);

        //total pages
        log.info(result.getTotalPages());

        //pageSize
        log.info(result.getSize());

        //pageNumber
        log.info(result.getNumber());

        //prev next
        log.info(result.hasPrevious() + ": " + result.hasNext());

        result.getContent().forEach(board ->log.info(board));

    }

}