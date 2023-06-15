//查找過去一年中購買最多的客戶（按價格）。
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
					ResultSet rs = stmt.executeQuery(
							"SELECT C.customer_name, SUM(P.price * OD.quantity) AS total_spent "
							+ "FROM Customer C "
							+ "JOIN Customer_Order CO ON C.customer_id = CO.customer_id "
							+ "JOIN Order_Details OD ON CO.C_order_id = OD.c_order_id "
							+ "JOIN Product P ON OD.product_id = P._product_id "
							+ "WHERE CO.date >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR) "
							+ "GROUP BY C.customer_name "
							+ "ORDER BY total_spent DESC "
							+ "LIMIT 1"
					);
					ResultSetMetaData rsMetaData = rs.getMetaData();
					int columnCount = rsMetaData.getColumnCount();
					System.out.println("customer_name\t\total_buy");	
			    while (rs.next()) {
				  		for (int i = 1; i <= columnCount; i++) {
					    		System.out.print(rs.getString(i) + "\t");
				  		}
				  		System.out.println();
			    }
	  } catch (SQLException e) {
			    e.printStackTrace();
			    System.out.println("Create Table Exception :" + e.toString());
		}
	}
}
