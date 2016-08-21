package com.tencent.streamshare.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.streamshare.Model.User;
import com.tencent.streamshare.R;
import com.tencent.streamshare.Utils.Util;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/21.
 */
public class UserListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private ArrayList<User> mData = new ArrayList<User>(); //UI
	private Context mContext;

	public UserListAdapter(Context context, ArrayList<User> data){
		this.mContext=context;
		this.mData =  data;
	}

	public void updateList(ArrayList<User> data){
		this.mData =  data;
		notifyDataSetChanged();
	}

	static class ViewHolder {
		TextView name;
		TextView count;
		SimpleDraweeView mPhoto;

	}

	@Override
	public User getItem(int position) {
		return (com.tencent.streamshare.Utils.Util.isEmpty(mData) || position <0 ||  position >= mData.size())?null:mData.get(position);
	}

	@Override
	public int getCount() {
		return Util.isEmpty(mData)?0:mData.size();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}





	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder  =null;

		inflater = LayoutInflater.from(mContext);
		if(holder == null ){
			convertView = inflater.inflate(R.layout.user_item_view, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.tv_user_name);
			holder.mPhoto = (SimpleDraweeView) convertView.findViewById(R.id.user_image);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		final User mUser = (User)getItem(position);
		holder.name.setText(mUser.getmNickName());
		if(mUser.getmHeadImagUrl()!=null){
			Uri uri = Uri.parse(mUser.getmHeadImagUrl());
			holder.mPhoto.setImageURI(uri);
		}





		return convertView;

	}


}
