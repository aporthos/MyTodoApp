package net.portes.topten.domain.models

/**
 * @author amadeus.portes
 */
data class TopTenItem(val header: TopTenHeader, val items: List<TopTenDto>)