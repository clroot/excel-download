package com.lannstark.style.color

import com.lannstark.exception.UnSupportedExcelTypeException
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFColor

class DefaultExcelColor private constructor(private val red: Byte, private val green: Byte, private val blue: Byte) :
    ExcelColor {
        /**
         * applyForeground
         * In current, only supports XSSFCellStyle because can not find HSSFCellStyle RGB configuration
         * Please share if you find the HSSFCellStyle RGB configuration
         */
        override fun applyForeground(cellStyle: CellStyle) {
            try {
                val xssfCellStyle = cellStyle as XSSFCellStyle
                xssfCellStyle.setFillForegroundColor(XSSFColor(byteArrayOf(red, green, blue), DefaultIndexedColorMap()))
            } catch (e: Exception) {
                throw UnSupportedExcelTypeException(
                    String.format(
                        "Excel Type %s is not supported now",
                        cellStyle.javaClass,
                    ),
                )
            }
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND)
        }

        companion object {
            private const val MIN_RGB = 0
            private const val MAX_RGB = 255

            @JvmStatic
            fun rgb(
                red: Int,
                green: Int,
                blue: Int,
            ): DefaultExcelColor {
                require(!(red < MIN_RGB || red > MAX_RGB || green < MIN_RGB || green > MAX_RGB || blue < MIN_RGB || blue > MAX_RGB)) {
                    String.format(
                        "Wrong RGB(%s %s %s)",
                        red,
                        green,
                        blue,
                    )
                }
                return DefaultExcelColor(red.toByte(), green.toByte(), blue.toByte())
            }
        }
    }
