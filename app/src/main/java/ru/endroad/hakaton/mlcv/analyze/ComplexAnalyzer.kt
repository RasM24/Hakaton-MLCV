package ru.endroad.hakaton.mlcv.analyze

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

class ComplexAnalyzer(
) : ImageAnalysis.Analyzer {

	override fun analyze(image: ImageProxy) {
		val luma = LuminosityAnalyzer.analyze(image)

		image.close()
	}
}
