package com.lannstark.style.align

import org.apache.poi.ss.usermodel.CellStyle

interface ExcelAlign {
    fun apply(cellStyle: CellStyle)
}
