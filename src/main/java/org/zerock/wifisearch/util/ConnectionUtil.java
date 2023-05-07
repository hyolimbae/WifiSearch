package org.zerock.wifisearch.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public enum ConnectionUtil {
    INSTANCE;

    private HikariDataSource wifiDS;
    private HikariDataSource historyDS;

    private HikariDataSource bookmarkDS;

    ConnectionUtil()
    {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/webdb");
        config.setUsername("webuser");
        config.setPassword("webuser");
        config.addDataSourceProperty("cachePrepStmts","true");
        config.addDataSourceProperty("prepStmtCacheSize","250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit","2048");

        wifiDS = new HikariDataSource(config);

        HikariConfig config2 = new HikariConfig();
        config2.setDriverClassName("org.mariadb.jdbc.Driver");
        config2.setJdbcUrl("jdbc:mariadb://localhost:3306/webdb2");
        config2.setUsername("webuser");
        config2.setPassword("webuser");
        config2.addDataSourceProperty("cachePrepStmts","true");
        config2.addDataSourceProperty("prepStmtCacheSize","250");
        config2.addDataSourceProperty("prepStmtCacheSqlLimit","2048");

        historyDS = new HikariDataSource(config2);

        HikariConfig config3 = new HikariConfig();
        config3.setDriverClassName("org.mariadb.jdbc.Driver");
        config3.setJdbcUrl("jdbc:mariadb://localhost:3306/webdb3");
        config3.setUsername("webuser");
        config3.setPassword("webuser");
        config3.addDataSourceProperty("cachePrepStmts","true");
        config3.addDataSourceProperty("prepStmtCacheSize","250");
        config3.addDataSourceProperty("prepStmtCacheSqlLimit","2048");

        bookmarkDS = new HikariDataSource(config3);
    }

    public Connection getConnection() throws Exception {
        return wifiDS.getConnection();
    }

    public Connection getHistoryConnection() throws Exception {
        return historyDS.getConnection();
    }

    public Connection getBookmarkConnection() throws Exception {
        return bookmarkDS.getConnection();
    }
}
