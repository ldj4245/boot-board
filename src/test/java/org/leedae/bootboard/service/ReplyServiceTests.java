package org.leedae.bootboard.service;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.leedae.bootboard.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ReplyServiceTests {

    @Autowired
    private ReplyService replyService;

    @Test
    public void testRegister(){

        ReplyDTO replyDTO = ReplyDTO.builder()
                .replyText("ReplyDTO Text")
                .replyer("replyer")
                .bno(101L)
                .build();

        log.info(replyService.register(replyDTO));
    }

}