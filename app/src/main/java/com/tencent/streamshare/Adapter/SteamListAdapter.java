package com.tencent.streamshare.Adapter;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ihongqiqu.util.StringUtils;
import com.sdsmdg.tastytoast.TastyToast;
import com.tencent.streamshare.Activity.PlayerActivity;
import com.tencent.streamshare.Model.StreamInfo;

import java.util.ArrayList;

import com.tencent.streamshare.Model.User;
import com.tencent.streamshare.R;
import com.tencent.streamshare.Utils.Util;

/**
 * Created by Administrator on 2016/8/21.
 */
public class SteamListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private ArrayList<StreamInfo> mData = new ArrayList<StreamInfo>(); //UI
	private Context mContext;

	public SteamListAdapter(Context context, ArrayList<StreamInfo> data){
		this.mContext=context;
		this.mData =  data;
	}



	static class ViewHolder {
		TextView name;
		TextView count;
		TextView time;
		View steamView;
		SimpleDraweeView mPhoto;

	}

	@Override
	public StreamInfo getItem(int position) {
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


	public void updateList(ArrayList<StreamInfo> data){
		this.mData =  data;
		notifyDataSetChanged();
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder  =null;

		inflater = LayoutInflater.from(mContext);
		if(holder == null ){
			convertView = inflater.inflate(R.layout.steam_item_view, null);
			holder = new ViewHolder();
			holder.steamView = convertView.findViewById(R.id.steam_layout);
			holder.name = (TextView) convertView.findViewById(R.id.tv_steam_name);
			holder.count = (TextView) convertView.findViewById(R.id.tv_stem_count);
			holder.time = (TextView) convertView.findViewById(R.id.tv_steam_time);
			holder.mPhoto = (SimpleDraweeView) convertView.findViewById(R.id.steam_image);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		final StreamInfo mStreamInfo = (StreamInfo)getItem(position);
		holder.name.setText(mStreamInfo.getmName());
		holder.count.setText(""+mStreamInfo.getmVIewCount());
		holder.time.setText(Util.unix2dateFormat(mStreamInfo.getmTime()));
		if(mStreamInfo.getmImgUrl()!=null){
			Uri uri = Uri.parse(mStreamInfo.getmImgUrl());
			holder.mPhoto.setImageURI(uri);
		}
		holder.steamView.setOnClickListener(new View.OnClickListener() {
												@Override
												public void onClick(View view) {
													if(mStreamInfo.ismHasRight()&&!User.getInstance().ismIsVip()) {
														TastyToast.makeText(mContext, "加入VIP会员，即可观看VIP专享直播", TastyToast.LENGTH_LONG, TastyToast.ERROR);
													} else {
														if (!StringUtils.isEmpty(mStreamInfo.getmUrl())) {
															User.getInstance().setmCurrentStream(mStreamInfo);
															Intent intent = new Intent();
															intent.setClass(mContext, PlayerActivity.class);
															intent.putExtra(PlayerActivity.STREAM_URL_TAG, mStreamInfo.getmUrl());
															mContext.startActivity(intent);
														}
													}
												}
											}
		);
		return convertView;

	}


}
