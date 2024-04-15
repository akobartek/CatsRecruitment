package pl.sokolowskibartlomiej.catsrecruitment.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import pl.sokolowskibartlomiej.catsrecruitment.databinding.ItemFeedBinding
import pl.sokolowskibartlomiej.catsrecruitment.domain.model.PhotoItem

class FeedRecyclerAdapter(val onItemClick: (PhotoItem) -> Unit) :
    RecyclerView.Adapter<FeedRecyclerAdapter.FeedViewHolder>() {

    private var items = listOf<PhotoItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FeedViewHolder(
        ItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) =
        holder.bindView(items[position])

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(list: List<PhotoItem>) {
        items = list
        notifyDataSetChanged()
    }

    inner class FeedViewHolder(private val binding: ItemFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: PhotoItem) {
            with(binding) {
                item.description?.let { description ->
                    val lines = description
                        .replace(Regex("(<(/)img>)|(<img.+?>)"), "")
                        .parseAsHtml()
                        .toString()
                        .split("\n")
                        .filter { it.isNotBlank() }
                        .toMutableList()
                    lines.getOrNull(0)?.let { itemAuthor.text = it }
                    lines.removeAt(0)
                    val rest = lines.joinToString("\n")
                    itemDescription.text = rest
                    itemDescription.visibility = if (rest.isBlank()) View.GONE else View.VISIBLE
                }

                Glide.with(root.context)
                    .load(item.photoUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(itemImage)

                root.setOnClickListener { onItemClick(item) }
            }
        }
    }
}