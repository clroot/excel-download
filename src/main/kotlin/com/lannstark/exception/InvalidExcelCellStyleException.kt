package com.lannstark.exception

import com.lannstark.ExcelException

class InvalidExcelCellStyleException(message: String, cause: Throwable) : ExcelException(message, cause)
