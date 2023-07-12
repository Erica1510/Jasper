package org.example.dao;

import java.sql.ResultSet;

public interface IHolidayDao {
    ResultSet findAll();
    ResultSet findAllByMonths();
    ResultSet countAllHolidaysPerMonth();
}
