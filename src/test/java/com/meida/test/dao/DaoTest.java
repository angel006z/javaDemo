package com.meida.test.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meida.backend.basic.dao.inter.IDeptDao;
import com.meida.backend.basic.domain.po.Dept;


//加载spring配置文件 https://blog.csdn.net/fanbaodan/article/details/100121551
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc.xml", "classpath:spring-mybatis.xml", "classpath:mybatis-config.xml",})
public class DaoTest {
	
	@Autowired
    private IDeptDao dao;

    @Test
    public void testDept() {
    	List<Dept> list =dao.getListByAll();
    	for (Dept dept : list) {
			System.out.println("DeptId:"+dept.getDeptId());
			System.out.println("DeptName："+dept.getDeptName());
		}
    }
    
}
