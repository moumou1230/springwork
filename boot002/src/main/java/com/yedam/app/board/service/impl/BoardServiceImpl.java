package com.yedam.app.board.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	private BoardMapper boardMapper;
	
	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	@Override
	public List<BoardVO> boardList() {
		return boardMapper.selectBoardAll();
	}
	@Override
	public BoardVO boardInfo(BoardVO boardVO) {
		return boardMapper.selectBoardInfo(boardVO);
	}
	@Override
	public int insertBoard(BoardVO boardVO) {
		int result = boardMapper.insertBoardInfo(boardVO);
		
		return result == 1 ? boardVO.getBno() : -1; //삼항연산자는 변수에 값을 주는경우에 주로 쓰인다.
	}
	@Override
	public Map<String, Object> updateBoard(BoardVO boardVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		int result = boardMapper.updateBoardInfo(boardVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		String updateDate = sdf.format(today);
		
		map.put("date", updateDate);
		map.put("success", isSuccessed);
		map.put("target", boardVO);
		
		return map;
	}
	@Override
	public int deleteBoard(int boardNo) {
		return boardMapper.deleteBoardInfo(boardNo);
	}
}
