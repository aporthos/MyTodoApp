package net.portes.topten.data.mapper

import net.portes.shared.data.mapper.BaseDefaultMapper
import net.portes.shared.data.mapper.BaseMapper
import net.portes.shared.util.TYPE_DOWNS
import net.portes.shared.util.TYPE_UPS
import net.portes.shared.util.TYPE_VOLUME
import net.portes.topten.R
import net.portes.topten.data.models.response.TopTenResponse
import net.portes.topten.domain.models.TopTenDto
import net.portes.topten.domain.models.TopTenHeader
import net.portes.topten.domain.models.TopTenItem
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class TopTenListMapper @Inject constructor(private val mapper: TopTenMapper) :
    BaseMapper<List<TopTenResponse>, List<TopTenItem>>, BaseDefaultMapper<List<TopTenResponse>> {

    override fun mapFrom(from: List<TopTenResponse>): List<TopTenItem> {
        val topTenList = mutableListOf<TopTenDto>().apply {
            from.map {
                add(mapper.mapFrom(it))
            }
        }

        return generateTopTenItem(topTenList)
    }

    override fun toDefault(): List<TopTenResponse> = emptyList()

    private fun generateTopTenItem(topTenList: MutableList<TopTenDto>): List<TopTenItem> {
        val filterByUps = filterTopTenByType(topTenList, TYPE_UPS)
        val filterByDowns = filterTopTenByType(topTenList, TYPE_DOWNS)
        val filterByVolume = filterTopTenByType(topTenList, TYPE_VOLUME)

        return mutableListOf<TopTenItem>().apply {
            if (filterByUps.isNotEmpty()) {
                add(TopTenItem(TopTenHeader(R.string.title_topten_type_ups), filterByUps))
            }

            if (filterByDowns.isNotEmpty()) {
                add(TopTenItem(TopTenHeader(R.string.title_topten_type_downs), filterByDowns))
            }

            if (filterByVolume.isNotEmpty()) {
                add(TopTenItem(TopTenHeader(R.string.title_topten_type_volume), filterByVolume))
            }
        }
    }

    private fun filterTopTenByType(topTenList: MutableList<TopTenDto>, type: Int): List<TopTenDto> = topTenList.filter { it.riseLowTypeId == type }

}