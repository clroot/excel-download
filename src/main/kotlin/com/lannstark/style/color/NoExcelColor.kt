package com.lannstark.style.color

import org.apache.poi.ss.usermodel.CellStyle

class NoExcelColor : ExcelColor {
    override fun applyForeground(cellStyle: CellStyle) {
        // Do nothing
    }
}
