package com.lannstark.resource

import org.apache.poi.ss.usermodel.DataFormat

interface DataFormatDecider {
    fun getDataFormat(
        dataFormat: DataFormat,
        type: Class<*>,
    ): Short
}
