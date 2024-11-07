package org.leedae.bootboard.service;

import org.leedae.bootboard.dto.PageRequestDTO;
import org.leedae.bootboard.dto.PageResponseDTO;
import org.leedae.bootboard.dto.ReplyDTO;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    ReplyDTO read(Long rno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);
}
