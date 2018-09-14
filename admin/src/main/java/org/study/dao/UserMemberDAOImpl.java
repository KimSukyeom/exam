package org.study.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.study.dto.UserMember;

@Repository
public class UserMemberDAOImpl implements UserMemberDAO{
	
	private static final String namespace = "org.study.mapper.UserMemberMapper";
	
	@Autowired
	private SqlSession session;
	
	@Override
	public void create(UserMember user) throws Exception {
		session.insert(namespace + ".create", user);
		
	}

	@Override
	public List<UserMember> listAll() throws Exception {
		
		return session.selectList(namespace + ".listAll");
	}
	
	
	
	
	
}
