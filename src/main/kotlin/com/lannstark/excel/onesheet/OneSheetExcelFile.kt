package com.lannstark.excel.onesheet

import com.lannstark.excel.SXSSFExcelFile
import com.lannstark.resource.DataFormatDecider
import com.lannstark.resource.DefaultDataFormatDecider

/**
 * OneSheetExcelFile
 *
 * - support Excel Version over 2007
 * - support one sheet rendering
 * - support different DataFormat by Class Type
 * - support Custom CellStyle according to (header or body) and data field
 */
class OneSheetExcelFile<T : Any>(
    data: List<T> = emptyList(),
    type: Class<T>,
    dataFormatDecider: DataFormatDecider = DefaultDataFormatDecider(),
) : SXSSFExcelFile<T>(data, type, dataFormatDecider) {
    private var currentRowIndex = ROW_START_INDEX

    override fun validateData(data: List<T>) {
        val maxRows = supplyExcelVersion.maxRows
        require(data.size <= maxRows) {
            String.format(
                "This concrete ExcelFile does not support over %s rows",
                maxRows,
            )
        }
    }

    override fun renderExcel(data: List<T>) {
        // 1. Create sheet and renderHeader
        sheet = wb.createSheet()
        renderHeadersWithNewSheet(sheet!!, currentRowIndex++, COLUMN_START_INDEX)

        if (data.isEmpty()) {
            return
        }

        // 2. Render Body
        for (renderedData in data) {
            renderBody(renderedData, currentRowIndex++, COLUMN_START_INDEX)
        }
    }

    override fun addRows(data: List<T>) {
        renderBody(data, currentRowIndex++, COLUMN_START_INDEX)
    }

    companion object {
        private const val ROW_START_INDEX = 0
        private const val COLUMN_START_INDEX = 0
    }
}
