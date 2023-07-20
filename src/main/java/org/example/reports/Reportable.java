package org.example.reports;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.*;
import java.util.HashMap;

import static org.example.constants.JasperConsts.OUTPUT_FILE_PATH;
import static org.example.constants.JasperConsts.REPORT_TEMPLATE_PATH;

public interface Reportable {
    void start() throws FileNotFoundException, JRException;

    static void showReportFromDataSource(JRDataSource dataSource) {
        try {
            // Load the report template
            JasperReport jasperReport = loadReportTemplate(REPORT_TEMPLATE_PATH);

            // Fill the report with data
            JasperPrint jasperPrint = fillReportWithData(jasperReport, dataSource);

            // Export the report to PDF and save it to a file
            exportReportToPDF(jasperPrint, OUTPUT_FILE_PATH + dataSource.getClass().getSimpleName() + ".pdf");

            // View the report in JasperViewer
            JasperViewer.viewReport(jasperPrint, false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    static JasperReport loadReportTemplate(String reportTemplatePath) throws JRException, FileNotFoundException {
        FileInputStream fis = new FileInputStream(reportTemplatePath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
        return (JasperReport) JRLoader.loadObject(bufferedInputStream);
    }

    static JasperPrint fillReportWithData(JasperReport jasperReport, JRDataSource dataSource) throws JRException {
        return JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
    }

    static void exportReportToPDF(JasperPrint jasperPrint, String outputPath) throws JRException {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(outputPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    }
}
