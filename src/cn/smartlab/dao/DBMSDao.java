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

	// �����������ݿ����
	Connection conn = null;

	// �õ�ִ��sql���Ķ���
	Statement state = null;

	// �������ݼ�����
	ResultSet rs = null;

	// ��ʼ������
	public DBMSDao() throws ClassNotFoundException, SQLException, Exception,
			IOException {

		Prop();

		// ��������
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, password);

		if (!conn.isClosed()) {
			System.out.println("�������ݿ�ɹ���");
		}

		state = conn.createStatement();
	}

	// �õ����ݿ��������Ϣ
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

	// ��ѯ���ݿ�����
	public ResultSet query(String sql) throws SQLException {

		rs = state.executeQuery(sql);// ���õ������ݴ����ڴ���
		return rs;

	}

	// �������ݿ���Ϣ
	public int update(String sql) throws SQLException {

		int row = state.executeUpdate(sql);// �õ�Ӱ������
		return row;

	}

	// �ر����ݿ�
	public void closeDB() throws SQLException {

		// ע������Ҫ�ͷŵ�������Դ
		if (!conn.isClosed()) {

			state.close();
			rs.close();
			conn.close();

		}
	}

	// ��������ڴ�
	@Override
	protected void finalize() throws Throwable {

		closeDB();

	}

}
