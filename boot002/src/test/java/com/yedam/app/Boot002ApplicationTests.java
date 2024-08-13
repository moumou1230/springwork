package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardVO;

@SpringBootTest
class Boot002ApplicationTests {
	@Autowired
	public BoardMapper boardMapper;
	
	
	  //@Test 
	  void contextLoads() { List<BoardVO> list =
	  boardMapper.selectBoardAll(); assertTrue(!list.isEmpty());
	 
	  }
	 
	
	//@Test
	void boardInfo() {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(100);
		
		BoardVO findVO = boardMapper.selectBoardInfo(boardVO);
		
		assertEquals("First Test",findVO.getTitle());
	}
	
	//@Test
	void boardInsert() {
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("작성되니");
		boardVO.setContents("되니");
		boardVO.setWriter("hong");
		boardVO.setRegdate(new Date());
		
		int result = boardMapper.insertBoardInfo(boardVO);
		System.out.println("제목 : " + boardVO.getTitle());
		
		assertEquals(1, result);
	}
	
	//@Test
	void boardDelete() {
		int result = boardMapper.deleteBoardInfo(101);
		assertEquals(1, result);
	}
	
	@Test
	void boardUpdate() {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(100);
		
		BoardVO findVO = boardMapper.selectBoardInfo(boardVO);
		System.out.println(findVO);
		findVO.setTitle("수정함");
		
		int result = boardMapper.updateBoardInfo(findVO);
		assertEquals(1, result);
	}
}
