package com.zhangguangzhao.demo.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GoodsController {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	@RequestMapping("list")
	public String goodsList(Model model,
			@RequestParam(defaultValue="1")int cpage
			) {
		
		//每页几条
		int pageSize = 10;
		
		//当前页
		int index = (cpage-1)*pageSize;
		
		//读取redis数据库中的数据
		BoundListOperations<String,Object> boundListOps = redisTemplate.boundListOps("goodsList");
		
		//总数据
		Long size = boundListOps.size();
		
		//总页数
		int pages = size/pageSize + size%pageSize>0?1:0;
		
		List<Object> range = boundListOps.range(index, pageSize-1);
		
		model.addAttribute("range", range);
		model.addAttribute("pages", pages);
		model.addAttribute("cpage", cpage);
		
		return "list";
	}
	
	
	@RequestMapping("zlist")
	public String zsetGoods(Model model,
			@RequestParam(defaultValue="1")int cpage
			) {
		
		//每页几条
		int pageSize = 10;
		
		//当前页
		int index = (cpage-1)*pageSize;
		
		//读取redis数据库中的数据
		BoundZSetOperations<String,Object> boundZSetOps = redisTemplate.boundZSetOps("zsetGoods");
		
		//总数据
		Long size = boundZSetOps.size();
		
		//总页数
		int pages = size/pageSize + size%pageSize>0?1:0;
		
		Set<Object> range = boundZSetOps.range(index, pageSize-1);
		
		model.addAttribute("range", range);
		model.addAttribute("pages", pages);
		model.addAttribute("cpage", cpage);
		
		return "zlist";
	}
}
