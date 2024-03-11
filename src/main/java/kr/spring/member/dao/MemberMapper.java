package kr.spring.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;

@Mapper
public interface MemberMapper {
	//회원관리 - 사용자
	@Select("SELECT hwmember_seq.nextval FROM dual")
	public int selectMem_num();
	@Insert("INSERT INTO hwmember (mem_num,email) VALUES (#{mem_num},#{email})")
	public void insertMember(MemberVO member);
	public void insertMember_detail(MemberVO member);
	public MemberVO selectCheckMember(String email);
	@Select("SELECT * FROM hwmember JOIN hwmember_detail USING(mem_num) WHERE mem_num=#{mem_num}")
	public MemberVO selectMember(int mem_num);
	public void updateMember(MemberVO member);
	public void updateMember_detail(MemberVO member);
	public void updatePassword(MemberVO member);
	public void deleteMember(int mem_num);
	public void deleteMember_detail(int mem_num);
	//자동 로그인
	@Update("UPDATE hwmember_detail SET au_id=#{au_id} WHERE mem_num=#{mem_num}")
	public void updateAu_id(String au_id,int mem_num);
	@Select("SELECT mem_num,auth,au_id,passwd,name,email FROM hwmember JOIN hwmember_detail USING(mem_num) WHERE au_id=#{au_id}")
	public MemberVO selectAu_id(String au_id);
	public void deleteAu_id(int mem_num);
	//프로필 이미지 업데이트
	@Update("UPDATE hwmember_detail SET photo_name=#{photo_name} WHERE mem_num=#{mem_num}")
	public void updateProfile(MemberVO member);
	
	//채팅 회원이름 검색
	@Select("SELECT mem_num,id,nick_name FROM spmember WHERE auth >= 2 AND id LIKE '%' || #{id} || '%'" )
	public List<MemberVO> selectSearchMember(String id);
	
	
	//회원관리 - 관리자
	
}
