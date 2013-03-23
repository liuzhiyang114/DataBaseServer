package cn.smartlab.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBMSDao {

	String driver = null;
	String url = null;
	String user = null;
	String password = null;

	// 申明连接数据库对象
	Connection conn = null;

	// 得到执行sql语句的对象
	Statement state = null;

	// 申明数据集对象
	ResultSet rs = null;

	// 初始化连接
	public DBMSDao() throws ClassNotFoundException, SQLException, Exception,
			IOException {

		Prop();

		// 加载驱动
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, password);

		if (!conn.isClosed()) {
			System.out.println("连接数据库成功！");
		}

		state = conn.createStatement();
	}

	// 得到数据库的配置信息
	private void Prop() throws FileNotFoundException, IOException {
		FileInputStream in = new FileInputStream(
				"C:/Workspaces/MyEclipse 8.5/DataBaseServer1/bin/db.properties");
		Properties dbconfig = new Properties();
		dbconfig.load(in);
		driver = dbconfig.getProperty("driver");
		url = dbconfig.getProperty("url");
		user = dbconfig.getProperty("user");
		password = dbconfig.getProperty("password");
	}

	// 查询数据库数据
	public ResultSet query(String sql) throws SQLException {

		rs = state.executeQuery(sql);// 将得到的数据存在内存中
		return rs;

	}

	// 更新数据库信息
	public int update(String sql) throws SQLException {

		int row = state.executeUpdate(sql);// 得到影响行数
		return row;

	}

	// 关闭数据库
	public void closeDB() throws SQLException {

		// 注意这里要释放掉所有资源
		if (!conn.isClosed()) {

			state.close();
			rs.close();
			conn.close();

		}
	}

	// 申请回收内存
	@Override
	protected void finalize() throws Throwable {

		closeDB();

	}

}
