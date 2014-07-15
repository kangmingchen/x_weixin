package com.x.test;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.x.service.SysConfigService;

/**
 * <p>
 * Title:JTest1
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @Author Chenkangming @Date 2013-11-21
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring*.xml" })
public class SysConfigTest {
	
	public volatile boolean test=true;
	
	@Autowired
	SysConfigService service;


	@Test
	public void test() {
		Map<String,String> map=service.getSysConfig();
		for(Map.Entry<String, String> entry:map.entrySet()){
			System.out.println(entry.getKey()+" - "+entry.getValue());
		}
	}
}
