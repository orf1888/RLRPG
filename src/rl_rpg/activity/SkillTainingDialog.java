package rl_rpg.activity;

import rl_rpg.utils.DialogBuilder;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

public class SkillTainingDialog extends DialogBuilder {
	static EditText skillImproveValue;

	// Ostatni parametr jest dowolnym widgetem, np. button, textEdit, etc...
	public SkillTainingDialog(Context context, String title, String message,
			OnClickListener posButListener, OnClickListener negButListener,
			String posButtonText, String negButtonText, View view) {
		super(context, title, message, posButListener, negButListener,
				posButtonText, negButtonText);
		skillImproveValue = new EditText(context);
		skillImproveValue.setHint("Enter the value");
		setView(view);
	}

	public String getValueFromView() {
		return skillImproveValue.getText().toString();
	}

	public void showDialog() {
		super.showDialog();
	}
}
