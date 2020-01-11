package com.mao;

import com.mao.config.IdBuilder;
import com.mao.mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class KeepYoungerManagerApplicationTests {

	private TestMapper testMapper;
	private IdBuilder idBuilder;
	@Autowired
	void setBookMapper(TestMapper testMapper){
		this.testMapper = testMapper;
	}
	@Autowired
	void setIdBuilder(IdBuilder idBuilder){
		this.idBuilder = idBuilder;
	}

	@Test
	void contextLoads() {
		System.out.println("hello world");
	}

	@Test
	void transId(){
		List<Integer> ids = testMapper.getIds();
		for (Integer id : ids) {
			long id2 = idBuilder.getInstance().nextId();
			testMapper.updateId(id,id2);
			System.out.println(id2);
		}
	}

}
