package cn.smartlab.json;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class JsonToArray {

	public JsonToArray() {

	}

	// 封装json数据
	public StringBuilder getObjectToJson(ResultSet rs) throws SQLException {
		StringBuilder json = new StringBuilder();
		ResultSetMetaData rsmd = rs.getMetaData();
		// json.append("type=server&action=getGPS&params={");
		json.append("{");
		while (rs.next()) {
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {

				String key = rsmd.getColumnName(i);// 获取键
				String value = rs.getString(i);// 获取值

				// 开始封装
				json.append("\"" + key + "\":");

				if (i <= rsmd.getColumnCount() - 1) {

					json.append("\"" + value + "\"" + ",");

				} else {// 保证最后输出的数据木有逗号

					json.append("\"" + value + "\"");

				}

			}

			// 用 &来分隔获取的数据(即：每一组数据用&分隔开)
			if (!rs.isLast()) {
				json.append("&");
			}
		}
		// 结束封装
		json.append("}" + "\n");

		// System.out.println(json);
		return json;
	}

	// 解析json数据
	public void getArrayToJson() {

		/*
		 * 具体的数据类型需要商榷， 想要解析成什么样的数据格式。
		 */

	}

}
