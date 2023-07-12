package org.example.reports;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import org.example.services.HolidayService;

public class JRResultSetExample implements Reportable{
    private final HolidayService service = new HolidayService();
    @Override
    public void start(){
        JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(service.findAll());

        Reportable.showReportFromDataSource(resultSetDataSource);
    }
    public static void main(String[] args) {
        JRResultSetExample main = new JRResultSetExample();
        main.start();
    }
}
