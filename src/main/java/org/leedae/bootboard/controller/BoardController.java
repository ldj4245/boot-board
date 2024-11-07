package org.leedae.bootboard.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.leedae.bootboard.dto.BoardDTO;
import org.leedae.bootboard.dto.BoardListReplyCountDTO;
import org.leedae.bootboard.dto.PageRequestDTO;
import org.leedae.bootboard.dto.PageResponseDTO;
import org.leedae.bootboard.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes){

        log.info("remove post.." + bno);
        boardService.remove(bno);

        redirectAttributes.addFlashAttribute("result","removed");

        return "redirect:/board/list";
    }

    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid BoardDTO boardDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

        log.info("board modify post........" + boardDTO);

        if(bindingResult.hasErrors()){
            log.info("has errors.....");

            String link = pageRequestDTO.getLink();

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("bno",boardDTO.getBno());

            return "redirect:/board/modify?"+link;

        }

        boardService.modify(boardDTO);

        redirectAttributes.addFlashAttribute("result","modified");
        redirectAttributes.addAttribute("bno",boardDTO.getBno());
        return "redirect:/board/read";


    }



    @GetMapping({"/read","/modify"})
    public void read(Long bno, PageRequestDTO pageRequestDTO, Model model){

        log.info("요청값:{}",pageRequestDTO.getLink());
        BoardDTO boardDTO = boardService.readOne(bno);
        log.info(boardDTO);

        model.addAttribute("dto",boardDTO);
    }

    @GetMapping("/register")
    public void registerGET(){

    }

    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("board POST register");

        if(bindingResult.hasErrors()){
            log.info("has errors........");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/board/register";
        }

        log.info(boardDTO);

        Long bno = boardService.register(boardDTO);

        redirectAttributes.addFlashAttribute("result",bno);

        return "redirect:/board/list";


    }


    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        PageResponseDTO<BoardListReplyCountDTO> responseDTO =
                boardService.listWithReplyCount(pageRequestDTO);

        log.info(responseDTO);

        model.addAttribute("responseDTO",responseDTO);
    }

}
