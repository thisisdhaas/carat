package edu.berkeley.cs.amplab.carat.android.lists;

import java.util.List;

import edu.berkeley.cs.amplab.carat.android.R;
import edu.berkeley.cs.amplab.carat.android.CaratApplication;
import edu.berkeley.cs.amplab.carat.thrift.ProcessInfo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProcessInfoAdapter extends BaseAdapter {
	private static List<ProcessInfo> searchArrayList;

	private LayoutInflater mInflater;
	
	private Context c = null;

	public ProcessInfoAdapter(Context context,
			List<ProcessInfo> results) {
	    this.c = context;
		searchArrayList = results;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return searchArrayList.size();
	}

	public Object getItem(int position) {
		return searchArrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.process, null);
			holder = new ViewHolder();
			holder.appIcon = (ImageView) convertView.findViewById(R.id.app_icon);
			holder.txtName = (TextView) convertView
					.findViewById(R.id.processName);
			holder.txtBenefit = (TextView) convertView
					.findViewById(R.id.processPriority);
			// holder.moreInfo = (ImageView)
			// convertView.findViewById(R.id.moreinfo);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (searchArrayList == null || position < 0 || position >= searchArrayList.size())
			return convertView;
		ProcessInfo x = searchArrayList.get(position);
		
		if (x == null)
            return convertView;
		
		holder.appIcon.setImageDrawable(CaratApplication.iconForApp(c, x.getPName()));
		holder.txtName.setText(CaratApplication.labelForApp(c, x.getPName()));
		holder.txtBenefit.setText(CaratApplication.translatedPriority(x.getImportance()));
		// holder.moreInfo...

		return convertView;
	}

	static class ViewHolder {
		ImageView appIcon;
		TextView txtName;
		TextView txtBenefit;
		// ImageView moreInfo;
	}
}
