package ru.endroad.hakaton.mlcv.application

import android.Manifest
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import ru.endroad.hakaton.mlcv.R
import ru.endroad.hakaton.mlcv.permissions.haveCameraPermission
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class SingleActivity : AppCompatActivity(R.layout.base_activity) {

	private val viewFinder: PreviewView
		get() = findViewById(R.id.viewFinder)

	private lateinit var cameraExecutor: ExecutorService

	private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
		if (isGranted) {
			startCamera()
		} else
			Unit
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		when {
			haveCameraPermission -> Unit
			else                 -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)
		}

		cameraExecutor = Executors.newSingleThreadExecutor()
	}

	private fun startCamera() {
		val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

		cameraProviderFuture.addListener({
			// Used to bind the lifecycle of cameras to the lifecycle owner
			val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

			// Preview
			val preview = Preview.Builder()
				.build()
				.also { it.setSurfaceProvider(viewFinder.surfaceProvider) }

			// Select back camera as a default
			val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

			try {
				// Unbind use cases before rebinding
				cameraProvider.unbindAll()

				// Bind use cases to camera
				cameraProvider.bindToLifecycle(
					this, cameraSelector, preview
				)

			} catch (exc: Exception) {
			}

		}, ContextCompat.getMainExecutor(this))
	}

	override fun onDestroy() {
		super.onDestroy()
		cameraExecutor.shutdown()
	}
}
