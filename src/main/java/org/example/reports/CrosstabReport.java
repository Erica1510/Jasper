package org.example.reports;

import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import org.example.services.HolidayService;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import static org.example.constants.JasperConsts.*;
import static org.example.constants.ReportStyleConsts.*;

public class CrosstabReport implements Reportable {

        private final HolidayService service = new HolidayService();

        @Override
        public void start() {
            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(service.findAllByMonths());
            JRResultSetDataSource resultSetDataSourceChart = new JRResultSetDataSource(service.countAllHolidaysPerMonth());

            TextColumnBuilder<Integer> monthColumn = col.column(MONTH_FIELD, DATE_FIELD,type.integerType());

            try {
                report()
                        .title(cmp.horizontalList(cmp.text("Holiday").setStyle(TITLE_STYLE), cmp.image(IMAGE_PATH)))
                        .setTitleStyle(stl.style().setLeftPadding(20).setRightPadding(20).setTopPadding(20))
                        .summary(
                                ctab.crosstab()
                                        .headerCell(cmp.text("State/Messe").setStyle(BOLD_HEADER_CELL_BORDERED))
                                        .addRowGroup(ctab.rowGroup(COUNTRY_FIELD, String.class).setTotalHeaderStyle(TOTAL_BORDERED))
                                        .addColumnGroup(ctab.columnGroup(monthColumn).setTotalHeaderStyle(TOTAL_BORDERED_RIGHT))
                                        .addMeasure(ctab.measure(ID_FIELD, Integer.class, Calculation.COUNT))
                                        .setCellWidth(30)
                                        .setStyle(stl.style().setBottomPadding(40))
                                        .setCellStyle(CELLS_BORDERED)
                                        .setGrandTotalStyle(GRAND_TOTAL_BORDERED)
                                        .setGroupStyle(HEADER_BORDERED),

                                cht.barChart()
                                        .setTitle("Holidays per month:")
                                        .setCategory(col.column(MONTH_FIELD, String.class))
                                        .series(
                                                cht.serie(col.column("Holidays count", COUNT_FIELD, Integer.class))
                                                        .setSeries(col.column("Countries", COUNTRY_FIELD, String.class))
                                        )
                                        .setDataSource(resultSetDataSourceChart)
                        )
                        .setSummaryStyle(stl.style().setPadding(20))
                        .setDataSource(resultSetDataSource)
                        .show();
            } catch (DRException e) {
                throw new RuntimeException(e);
            }
        }

        public static void main(String[] args) {
            CrosstabReport report = new CrosstabReport();
            report.start();
        }
    }