package com.fbw.recyclerviewproject.emoji;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fbw.recyclerviewproject.R;

public class MsgAdapter extends BaseAdapter{
	
	private List<CharSequence> messages = null;
	
	private LayoutInflater inflater = null;
	
	public MsgAdapter(Context context, List<CharSequence> msg) {
		this.messages = msg;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return messages == null ? 0 : messages.size();
	}

	@Override
	public Object getItem(int position) {
		return messages.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		ViewHolder holder = null;
		if(v == null) {
			holder = new ViewHolder();
			v = inflater.inflate(R.layout.layout_message, parent, false);
			holder.txMsg = (TextView)v.findViewById(R.id.tx_msg);
			v.setTag(holder);
		} else {
			holder = (ViewHolder)v.getTag();
		}
		holder.txMsg.setText(messages.get(position));
		return v;
	}

	public static class ViewHolder {
		TextView txMsg;
	}
}
