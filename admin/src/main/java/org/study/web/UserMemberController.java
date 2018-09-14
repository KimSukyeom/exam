package org.study.web;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.study.dao.UserMemberDAO;
import org.study.dto.UserMember;

/**
 * 
 * @author kissi
 * - listAll
 * - user
 * - procCreate
 */

@Controller
public class UserMemberController {
	private static final Logger logger = LoggerFactory.getLogger(UserMemberController.class);
	
	@Autowired
	private UserMemberDAO dao;
	
	/**
	 * listAll �޼ҵ�� UserMember(dto)�� ��� name, age, hobby �� �����ֱ� ���� �޼ҵ�
	 * @param model UserMember �Ӽ� ���� �ڵ����� spring���� �ҷ� �������� ���
	 * @return list.jsp �� �������� �̵�
	 */
	
	@RequestMapping("list")
	public String listAll(Model model) {
		logger.info("/list was called");
		try {
			List<UserMember> list = dao.listAll();
			model.addAttribute("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "list";
	}
	
	/**
	 * ó�� index.jsp�� name, age, hobby ������ �� �����ֱ� ���� �޼ҵ�
	 * 
	 * @return index.jsp �� �������� �̵�.
	 */
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String user() {
		logger.info("/index was called");
		return "index";
	}
	
	/**
	 * index.jsp�� name, age, hobby �� �Է�������  �Է¹��� ���� post ������� 
	 * ���� ���������� ����̵Ǹ� success �����ϸ� failure �� ȣ���Ͽ� redirect list��
	 * ������ ���� �޼ҵ�
	 * 
	 * @param user
	 * 
	 *  dto ������� name, age, hobby �Ӽ����� ����ϱ� parameter
	 *  
	 * @param rttr
	 * 
	 * addFlashAttribute() �� ����ϱ� ���� parameter
	 * 
	 * 
	 * @return list.jsp �� �������� �̵�
	 */
	
	@RequestMapping(value="/index", method=RequestMethod.POST)
	public String procCreate(UserMember user, RedirectAttributes rttr) {
		logger.info("/user post was called: " + user);
		// database process
		try {
			dao.create(user);
			rttr.addFlashAttribute("msg", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			rttr.addFlashAttribute("msg","failure");
			e.printStackTrace();
		}
		
		return "redirect:/list";
	}
}
