package kr.spring.matching.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.matching.dao.MatchingMapper;
import kr.spring.matching.vo.AdviceVO;
import kr.spring.matching.vo.EmpVO;
import kr.spring.matching.vo.LetterVO;
import kr.spring.member.vo.MemberVO;

@Service
@Transactional
public class MatchingServiceImpl implements MatchingService{
	
	@Autowired
	MatchingMapper matchingMapper;
	
	@Override
	public void insertEmp(EmpVO empVO) {
		matchingMapper.insertEmp(empVO);
	}

	@Override
	public List<EmpVO> listEmp(Map<String, Object> map) {
		return matchingMapper.listEmp(map);
	}

	@Override
	public EmpVO selectEmp(int mem_num) {
		return matchingMapper.selectEmp(mem_num);
	}

	@Override
	public int selectEmpCount() {
		return matchingMapper.selectEmpCount();
	}

	@Override
	public List<MemberVO> selectMemberList() {
		return matchingMapper.selectMemberList();
	}

	@Override
	public void insertAdvice(AdviceVO adviceVO) {
		matchingMapper.insertAdvice(adviceVO);
		
	}

	@Override
	public List<AdviceVO> selectReceivedAdvice(Map<String,Object> map) {
		return matchingMapper.selectReceivedAdvice(map);
	}

	@Override
	public List<AdviceVO> selectSentAdvice(Map<String,Object> map) {
		return matchingMapper.selectSentAdvice(map);
	}

	@Override
	public AdviceVO selectAdvice(int advice_num) {
		return matchingMapper.selectAdvice(advice_num);
	}

	@Override
	public int receivedAdviceCount(int mem_num) {
		return matchingMapper.receivedAdviceCount(mem_num);
	}

	@Override
	public int sentAdviceCount(int mem_num) {
		return matchingMapper.sentAdviceCount(mem_num);
	}

	@Override
	public AdviceVO readAdvice(int advice_num, HttpSession session) {
		
		MemberVO receiverVO = (MemberVO)session.getAttribute("user");
		int receiver_memnum = receiverVO.getMem_num();
		AdviceVO adviceVO = matchingMapper.selectAdvice(advice_num);
		if(receiver_memnum == adviceVO.getReceiver()) {
			matchingMapper.readAdvice(advice_num);
		}
		return matchingMapper.selectAdvice(advice_num);
	}
	
	
	//보낸 첨삭 삭제
	@Override
	public void deleteSentAdvice(int advice_num) {
		int del = matchingMapper.selectReceivedAdvice(advice_num);
		matchingMapper.deleteSentAdvice(advice_num);
		if(del==1) {
			matchingMapper.deleteAdvice(advice_num);
		}
	}
	
	//받은 첨삭 삭제
	@Override
	public void deleteReceivedAdvice(int advice_num) {
		int del = matchingMapper.selectAdviceSentDel(advice_num);
		matchingMapper.deleteReceivedAdvice(advice_num);
		if(del ==1) {
			matchingMapper.deleteAdvice(advice_num);
		}
	}

	@Override
	public AdviceVO AdviceCheck(int sender, int receiver) {
		return matchingMapper.AdviceCheck(sender, receiver);
	}

	@Override
	public MemberVO selectMember(int mem_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findMemnumById(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void completeAdvice(int sender, int receiver) {
		matchingMapper.completeAdvice(sender, receiver);
		matchingMapper.completeAdvice(receiver, sender);
	}
	
	
	
	

}
