package com.indiapoliticaledge.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.DepartmentsList;

import java.util.ArrayList;

public class CustomSpinnerAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflter;
    private ArrayList<DepartmentsList> departmentsLists;

    public CustomSpinnerAdapter(Context applicationContext, ArrayList<DepartmentsList> departmentsLists) {
        this.context = applicationContext;
        inflter = (LayoutInflater.from(applicationContext));
        this.departmentsLists = departmentsLists;
    }

    @Override
    public int getCount() {
        return departmentsLists.size();
    }

    @Override
    public DepartmentsList getItem(int i) {
        return departmentsLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.item_department_spinner, null);
        TextView names = (TextView) view.findViewById(R.id.textView);
        names.setText(departmentsLists.get(i).departmentName);
        return view;
    }
}
