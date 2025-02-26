package com.lannstark.style.color

import org.apache.poi.ss.usermodel.CellStyle

interface ExcelColor {
    fun applyForeground(cellStyle: CellStyle)
}
