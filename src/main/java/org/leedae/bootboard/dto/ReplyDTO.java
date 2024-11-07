package org.leedae.bootboard.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {

    private Long rno; //댓글번호

    @NotNull
    private Long bno; //게시판번호


    @NotEmpty
    private String replyText; //댓글 텍스트

    @NotEmpty
    private String replyer;

    private LocalDateTime regDate, modDate;



}
