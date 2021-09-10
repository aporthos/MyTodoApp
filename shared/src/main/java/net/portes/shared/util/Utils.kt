package net.portes.shared.util

import okhttp3.MediaType
import okhttp3.ResponseBody

/**
 * @author amadeus.portes
 */
fun anyError(): ResponseBody = ResponseBody.create(
    MediaType.parse("application/json"), """
                {
                    "code": "InvalidCredentials",
                    "message": "That hash, timestamp and key combination is invalid."
                }
            """.trimIndent()

)