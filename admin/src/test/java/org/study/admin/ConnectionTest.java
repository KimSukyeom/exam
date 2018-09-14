package org.study.admin;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"
})
public class ConnectionTest {
	@Autowired
	private DataSource ds;
	
	@Autowired 
	private SqlSessionFactory sqlFactory;
	
	private static final Logger logger = 
			LoggerFactory.getLogger(ConnectionTest.class);

	@Inject
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        logger.info("setup............");
    }
    
    // 스프링프로젝트의 was의 기동없이 /doA 컨트롤러에 get방식 리퀘스트 테스트
    @Test
    public void testDoA() throws Exception{
    	mockMvc.perform(MockMvcRequestBuilders.get("/doA"));
    }
	
	// sql과 mybatis 연결 테스트
	@Test
	public void testFactory(){
		
		System.out.println(sqlFactory);
		
	}
	
	@Test
	public void testSession()throws Exception{
		
		try(SqlSession session = sqlFactory.openSession()){
			
			System.out.println(session);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	// 데이터베이스 연결 됐는지 테스트
	@Test
	public void testConnection() throws Exception {
		Assert.assertNotNull(ds.getConnection());
		
	}
	
	
	
	
}
