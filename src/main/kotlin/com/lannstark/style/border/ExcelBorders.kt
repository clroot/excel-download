package com.lannstark.style.border

import org.apache.poi.ss.usermodel.CellStyle

interface ExcelBorders {
    fun apply(cellStyle: CellStyle)
}
