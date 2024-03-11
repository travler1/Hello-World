package kr.spring.matching.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.matching.vo.AdviceVO;
import kr.spring.matching.vo.EmpVO;
import kr.spring.matching.vo.LetterVO;
import kr.spring.member.vo.MemberVO;

@Mapper
public interface MatchingMapper {
	
	
	/*=====================================
	 * 				매칭페이지기능
	 *====================================*/
	//현직자 등록
	public void insertEmp(EmpVO empVO);
	//현직자 등록리스트출력
	public List<EmpVO> listEmp(Map<String,Object> map);
	@Select("SELECT count(*) FROM emp JOIN hwmember USING(mem_num) WHERE auth=2")
	//현직자수 카운트
	public int selectEmpCount();
	//현직자 정보출력
	public EmpVO selectEmp(int mem_num);
	//현직자 회원리스트 출력
	@Select("SELECT * FROM hwmember WHERE auth=2")
	public List<MemberVO> selectMemberList();
	

	/*=====================================
	 * 			 첨삭(Advice) 기능
	 *====================================*/
	//첨삭요청하기
	public void insertAdvice(AdviceVO adviceVO);
	//받은첨삭 리스트
	public List<AdviceVO> selectReceivedAdvice(Map<String,Object> map);
	//보낸첨삭 리스트
	public List<AdviceVO> selectSentAdvice(Map<String,Object> map);
	//특정첨삭 출력
	@Select("SELECT * FROM advice WHERE advice_num=#{advice_num}")
	public AdviceVO selectAdvice(int advice_num);
	//첨삭 답장시(첨삭요청 존재유무확인)
	@Select("SELECT * FROM advice WHERE sender=#{sender} AND receiver=#{receiver}")
	public AdviceVO AdviceCheck(int sender, int receiver);
	//받은첨삭 갯수
	@Select("SELECT COUNT(*) FROM advice WHERE receiver=#{mem_nmm}")	
	public int receivedAdviceCount(int mem_num);
	//보낸첨삭 갯수
	@Select("SELECT COUNT(*) FROM advice WHERE sender=#{mem_nmm}")	
	public int sentAdviceCount(int mem_num);
	//받은첨삭 읽음처리
	@Update("UPDATE advice SET date_read= CASE WHEN date_read IS NULL THEN SYSDATE ELSE date_read END WHERE advice_num=#{advice_num}")
	public void readAdvice(int advice_num);
	//첨삭 답장하기
	public void completeAdvice(int sender, int receiver);
	//받은 첨삭 삭제상태로 변경
	@Update("UPDATE advice SET receive_del = 1 WHERE advice_num=#{advice_num}")
	public void deleteReceivedAdvice(int advice_num);
	//받은 첨삭 보낸사람 삭제상태여부 불러오기
	@Select("SELECT sent_del FROM advice WHERE advice_num=#{advice_num}")
	public int selectAdviceSentDel(int advice_num);
	//첨삭 테이블에서 삭제
	@Delete("DELETE FROM advice WHERE advice_num=#{advice_num}")
	public void deleteAdvice(int advice_num);
	//보낸 첨삭 삭제로 변경
	@Update("UPDATE advice SET sent_del = 1 WHERE advice_num=#{advice_num}")
	public void deleteSentAdvice(int advice_num);
	//보낸 첨삭 받은사람 삭제상태여부 불러오기
	@Select("SELECT receive_del FROM advice WHERE advice_num=#{advice_num}")
	public int selectAdviceReceiveDel(int advice_num);
	
}
