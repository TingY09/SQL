//  假設USPS運送的帶有跟蹤號123456包裹據報導已在事故中被摧毀。查找客戶的聯繫資訊。
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
            "SELECT customer_name, contact_info "
			+ "FROM customer, customer_order "
			+ "WHERE tracking_number = 'TN0001' and "
			+ "customer.customer_id = customer_order.customer_id"
		);
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnCount = rsMetaData.getColumnCount();
		System.out.println("customer_name\t\tcontact_info");	
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
