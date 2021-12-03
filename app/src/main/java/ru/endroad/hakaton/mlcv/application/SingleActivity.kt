package ru.endroad.hakaton.mlcv.application

import android.Manifest
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.view.PreviewView
import ru.endroad.hakaton.mlcv.R
import ru.endroad.hakaton.mlcv.analyze.ComplexAnalyzer
import ru.endroad.hakaton.mlcv.camerax.CameraXFactory
import ru.endroad.hakaton.mlcv.camerax.cameraProvider
import ru.endroad.hakaton.mlcv.permissions.haveCameraPermission
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class SingleActivity : AppCompatActivity(R.layout.base_activity) {

	private val viewFinder: PreviewView
		get() = findViewById(R.id.viewFinder)

	private val text: TextView
		get() = findViewById(R.id.text)

	private val label: TextView
		get() = findViewById(R.id.label)

	private val cameraExecutor: ExecutorService by lazy { Executors.newSingleThreadExecutor() }

	private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
		if (isGranted) {
			startCamera()
		} else
			Unit
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		when {
			haveCameraPermission -> startCamera()
			else                 -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)
		}
	}

	private fun startCamera() {
		baseContext.cameraProvider { cameraProvider ->
			val analyzer = ComplexAnalyzer(
				textListener = text::setText,
				labelListener = label::setText,
			)

			cameraProvider.unbindAll()

			cameraProvider.bindToLifecycle(
				this,
				CameraSelector.DEFAULT_BACK_CAMERA,
				CameraXFactory.createPreview(viewFinder),
				CameraXFactory.createImageAnalyzer(cameraExecutor, analyzer)
			)
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		cameraExecutor.shutdown()
	}
}
