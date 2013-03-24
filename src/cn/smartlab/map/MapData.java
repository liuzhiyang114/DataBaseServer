package cn.smartlab.map;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.smartlab.dao.DBMSDao;
import cn.smartlab.json.JsonToArray;

public class MapData {

	/*
	 * �����ݿ�����ݿ��װ��map����, �Ա�DataBaseServer����
	 */

	List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

	// ����sql���
	// String sql =
	// "select parkno,pname,lat,lon,pamount,restamount,pinfo from parkinfo";

	String sql = "select * from city";

	@Test
	public void Test() throws SQLException, ClassNotFoundException, Exception,
			IOException {

		ResultSet rs = new DBMSDao().query(sql);

		// �����ѯ���������ѯ���ת����List<Map>��ʽ��
		ResultSetMetaData rsmd = rs.getMetaData();
		int num = rsmd.getColumnCount();

		while (rs.next()) {
			Map map = new LinkedHashMap();
			for (int i = 0; i < num; i++) {
				String columnName = rsmd.getColumnName(i + 1);
				map.put(columnName, rs.getString(columnName));
			}
			resultList.add(map);
		}

		// ȡ��ģ������List<Map>��ֵ
		for (Object obj : resultList) {

			System.out.println(obj.toString());

		}

	}

	@Test
	public void testJson() throws Exception {

		System.out.println("---------------------------------------------");
		ResultSet rs = new DBMSDao().query(sql);
		// ��װ��json
		StringBuilder json = new JsonToArray().getObjectToJson(rs);

		System.out.println(json);
	}

}
