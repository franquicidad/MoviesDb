package com.franco.moviesdb


import com.franco.moviesdb.network.model.MoviesActionModel
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
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.franco.moviesdb.network.model.TvActionModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*


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

fun <T> CoroutineScope.collectFlow(flow: Flow<T>, body: suspend (T) -> Unit) {
    flow.onEach { body(it) }
        .launchIn(this)
}

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

@ExperimentalCoroutinesApi
val RecyclerView.lastVisibleEvents: Flow<Int>
    get() = callbackFlow<Int> {
        val lm = layoutManager as GridLayoutManager

        val listener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                offer(lm.findLastVisibleItemPosition())
            }
        }
        addOnScrollListener(listener)
        awaitClose { removeOnScrollListener(listener) }
    }.conflate()
//fun TextView.loadTextTv(movieModel: MoviesActionModel) {
//
//    this.text = movieModel.title
//}

inline fun <reified T : Activity> Context.startActivity(vararg pairs: Pair<String, Any?>) {

    Intent(this, T::class.java).apply { putExtras(bundleOf(*pairs)) }
        .also(::startActivity)
}

inline fun SearchView.onQueryTextChanged(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            listener(newText.orEmpty())
            return true
        }

    })
}

inline fun <reified T : ViewModel> ViewModelStoreOwner.getViewModel(body: T.() -> Unit = {}): T =
    ViewModelProvider(this).get<T>().apply(body)

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(this, Observer(observer))

}

@ExperimentalCoroutinesApi
val View.onClickEvents: Flow<View>
    get() = callbackFlow {
        val onClickListener = View.OnClickListener { offer(it) }
        setOnClickListener(onClickListener)
        awaitClose { setOnClickListener(null) }
    }.conflate()