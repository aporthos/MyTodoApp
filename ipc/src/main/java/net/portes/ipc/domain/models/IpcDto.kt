package net.portes.ipc.domain.models

/**
 * @author amadeus.portes
 */
data class IpcDto(
    val dateTimeStamp: Float,
    val price: Float,
    val percentageChange: Float,
    val volume: Int,
    val change: Float
)