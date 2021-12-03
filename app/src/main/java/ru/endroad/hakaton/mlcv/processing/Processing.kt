package ru.endroad.hakaton.mlcv.processing

import ru.endroad.hakaton.mlcv.application.AlertLuminosityStatus

object Processing {

	private val trainNumberRegex = Regex.fromLiteral("([0-9]{8})")
	private val autoNumber = Regex.fromLiteral("/^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}\$/ui")

	fun processText(data: List<String>): String =
		data
//			.filter { it.matches(trainNumberRegex) || it.matches(autoNumber) }
			.joinToString(prefix = "Номера транспортных средств: ")

	fun processLabels(data: List<String>): String = data.joinToString()

	fun processLuminosity(data: Double): AlertLuminosityStatus =
		when {
			data < 10.0  -> AlertLuminosityStatus.DARK
			data > 140.0 -> AlertLuminosityStatus.LIGHT
			else         -> AlertLuminosityStatus.NORMAL
		}
}
