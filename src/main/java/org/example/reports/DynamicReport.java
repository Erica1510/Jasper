package org.example.reports;

import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import org.example.constants.JasperConsts;
import org.example.services.HolidayService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

public class DynamicReport implements Reportable {
    private static final String[] COLUMN_NAMES = {"Country", "Name", "Date"};
    private static final String[] FIELD_NAMES = {"country", "name", "date"};
    private final HolidayService service = new HolidayService();

    @Override
    public void start() {
        try {
            report()
                    .columns(createColumns())
                    .title(createTitle())
                    .pageFooter(cmp.pageXofY())
                    .setDataSource(createDataSource())
                    .show();
        } catch (DRException e) {
            throw new RuntimeException(e);
        }
    }

    private DRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource(FIELD_NAMES);
        ResultSet resultSet = service.findAll();

        try {
            while (resultSet.next()) {
                String country = resultSet.getString("country");
                String name = resultSet.getString("name");
                Date date = resultSet.getDate("date");
                dataSource.add(country, name, date);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dataSource;
    }


    private TextColumnBuilder<String>[] createColumns() {
        StyleBuilder boldBorderedStyle = stl.style().bold().setBorder(stl.pen1Point()).setPadding(5);

        TextColumnBuilder<String> column1 = Columns.column(COLUMN_NAMES[0], FIELD_NAMES[0], type.stringType());
        column1.setStyle(boldBorderedStyle);

        TextColumnBuilder<String> column2 = Columns.column(COLUMN_NAMES[1], FIELD_NAMES[1], type.stringType());
        column2.setStyle(boldBorderedStyle);

        TextColumnBuilder<Date> column3 = Columns.column(COLUMN_NAMES[2], FIELD_NAMES[2], type.dateType());
        column3.setStyle(boldBorderedStyle);

        return new TextColumnBuilder[]{column1, column2, column3};
    }

    private ComponentBuilder<?, ?> createTitle() {
        return cmp.horizontalList(
                cmp.text("Holiday").setStyle(stl.style().bold().setFontSize(18)),
                cmp.image(JasperConsts.IMAGE_PATH).setFixedDimension(120, 60)
        ).setStyle(stl.style().setPadding(10));
    }

    public static void main(String[] args) {
        DynamicReport dynamicReport = new DynamicReport();
        dynamicReport.start();
    }
}
