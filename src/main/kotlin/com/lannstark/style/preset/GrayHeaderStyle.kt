package com.lannstark.style.preset

import com.lannstark.style.CustomExcelCellStyle
import com.lannstark.style.border.DefaultExcelBorders
import com.lannstark.style.border.ExcelBorderStyle
import com.lannstark.style.configurer.ExcelCellStyleConfigurer

class GrayHeaderStyle : CustomExcelCellStyle() {
    override fun configure(configurer: ExcelCellStyleConfigurer) {
        configurer.apply {
            foregroundColor(222, 226, 230)
            excelBorders(DefaultExcelBorders.newInstance(ExcelBorderStyle.THIN))
        }
    }
}