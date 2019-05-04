package com.manager;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextTest extends ApplicationContextRunner{
  @Test
  public void testApplicationContext() {
	  System.out.println(this.context.getBeanDefinitionCount());
  }
}
