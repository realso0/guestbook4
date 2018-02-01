package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> getList() {
		List<GuestbookVo> list = sqlSession.selectList("guestbook.getList"); //앞에는 쿼리문의 위치, 뒤에는 입력받는 값
		return list;
	}
	
	public void insert(GuestbookVo guestbookVo) {
		sqlSession.insert("guestbook.insert", guestbookVo);
//		int count=sqlSession.insert("guestbook.insert", guestbookVo);
//		System.out.println(count); //1건 추가완료 됬는다는 것을 출력함.
	}
	
	public void delete(int no1, String password) {
		Map<String, Object> guestbookMap=new HashMap<String, Object>();
		guestbookMap.put("no", no1);
		guestbookMap.put("password", password);
		
		sqlSession.delete("guestbook.delete", guestbookMap);
	}
}
