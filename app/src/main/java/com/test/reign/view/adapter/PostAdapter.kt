package com.test.reign.view.adapter

import com.test.reign.R
import com.test.reign.databinding.AdapterPostBinding
import com.test.reign.model.Post
import com.test.reign.view.DeletePostInterface
import easyadapter.dc.com.library.EasyAdapter

class PostAdapter(
    private val deletePostInterface: DeletePostInterface
): EasyAdapter<Post, AdapterPostBinding>(R.layout.adapter_post) {

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
            authorAndDate.text = "${post.author} - ${post.created_at}"
            llDelete.setOnClickListener {
                remove(post)
                deletePostInterface.deletePost(post)
            }
        }
    }
}
