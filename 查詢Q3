// 在高雄的每家商店找到缺貨的產品。
public static void main(String[] args) {
		Connection connection = null;
		String url = "jdbc:mariadb://140.127.74.226:3306/M11075706";
		String user = "M11075706";
		String pwd = "M11075706";
		PreparedStatement pst = null;
		Statement stmt = null;

		try {
			connection = DriverManager.getConnection(url, user, pwd);
			stmt = connection.createStatement();

			// 查詢資料
			ResultSet rs = stmt.executeQuery(
					"SELECT S.store_name, P.product_name "
					+ "FROM Inventory I "
					+ "JOIN Store S ON I.store_id = S.store_id "
					+ "JOIN Product P ON I.product_id = P._product_id "
					+ "WHERE S.city = '高雄' "
					+ "AND I.inventory_status = '不足'"
			);
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int columnCount = rsMetaData.getColumnCount();
			 System.out.println("store_id\t\tproduct_id\t\tcustomer_name");	
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
