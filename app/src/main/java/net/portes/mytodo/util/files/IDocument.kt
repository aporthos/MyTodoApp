package net.portes.mytodo.util.files

import net.portes.topten.domain.models.TopTenItem
import java.io.File

interface IDocument {
    fun create(topTenItem: List<TopTenItem>): File
}