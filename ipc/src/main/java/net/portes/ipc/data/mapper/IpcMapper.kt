package net.portes.ipc.data.mapper

import net.portes.ipc.data.models.response.IpcResponse
import net.portes.ipc.domain.models.IpcDto
import net.portes.shared.data.mapper.BaseMapper
import net.portes.shared.util.DATE_FORMAT
import net.portes.shared.util.ZERO_FLOAT
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class IpcMapper @Inject constructor() : BaseMapper<IpcResponse, IpcDto> {

    private val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())

    override fun mapFrom(from: IpcResponse): IpcDto = IpcDto(
        dateTimeStamp = toConvertDateToTimeStamp(from.date),
        price = from.price,
        percentageChange = from.percentageChange,
        volume = from.volume,
        change = from.change
    )

    private fun toConvertDateToTimeStamp(date: String): Float {
        val data = try {
            simpleDateFormat.parse(date)
        } catch (e: ParseException) {
            Timber.i("toConvertDateToTimeStamp: -> ${e.message}")
            return ZERO_FLOAT
        }

        return data.time.toFloat()
    }

}