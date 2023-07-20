package org.example.dao;

import org.example.constants.DBConstants;
import org.example.util.HolidayDBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HolidayDao implements IHolidayDao{
    private static final HolidayDBConnection DB_CONNECTION = new HolidayDBConnection();

    public HolidayDao() {
    }


    private Statement createStatement() throws SQLException {
        Connection connection = DB_CONNECTION.getConnection();
        return connection.createStatement();
    }


    @Override
    public ResultSet findAll() {
        try {
            Statement stmt = createStatement();
            return stmt.executeQuery(DBConstants.SELECT_ALL_QUERY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet findAllByMonths() {
        try {
            Statement stmt = createStatement();
            return stmt.executeQuery(DBConstants.SELECT_ALL_WITH_MONTH_NUMBERS_QUERY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet countAllHolidaysPerMonth() {
        try {
            Statement stmt = createStatement();
            return stmt.executeQuery(DBConstants.SELECT_COUNT_HOLIDAYS_BY_MONTH_NAME);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
