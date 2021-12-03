package ru.endroad.hakaton.mlcv.camerax

import android.content.Context
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

fun Context.cameraProvider(listener: (ProcessCameraProvider) -> Unit) {
	val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
	cameraProviderFuture.addListener(
		{
			try {
				listener(cameraProviderFuture.get())
			} catch (ex: Exception) {
			}
		},
		ContextCompat.getMainExecutor(this)
	)
}

object CameraXFactory {

	fun createPreview(view: PreviewView): Preview =
		Preview.Builder()
			.build()
			.also { it.setSurfaceProvider(view.surfaceProvider) }

	fun createImageAnalyzer(executor: Executor, analyzer: ImageAnalysis.Analyzer): ImageAnalysis =
		ImageAnalysis.Builder()
			.build()
			.also { it.setAnalyzer(executor, analyzer) }
}
