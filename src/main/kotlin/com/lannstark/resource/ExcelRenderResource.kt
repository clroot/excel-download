package com.lannstark.resource

import com.lannstark.resource.collection.PreCalculatedCellStyleMap
import org.apache.poi.ss.usermodel.CellStyle

class ExcelRenderResource(
    private val styleMap: PreCalculatedCellStyleMap,
    // TODO dataFieldName -> excelHeaderName Map Abstraction
    private val excelHeaderNames: MutableMap<String, String>,
    @JvmField val dataFieldNames: List<String>,
) {
    fun getCellStyle(
        dataFieldName: String,
        excelRenderLocation: ExcelRenderLocation,
    ): CellStyle {
        return styleMap[ExcelCellKey.of(dataFieldName, excelRenderLocation)]
    }

    fun getExcelHeaderName(dataFieldName: String): String? {
        return excelHeaderNames[dataFieldName]
    }
}
