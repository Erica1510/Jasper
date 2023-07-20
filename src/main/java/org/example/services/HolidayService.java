package org.example.services;

import org.example.dao.HolidayDao;
import org.example.dao.IHolidayDao;
import org.example.model.Holiday;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.constants.JasperConsts.*;

public class HolidayService {
    private final IHolidayDao dao = new HolidayDao();

    public List<Map<String, ?>> findAllToListMaps() throws SQLException {
        ResultSet resultSet = dao.findAll();
        List<Map<String, ?>> maps = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, Object> map = createHolidayMap(resultSet);
            maps.add(map);
        }
        return maps;
    }

    public List<Holiday> findAllToListBeans() throws SQLException {
        ResultSet resultSet = dao.findAll();
        List<Holiday> holidays = new ArrayList<>();
        while (resultSet.next()) {
            Holiday holiday = createHolidayBean(resultSet);
            holidays.add(holiday);
        }
        return holidays;
    }

    public ResultSet findAll() {
        return dao.findAll();
    }

    public ResultSet findAllByMonths() {
        return dao.findAllByMonths();
    }

    public ResultSet countAllHolidaysPerMonth() {
        return dao.countAllHolidaysPerMonth();
    }

    private Map<String, Object> createHolidayMap(ResultSet resultSet) throws SQLException {
        Map<String, Object> map = new HashMap<>();
        map.put(COUNTRY_FIELD, resultSet.getString(COUNTRY_FIELD));
        map.put(DATE_FIELD, resultSet.getDate(DATE_FIELD));
        map.put(NAME_FIELD, resultSet.getString(NAME_FIELD));
        return map;
    }

    private Holiday createHolidayBean(ResultSet resultSet) throws SQLException {
        Holiday holiday = new Holiday();
        holiday.setCountry(resultSet.getString(COUNTRY_FIELD));
        holiday.setDate(resultSet.getDate(DATE_FIELD));
        holiday.setName(resultSet.getString(NAME_FIELD));
        return holiday;
    }
}
