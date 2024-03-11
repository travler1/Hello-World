package kr.spring.member.service;

import java.util.List;

import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;

public interface MemberService {
	//회원관리 - 사용자
	public void insertMember(MemberVO member); //insertMember, selectMem_num, insertMember_detail 이렇게 세 개를 트랜잭션처리할거임.
	public MemberVO selectCheckMember(String email);
	public MemberVO selectMember(int mem_num);
	public void updateMember(MemberVO member);
	public void updatePassword(MemberVO member);
	public void deleteMember(int mem_num);
	//자동 로그인
	public void updateAu_id(String au_id,int mem_num);
	public MemberVO selectAu_id(String au_id);
	public void deleteAu_id(int mem_num);
	//프로필 이미지 업데이트
	public void updateProfile(MemberVO member);
	
	//채팅 회원이름 검색
	public List<MemberVO> selectSearchMember(String id);
	
	
	//회원관리 - 관리자
}
