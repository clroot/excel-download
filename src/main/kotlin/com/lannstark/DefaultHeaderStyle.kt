package com.lannstark

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class DefaultHeaderStyle(val style: ExcelColumnStyle)
