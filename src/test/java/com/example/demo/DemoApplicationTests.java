package com.example.demo;

import cn.hutool.json.JSON;
import com.example.demo.config.AuthConfigProperties;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String, Serializable> redisCacheTemplate;

	private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	AuthConfigProperties authConfigProperties;

	@Test
	void contextLoads() {

		String forObject = restTemplate.getForObject("http://localhost:8080/aa", String.class);
		System.out.println(forObject);

	}

	@Test
	void testRedis() {
		stringRedisTemplate.opsForValue().set("key2", "value2");
		String key = stringRedisTemplate.opsForValue().get("key");
	}

	@Test
	void testRedis2() {
		redisCacheTemplate.opsForValue().set("user" ,new User(1l ,"jim2"));
		User user = (User) redisCacheTemplate.opsForValue().get("user");
		System.out.println(user.toString());

	}

	@Test
	void test3() {

//		redisTemplate.opsForHash().put("testUser","123","12");
//		System.out.println(redisTemplate.opsForHash().get("testUser", "qwe"));
//		stringRedisTemplate.opsForHash().put("test" ,"qwe" ,"不就是");
		System.out.println(stringRedisTemplate.opsForHash().get("test", "123"));

		stringRedisTemplate.opsForList().leftPush("users", "lele");
		stringRedisTemplate.opsForList().leftPushAll("users", "lili1", "xm2");

	}



}
