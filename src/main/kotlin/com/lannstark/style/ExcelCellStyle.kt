package com.lannstark.style

import org.apache.poi.ss.usermodel.CellStyle

interface ExcelCellStyle {
    fun apply(cellStyle: CellStyle)
}
