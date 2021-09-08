package net.portes.mytodo.ui.ipc

import android.graphics.Color
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import net.portes.ipc.domain.model.IpcDto
/**
 * @author amadeus.portes
 */
object LineChartFactory {

    fun createLineChart(ipcLineChart: LineChart, ipcList: List<IpcDto>, nameLineChart: String, descriptionLineChart: String) {
        val ipcListEntry = mutableListOf<Entry>().apply {
            ipcList.map {
                add(Entry(it.dateTimeStamp, it.price))
            }
        }

        val ipcLineDataSet = LineDataSet(ipcListEntry, nameLineChart)
        ipcLineDataSet.setDrawValues(false)

        val ipcLineData = LineData(ipcLineDataSet)
        ipcLineChart.data = ipcLineData

        ipcLineChart.xAxis.setDrawGridLines(false)
        ipcLineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        ipcLineChart.xAxis.setDrawAxisLine(false)
        ipcLineChart.xAxis.setDrawLabels(false)

        ipcLineChart.description.isEnabled = true
        ipcLineChart.description.text = descriptionLineChart

        ipcLineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        ipcLineDataSet.color = Color.RED
        ipcLineDataSet.setDrawCircles(false)

        ipcLineChart.axisLeft.setDrawAxisLine(false)

        ipcLineChart.notifyDataSetChanged()
        ipcLineChart.invalidate()
    }

}