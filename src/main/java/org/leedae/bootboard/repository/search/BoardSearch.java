package org.leedae.bootboard.repository.search;

import org.leedae.bootboard.domain.Board;
import org.leedae.bootboard.dto.BoardListReplyCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


//BoardService에 쓰기 위해 밑거름
public interface BoardSearch {

    Page<Board> search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types,
                                                      String keyword,
                                                      Pageable pageable);
}
