package com.lannstark

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class DefaultBodyStyle(val style: ExcelColumnStyle)
