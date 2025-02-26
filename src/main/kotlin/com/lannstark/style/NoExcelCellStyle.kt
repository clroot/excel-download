package com.lannstark.style

import org.apache.poi.ss.usermodel.CellStyle

class NoExcelCellStyle : ExcelCellStyle {
    override fun apply(cellStyle: CellStyle) {
        // Do nothing
    }
}
