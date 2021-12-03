package ru.endroad.hakaton.mlcv.analyze

import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

object TextAnalyzer {

	private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

	fun analyze(image: InputImage, listener: (String) -> Unit) {
		image.let { recognizer.process(it).addOnSuccessListener { result -> listener(result.text) } }
	}
}
