package rl_rpg.activity.adapter;

import java.util.List;

import rl_rpg.activity.R;
import rl_rpg.model.ChallengeListModel;
import rl_rpg.utils.DialogBuilder;
import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ChallengeListAdapter extends ListAdapter {
	@SuppressWarnings("rawtypes")
	public ChallengeListAdapter(Activity a, List d, Resources res) {
		super(a, d, res, R.layout.challenge_item);
	}

	@Override
	OnClickListener getOnRowClickListener() {
		return rowOnClickListener;
	}

	@Override
	IViewHolder createViewHolder(View vi) {
		return new ViewHolder(vi, this);
	}

	public static class ViewHolder implements IViewHolder {
		ViewHolder(View viev, ListAdapter parent) {
			challengeName = (TextView) viev.findViewById(R.id.challengeName);
			challengeDescrip = (TextView) viev
					.findViewById(R.id.challengeDesription);
			image = (ImageView) viev.findViewById(R.id.challengeImg);
			challengeStart = (Button) viev.findViewById(R.id.challengeStart);
			this.parent = parent;
		}

		TextView challengeName;
		TextView challengeDescrip;
		// public TextView textWide;
		ImageView image;
		Button challengeStart;
		ListAdapter parent;
		int position;

		@Override
		public void clear() {
			challengeName.setText("No Data");
		}

		@Override
		public void set(Object model, int position) {
			this.position = position;
			ChallengeListModel tempValues = (ChallengeListModel) model;
			challengeName.setText(tempValues.getChallengeName());
			challengeDescrip.setText(tempValues.getChallengeDescr());
			challengeStart.setTag(Integer.valueOf(position));
			challengeStart.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Integer position = (Integer) v.getTag();
					ChallengeListModel arch = (ChallengeListModel) parent.data
							.get(position);
					Toast.makeText(parent.inflater.getContext(),
							"Zacz¹³eœ " + arch.getChallengeName(),
							Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	OnClickListener rowOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View view) {
			ViewHolder h = (ViewHolder) view.getTag();

			ChallengeListModel challenge = (ChallengeListModel) data
					.get(h.position);
			// To jest w³asnie sytuacja kiedy potrzebujemy w dialogu tylko
			// jednego
			// przycisku, który wraca do Activity
			DialogBuilder dialog = new DialogBuilder(activity,
					challenge.getChallengeName(),
					challenge.getChallengeDescr(), null, null, "OK", null);
			dialog.showDialog();
		}
	};
}
