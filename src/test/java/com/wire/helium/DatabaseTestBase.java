package com.wire.helium;

import org.apache.log4j.BasicConfigurator;
import org.flywaydb.core.Flyway;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Driver;
import java.sql.DriverManager;

abstract public class DatabaseTestBase {
    protected static Flyway flyway;
    protected static Jdbi jdbi;

    @BeforeAll
    public static void initiate() throws Exception {
        BasicConfigurator.configure();

        String databaseUrl = System.getenv("POSTGRES_URL");
        databaseUrl = "jdbc:postgresql://" + (databaseUrl != null ? databaseUrl : "localhost/helium");
        String user = System.getenv("POSTGRES_USER");
        String password = System.getenv("POSTGRES_PASSWORD");

        Class driverClass = Class.forName("org.postgresql.Driver");
        final Driver driver = (Driver) driverClass.getDeclaredConstructor().newInstance();
        DriverManager.registerDriver(driver);

        jdbi = (password != null ? Jdbi.create(databaseUrl, user, password) : Jdbi.create(databaseUrl))
                .installPlugin(new SqlObjectPlugin());

        flyway = Flyway
                .configure()
                .dataSource(databaseUrl, user, password)
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();
    }

    @AfterAll
    public static void classCleanup() {
        flyway.clean();
    }
}
