package net.portes.shared.extensions

import android.app.Activity
import android.content.*
import android.content.Intent.*
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentActivity
import net.portes.shared.util.*
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

/**
 * @author amadeus.portes
 */

fun Context.isBiometricSuccess() =
    BiometricManager.from(this).canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS

fun Activity.createFun(
    context: FragmentActivity,
    failed: () -> Unit,
    success: () -> Unit
): BiometricPrompt {
    val executor = ContextCompat.getMainExecutor(context)

    val callback = object : BiometricPrompt.AuthenticationCallback() {

        override fun onAuthenticationFailed() {
            super.onAuthenticationFailed()
            failed()
        }

        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            success()
        }
    }
    return BiometricPrompt(context, executor, callback)
}

fun View.takeScreen(): Bitmap? {
    var screenshot: Bitmap? = null
    try {
        screenshot = Bitmap.createBitmap(
            measuredWidth,
            measuredHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(screenshot)
        draw(canvas)
    } catch (e: Exception) {
        Timber.e("toShareScreen: -> ${e.message} ")
    } finally {
        return screenshot
    }
}

fun Bitmap.toSaveStorage(context: Context, success: (File) -> Unit) {
    val nameFile = "${System.currentTimeMillis()}.jpg"
    val directory = "${Environment.DIRECTORY_PICTURES}/$DIRECTORY_MYTODO"
    val imagesDir = context.getExternalFilesDir(directory)
    var outputStream: OutputStream?
    var imageFile: File? = null

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val imageUri: Uri?
        context.contentResolver.also { resolver ->
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, nameFile)
                put(MediaStore.MediaColumns.MIME_TYPE, MIME_TYPE)
                put(MediaStore.Images.Media.RELATIVE_PATH, directory)
            }

            imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            outputStream = imageUri?.let {
                resolver.openOutputStream(it)
            }
        }
        getDataColumn(context, nameFile)?.let {
            imageFile = File(it)
        }
    } else {
        imageFile = File(imagesDir, nameFile)
        outputStream = FileOutputStream(imageFile)
    }

    outputStream?.use {
        compress(Bitmap.CompressFormat.JPEG, QUALITY_IMAGE, it)
        it.flush()
        it.close()
        imageFile?.let(success)
    }
}

fun File.getUriForFile(context: Context): Uri = FileProvider.getUriForFile(
    context,
    context.applicationContext.packageName.toString() + ".provider",
    this
)

fun Context.toShareEmail(text: String, subject: String = "", imageFile: File): Boolean {
    val imageUri = imageFile.getUriForFile(this)
    val intent = Intent(ACTION_SEND).apply {
        putExtra(EXTRA_TEXT, "rerererer")
        type = TYPE_EMAIL
        putExtra(EXTRA_STREAM, imageUri)
    }

    return try {
        startActivity(createChooser(intent, null))
        true
    } catch (e: ActivityNotFoundException) {
        Timber.e("toShareEmail: -> ${e.message} ")
        false
    }
}

fun TextView.clickLink(text: Int, action: (view: View) -> Unit) {
    movementMethod = LinkMovementMethod.getInstance()
    val spannableString = SpannableString(context.getString(text))
    spannableString.setSpan(
        getClickableSpan(action),
        ZERO_INT,
        context.getString(text).length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    this.text = spannableString
}

fun Context.browse(url: String, newTask: Boolean = false): Boolean {
    return try {
        val intent = Intent(ACTION_VIEW).apply {
            data = Uri.parse(url)
            if (newTask) addFlags(FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
        true
    } catch (e: Exception) {
        Timber.e("browse: -> ${e.message}")
        false
    }
}

fun getClickableSpan(action: (view: View) -> Unit): ClickableSpan {
    return object : ClickableSpan() {
        override fun onClick(view: View) {
            action(view)
        }
    }
}

// TODO: 13/09/21 Review deprecated = MediaStore.Images.Media.DATA
private fun getDataColumn(context: Context, name: String): String? {
    var cursor: Cursor? = null
    val projection = arrayOf(
        MediaStore.Images.Media._ID,
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.DATA
    )

    try {
        cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            "${MediaStore.Images.Media.DISPLAY_NAME} like ? ",
            arrayOf("%$name%"),
            null
        )

        if (cursor != null && cursor.moveToFirst()) {
            val index = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
            return cursor.getString(index)
        }
    } finally {
        cursor?.close()
    }
    return null
}