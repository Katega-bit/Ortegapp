package com.example.ortegapp_font.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ortegapp_font.databinding.ItemCommentBinding
import com.example.ortegapp_font.databinding.ItemListFavoriteBinding
import com.example.ortegapp_font.model.CommentResponse
import com.example.ortegapp_font.model.Producto
import com.squareup.picasso.Picasso

class AdapterComment(var commentList : List<CommentResponse> =  emptyList()
) : RecyclerView.Adapter<AdapterComment.CommentViewHolder>() {



    fun updateList(commentList : List<CommentResponse>){
        if (commentList != null){
            this.commentList = commentList
            notifyDataSetChanged()

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(this.commentList[position])
    }

    class CommentViewHolder(val binding : ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(commentItem : CommentResponse){

            Picasso.get().load(commentItem.avatar).into(binding.imageView)
            binding.comment.text = commentItem.mensaje
            binding.commentUser.text = commentItem.fullname

        }


    }

}