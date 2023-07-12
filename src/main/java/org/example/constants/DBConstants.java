package org.example.constants;

public final class DBConstants {
    public static final String SELECT_ALL_QUERY = "SELECT * FROM holidays";
    public static final String SELECT_ALL_WITH_MONTH_NUMBERS_QUERY = "SELECT id, country, name, EXTRACT(MONTH FROM date) AS date FROM holidays";
    public static final String SELECT_COUNT_HOLIDAYS_BY_MONTH_NAME = "SELECT country, EXTRACT(MONTH FROM date) AS direction , TO_CHAR(date, 'Mon') AS month, COUNT(name) AS count " +
            "FROM holidays " +
            "GROUP BY direction, month, country " +
            "ORDER BY direction";
    public static final String SELECT_COUNT_HOLIDAYS_BY_MONTH_NUMBER = "SELECT country, EXTRACT(MONTH FROM date) AS month, COUNT(name) AS count FROM holidays GROUP BY month, country ORDER BY month";
}
