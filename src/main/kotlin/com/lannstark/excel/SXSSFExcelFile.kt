package com.lannstark.excel

import com.lannstark.exception.ExcelInternalException
import com.lannstark.resource.*
import com.lannstark.utils.SuperClassReflectionUtils
import org.apache.poi.ss.SpreadsheetVersion
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.streaming.SXSSFWorkbook
import java.io.IOException
import java.io.OutputStream

abstract class SXSSFExcelFile<T : Any>
    @JvmOverloads
    constructor(
        data: List<T> = emptyList(),
        type: Class<T>,
        dataFormatDecider: DataFormatDecider = DefaultDataFormatDecider(),
    ) :
    ExcelFile<T> {
        @JvmField
        protected var wb: SXSSFWorkbook

        @JvmField
        protected var sheet: Sheet? = null
        protected var resource: ExcelRenderResource

        init {
            validateData(data)
            this.wb = SXSSFWorkbook()
            this.resource = ExcelRenderResourceFactory.prepareRenderResource(type, wb, dataFormatDecider)
            renderExcel(data)
        }

        protected open fun validateData(data: List<T>) {}

        protected abstract fun renderExcel(data: List<T>)

        protected fun renderHeadersWithNewSheet(
            sheet: Sheet,
            rowIndex: Int,
            columnStartIndex: Int,
        ) {
            val row = sheet.createRow(rowIndex)
            var columnIndex = columnStartIndex
            for (dataFieldName in resource.dataFieldNames) {
                val cell = row.createCell(columnIndex++)
                cell.cellStyle = resource.getCellStyle(dataFieldName, ExcelRenderLocation.HEADER)
                cell.setCellValue(resource.getExcelHeaderName(dataFieldName))
            }
        }

        protected fun renderBody(
            data: Any,
            rowIndex: Int,
            columnStartIndex: Int,
        ) {
            val row = sheet!!.createRow(rowIndex)
            var columnIndex = columnStartIndex
            for (dataFieldName in resource.dataFieldNames) {
                val cell = row.createCell(columnIndex++)
                try {
                    val field = SuperClassReflectionUtils.getField(data.javaClass, (dataFieldName))
                    field.isAccessible = true
                    cell.cellStyle = resource.getCellStyle(dataFieldName, ExcelRenderLocation.BODY)
                    val cellValue = field[data]
                    renderCellValue(cell, cellValue)
                } catch (e: Exception) {
                    throw ExcelInternalException(e.message!!, e)
                }
            }
        }

        private fun renderCellValue(
            cell: Cell,
            cellValue: Any?,
        ) {
            if (cellValue is Number) {
                cell.setCellValue(cellValue.toDouble())
                return
            }
            cell.setCellValue(cellValue?.toString() ?: "")
        }

        @Throws(IOException::class)
        override fun write(stream: OutputStream) {
            wb.write(stream)
            wb.close()
            wb.dispose()
            stream.close()
        }

        companion object {
            @JvmStatic
            protected val supplyExcelVersion: SpreadsheetVersion = SpreadsheetVersion.EXCEL2007
        }
    }
