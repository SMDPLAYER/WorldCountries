package com.mysoftpanda.android.dunyomamlakatlari;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

//public class WsCustomAdapter extends BaseAdapter {
//    Context context;
//    List<WsRowItem> rowItems;
//
//    private class ViewHolder {
//        TextView contactType;
//        TextView member_name;
//        ImageView profile_pic;
//        TextView status;
//
//        private ViewHolder() {
//        }
//    }
//
//    WsCustomAdapter(Context context, List<WsRowItem> list) {
//        this.context = context;
//        this.rowItems = list;
//    }
//
//    public int getCount() {
//        return this.rowItems.size();
//    }
//
//    public Object getItem(int i) {
//        return this.rowItems.get(i);
//    }
//
//    public long getItemId(int i) {
//        return (long) this.rowItems.indexOf(getItem(i));
//    }
//
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        view = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.ws_app_list_item, null);
//        ViewHolder viewHolder = new ViewHolder();
//        viewHolder.member_name = (TextView) view.findViewById(R.id.member_name);
//        viewHolder.profile_pic = (ImageView) view.findViewById(R.id.profile_pic);
//        viewHolder.status = (TextView) view.findViewById(R.id.status);
//        viewHolder.contactType = (TextView) view.findViewById(R.id.contact_type);
//        WsRowItem wsRowItem = (WsRowItem) this.rowItems.get(i);
//        viewHolder.profile_pic.setImageResource(wsRowItem.getProfile_pic_id());
//        viewHolder.member_name.setText(wsRowItem.getMember_name());
//        viewHolder.status.setText(wsRowItem.getStatus());
//        viewHolder.contactType.setText(wsRowItem.getContactType());
//        view.setTag(viewHolder);
//        return view;
//    }
//}
