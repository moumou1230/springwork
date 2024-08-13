package com.yedam.app.board.mapper;

import java.util.List;

import com.yedam.app.board.service.BoardVO;

public interface BoardMapper {
	//전체조회
	public List<BoardVO> selectBoardAll();
	
	//단건조회 : 조건 - bno
	public BoardVO selectBoardInfo(BoardVO boardVO);
	
	//등록 : 대상 = title,contents, writer, regdate, image
	public int insertBoardInfo(BoardVO boardVO);
	
	//수정: 대상 = title,contents, writer, regdate, image
	public int updateBoardInfo(BoardVO boardVO);
	
	//삭제 : 조건 - bno
	public int deleteBoardInfo(int BoardNo);
}
