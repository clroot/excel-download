package com.lannstark.style

import com.lannstark.style.configurer.ExcelCellStyleConfigurer
import org.apache.poi.ss.usermodel.CellStyle

abstract class CustomExcelCellStyle : ExcelCellStyle {
    private val configurer = ExcelCellStyleConfigurer()

    init {
        configure(configurer)
    }

    abstract fun configure(configurer: ExcelCellStyleConfigurer?)

    override fun apply(cellStyle: CellStyle) {
        configurer.configure(cellStyle)
    }
}
