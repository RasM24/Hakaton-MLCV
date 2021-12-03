package ru.endroad.hakaton.mlcv.analyze

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

class ComplexAnalyzer(
) : ImageAnalysis.Analyzer {

	override fun analyze(imageProxy: ImageProxy) {
		val luma = LuminosityAnalyzer.analyze(imageProxy)
		TextAnalyzer.analyze(imageProxy)
		ImageLabelAnalyzer.analyze(imageProxy)

		imageProxy.close()
	}
}
