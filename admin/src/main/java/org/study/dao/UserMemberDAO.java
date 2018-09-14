package org.study.dao;


import java.util.List;

import org.study.dto.UserMember;

public interface UserMemberDAO {
	public void create(UserMember user) throws Exception;
	public List<UserMember> listAll() throws Exception;
}
