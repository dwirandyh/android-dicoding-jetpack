
package com.dwirandyh.jetpack.external

import java.text.SimpleDateFormat
import java.util.*


fun String.convertToDate(format: String): Date {
    return SimpleDateFormat(format, Locale.getDefault()).parse(this) ?: Date()
}