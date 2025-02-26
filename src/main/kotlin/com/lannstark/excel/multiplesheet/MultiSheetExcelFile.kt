package com.lannstark.excel.multiplesheet

import com.lannstark.excel.SXSSFExcelFile
import com.lannstark.resource.DataFormatDecider
import com.lannstark.resource.DefaultDataFormatDecider
import org.apache.commons.compress.archivers.zip.Zip64Mode

/**
 * MultiSheetExcelFile
 *
 * - support Excel Version over 2007
 * - support multi sheet rendering
 * - support Dffierent DataFormat by Class Type
 * - support Custom CellStyle according to (header or body) and data field
 */
class MultiSheetExcelFile<T : Any>(
    data: List<T> = emptyList(),
    type: Class<T>,
    dataFormatDecider: DataFormatDecider = DefaultDataFormatDecider(),
) : SXSSFExcelFile<T>(data, type, dataFormatDecider) {
    private var currentRowIndex = ROW_START_INDEX

    init {
        wb.setZip64Mode(Zip64Mode.Always)
    }

    override fun renderExcel(data: List<T>) {
        // 1. Create header and return if data is empty
        if (data.isEmpty()) {
            createNewSheetWithHeader()
            return
        }

        // 2. Render body
        createNewSheetWithHeader()
        addRows(data)
    }

    override fun addRows(data: List<T>) {
        for (renderedData in data) {
            renderBody(renderedData, currentRowIndex++, COLUMN_START_INDEX)
            if (currentRowIndex == maxRowCanBeRendered) {
                currentRowIndex = 1
                createNewSheetWithHeader()
            }
        }
    }

    private fun createNewSheetWithHeader() {
        sheet = wb.createSheet()
        renderHeadersWithNewSheet(sheet!!, ROW_START_INDEX, COLUMN_START_INDEX)
        currentRowIndex++
    }

    companion object {
        private val maxRowCanBeRendered = supplyExcelVersion.maxRows - 1
        private const val ROW_START_INDEX = 0
        private const val COLUMN_START_INDEX = 0
    }
}
