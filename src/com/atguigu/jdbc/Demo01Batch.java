package com.atguigu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * ClassName: Demo01
 * Package: com.atguigu.jdbc
 * Description:  insert data into database
 *
 * @Author: Ang Li
 * @Create: 8/18/23 - 9:01 AM
 * @Version: v1.0
 */
public class Demo01Batch {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. add jar file(mysql-connector-java.jar) into lib
        // 2. load driver
        Class.forName("org.gjt.mm.mysql.Driver");
        // 3. get connected object
        // 3-1. url
        String url = "jdbc:mysql://localhost:3306/fruitdb?"
                + "useSSL=false"
                + "&requireSSL=false"
                + "&verifyServerCertificate=false"
                + "&enabledTLSProtocols=TLSv1.2,TLSv1.3"
                + "&rewriteBatchedStatements=true";
        String user = "root";
        String pwd = "Luck1988!";
        Connection conn = DriverManager.getConnection(url, user, pwd);
        String sql = "insert into t_fruit values(0, ?, ? ,?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        for (int i = 0; i < 10; i++) {
            ps.setString(1, "apple" + i);
            ps.setInt(2, 15);
            ps.setInt(3, 100);
            ps.setString(4, "apple is delicious");
            ps.addBatch();

            if (i % 100 == 0) { // execute batches of 100 sql instructions per batch
                int[] count = ps.executeBatch();
                ps.clearBatch();
                System.out.println(Arrays.toString(count));
            }
        }


        ps.close();
        conn.close();
    }

}
