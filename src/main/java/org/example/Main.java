package org.example;

import net.sf.jasperreports.engine.JRException;
import org.example.reports.*;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws JRException, FileNotFoundException {
        Reportable beanReport = new JRBeanExample();
        beanReport.start();
        Reportable mapReport = new JRMapExample();
        mapReport.start();
        Reportable rsReport = new JRResultSetExample();
        rsReport.start();
        Reportable dynamicReport = new DynamicReport();
        dynamicReport.start();
        Reportable crossReport = new CrosstabReport();
        crossReport.start();
    }
}
