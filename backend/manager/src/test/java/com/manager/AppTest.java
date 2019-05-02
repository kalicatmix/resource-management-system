package com.manager;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.properties.PropertiesConfiguration;
import org.apache.logging.log4j.core.config.properties.PropertiesConfigurationFactory;
import org.apache.logging.log4j.core.impl.Log4jContextFactory;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.junit.Test;
import org.slf4j.LoggerFactory;

public class AppTest {
  @Test
  public void testApp() throws ClassNotFoundException {
     Class.forName("com.mysql.cj.jdbc.Driver");
     try {
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8","root","mysqlinnod");
	    Statement statement=connection.createStatement();
	    ResultSet set=statement.executeQuery("select * from user");
	    for(int i=1;i<=set.getMetaData().getColumnCount();i++) {
	     System.out.println(set.getMetaData().getColumnName(i));
	     }
	    
     } catch (SQLException e) {
		e.printStackTrace();
	}
  }
}
