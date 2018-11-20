package com.meida.test.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import com.meida.mybatis.dto.MeetingDto;


public class MeetingTest {
	private static Logger logger = Logger.getLogger(MeetingTest.class);
	static {
		//PropertyConfigurator.configure(UserTest.class.getClassLoader().getResource("log4j.properties"));
	}
	private SqlSession getSqlSession() {
		String resource = "mybatis.config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			System.out.println(inputStream);
		} catch (IOException e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
		}

		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = factory.openSession();
		return sqlSession;
	}
	public static Date getSqlDate() {  
	    Date sqlDate = new java.sql.Date(new Date().getTime());  
	    return sqlDate;  
	}  
	@Test
	public void testInsert() {
		SqlSession sqlSession = getSqlSession();
		try {
			MeetingDto meetingDto = new MeetingDto();			
			meetingDto.setTitle("今天开会");
			meetingDto.setContent("有重要事情宣布");
			meetingDto.setAddress("上海市徐家汇99号");			
			System.out.println(getSqlDate());
			meetingDto.setStartTime(getSqlDate());
			meetingDto.setEndTime(getSqlDate());
			meetingDto.setSender("bing");
			sqlSession.insert("Lilei002.insertMeeting", meetingDto);
			sqlSession.commit();
			System.out.println("OK");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}

	}

	@Test
	public void testDelete() {
		SqlSession sqlSession = getSqlSession();

		try {
			sqlSession.delete("Lilei002.deleteMeeting", 3);
			sqlSession.commit();
			System.out.println("OK");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}

	}

	@Test
	public void testUpdate() {

		SqlSession sqlSession = getSqlSession();
		try {
			MeetingDto MeetingDto = new MeetingDto();
			MeetingDto.setId(4);
			MeetingDto.setTitle("今天开会22");
			MeetingDto.setContent("今天开会22");
			sqlSession.update("Lilei002.updateMeeting", MeetingDto);
			sqlSession.commit();
			System.out.println("OK");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}

	}

	@Test
	public void testSelectCount() {

		SqlSession sqlSession = getSqlSession();
		try {

			int count = sqlSession.selectOne("Lilei002.selectCount");
			System.out.println("count:" + count);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}

	}

	
	@Test
	public void testSelectMeeting() {

		SqlSession sqlSession = getSqlSession();
		try {

			MeetingDto meetingDto = new MeetingDto();			
			meetingDto.setTitle("今天开会22");
			
			List<MeetingDto> listResultDto = sqlSession.selectList("Lilei002.selectMeeting",meetingDto);
					
			for (MeetingDto itemResultDto : listResultDto) {
				System.out.print("id:" + itemResultDto.getId());
				System.out.print(" title:" + itemResultDto.getTitle());
				System.out.println(" content:" + itemResultDto.getContent());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}

	}
	@Test
	public void testSelectMeetingMap() {
		
		SqlSession sqlSession = getSqlSession();
		try {
			
			MeetingDto meetingDto = new MeetingDto();			
			meetingDto.setTitle("今天开会22");
			
			List<MeetingDto> listResultDto = sqlSession.selectList("Lilei002.selectMeetingMap",meetingDto);
			
			for (MeetingDto itemResultDto : listResultDto) {
				System.out.print("id:" + itemResultDto.getId());
				System.out.print(" title:" + itemResultDto.getTitle());
				System.out.println(" content:" + itemResultDto.getContent());
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		
	}

}
