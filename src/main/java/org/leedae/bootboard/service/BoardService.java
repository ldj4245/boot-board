package org.leedae.bootboard.service;

import org.leedae.bootboard.dto.BoardDTO;
import org.leedae.bootboard.dto.BoardListReplyCountDTO;
import org.leedae.bootboard.dto.PageRequestDTO;
import org.leedae.bootboard.dto.PageResponseDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    //댓글의 숫자까지 처리
    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);
}
