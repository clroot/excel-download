package com.lannstark.style

import com.lannstark.style.align.DefaultExcelAlign
import com.lannstark.style.align.ExcelAlign
import com.lannstark.style.border.DefaultExcelBorders
import com.lannstark.style.border.DefaultExcelBorders.Companion.newInstance
import com.lannstark.style.border.ExcelBorderStyle
import com.lannstark.style.color.DefaultExcelColor.Companion.rgb
import com.lannstark.style.color.ExcelColor
import org.apache.poi.ss.usermodel.CellStyle

/**
 * Example of using ExcelCellStyle as Enum
 */
enum class DefaultExcelCellStyle(
    private val backgroundColor: ExcelColor,
    /**
     * like CSS margin or padding rule,
     * List<DefaultExcelBorder> represents rgb TOP RIGHT BOTTOM LEFT
     </DefaultExcelBorder> */
    private val borders: DefaultExcelBorders,
    private val align: ExcelAlign,
) :
    ExcelCellStyle {
    GREY_HEADER(
        rgb(217, 217, 217),
        newInstance(ExcelBorderStyle.THIN),
        DefaultExcelAlign.CENTER_CENTER,
    ),
    BLUE_HEADER(
        rgb(223, 235, 246),
        newInstance(ExcelBorderStyle.THIN),
        DefaultExcelAlign.CENTER_CENTER,
    ),
    BODY(
        rgb(255, 255, 255),
        newInstance(ExcelBorderStyle.THIN),
        DefaultExcelAlign.RIGHT_CENTER,
    ),
    ;

    override fun apply(cellStyle: CellStyle) {
        backgroundColor.applyForeground(cellStyle)
        borders.apply(cellStyle)
        align.apply(cellStyle)
    }
}
