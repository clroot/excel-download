package com.lannstark.style.configurer

import com.lannstark.style.align.ExcelAlign
import com.lannstark.style.align.NoExcelAlign
import com.lannstark.style.border.ExcelBorders
import com.lannstark.style.border.NoExcelBorders
import com.lannstark.style.color.DefaultExcelColor.Companion.rgb
import com.lannstark.style.color.ExcelColor
import com.lannstark.style.color.NoExcelColor
import org.apache.poi.ss.usermodel.CellStyle

class ExcelCellStyleConfigurer {
    private var excelAlign: ExcelAlign = NoExcelAlign()
    private var foregroundColor: ExcelColor = NoExcelColor()
    private var excelBorders: ExcelBorders = NoExcelBorders()

    fun excelAlign(excelAlign: ExcelAlign): ExcelCellStyleConfigurer {
        this.excelAlign = excelAlign
        return this
    }

    fun foregroundColor(
        red: Int,
        blue: Int,
        green: Int,
    ): ExcelCellStyleConfigurer {
        this.foregroundColor = rgb(red, blue, green)
        return this
    }

    fun excelBorders(excelBorders: ExcelBorders): ExcelCellStyleConfigurer {
        this.excelBorders = excelBorders
        return this
    }

    fun configure(cellStyle: CellStyle) {
        excelAlign.apply(cellStyle)
        foregroundColor.applyForeground(cellStyle)
        excelBorders.apply(cellStyle)
    }
}
