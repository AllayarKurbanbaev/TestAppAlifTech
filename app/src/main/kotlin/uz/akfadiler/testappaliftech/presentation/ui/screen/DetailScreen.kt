package uz.akfadiler.testappaliftech.presentation.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.akfadiler.testappaliftech.R
import uz.akfadiler.testappaliftech.data.remote.response.posts.PostResponse
import uz.akfadiler.testappaliftech.databinding.ScreenDetailBinding
import uz.akfadiler.testappaliftech.presentation.viewmodel.detail.DetailViewModel
import uz.akfadiler.testappaliftech.presentation.viewmodel.detail.DetailViewModelImpl
import uz.akfadiler.testappaliftech.utils.gone
import uz.akfadiler.testappaliftech.utils.snackMessage
import uz.akfadiler.testappaliftech.utils.visible

@AndroidEntryPoint
class DetailScreen : Fragment(R.layout.screen_detail) {

    private val binding by viewBinding(ScreenDetailBinding::bind)
    private val viewModel: DetailViewModel by viewModels<DetailViewModelImpl>()
    private val navArgs: DetailScreenArgs by navArgs()

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
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
    }

    private fun initViews(binding: ScreenDetailBinding) = with(binding) {
        viewModel.loadPosts(navArgs.userId)
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

    }

    private val errorObserver = Observer<String> {
        snackMessage(it)
    }
}