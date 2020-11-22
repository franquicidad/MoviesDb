package com.franco.moviesdb


import com.franco.moviesdb.data.Entity.MoviesActionModel
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.franco.moviesdb.data.Entity.TvActionModel


fun Context.newToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

//fun RecyclerView.ViewHolder.toast(message: String){
//    itemView.context.newToast(message)
//}
fun ViewGroup.inflate(@LayoutRes layout: Int): View {
    val view = LayoutInflater.from(context).inflate(layout, this, false)
    return view
}

fun ImageView.loadUrl(completeUrl: String, progress: ProgressBar) {

    Glide.with(this)
        .load(completeUrl)
        .placeholder(R.drawable.progress)
        .into(this)

    progress.visibility = View.GONE
}

fun TextView.loadTextMovie(movieModel: MoviesActionModel) {

    this.text = movieModel.title
}

fun TextView.loadTextTv(movieModel: TvActionModel) {

    this.text = movieModel.originalName
}

inline fun <reified T : Activity> Context.startActivity(vararg pairs: Pair<String, Any?>) {

    Intent(this, T::class.java).apply { putExtras(bundleOf(*pairs)) }
        .also(::startActivity)
}

inline fun <reified T : ViewModel> ViewModelStoreOwner.getViewModel(body: T.() -> Unit = {}): T =
    ViewModelProvider(this).get<T>().apply(body)

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(this, Observer(observer))

}