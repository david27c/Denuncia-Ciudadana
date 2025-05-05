package com.example.denunciaciudadana;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class ReportAdapter extends ArrayAdapter<Report> {
    public ReportAdapter(Context context, List<Report> reports) {
        super(context, 0, reports);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Report report = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.report_item, parent, false);
        }

        TextView titleText = convertView.findViewById(R.id.report_title);
        TextView dateText = convertView.findViewById(R.id.report_date);

        titleText.setText(report.getTitle());
        dateText.setText(report.getDate());

        return convertView;
    }
}
