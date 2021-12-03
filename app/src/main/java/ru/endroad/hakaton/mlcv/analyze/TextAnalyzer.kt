package ru.endroad.hakaton.mlcv.analyze

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

object TextAnalyzer {

	private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

	@SuppressLint("UnsafeOptInUsageError")
	fun analyze(imageProxy: ImageProxy) {
		imageProxy.image
			?.let { image -> InputImage.fromMediaImage(image, imageProxy.imageInfo.rotationDegrees) }
			?.let { recognizer.process(it).addOnSuccessListener { result -> Log.d("analyze", result.text) } }
	}
}
