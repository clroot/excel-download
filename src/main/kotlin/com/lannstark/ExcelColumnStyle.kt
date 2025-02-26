package com.lannstark

import com.lannstark.style.ExcelCellStyle
import kotlin.reflect.KClass

annotation class ExcelColumnStyle(
    /**
     * Enum implements [com.lannstark.style.ExcelCellStyle]
     * Also, can use just class.
     * If not use Enum, enumName will be ignored
     * @see com.lannstark.style.DefaultExcelCellStyle
     *
     * @see com.lannstark.style.CustomExcelCellStyle
     */
    val excelCellStyleClass: KClass<out ExcelCellStyle>,
    /**
     * name of Enum implements [com.lannstark.style.ExcelCellStyle]
     * if not use Enum, enumName will be ignored
     */
    val enumName: String = "",
)
