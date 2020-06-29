package com.example.testtypicode.ui.photos

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testtypicode.R
import com.example.testtypicode.cache.ImageLoader
import com.example.testtypicode.data.pojo.Photo
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_photo.*

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
            tv_photo_title.text = photo.title
            Log.d("My_PhotosAdapter", photo.url)

            Picasso.get()
                .load(photo.url)
                .into(
                    iv_photo_image,
                    object : Callback {
                        override fun onSuccess() {
                            pb_photo_loading.visibility = View.GONE
                            iv_photo_image.visibility = View.VISIBLE
                        }

                        override fun onError(e: Exception?) {
                            e?.printStackTrace()
                        }
                    }
                )
        }
    }

    // https://androidexample.com/Download_Images_From_Web_And_Lazy_Load_In_ListView_-_Android_Example/index.php?view=article_discription&aid=112&aaid=134
    // https://www.technotalkative.com/android-load-images-from-web-and-caching/
    // https://eclipsesource.com/blogs/2012/07/31/loading-caching-and-displaying-images-in-android-part-1/
    // https://www.tutorialspoint.com/how-to-download-image-from-url-in-android
    // https://stackoverflow.com/questions/18210700/best-method-to-download-image-from-url-in-android
    // https://www.tutorialspoint.com/how-to-download-and-save-an-image-from-a-given-url-in-android
    // https://medium.com/@crossphd/android-image-loading-from-a-string-url-6c8290b82c5e
}