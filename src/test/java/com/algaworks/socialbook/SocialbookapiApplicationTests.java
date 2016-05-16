package com.algaworks.socialbook;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.algaworks.socialbooks.SocialbooksapiApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SocialbooksapiApplication.class)
@WebAppConfiguration
public class SocialbookapiApplicationTests {

	@Test
	public void contextLoads() {
	}

}
