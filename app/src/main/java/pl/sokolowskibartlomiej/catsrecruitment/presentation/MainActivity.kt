package pl.sokolowskibartlomiej.catsrecruitment.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DefaultItemAnimator
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.sokolowskibartlomiej.catsrecruitment.R
import pl.sokolowskibartlomiej.catsrecruitment.databinding.ActivityMainBinding
import pl.sokolowskibartlomiej.catsrecruitment.domain.model.PhotoItem

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { FeedRecyclerAdapter(::onRecyclerItemClicked) }
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.feedRecyclerView.scheduleLayoutAnimation()
                    adapter.setItems(state.items)
                    binding.loadingIndicator.apply {
                        if (state.isLoading) show() else hide()
                    }

                    if (state.isLoadingFailed) showNoInternetDialog()

                    println("CATS " + state.items)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.feedRecyclerView.apply {
            itemAnimator = DefaultItemAnimator()
            adapter = this@MainActivity.adapter
        }
    }

    private fun onRecyclerItemClicked(item: PhotoItem) {
        item.link?.let { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it))) }
    }

    private fun showNoInternetDialog() {
        MaterialAlertDialogBuilder(this)
            .setIcon(R.drawable.ic_no_internet)
            .setTitle(R.string.no_internet_dialog_title)
            .setMessage(R.string.no_internet_dialog_message)
            .setCancelable(false)
            .setPositiveButton(R.string.button_try_again) { dialog, _ ->
                dialog.dismiss()
                viewModel.loadPhotos()
            }
            .setNegativeButton(R.string.button_cancel) { dialog, _ ->
                dialog.dismiss()
                finish()
            }
            .create()
            .show()
    }
}