package net.portes.mytodo.util.files

import android.content.Context
import android.os.Environment
import com.itextpdf.kernel.colors.ColorConstants
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.property.TextAlignment
import net.portes.mytodo.R
import net.portes.shared.extensions.parseMoney
import net.portes.shared.extensions.parsePercentage
import net.portes.shared.util.DIRECTORY_MYTODO
import net.portes.shared.util.TYPE_UPS
import net.portes.shared.util.TYPE_VOLUME
import net.portes.topten.domain.models.TopTenDto
import net.portes.topten.domain.models.TopTenItem
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * @author amadeus.portes
 */
class PdfDocument(private val context: Context) : IDocument {

    override fun create(topTenItem: List<TopTenItem>): File {
        var document: Document? = null
        val nameFile = "${System.currentTimeMillis()}.pdf"
        val directory = "${Environment.DIRECTORY_DOCUMENTS}/$DIRECTORY_MYTODO"
        val fileDir = context.getExternalFilesDir(directory)
        val file = File(fileDir, nameFile)

        try {
            document = createDocument(file)
            addTitle(document)
            addEmptyLine(document)
            topTenItem.forEach {
                addHeader(document, context.getString(it.header.name))
                addTable(document, it.items)
            }
            addEmptyLine(document)
        } catch (e: IOException) {
            Timber.e("toCreatePDF: -> ${e.message}")
        } finally {
            document?.close()
        }
        return file
    }

    private fun createDocument(file: File): Document {
        val outputStream = FileOutputStream(file)
        val pdfWriter = PdfWriter(outputStream)
        val pdfDocument = com.itextpdf.kernel.pdf.PdfDocument(pdfWriter)
        return Document(pdfDocument)
    }

    private fun addTitle(document: Document) {
        document.add(
            Paragraph(context.getString(R.string.title_topten_market)).setBold().setUnderline()
                .setTextAlignment(TextAlignment.CENTER)
        )
    }

    private fun addEmptyLine(layoutDocument: Document, number: Int = 1) {
        for (i in 0 until number) {
            layoutDocument.add(Paragraph(" "))
        }
    }

    private fun addTable(document: Document, items: List<TopTenDto>) {
        val table = Table(floatArrayOf(100f, 100f), true)
        table.addCell(
            Paragraph(context.getString(R.string.message_issue_id)).setBold()
                .setTextAlignment(TextAlignment.CENTER)
        )
        table.addCell(
            Paragraph(context.getString(R.string.message_last_price_percentage_change)).setBold()
                .setTextAlignment(TextAlignment.CENTER)
        )

        for (i in items) {
            table.addCell(Paragraph(i.issueId).setTextAlignment(TextAlignment.CENTER))
            table.addCell(
                Paragraph(
                    createPercentage(
                        "${i.lastPrice.parseMoney()} / ${i.percentageChange.parsePercentage()}",
                        i.riseLowTypeId
                    )
                ).setTextAlignment(
                    TextAlignment.CENTER
                )
            )
        }

        document.add(table)
    }

    private fun createPercentage(percentage: String, type: Int): Text {
        val text = Text(percentage)
        if (type == TYPE_UPS || type == TYPE_VOLUME) {
            text.setFontColor(ColorConstants.GREEN)
        } else {
            text.setFontColor(ColorConstants.RED)
        }
        return text
    }

    private fun addHeader(document: Document, text: String) {
        val header: Text = Text(text).setFontColor(ColorConstants.WHITE)
        document.add(
            Paragraph(header).setBold().setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(ColorConstants.BLACK)
        )
    }

}