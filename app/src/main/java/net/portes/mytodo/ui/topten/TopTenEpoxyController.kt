package net.portes.mytodo.ui.topten

import com.airbnb.epoxy.EpoxyController
import net.portes.mytodo.itemHeader
import net.portes.mytodo.itemTopten
import net.portes.topten.domain.models.TopTenItem

/**
 * @author amadeus.portes
 */
class TopTenEpoxyController : EpoxyController() {
    var topTenList: List<TopTenItem> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        topTenList.forEach {
            itemHeader {
                id(it.header.name)
                header(it.header)
            }

            it.items.forEach {
                itemTopten {
                    id(it.issueId)
                    item(it)
                }
            }
        }
    }
}