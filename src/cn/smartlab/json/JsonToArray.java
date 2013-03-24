package cn.smartlab.json;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class JsonToArray {

	public JsonToArray() {

	}

	// ��װjson����
	public StringBuilder getObjectToJson(ResultSet rs) throws SQLException {
		StringBuilder json = new StringBuilder();
		ResultSetMetaData rsmd = rs.getMetaData();
		// json.append("type=server&action=getGPS&params={");
		json.append("{");
		while (rs.next()) {
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {

				String key = rsmd.getColumnName(i);// ��ȡ��
				String value = rs.getString(i);// ��ȡֵ

				// ��ʼ��װ
				json.append("\"" + key + "\":");

				if (i <= rsmd.getColumnCount() - 1) {

					json.append("\"" + value + "\"" + ",");

				} else {// ��֤������������ľ�ж���

					json.append("\"" + value + "\"");

				}

			}

			// �� &���ָ���ȡ������(����ÿһ��������&�ָ���)
			if (!rs.isLast()) {
				json.append("&");
			}
		}
		// ������װ
		json.append("}" + "\n");

		// System.out.println(json);
		return json;
	}

	// ����json����
	public void getArrayToJson() {

		/*
		 * ���������������Ҫ��ȶ�� ��Ҫ������ʲô�������ݸ�ʽ��
		 */

	}

}
