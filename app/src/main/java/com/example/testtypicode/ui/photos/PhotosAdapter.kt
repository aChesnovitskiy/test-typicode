package com.example.testtypicode.ui.photos

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testtypicode.R
import com.example.testtypicode.data.pojo.Photo
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_photo.*
import java.io.InputStream

class PhotosAdapter() :
    RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {
    private var photos = listOf<Photo>()

    fun updatePhotos(data: List<Photo>) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldPos: Int, newPos: Int) =
                photos[oldPos].id == data[newPos].id

            override fun areContentsTheSame(oldPos: Int, newPos: Int) =
                photos[oldPos] == data[newPos]

            override fun getOldListSize() = photos.size
            override fun getNewListSize() = data.size
        }
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        photos = data
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_photo, parent, false
            )
        )

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) =
        holder.bind(photos[position])

    inner class PhotoViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bind(photo: Photo) {
            tv_photo_title.text = photo.id.toString()
//            iv_photo_image.setImageBitmap()
        }
    }

    // TODO https://www.tutorialspoint.com/how-to-download-image-from-url-in-android
    // https://stackoverflow.com/questions/18210700/best-method-to-download-image-from-url-in-android
    // https://www.tutorialspoint.com/how-to-download-and-save-an-image-from-a-given-url-in-android
    // https://medium.com/@crossphd/android-image-loading-from-a-string-url-6c8290b82c5e
//    private class DownloadImage : AsyncTask<String?, Any?, Any?>() {
//        override fun onPreExecute() {
//            super.onPreExecute()
//            mProgressDialog = ProgressDialog(this@MainActivity)
//            mProgressDialog.setTitle("Download Image Tutorial")
//            mProgressDialog.setMessage("Loading...")
//            mProgressDialog.setIndeterminate(false)
//            mProgressDialog.show()
//        }
//
//        override fun doInBackground(vararg URL: String?): Bitmap? {
//            val imageURL = URL[0]
//            var bitmap: Bitmap? = null
//            try {
//                val input: InputStream = java.net.URL(imageURL).openStream()
//                bitmap = BitmapFactory.decodeStream(input)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//            return bitmap
//        }
//
//        override fun onPostExecute(result: Any?) {
//            super.onPostExecute(result)
//        }
//
//        protected fun onPostExecute(result: Bitmap?) {
//            // Set the bitmap into ImageView
//            image.setImageBitmap(result)
//            // Close progressdialog
//            mProgressDialog.dismiss()
//        }
//    }
}