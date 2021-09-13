package net.portes.mytodo.util.files

import android.content.Context

/**
 * @author amadeus.portes
 */
object DocumentFactory {
    fun getFileFactory(type: DocumentType, context: Context): IDocument = when (type) {
        is DocumentType.Csv -> CsvDocument()
        is DocumentType.Pdf -> PdfDocument(context)
    }
}

