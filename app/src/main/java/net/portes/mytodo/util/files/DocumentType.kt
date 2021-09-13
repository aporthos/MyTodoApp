package net.portes.mytodo.util.files

sealed class DocumentType {
    object Pdf : DocumentType()
    object Csv : DocumentType()
}