// 找到那些未在承諾時間內交付的包裝。
public static void main(String[] args) {
		PreparedStatement pst = null;
		Statement stmt = null;
		try {
			  connection = DriverManager.getConnection(url, user, pwd);
			  stmt = connection.createStatement();

			// 查詢資料
			ResultSet rs = stmt.executeQuery(
					"SELECT tracking_number "
					+ "FROM shipment "
					+ "WHERE status = '遲到' or status = '遺失'"
			);
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int columnCount = rsMetaData.getColumnCount();
			System.out.println("tracking_number");	
			while (rs.next()) {
				  for (int i = 1; i <= columnCount; i++) {
					    System.out.print(rs.getString(i) + "\t");
				  }
				  System.out.println();
			}
	}
}
