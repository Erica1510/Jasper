package org.example.reports;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import org.example.model.Holiday;
import org.example.services.HolidayService;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JRMapExample implements Reportable{
    private final HolidayService service = new HolidayService();

    private JRDataSource createDataSourceFromJRMap() throws Exception {
        List<Map<String,?>> maps = new ArrayList<>();
            List<Holiday> list = service.findAllToListBeans();
        for(Holiday holiday : list){
            Map<String,String> map = new HashMap<>();
            map.put("country",holiday.getCountry());
            map.put("date", String.valueOf(holiday.getDate()));
            map.put("name",holiday.getName());
            maps.add(map);
        }
        JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(maps);
        return dataSource;
    }
    @Override
    public void start() {
        try {
            List<Map<String,?>> maps = new ArrayList<>(service.findAllToListMaps());
            File file = new File("src/main/resources/reports/myMapReport.pdf");
            JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(maps);
            JasperReport report = JasperCompileManager.compileReport("src/main/resources/reports/holydays.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report,null,createDataSourceFromJRMap());
            JasperExportManager.exportReportToPdfFile(print,file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JRMapExample main = new JRMapExample();
        main.start();
    }
}
