package com.care.root;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.care.root.member.controller.MemberController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@RunWith(SpringRunner.class)	// test동작시킬때 꼭 필요
@ContextConfiguration(locations= {
		"classpath:TestMember.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		})
public class TestMock {		
	@Autowired MemberController mc;
	MockMvc mock;
	
	@Before
	public void setUp() {			// Test가 실행되기 전에 실행됨
		System.out.println("test 실행전---");
		mock = MockMvcBuilders.standaloneSetup(mc).build();
	}
	@Test
	public void testIndex() throws Exception {
		System.out.println("-------- test코드 실행");
		mock.perform(get("/index"))	// 경로앞에 무조건 / 들어와야 함
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(forwardedUrl("member/index"));
	}
	
	@Test
	@Transactional(transactionManager = "txMgr")
	public void testInsert() throws Exception{
		mock.perform(post("/insert").param("id","999").param("name", "김길이"))
		.andDo(print())
		.andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void testMemberview() throws Exception {
	        mock.perform(get("/memberview")).andDo(print())
	        .andExpect(status().isOk())
	        .andExpect(forwardedUrl("member/memberview"))
	        .andExpect(model().attributeExists("list"));
	}

}








