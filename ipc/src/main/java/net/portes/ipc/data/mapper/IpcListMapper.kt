package net.portes.ipc.data.mapper

import net.portes.ipc.data.models.response.IpcResponse
import net.portes.ipc.domain.model.IpcDto
import net.portes.shared.data.mapper.BaseDefaultMapper
import net.portes.shared.data.mapper.BaseMapper
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class IpcListMapper @Inject constructor(private val mapper: IpcMapper) :
    BaseMapper<List<IpcResponse>, List<IpcDto>>, BaseDefaultMapper<List<IpcResponse>> {

    override fun mapFrom(from: List<IpcResponse>): List<IpcDto> =
        mutableListOf<IpcDto>().apply {
            from.map {
                add(mapper.mapFrom(it))
            }
        }

    override fun toDefault(): List<IpcResponse> = emptyList()

}