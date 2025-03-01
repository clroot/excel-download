package com.lannstark.excel

import java.io.IOException
import java.io.OutputStream

interface ExcelFile<T> {
    @Throws(IOException::class)
    fun write(stream: OutputStream)

    fun addRows(data: List<T>)
}
