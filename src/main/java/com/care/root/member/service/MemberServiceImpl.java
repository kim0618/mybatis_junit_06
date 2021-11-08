package com.care.root.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.member.dao.MemberDAO;
import com.care.root.member.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired MemberDAO mapper;
	public void insertMember(MemberDTO dto) {
		mapper.insertMember(dto);
	}
	
	@Override
	public void memberView(Model model) {
		model.addAttribute("list", mapper.memberView());
	}
	
}
