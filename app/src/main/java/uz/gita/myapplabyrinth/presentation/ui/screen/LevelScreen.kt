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
import uz.gita.myapplabyrinth.databinding.ScreenLevelBinding
import uz.gita.myapplabyrinth.presentation.viewmodels.LevelViewModel
import uz.gita.myapplabyrinth.presentation.viewmodels.impl.LevelViewModelImpl
import uz.gita.myapplabyrinth.utils.scope

@AndroidEntryPoint
class LevelScreen : Fragment(R.layout.screen_level) {
    private val binding by viewBinding(ScreenLevelBinding::bind)
    private val viewModel: LevelViewModel by viewModels<LevelViewModelImpl>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        level1.setOnClickListener { viewModel.openNextScreenByLevel(1) }
        level2.setOnClickListener { viewModel.openNextScreenByLevel(2) }
        level3.setOnClickListener { viewModel.openNextScreenByLevel(3) }

        viewModel.openNextScreenLiveData.observe(this@LevelScreen, openNextScreenObserver)
    }

    private val openNextScreenObserver = Observer<Int> {
        val bundle = Bundle()
        bundle.putInt("LEVEL", it)
        findNavController().navigate(R.id.action_levelScreen_to_gameScreen, bundle)
    }
}