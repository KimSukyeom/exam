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
	 * listAll 메소드는 UserMember(dto)에 담긴 name, age, hobby 를 보여주기 위한 메소드
	 * @param model UserMember 속성 값을 자동으로 spring에서 불러 오기위해 사용
	 * @return list.jsp 뷰 페이지로 이동
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
	 * 처음 index.jsp에 name, age, hobby 페이지 를 보여주기 위한 메소드
	 * 
	 * @return index.jsp 뷰 페이지로 이동.
	 */
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String user() {
		logger.info("/index was called");
		return "index";
	}
	
	/**
	 * index.jsp에 name, age, hobby 를 입력했을때  입력받은 값을 post 방식으로 
	 * 글이 성공적으로 등록이되면 success 실패하면 failure 를 호출하여 redirect list로
	 * 보내기 위한 메소드
	 * 
	 * @param user
	 * 
	 *  dto 기능으로 name, age, hobby 속성들을 사용하기 parameter
	 *  
	 * @param rttr
	 * 
	 * addFlashAttribute() 를 사용하기 위한 parameter
	 * 
	 * 
	 * @return list.jsp 뷰 페이지로 이동
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
