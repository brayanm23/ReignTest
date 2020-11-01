package com.test.reign.view.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater.from
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.test.reign.R
import com.test.reign.R.id.action_listFragment_to_detailFragment
import com.test.reign.model.Post
import kotlinx.android.synthetic.main.adapter_post.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>()/*, OnClickListener*/ {

    companion object {
        const val URL_KEY = "url_key"
        const val TITLE_KEY = "title_key"
        const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
        const val STRING_POST_DATE_FORMAT = "d 'de' MMMM' a las 'HH:mm' h'"
        const val LANGUAGE_TAG = "es-ES"
    }

    private var posts = ArrayList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(from(parent.context).inflate(R.layout.adapter_post, parent, false))
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    fun setPosts(movements: List<Post>) {
        val currentSize = this.posts.size

        this.posts.addAll(movements)
        notifyItemRangeInserted(currentSize, movements.size)
    }

    /*override fun onClick(v: View?) {
        Log.d("brayan", "onclick")
        val position = v!!.tag as Int
        val bundle = Bundle().apply { putString(URL_KEY, posts[position].story_url) }
        v.findNavController().navigate(action_listFragment_to_detailFragment, bundle)
    }*/

    class PostViewHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {
        fun bind(post: Post) {
            /*containerView.foregroundContainer.setOnClickListener {
                val bundle = Bundle().apply {
                    putString(URL_KEY, post.story_url)
                    putString(TITLE_KEY, post.story_title)
                }
                it.findNavController().navigate(action_listFragment_to_detailFragment, bundle)
            }*/
            containerView.post_title.text = post.story_title
            containerView.author_and_date.text = "${post.author} - ${parseDate(post.created_at)}"
        }

        private fun parseDate(dateInString: String): String {
            return try {
                val date = SimpleDateFormat(DATE_FORMAT).parse(dateInString)
                SimpleDateFormat(STRING_POST_DATE_FORMAT, Locale.forLanguageTag(LANGUAGE_TAG)).format(date)
            } catch (exception: ParseException) {
                ""
            }
        }
    }
}
