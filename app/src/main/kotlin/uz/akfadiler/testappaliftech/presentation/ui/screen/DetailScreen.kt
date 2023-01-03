package uz.akfadiler.testappaliftech.presentation.ui.screen

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import uz.akfadiler.testappaliftech.R
import uz.akfadiler.testappaliftech.data.remote.response.posts.PostResponse
import uz.akfadiler.testappaliftech.databinding.ScreenDetailBinding
import uz.akfadiler.testappaliftech.presentation.ui.adapter.PostsAdapter
import uz.akfadiler.testappaliftech.presentation.viewmodel.detail.DetailViewModel
import uz.akfadiler.testappaliftech.presentation.viewmodel.detail.DetailViewModelImpl
import uz.akfadiler.testappaliftech.utils.gone
import uz.akfadiler.testappaliftech.utils.snackMessage
import uz.akfadiler.testappaliftech.utils.visible

@AndroidEntryPoint
class DetailScreen : Fragment(R.layout.screen_detail), OnClickListener {

    private val binding by viewBinding(ScreenDetailBinding::bind)
    private val viewModel: DetailViewModel by viewModels<DetailViewModelImpl>()
    private val navArgs: DetailScreenArgs by navArgs()
    private val postsAdapter: PostsAdapter by lazy { PostsAdapter() }
    private var tabSelectedId: TabLayout.Tab? = null

    override fun onCreate(savedInstanceState: Bundle?) = with(viewModel) {
        super.onCreate(savedInstanceState)
        errorLiveData.observe(this@DetailScreen, errorObserver)
        loadPostsLiveData.observe(this@DetailScreen, loadPostsObserver)
        backPressedLiveData.observe(this@DetailScreen, backPressedObserver)
        deletePostLiveData.observe(this@DetailScreen, deleteObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(binding)
        initObservers(viewModel)
    }

    private fun initObservers(viewModel: DetailViewModel) = with(viewModel) {
        progressLiveData.observe(viewLifecycleOwner, progressObserver)
    }

    private fun initViews(binding: ScreenDetailBinding) = with(binding) {
        backButton.setOnClickListener(this@DetailScreen)
        if (tabSelectedId != null) tabLayoutDetail.selectTab(tabSelectedId)
        tabLayoutDetail.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tabSelectedId = tab
                when (tabLayoutDetail.selectedTabPosition) {
                    0 -> {
                        viewModel.loadPosts(navArgs.userId)
                    }
                    1 -> {

                    }
                    2 -> {

                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Timber.d(tab.toString())
            }

        })
    }

    private val progressObserver = Observer<Boolean> {
        if (it) binding.progress.visible()
        else binding.progress.gone()
    }

    private val deleteObserver = Observer<Unit> {
        snackMessage("Delete success!")
    }

    private val backPressedObserver = Observer<Unit> {
        findNavController().popBackStack()
    }

    private val loadPostsObserver = Observer<List<PostResponse>> {
        binding.recyclerView.adapter = postsAdapter
        postsAdapter.submitList(it)
    }

    private val errorObserver = Observer<String> {
        snackMessage(it)
    }

    override fun onClick(view: View?) = with(binding) {
        when (view) {
            backButton -> viewModel.onBackPressed()
        }
    }
}