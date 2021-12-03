package ru.endroad.hakaton.mlcv.analyze

import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

object ImageLabelAnalyzer {

	private val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

	fun analyze(image: InputImage, listener: (List<String>) -> Unit) {
		image.let { labeler.process(it).addOnSuccessListener { result -> listener(result.map(ImageLabel::getText)) } }
	}
}
