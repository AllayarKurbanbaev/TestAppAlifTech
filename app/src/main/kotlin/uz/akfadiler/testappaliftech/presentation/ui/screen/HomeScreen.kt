package uz.akfadiler.testappaliftech.presentation.ui.screen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import uz.akfadiler.testappaliftech.R
import uz.akfadiler.testappaliftech.data.remote.response.user.GeoUser
import uz.akfadiler.testappaliftech.databinding.ScreenHomeBinding
import uz.akfadiler.testappaliftech.domain.model.UserData
import uz.akfadiler.testappaliftech.presentation.ui.adapter.UsersAdapter
import uz.akfadiler.testappaliftech.presentation.viewmodel.home.HomeViewModel
import uz.akfadiler.testappaliftech.presentation.viewmodel.home.HomeViewModelImpl
import uz.akfadiler.testappaliftech.utils.gone
import uz.akfadiler.testappaliftech.utils.snackMessage
import uz.akfadiler.testappaliftech.utils.visible

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {
    private val binding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private val userAdapter: UsersAdapter by lazy { UsersAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) = with(viewModel) {
        super.onCreate(savedInstanceState)
        errorLiveData.observe(this@HomeScreen, errorObserver)
        loadLiveData.observe(this@HomeScreen, loadObserver)
        locationLiveData.observe(this@HomeScreen, locationObserver)
        phoneLiveData.observe(this@HomeScreen, phoneObserver)
        openNextScreenLiveData.observe(this@HomeScreen, openNextScreenObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(binding)
        initObservers(viewModel)
    }

    private fun initObservers(viewModel: HomeViewModel) = with(viewModel) {
        progressLiveData.observe(viewLifecycleOwner, progressObserver)
    }

    private fun initViews(binding: ScreenHomeBinding) = with(binding) {
        recyclerView.adapter = userAdapter
        userAdapter.setOnLocationClickListener {
            viewModel.location(it)
        }
        userAdapter.setOnPhoneClickListener {
            viewModel.phone(it)
        }
        userAdapter.setOnPostsClickListener {
            viewModel.posts(it)
        }
    }

    private val progressObserver = Observer<Boolean> {
        if (it) binding.progress.visible()
        else binding.progress.gone()
    }

    private val openNextScreenObserver = Observer<Int> {
        findNavController().navigate(HomeScreenDirections.actionHomeScreenToDetailScreen(it))
    }

    private val phoneObserver = Observer<String> {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.data = Uri.parse("tel:$it")
        activity?.startActivity(intent)
    }

    private val locationObserver = Observer<GeoUser> {
        Timber.d(it.toString())
        val latitude = it.lat
        val longitude = it.lng
        if (latitude != null && longitude != null) {
            val uri: String = String.format(
                "geo:0,0?q=%f,%f", latitude, longitude
            )
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            requireContext().startActivity(mapIntent)
        } else snackMessage(getString(R.string.no_location))
    }

    private val loadObserver = Observer<List<UserData>> {
        userAdapter.submitList(it)
    }

    private val errorObserver = Observer<String> {
        snackMessage(it)
    }
}