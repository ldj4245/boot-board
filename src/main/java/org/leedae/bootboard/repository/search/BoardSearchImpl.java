package org.leedae.bootboard.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.leedae.bootboard.domain.Board;
import org.leedae.bootboard.domain.QBoard;
import org.leedae.bootboard.domain.QReply;
import org.leedae.bootboard.dto.BoardListReplyCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {



    public BoardSearchImpl(){
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard board = QBoard.board; //Q도메인 객체

        JPQLQuery<Board> query = from(board);

        query.where(board.title.contains("1"));

        //paging
        this.getQuerydsl().applyPagination(pageable,query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if((types != null && types.length >0) && keyword != null){

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types){

                switch (type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        //bno > 0
        query.where(board.bno.gt(0L));


        //paging
        this.getQuerydsl().applyPagination(pageable,query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

       return new PageImpl<>(list,pageable,count);

    }

    @Override
    public Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable) {


        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        JPQLQuery<Board> query = from(board);
        query.leftJoin(reply).on(reply.board.eq(board));

        query.groupBy(board);

        if( (types != null && types.length >0) && keyword != null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types){

                switch (type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }

            query.where(booleanBuilder);
        }

        //bno > 0
        query.where(board.bno.gt(0L));

        JPQLQuery<BoardListReplyCountDTO> dtoQuery = query.select(Projections.
                bean(BoardListReplyCountDTO.class,
                        board.bno,
                        board.title,
                        board.writer,
                        board.regDate,
                        reply.count().as("replyCount")
                ));
        this.getQuerydsl().applyPagination(pageable,dtoQuery);

        List<BoardListReplyCountDTO> dtoList = dtoQuery.fetch();

        long count = dtoQuery.fetchCount();

        return new PageImpl<>(dtoList,pageable,count);


    }
}
