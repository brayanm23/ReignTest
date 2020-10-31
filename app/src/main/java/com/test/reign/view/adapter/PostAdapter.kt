package com.test.reign.view.adapter

import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.reign.R
import com.test.reign.model.Post
import kotlinx.android.synthetic.main.adapter_post.view.*

class PostAdapter(private val posts: List<Post>): RecyclerView.Adapter<PostAdapter.PostViewHolder>()/*, OnClickListener*/ {

    //private var posts = ArrayList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(from(parent.context).inflate(R.layout.adapter_post, parent, false))
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    /*fun setPosts(movements: List<Post>) {
        val currentSize = this.posts.size

        this.posts.addAll(movements)
        notifyItemRangeInserted(currentSize, movements.size)
    }*/

    /*override fun onClick(v: View?) {
        val position = v!!.tag as Int

        val bundle = Bundle().apply {
            putString(TRANSACTION_ID_KEY, movements[position].id)
            putString(ACCOUNT_ID, accountId)
            putString(CURRENCY_SYMBOL, currency)
        }

        v.findNavController().navigate(action_accountsFragment_to_movementFragment, bundle)
    }*/

    class PostViewHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {
        fun bind(post: Post) {
            containerView.post_title.text = post.story_title
            containerView.author_and_date.text = "${post.author} - ${post.created_at}"
            //containerView.titleText.text = post.story_title
            //containerView.descriptionText.text = "${post.author} - ${post.created_at}"
        }
    }
}
