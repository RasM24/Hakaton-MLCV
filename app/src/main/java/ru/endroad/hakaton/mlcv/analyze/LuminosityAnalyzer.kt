package ru.endroad.hakaton.mlcv.analyze

import androidx.camera.core.ImageProxy
import java.nio.ByteBuffer

object LuminosityAnalyzer {

	fun analyze(image: ImageProxy): Double =
		image.planes[0].buffer
			.toByteArray()
			.map { data -> data.toInt() and 0xFF }
			.average()

	private fun ByteBuffer.toByteArray(): ByteArray {
		rewind()
		val data = ByteArray(remaining())
		get(data)
		return data
	}
}
