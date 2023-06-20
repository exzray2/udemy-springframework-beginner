package com.exzray.spring6di;

import com.exzray.spring6di.controller.MyController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Spring6DiApplicationTests {

	private final ApplicationContext  context;

	private final MyController myController;

	Spring6DiApplicationTests(ApplicationContext context, MyController myController) {
		this.context = context;
		this.myController = myController;
	}

	@Test
	void testControllerDI(){
		myController.hello();
	}

	@Test
	void testGetControllerFromContext(){
		MyController myController = context.getBean(MyController.class);
		myController.hello();
	}

	@Test
	void contextLoads() {
	}

}
