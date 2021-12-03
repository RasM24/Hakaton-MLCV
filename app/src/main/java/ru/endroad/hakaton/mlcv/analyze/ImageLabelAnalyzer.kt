package ru.endroad.hakaton.mlcv.analyze

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

object ImageLabelAnalyzer {

	val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

	@SuppressLint("UnsafeOptInUsageError")
	fun analyze(imageProxy: ImageProxy) {
		imageProxy.image
			?.let { image -> InputImage.fromMediaImage(image, imageProxy.imageInfo.rotationDegrees) }
			?.let { labeler.process(it).addOnSuccessListener { result -> Log.d("analyzeLabel", result[0].text) } }
	}

}
