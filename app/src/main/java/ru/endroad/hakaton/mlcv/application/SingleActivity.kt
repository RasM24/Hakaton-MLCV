package ru.endroad.hakaton.mlcv.application

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ru.endroad.hakaton.mlcv.R
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class SingleActivity : AppCompatActivity(R.layout.base_activity) {

	private lateinit var cameraExecutor: ExecutorService

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		// Request camera permissions
		if (allPermissionsGranted()) {
			startCamera()
		} else {
			ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
		}


		cameraExecutor = Executors.newSingleThreadExecutor()
	}

	private fun startCamera() {}

	private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
		ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
	}

	override fun onDestroy() {
		super.onDestroy()
		cameraExecutor.shutdown()
	}

	companion object {

		private const val TAG = "CameraXBasic"
		private const val REQUEST_CODE_PERMISSIONS = 10
		private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
	}
}
