package com.lannstark.resource.collection

import com.lannstark.resource.DataFormatDecider
import com.lannstark.resource.ExcelCellKey
import com.lannstark.style.ExcelCellStyle
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Workbook

/**
 * PreCalculatedCellStyleMap
 *
 * Determines cell's style
 * In currently, PreCalculatedCellStyleMap determines {org.apache.poi.ss.usermodel.DataFormat}
 *
 */
class PreCalculatedCellStyleMap(private val dataFormatDecider: DataFormatDecider) {
    private val cellStyleMap: MutableMap<ExcelCellKey, CellStyle> = HashMap()

    fun put(
        fieldType: Class<*>,
        excelCellKey: ExcelCellKey,
        excelCellStyle: ExcelCellStyle,
        wb: Workbook,
    ) {
        val cellStyle = wb.createCellStyle()
        val dataFormat = wb.createDataFormat()
        cellStyle.dataFormat = dataFormatDecider.getDataFormat(dataFormat, fieldType)
        excelCellStyle.apply(cellStyle)
        cellStyleMap[excelCellKey] = cellStyle
    }

    operator fun get(excelCellKey: ExcelCellKey): CellStyle {
        return cellStyleMap[excelCellKey] ?: throw IllegalStateException("No style found for $excelCellKey")
    }

    val isEmpty: Boolean
        get() = cellStyleMap.isEmpty()
}
