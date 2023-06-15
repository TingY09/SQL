//按過去一年銷售的美元金額查找前 2 種產品
package TEST;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) {
		Connection connection = null;
		String url = "jdbc:mariadb://140.127.74.226:3306/M11075706";
		String user = "M11075706";
		String pwd = "M11075706";
		Statement stmt = null;

		try {
			connection = DriverManager.getConnection(url, user, pwd);
			stmt = connection.createStatement();

			// 查詢資料
			  ResultSet rs = stmt.executeQuery( "SELECT P.product_name, P.price " +
                      "FROM Product P " +
                      "JOIN Order_Details OD ON P._product_id = OD.product_id " +
                      "JOIN Customer_Order CO ON OD.c_order_id = CO.c_order_id " +
                      "WHERE CO.date >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR) " +
                      "GROUP BY P._product_id " +
                      "ORDER BY SUM(P.price) DESC " +
                      "LIMIT 2" );
			  ResultSetMetaData rsMetaData = rs.getMetaData();
			  int columnCount = rsMetaData.getColumnCount();
			  System.out.println("product_name\t\tprice");
			  while (rs.next()) { 
				  for(int i = 1; i <= columnCount; i++) { 
					  System.out.print(rs.getString(i) +"\t");
				  } 
				  System.out.println();
			  }
			 

			// 更新資料

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Create Table Exception :" + e.toString());
		}

		System.out.println("Successfully connected to database");

	}
}
