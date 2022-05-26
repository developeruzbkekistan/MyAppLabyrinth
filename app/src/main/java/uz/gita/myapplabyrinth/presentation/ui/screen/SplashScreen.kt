package uz.gita.myapplabyrinth.presentation.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.myapplabyrinth.R
import uz.gita.myapplabyrinth.databinding.ScreenSplashBinding
import uz.gita.myapplabyrinth.presentation.viewmodels.SplashViewModel
import uz.gita.myapplabyrinth.presentation.viewmodels.impl.SplashViewModelImpl

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val binding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel : SplashViewModel by viewModels<SplashViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openNextScreenLiveData.observe(viewLifecycleOwner, openNextScreenObserver)
    }

    private val openNextScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_splashScreen_to_levelScreen)
    }
}


