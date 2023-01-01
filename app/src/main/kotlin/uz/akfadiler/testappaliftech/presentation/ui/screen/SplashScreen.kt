package uz.akfadiler.testappaliftech.presentation.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.akfadiler.testappaliftech.R
import uz.akfadiler.testappaliftech.databinding.ScreenSplashBinding
import uz.akfadiler.testappaliftech.presentation.viewmodel.splash.SplashViewModel
import uz.akfadiler.testappaliftech.presentation.viewmodel.splash.SplashViewModelImpl

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {

    private val binding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openNextScreenLiveData.observe(this, openNextScreenObserver)
    }

    private val openNextScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_splashScreen_to_homeScreen)
    }
}