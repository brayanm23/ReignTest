package com.test.reign.view.adapter

import com.test.reign.R
import com.test.reign.databinding.AdapterPostBinding
import com.test.reign.model.Post
import com.test.reign.view.DeletePostInterface
import easyadapter.dc.com.library.EasyAdapter
import java.text.SimpleDateFormat
import java.util.*

class PostAdapter(
    private val deletePostInterface: DeletePostInterface
): EasyAdapter<Post, AdapterPostBinding>(R.layout.adapter_post) {

    private companion object {
        const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
        const val STRING_MOVEMENT_DATE_FORMAT = "d 'de' MMMM 'a las' HH:mm' h'"
        const val LANGUAGE_TAG = "es-ES"
    }

    override fun onCreatingHolder(binding: AdapterPostBinding, baseHolder: EasyHolder) {
        super.onCreatingHolder(binding, baseHolder)
        binding.root.setOnClickListener(baseHolder.clickListener)
        binding.llDelete.post {
            baseHolder.setEnableSwipeToDelete(binding.llCategory, 0, binding.llDelete.measuredWidth)
        }
    }

    override fun onBind(binding: AdapterPostBinding, post: Post) {}

    override fun onBind(binding: AdapterPostBinding, post: Post, payloads: MutableList<Any>) {
        super.onBind(binding, post, payloads)
        binding.apply {
            postTitle.text = post.story_title
            authorAndDate.text = "${post.author} - ${formatDate(post.created_at)}"
            llDelete.setOnClickListener {
                remove(post)
                deletePostInterface.deletePost(post)
            }
        }
    }

    private fun formatDate(date: String): String{
        val parse = SimpleDateFormat(DATE_FORMAT).parse(date)
        return SimpleDateFormat(STRING_MOVEMENT_DATE_FORMAT, Locale.forLanguageTag(LANGUAGE_TAG)).format(parse)
    }
}
