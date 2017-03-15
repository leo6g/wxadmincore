package com.lfc.wxadmincore.common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonDAO {

	public static final String driverName = ConfigHelper.prop.getProperty("driverClass"),
			connectionURL = ConfigHelper.prop.getProperty("jdbcUrl"),
			dbUser = ConfigHelper.prop.getProperty("username"), dbPwd = ConfigHelper.prop.getProperty("pwd");

	public static List<Map<String,Object>> getResultSetMap(String sql) {
		List<Map<String,Object>> rtVal = new ArrayList<Map<String,Object>>();
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCnt = rsmd.getColumnCount();
			while (rs.next()) {
				Map<String,Object> map = new HashMap<String,Object>();
				for (int i = 1; i <= columnCnt; i++) {
					String columnName = rsmd.getColumnName(i);
					Object columnVal = rs.getString(columnName);
					if (null != columnName && !columnName.trim().equals("")) {
						map.put(columnName, columnVal);
					}
				}
				rtVal.add(map);
			}

			rs.close();
			stmt.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtVal;
	}

	public static Connection getConnection() {
		Connection rtVal = null;

		try {
			Class.forName(driverName);
			rtVal = DriverManager.getConnection(connectionURL, dbUser, dbPwd);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return rtVal;
	}

	public static Integer executeUpdate(String sql) {
		Integer effectiveLines = 0;
		Connection con = null;
		try {
			con = getConnection();
			Statement st = con.createStatement();
			effectiveLines = st.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {}
			}
		}
		return effectiveLines;
	}
	
	public static Integer executeUpdateBatch(String[] sql) {
		Integer effectiveLines = 0;
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			Statement st = con.createStatement();
			for(String strSql:sql){
				effectiveLines += st.executeUpdate(strSql);
			}
			con.setAutoCommit(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {}
			}
		}
		return effectiveLines;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
