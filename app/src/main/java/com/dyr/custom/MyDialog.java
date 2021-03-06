package com.dyr.custom;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hldj.hmyg.R;

public class MyDialog {
	Context context;
	Dialogcallback dialogcallback;
	Dialog dialog;
	Button sure;
	Button cancle;
	TextView textView;
	EditText editText;

	/**
	 * init the dialog
	 * 
	 * @return
	 */
	public MyDialog(Context con) {
		this.context = con;
		dialog = new Dialog(context, R.style.alert_dialog);
//		dialog.getWindow().setBackgroundDrawableResource(
//				R.drawable.material_dialog_window);
		dialog.setContentView(R.layout.dialog_edit);
		textView = (TextView) dialog.findViewById(R.id.textview);
		sure = (Button) dialog.findViewById(R.id.button1);
		cancle = (Button) dialog.findViewById(R.id.button0);
		editText = (EditText) dialog.findViewById(R.id.editText1);
		sure.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogcallback.dialogdo(editText.getText().toString());
				dismiss();
			}
		});
		cancle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}

	/**
	 * 设定一个interfack接口,使mydialog可以處理activity定義的事情
	 * 
	 * @author sfshine
	 * 
	 */
	public interface Dialogcallback {
		public void dialogdo(String string);
	}

	public void setDialogCallback(Dialogcallback dialogcallback) {
		this.dialogcallback = dialogcallback;
	}

	/**
	 * @category Set The Content of the TextView
	 * */
	public void setContent(String content) {
		textView.setText(content);
	}

	/**
	 * Get the Text of the EditText
	 * */
	public String getText() {
		return editText.getText().toString();
	}

	public void show() {
		dialog.show();
	}

	public void hide() {
		dialog.hide();
	}

	public void dismiss() {
		dialog.dismiss();
	}
}