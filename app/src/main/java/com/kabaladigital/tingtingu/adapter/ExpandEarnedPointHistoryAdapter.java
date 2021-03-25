package com.kabaladigital.tingtingu.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.models.ChildObject;
import com.kabaladigital.tingtingu.models.ParentObject;
import com.kabaladigital.tingtingu.util.PreferenceUtils;

import java.util.List;

public class ExpandEarnedPointHistoryAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<ParentObject> _listDataHeader; // header titles
    private final String langType;

    // child data in format of header title, child title
    public ExpandEarnedPointHistoryAdapter(Context context, List<ParentObject> listDataHeader) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this.langType = PreferenceUtils.getInstance().getString(R.string.pref_user_selected_language_key);
    }

    @Override
    public int getGroupCount() {
        return _listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int size = 0;
        if (_listDataHeader.get(groupPosition).getChildren() != null)
            size = _listDataHeader.get(groupPosition).getChildren().size();
        return size;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return _listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return _listDataHeader.get(groupPosition).getChildren().get(childPosititon);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                                                  View convertView, ViewGroup parent) {

//        String headerTitle = (String) getGroup(groupPosition);
//        String headerTitle2 = (String) getGroup(groupPosition);

        ParentObject parentObjects = _listDataHeader.get(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                                     .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        TextView lblListHeaderPoint = (TextView) convertView.findViewById(R.id.lblListHeader_point);

        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(parentObjects.getDate());

        lblListHeaderPoint.setTypeface(null, Typeface.NORMAL);
        lblListHeaderPoint.setText(parentObjects.getPoint());

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                                   boolean isLastChild, View convertView, ViewGroup parent) {
//        final String childText = (String) getChild(groupPosition, childPosition);
//        final String childText2 = (String) getChild(groupPosition, childPosition);

        ParentObject parentObjects = _listDataHeader.get(groupPosition);
        final ChildObject child = parentObjects.getChildren().get(childPosition);


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.earned_list_item, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
        txtListChild.setText(child.getChildDate());

        TextView txtListChild2 = (TextView) convertView.findViewById(R.id.lblListItem2);
        txtListChild2.setText(child.getChildPoint());

        TextView tvDescription = (TextView) convertView.findViewById(R.id.tv_description);

        switch (child.getChildType()){
            case 1:
                tvDescription.setText(R.string.daily_point_credit);
                break;
            case 2:
                if (langType.equals("hi")){
                    tvDescription.setText("जिनको रेफर किया : " + child.getChildMobileNumber());
                }else {
                    tvDescription.setText("Referred To : " + child.getChildMobileNumber());
                }
                break;
            case 3:
                if (langType.equals("hi")){
                    tvDescription.setText("जिसने रेफेर किया : " + child.getChildMobileNumber());
                }else {
                    tvDescription.setText("Referred By : " + child.getChildMobileNumber());
                }
                break;
            case 8:
                if (langType.equals("hi")){
                    tvDescription.setText("सर्वे : ");
                }else {
                    tvDescription.setText("Survey : "+ child.getSurveyId());
                }
                break;
            default:
                tvDescription.setText(child.getChildDesc());
                break;
        }


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
