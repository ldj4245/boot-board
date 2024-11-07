package org.leedae.bootboard.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.leedae.bootboard.dto.BoardDTO;
import org.leedae.bootboard.dto.PageRequestDTO;
import org.leedae.bootboard.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Log4j2
class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {

        log.info(boardService.getClass().getName());


        BoardDTO boardDTO = BoardDTO.builder()
                .title("Sample Title...")
                .content("Sample Content")
                .writer("user00")
                .build();

        Long bno = boardService.register(boardDTO);


        boardService.register(boardDTO);

        log.info("bno : " + bno);

    }


    @Test
    public void testModify() {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("updated....101")
                .content("updated content 101...")
                .build();


        boardService.modify(boardDTO);
    }


    @Test
    public void testList(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();


        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);
    }

}