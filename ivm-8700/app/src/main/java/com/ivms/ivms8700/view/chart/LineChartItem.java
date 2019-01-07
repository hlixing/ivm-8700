package com.ivms.ivms8700.view.chart;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.LineData;
import com.ivms.ivms8700.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;


public class LineChartItem extends ChartItem {

	private Typeface mTf;
	private ChartData<?> cd;

	public LineChartItem(ChartData<?> cd, Context c) {
		super(cd);
		this.cd = cd;
		cd.setValueTextColor(Color.RED);
		cd.setValueTextSize(9f);
		mTf = Typeface.createFromAsset(c.getAssets(), "OpenSans-Regular.ttf");
	}

	@Override
	public int getItemType() {
		return TYPE_LINECHART;
	}

	@Override
	public View getView(int position, View convertView, Context c) {

		ViewHolder holder = null;

		if (convertView == null) {

			holder = new ViewHolder();

			convertView = LayoutInflater.from(c).inflate(
					R.layout.list_item_linechart, null);
			holder.chart = (LineChart) convertView.findViewById(R.id.chart);
			holder.chart.setScaleEnabled(true);
			holder.chart.setPinchZoom(true);
			MyMarkerView mv = new MyMarkerView(c, R.layout.custom_marker_view);
			holder.chart.setMarkerView(mv);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// apply styling
		// holder.chart.setValueTypeface(mTf);
		holder.chart.setDescription("");
		holder.chart.setDrawGridBackground(false);

		XAxis xAxis = holder.chart.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
		xAxis.setTypeface(mTf);
		xAxis.setDrawGridLines(false);
		xAxis.setDrawAxisLine(true);
		YAxis leftAxis = holder.chart.getAxisLeft();
		leftAxis.setTypeface(mTf);
//		leftAxis.setLabelCount(5, false);

		YAxis rightAxis = holder.chart.getAxisRight();
		rightAxis.setTypeface(mTf);
//		rightAxis.setLabelCount(5, false);
		rightAxis.setDrawGridLines(false);

		// set data
		holder.chart.setData((LineData) mChartData);

		// do not forget to refresh the chart
		// holder.chart.invalidate();
		holder.chart.animateX(750);

		return convertView;
	}

	private static class ViewHolder {
		LineChart chart;
	}
}