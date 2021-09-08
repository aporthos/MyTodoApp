package net.portes.ipc

import net.portes.ipc.data.models.response.IpcResponse

/**
 * @author amadeus.portes
 */
object MockFactory {

    val responseList = arrayListOf(
        response()
    )

    fun response() = IpcResponse(
        date = "2020-08-18T04:11:21.06-05:00",
        price = 39117.84f,
        percentageChange = -0.44f,
        volume = 13355546,
        change = -171.87f
    )

}