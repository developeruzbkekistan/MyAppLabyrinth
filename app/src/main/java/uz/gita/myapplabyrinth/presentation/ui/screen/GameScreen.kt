package uz.gita.myapplabyrinth.presentation.ui.screen

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.myapplabyrinth.R
import uz.gita.myapplabyrinth.databinding.ScreenGameBinding
import uz.gita.myapplabyrinth.presentation.viewmodels.GameViewModel
import uz.gita.myapplabyrinth.presentation.viewmodels.impl.GameViewModelImpl
import uz.gita.myapplabyrinth.utils.myLog
import uz.gita.myapplabyrinth.utils.scope
import kotlin.math.abs

@AndroidEntryPoint
class GameScreen : Fragment(R.layout.screen_game) {
    private val viewModel: GameViewModel by viewModels<GameViewModelImpl>()
    private val binding by viewBinding(ScreenGameBinding::bind)
    private var level: Int = 0
    private var cHeight = 0
    private var cWidth = 0
    private lateinit var ballRef: ImageView
    private lateinit var myEvent: SensorEvent
    private var size = 0
    private lateinit var map: Array<Array<Int>>
    private val sensorService by lazy { requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    private val sensor by lazy { sensorService.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        arguments?.let { level = it.getInt("LEVEL", 0) }
        container.post {
            cHeight = container.height
            cWidth = container.width
            myLog("cHeight = $cHeight")
            myLog("cWidth = $cWidth")
            viewModel.loadMapByLevel(level)
        }

        viewModel.mapByLevelLiveData.observe(viewLifecycleOwner, mapByLevelObserver)
    }

    override fun onResume() {
        super.onResume()
        sensorService.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorService.unregisterListener(listener)
    }

    private val listener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            if (::ballRef.isInitialized) myEvent = event
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

        }
    }

    private fun getNewCoordinate(): Flow<Pair<Float, Float>> = flow {
        while (true) {
            delay(100)
            if (abs(myEvent.values[0]) > 0.5 || abs(myEvent.values[1]) > 0.5)
                emit(Pair(myEvent.values[0], myEvent.values[1]))
        }
    }

    private fun manageBall() {
        getNewCoordinate().onEach {
            if (it.second < 0) {  // left
                val i = (ballRef.x / size).toInt()
                val j = (ballRef.y / size).toInt()
                if (map[i - 1][j] == 1) {
                    moveBall(ballRef.x - i * size, 0f)
                } else {
                    moveBall(size * 0.4f * it.second, 0f)
                }
            } else {  // right
                val i = (ballRef.x / size).toInt() + 1
                val j = (ballRef.y / size).toInt()
                if (map[i + 1][j] == 1) {
                    moveBall((i + 1) * size - ballRef.x, 0f)
                } else {
                    moveBall(size * 0.4f * it.second, 0f)
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun moveBall(rangeX: Float, rangeY: Float) {
        ballRef.x += rangeX
        ballRef.y += rangeY
    }

    private val mapByLevelObserver = Observer<Array<Array<Int>>> { map ->
        this@GameScreen.map = map
        val rowCount = map.size
        val columnCount = map[0].size
        val _h = cHeight / rowCount
        val _w = cWidth / columnCount
        size = _h
        var i = 0
        var j = 0
        map.forEachIndexed { indexRow, row ->
            row.forEachIndexed { indexColumn, amount ->
                if (amount == 2) {
                    i = indexRow
                    j = indexColumn
                }
                if (amount == 1) {
                    val img = ImageView(requireContext())
                    binding.container.addView(img)
                    img.x = indexColumn * _w * 1f
                    img.y = indexRow * _h * 1f
                    img.layoutParams.apply {
                        height = _h
                        width = _w
                    }
                    img.scaleType = ImageView.ScaleType.FIT_XY
                    img.setImageResource(R.drawable.ic_board)
                }
            }
        }
        addBall(i, j, _w, _h)
    }

    private fun addBall(i: Int, j: Int, _w: Int, _h: Int) {
        val img = ImageView(requireContext())
        binding.container.addView(img)
        img.x = j * _w * 1f
        img.y = i * _h * 1f
        img.layoutParams.apply {
            height = _h
            width = _w
        }
        img.scaleType = ImageView.ScaleType.FIT_XY
        img.setImageResource(R.drawable.ic_board)
        ballRef = img
        img.setImageResource(R.drawable.ic_metall_ball)
        manageBall()
    }
}