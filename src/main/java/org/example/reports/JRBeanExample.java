package org.example.reports;

import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.*;
import org.example.model.Holiday;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.example.services.HolidayService;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class JRBeanExample implements Reportable{
    private final HolidayService service = new HolidayService();
    private DRDataSource createDataSource() throws Exception{
        DRDataSource dataSource = new DRDataSource("country","date","name");
        List<Holiday> list = service.findAllToListBeans();
        for(Holiday holiday: list){
            String country = holiday.getCountry();
            String date = String.valueOf(holiday.getDate());
            String name = holiday.getName();
            dataSource.add(country,date,name);
        }
        return dataSource;
    }
    public void createReport() {
        try {
            List<Holiday> holidays = service.findAllToListBeans();
            File file = new File("src/main/resources/reports/myBeanReport.pdf");

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(holidays);
            JasperReport report = JasperCompileManager.compileReport("src/main/resources/reports/holydays.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report,null,createDataSource());
            JasperExportManager.exportReportToPdfFile(print,file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        JRBeanExample beanExample = new JRBeanExample();
        beanExample.createReport();
    }

    @Override
    public void start() throws FileNotFoundException, JRException {

    }
}
