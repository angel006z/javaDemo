package com.meida.test.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meida.backend.basic.dao.inter.IDeptDao;
import com.meida.backend.basic.po.Dept;

//加载spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
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
