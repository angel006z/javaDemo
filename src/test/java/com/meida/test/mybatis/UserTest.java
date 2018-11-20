package com.meida.test.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import com.meida.mybatis.dto.UserDto;

public class UserTest {
	private static Logger logger = Logger.getLogger(UserTest.class);
   private static SqlSessionFactory sessionFactory;
	static {
		//PropertyConfigurator.configure(UserTest.class.getClassLoader().getResource("log4j.properties"));
		
		String resource = "mybatis.config.xml";
//		InputStream inputStream = null;
//		try {
//			inputStream = Resources.getResourceAsStream(resource);
//			System.out.println(inputStream);
//			
//		} catch (IOException e) {
//			logger.debug(e.getMessage());
//			e.printStackTrace();
//		}
//
//		sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
			System.out.println(reader);
			
		} catch (IOException e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
		}
		sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		
	}

	private static SqlSession getSqlSession() {
		
		SqlSession sqlSession = sessionFactory.openSession();
		return sqlSession;
	}

	@Test
	public void testInsert() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserDto userDto = new UserDto();
			// userDto.setUserId(1);
			userDto.setUserName("test");
			userDto.setPassword("123456");
			sqlSession.insert("Lilei.insertUser", userDto);
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
			sqlSession.delete("Lilei.deleteUser", 3);
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
			UserDto userDto = new UserDto();
			userDto.setUserId(4);
			userDto.setUserName("test4");
			userDto.setPassword("password");
			sqlSession.update("Lilei.updateUser", userDto);
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

			int count = sqlSession.selectOne("Lilei.selectCount");
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
	public void testSelectUser() {

		SqlSession sqlSession = getSqlSession();
		try {

			UserDto userDto = new UserDto();
			userDto.setUserName("test4");

			List<UserDto> listResultDto = sqlSession.selectList("Lilei.selectUser", userDto);

			for (UserDto userResultDto : listResultDto) {
				System.out.print("userid:" + userResultDto.getUserId());
				System.out.print(" username:" + userResultDto.getUserName());
				System.out.println(" password:" + userResultDto.getPassword());
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
	public void testSelectUserMap() {

		SqlSession sqlSession = getSqlSession();
		try {

			UserDto userDto = new UserDto();
			userDto.setUserName("test5");

			List<UserDto> listResultDto = sqlSession.selectList("Lilei.selectUserMap", userDto);

			for (UserDto userResultDto : listResultDto) {
				System.out.print("userid:" + userResultDto.getUserId());
				System.out.print(" username:" + userResultDto.getUserName());
				System.out.println(" password:" + userResultDto.getPassword());
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
