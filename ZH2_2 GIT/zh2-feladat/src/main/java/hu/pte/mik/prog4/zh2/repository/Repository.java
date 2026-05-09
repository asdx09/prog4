package hu.pte.mik.prog4.zh2.repository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class Repository {

    private static DataSource dataSource;

    private static final String JNDI_DATASOURCE_NAME = "jdbc/ZH2MariaDB";
    private static final String ROOT_CONTEXT = "java:comp/env";

    protected Connection getConnection() throws NamingException, SQLException {
        return getDataSource().getConnection();
    }

    private static DataSource getDataSource() throws NamingException {
        if(dataSource == null)
        {
            Context initCtx = new InitialContext();
            Context ctx = (Context) initCtx.lookup(ROOT_CONTEXT);
            dataSource = (DataSource) ctx.lookup(JNDI_DATASOURCE_NAME);
        }
        return dataSource;
    }

}
