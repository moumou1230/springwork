package com.yedam.app.board.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Controller
public class BoardController {
	private BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	// 전체조회 : URI - boardList / RETURN - board/boardList
	@GetMapping("boardList")
	public String boardList(Model model) {
		List<BoardVO> list = boardService.boardList();
		model.addAttribute("boards", list);
		return "board/boardList";
	}

	// 단건조회 : URI - boardInfo / PARAMETER - BoardVO(QueryString) - 객체형태고
	// queryString이면 어노테이션을 선언하지 않겠다.
	// RETURN - board/boardInfo
	@GetMapping("boardInfo")
	public String boardInfo(BoardVO boardVO, Model model) {
		BoardVO findVO = boardService.boardInfo(boardVO);

		model.addAttribute("board", findVO);
		return "board/boardInfo";
	}

	// 등록 - 페이지 : URI - boardInsert / RETURN - board/boardInsert
	@GetMapping("boardInsert")
	public String boardInsertPage(BoardVO boardVO) {
		return " board/boardInsert";
	}

	// 등록 - 처리 : URI - boardInsert / PARAMETER - BoardVO(QueryString)
	// RETURN - 단건조회 다시 호출
	@PostMapping("boardInsert") // 일반적으로 <from/> 활용 => QueryString
	public String boardInsert(BoardVO boardVO) {
		int bono = boardService.insertBoard(boardVO);

		return "redirect:boardInfo?bno=" + bono;
	}

	// 수정 - 페이지 : URI - boardUpdate / PARAMETER - BoardVO(QueryString)
	// RETURN - board/boardUpdate
	@GetMapping("boardUpdate")
	public String boardUpdatePage(BoardVO boardVO, Model model) {
		BoardVO findVO = boardService.boardInfo(boardVO);
		model.addAttribute("board", findVO);
		return "board/boardUpdate";
	}

	// 수정 - 처리 : URI - boardUpdate / PARAMETER - BoardVO(JSON)
	// RETURN - 수정결과 데이터(Map) -- 데이터가 리턴되면 AJAX를 사용하겠다는 의미(ResponseBody)
	//=>등록이랑 같다 (내부에서 수행하는 쿼리문만 -update문) 
	@PostMapping("boardUpdate")
	@ResponseBody
	public Map<String, Object> boardUpdate(@RequestBody BoardVO boardVO) {//body를 가지고 있는게 post랑 put 뿐이라서 get사용불가능
		return boardService.updateBoard(boardVO);
	}

	// 삭제 - 처리 : URI - boardDelete / PARAMETER - Integer --파리미터가 단일이면 QueryString 으로 넘긴다는 의미
	// RETURN - 전체조회 다시 호출
	@GetMapping("boardDelete")
	public String boardDelete(Integer bno) {//@RequestParam 어노테이션이 달리면 필수가 된다.
		boardService.deleteBoard(bno);
		return "redirect:boardList";
	}
}
