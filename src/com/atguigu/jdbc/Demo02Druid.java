package com.atguigu.jdbc;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ClassName: Demo02Druid
 * Package: com.atguigu.jdbc
 * Description:
 *
 * @Author: Ang Li
 * @Create: 9/13/23 - 1:07 PM
 * @Version: v1.0
 */

// Druid 数据连接池
public class Demo02Druid {
    public static void main(String[] args) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("org.gjt.mm.mysql.Driver");
        String url = "jdbc:mysql://localhost:3306/fruitdb?"
                + "useSSL=false"
                + "&requireSSL=false"
                + "&verifyServerCertificate=false"
                + "&enabledTLSProtocols=TLSv1.2,TLSv1.3"
                + "&rewriteBatchedStatements=true";
        dataSource.setUrl(url);
        dataSource.setUsername("root");
        dataSource.setPassword("Luck1988!");

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
