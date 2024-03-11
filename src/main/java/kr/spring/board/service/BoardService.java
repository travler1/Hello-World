package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import kr.spring.board.vo.BoardFavVO;
import kr.spring.board.vo.BoardReplyVO;
import kr.spring.board.vo.BoardVO;

@Service
public interface BoardService {
	//부모글
	public List<BoardVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertBoard(BoardVO board);
	public BoardVO selectBoard(int board_num);
	public void updateHit(int board_num);
	public void updateBoard(BoardVO board);
	public void deleteBoard(int board_num);
	public void deleteFile(int board_num);

	//좋아요
	public BoardFavVO selectFav(BoardFavVO fav);
	public int selectFavCount(int board_num);
	public void insertFav(BoardFavVO fav);
	public void deleteFav(BoardFavVO boardFav);
	
	//댓글
	public List<BoardReplyVO> selectListReply(Map<String,Object> map);
	public int selectReplyRowCount(Map<String,Object> map);
	public BoardReplyVO selectReply(int re_num);
	public void insertReply(BoardReplyVO boardReply);
	public void updateReply(BoardReplyVO boardReply);
	public void deleteReply(int re_num);
}
