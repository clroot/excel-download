package com.lannstark.resource

import java.util.*

class ExcelCellKey private constructor(
    private val dataFieldName: String,
    private val excelRenderLocation: ExcelRenderLocation,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as ExcelCellKey
        return dataFieldName == that.dataFieldName &&
            excelRenderLocation == that.excelRenderLocation
    }

    override fun hashCode(): Int {
        return Objects.hash(dataFieldName, excelRenderLocation)
    }

    companion object {
        @JvmStatic
        fun of(
            fieldName: String,
            excelRenderLocation: ExcelRenderLocation,
        ): ExcelCellKey {
            return ExcelCellKey(fieldName, excelRenderLocation)
        }
    }
}
