package org.example.constants;

import net.sf.dynamicreports.report.builder.style.BorderBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;

import java.awt.*;

import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

public final class ReportStyleConsts {
    private ReportStyleConsts() {}

    public static final BorderBuilder BORDER_AROUND = stl.border().setBottomPen(stl.pen1Point())
            .setLeftPen(stl.pen1Point())
            .setRightPen(stl.pen1Point())
            .setTopPen(stl.pen1Point());

    public static final StyleBuilder BOLD_HEADER_CELL_BORDERED = stl.style().bold().setBackgroundColor(new Color(166,201,224)).setBorder(BORDER_AROUND).setFontSize(10);
    public static final StyleBuilder BOLD_BORDERED = stl.style().bold().setBorder(BORDER_AROUND);
    public static final StyleBuilder HEADER_STYLE = stl.style().setBackgroundColor(new Color(188,213,227));
    public static final StyleBuilder HEADER_BORDERED = stl.style().setBackgroundColor(new Color(188,213,227)).setBorder(BORDER_AROUND).bold();
    public static final StyleBuilder CELLS_BORDERED = stl.style().setBackgroundColor(new Color(206,228,242)).setBorder(BORDER_AROUND);
    public static final StyleBuilder GRAND_TOTAL_BORDERED = stl.style().setBackgroundColor(new Color(255,213,227)).setBorder(BORDER_AROUND);
    public static final StyleBuilder TOTAL_BORDERED = stl.style().setBackgroundColor(new Color(255,196,206)).setBorder(BORDER_AROUND).bold();
    public static final StyleBuilder TOTAL_BORDERED_RIGHT = stl.style().setBackgroundColor(new Color(255,196,206)).setBorder(BORDER_AROUND).bold().setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT);
    public static final StyleBuilder TITLE_STYLE = stl.style().bold().setFontSize(19);
    public static final StyleBuilder DETAIL_BORDERED = stl.style().setLeftPadding(2).setBorder(BORDER_AROUND);
}
