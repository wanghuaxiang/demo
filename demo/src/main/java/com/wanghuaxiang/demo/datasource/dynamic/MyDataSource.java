package com.wanghuaxiang.demo.datasource.dynamic;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author: 王华翔
 * @version: 创建时间：2019年1月1日 下午12:57:35
 * @description:
 */
//@Component
//@Primary
public class MyDataSource implements DataSource {

	@Autowired
	@Qualifier("masterDataSource")
	private DataSource	masterDataSource;
	@Autowired
	@Qualifier("slaverDataSource")
	private DataSource	slaverDataSource;

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return masterDataSource.getLogWriter();
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		masterDataSource.setLogWriter(out);
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		masterDataSource.setLoginTimeout(seconds);
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return masterDataSource.getLoginTimeout();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return masterDataSource.getParentLogger();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return masterDataSource.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return masterDataSource.isWrapperFor(iface);
	}

	@Override
	public Connection getConnection() throws SQLException {
		return masterDataSource.getConnection();
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return masterDataSource.getConnection(username, password);
	}

	public DataSource getMasterDataSource() {
		return masterDataSource;
	}

	public void setMasterDataSource(DataSource masterDataSource) {
		this.masterDataSource = masterDataSource;
	}

	public DataSource getSlaverDataSource() {
		return slaverDataSource;
	}

	public void setSlaverDataSource(DataSource slaverDataSource) {
		this.slaverDataSource = slaverDataSource;
	}
}
