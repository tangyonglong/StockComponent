package com.yltang.tools.datasource;

import java.util.List;

import com.yltang.tools.datasource.core.DataSourceFactory;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataSource<PrUser> dsUser;
		try {
			dsUser = DataSourceFactory.buildDataSource(PrUser.class);
			dsUser.openDataSource(null);
			List<PrUser> users = dsUser.readData(1, 10);
			System.out.println(users);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
