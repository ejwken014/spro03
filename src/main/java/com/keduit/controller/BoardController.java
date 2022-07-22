package com.keduit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;
import com.keduit.domain.PageDTO;
import com.keduit.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@Log4j
@RequestMapping("/board/*")
public class BoardController {
   
   private final BoardService service;
   
//   @GetMapping("/list")
//   public void list(Model model) {
//      
//      log.info("list.........");
//      model.addAttribute("list", service.getList());
//      
//   }

   @GetMapping("/list")
   //Model : 파라미터가 아닌, 다른 곳에서 데이터를 가져올 때 사용
   //view까지 데이터를 전달 하고 싶을 때
   public void list(Criteria cri, Model model) {
    
      log.info("list........."+cri);
      model.addAttribute("list", service.getList(cri));
      int total = service.getTotalCount(cri);
      log.info("total : "+total);
      
      model.addAttribute("pageMaker",new PageDTO(total, cri));
    
   }
   
   @GetMapping("/register")
   //return이 없으면 메소드명과 같은 jsp를 불러온다.
   public void register() {
	   
   }
   
   @PostMapping("/register")
   public String register(BoardVO board, RedirectAttributes rttr) {
	   log.info("register.........."+board);
	   
	   Long bno = service.register(board);
	   log.info("BNO : "+bno);
	   
	   rttr.addFlashAttribute("result", board.getBno());
	   
	   //response.sendRedirect("...");
	   return "redirect:/board/list";
	   //리턴타입을 String 타입으로 하면 분기점을 나눌 수 있음
	   // /board/list.jsp 를 찾아서 화면에 띄움
   }
   
//   @GetMapping("/get")
//   public void get(@RequestParam("bno") Long bno, Model model) {
//	   log.info("/get.............");
//	   model.addAttribute("board", service.get(bno));
//   }
   
   //매핑 2가지
   @GetMapping({"/get","/modify"})
   public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
	   log.info("/get or modify.........");
	   model.addAttribute("board",service.get(bno));
   }
   
   
   @PostMapping("/modify")
   public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
	   log.info("modify..........."+board);
	   int count = service.modify(board);
	   
	   if(count == 1) {
		   rttr.addFlashAttribute("result", "수정 성공");
	   }
	   
//	   rttr.addAttribute("pageNum", cri.getPageNum());
//	   rttr.addAttribute("amount", cri.getAmount());
//	   rttr.addAttribute("type", cri.getType());
//	   rttr.addAttribute("keyword", cri.getKeyword());
	   
	   return "redirect:/board/list"+cri.getListLink();
   }
   
   @PostMapping("/remove")//삭제 후 리스트로 돌아갈 것이기 때문에 RedirectAttributes 사용
   public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
	   log.info("remove.............."+bno);
	   
	   if(service.remove(bno)) {
		   rttr.addFlashAttribute("result","삭제 success");
	   }
	   
//	   rttr.addAttribute("pageNum",cri.getPageNum());
//	   rttr.addAttribute("amount",cri.getAmount());
//	   rttr.addAttribute("type", cri.getType());
//	   rttr.addAttribute("keyword", cri.getKeyword());
	   return "redirect:/board/list"+cri.getListLink();
   }

}