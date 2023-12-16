package com.atguigu.jdbc;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: Demo02Druid
 * Package: com.atguigu.jdbc
 * Description:
 *
 * @Author: Ang Li
 * @Create: 9/13/23 - 1:07 PM
 * @Version: v1.0
 */

public class Demo04Druid {
    public static void main(String[] args) throws SQLException, IOException {
        Properties properties = new Properties();
        InputStream is = Demo04Druid.class.getClassLoader().getResourceAsStream("jdbc.properties");
        properties.load(is);

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
        String url = "jdbc:mysql://localhost:3306/fruitdb?"
                + "useSSL=false"
                + "&requireSSL=false"
                + "&verifyServerCertificate=false"
                + "&enabledTLSProtocols=TLSv1.2,TLSv1.3"
                + "&rewriteBatchedStatements=true";
        dataSource.setUrl(url);
        dataSource.setUsername(properties.getProperty("jdbc.userName"));
        dataSource.setPassword(properties.getProperty("jdbc.pwd"));

        dataSource.setInitialSize(Integer.parseInt(properties.getProperty("jdbc.initSize")));


        for (int i = 0; i < 5; i++) {
            Connection conn1 = dataSource.getConnection();
            Connection conn2 = dataSource.getConnection();

            System.out.println(conn1);
            System.out.println(conn2);

            if (i % 3 == 0) {
                conn1.close();
                conn2.close();
            }
        }

    }

}
