package com.meida.test.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * junit测试
   *   注意：编写测试类的原则： 
 * ①测试方法上必须使用@Test进行修饰 
 * ②测试方法必须使用public void 进行修饰，不能带任何的参数 
 * ③新建一个源代码目录来存放我们的测试代码，即将测试代码和项目业务代码分开 
 * ④测试类所在的包名应该和被测试类所在的包名保持一致 
 * ⑤测试单元中的每个方法必须可以独立测试，测试方法间不能有任何的依赖 
 * ⑥测试类使用Test作为类名的后缀（不是必须） 
 * ⑦测试方法使用test作为方法名的前缀（不是必须） 
 * @author BING
 */
public class JunitTest {

	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("@BeforeClass");
	}
	
	@Before
	public void before() {
		System.out.println("@Before");
	}

	@Ignore
	public void ignore() {
		System.out.println("@Ignore");
	}

	@After
	public void after() {
		System.out.println("@After");
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("@AfterClass");
	}

	
	@Test
	public void testAdd() {
		
		int a = 12;
		int b = 13;
		System.out.println("结果："+(a+b));
		Assert.assertEquals(a + b, 25);
		//Assert.assertEquals(a + b, 26);
	}
}
