package com.mycompany.testng;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Configuration;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 
 * @author yu.liuyly
 * @
 *
 */
@Test(groups={"classGroup"})
public class StringUtilsTest {
	
	/**
	 * 
	 */
	private int number;
	
	/**
	 * 
	 * @param number
	 */
	
	public StringUtilsTest(){}
	
	public StringUtilsTest(int number){
		this.number = number;
	}
	
	@Test(groups={"string"}, dependsOnMethods={"trim"}, dependsOnGroups={"num"})
	public void isEmpty(){
		assert StringUtils.isEmpty(null);
		assert StringUtils.isEmpty("");
		System.out.println("test1");
	}
	
	@Test(groups={"num"})
	public void trim(){
		assert "foo".equals(StringUtils.trim("  foo  "));
		System.out.println("test2");
	}
	
	@BeforeClass
	public void beforeClass(){
		System.out.println("BeforeClass");
	}
	
	@AfterClass
	public void afterClass(){
		System.out.println("@AfterClass");
	}
	
	@BeforeMethod
	public void beforeMethod(){
		System.out.println("BeforeMethod");
	}
	
	@AfterMethod
	public void afterMethod(){
		System.out.println("@AfterMethod");
	}
	@Deprecated
	@Test(expectedExceptions={NumberFormatException.class})
	public void numUtil(){
		NumberUtils.createDouble("12.12.12");
		assert false;
	}
	
	@Test
	@Parameters({"param1"})
	public void print(@Optional("Mine") String str1){
			System.out.println(str1);
	}
	
	@DataProvider(name="data")
	public static Object[][] createData(Method mtd){
		System.out.println(mtd.getName());
		return new Object[][]{
				{"Param1", new Integer(1)},
				{"Param2", new Integer(2)},
				{"Param3", new Integer(3)}
		};
	}
	
	@Test(dataProvider="data", dataProviderClass=com.mycompany.testng.StringUtilsTest.class)
	public void dataTest(String str, Integer in){
		System.out.println(str);
		System.out.println(in.toString());
	}
	
	@Test(groups={"factory"})
	public void factoryTest(){
		for(int i=0; i<number; i++){
			System.out.println("gg");
		}
	}
	
	@Test(invocationCount=5, threadPoolSize=3)
	public void multiTreadTest(){
		System.out.println(Thread.currentThread().getId());
	}
}
