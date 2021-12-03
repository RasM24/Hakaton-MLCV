package ru.endroad.hakaton.mlcv.analyze

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage

class ComplexAnalyzer(
	private val textListener: (String) -> Unit,
	private val labelListener: (String) -> Unit,
	private val luminosityListener: (Double) -> Unit,
) : ImageAnalysis.Analyzer {

	@SuppressLint("UnsafeOptInUsageError")
	override fun analyze(imageProxy: ImageProxy) {
		val imageInput = imageProxy.image?.let { image -> InputImage.fromMediaImage(image, imageProxy.imageInfo.rotationDegrees) }

		LuminosityAnalyzer.analyze(imageProxy).let(luminosityListener)
		imageInput?.let { TextAnalyzer.analyze(it, textListener) }
		imageInput?.let { ImageLabelAnalyzer.analyze(it, labelListener) }

		imageProxy.close()
	}
}
