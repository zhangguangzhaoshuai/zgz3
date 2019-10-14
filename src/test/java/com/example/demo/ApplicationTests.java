package com.example.demo;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhangguangzhao.demo.bean.Goods;
import com.zhangguangzhao.utils.IOToFileUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	@Test
	public void testList() {
		
		//读取文件
		List<String> readFileByLinesList = IOToFileUtils.readFileByLinesList(System.getProperty("user.dir")+"/src/test/java/test.txt");
		
		BoundListOperations<String,Object> boundListOps = redisTemplate.boundListOps("goodsList");
		
		//遍历文件
		for (String string : readFileByLinesList) {
			
			//依照“==”进行切割
			String[] split = string.split("\\=\\=");
			
			//创建对象
			Goods goods = new Goods();
			
			goods.setGid(Integer.parseInt(split[0]));
			goods.setGname(split[1]);
			goods.setPrice(split[2]);
			goods.setBfb(Double.parseDouble(split[3]));
			
			
			//存入数据库
			Long push = boundListOps.leftPush(goods);
			System.out.println(push);
		}
	}

	
	@Test
	public void zsetList() {
		
		//读取文件
		List<String> readFileByLinesList = IOToFileUtils.readFileByLinesList(System.getProperty("user.dir")+"/src/test/java/test.txt");
		
		BoundZSetOperations<String,Object> boundZSetOps = redisTemplate.boundZSetOps("zsetGoods");
		
		//遍历文件
		for (String string : readFileByLinesList) {
			
			//依照“==”进行切割
			String[] split = string.split("\\=\\=");
			
			//创建对象
			Goods goods = new Goods();
			
			goods.setGid(Integer.parseInt(split[0]));
			goods.setGname(split[1]);
			goods.setPrice(split[2]);
			goods.setBfb(Double.parseDouble(split[3]));
			
			//存入数据库
			Boolean add = boundZSetOps.add(goods,Double.parseDouble(split[3]));
			System.out.println(add);
		}
	}
	
}
