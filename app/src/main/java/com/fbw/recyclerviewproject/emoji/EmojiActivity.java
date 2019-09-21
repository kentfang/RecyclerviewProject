package com.fbw.recyclerviewproject.emoji;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.fbw.recyclerviewproject.R;

public class EmojiActivity extends Activity implements OnItemClickListener {

	private ListView lvMsg = null;

	private EditText etInput = null;

	private GridView gvEmotions = null;

	private List<Emotion> emotions = null;

	private List<CharSequence> messages = new ArrayList<CharSequence>();

	private MsgAdapter adapter = null;
	
	private InputMethodManager inputManager = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emoji_activity_main);
		inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		findViews();
		initViews();
		togglekeyboard(false);
		toggleEmotionList(false);
	}

	private void findViews() {
		lvMsg = (ListView) findViewById(R.id.lv_msg);
		etInput = (EditText) findViewById(R.id.et_input);
		gvEmotions = (GridView) findViewById(R.id.gv_emotions);
		gvEmotions.setOnItemClickListener(this);
	}

	private void initViews() {
		adapter = new MsgAdapter(this, messages);
		lvMsg.setAdapter(adapter);
		try {
			InputStream inputStream = this.getResources().getAssets()
					.open("emotions.xml");
			emotions = XmlUtil.getEmotions(inputStream);

			ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
			for (int i = 0; i < emotions.size(); i++) {
				Emotion emotion = emotions.get(i);
				Log.d("emoji","getName:"+emotion.getName());
				Log.d("emoji","getCode:"+emotion.getCode());
				if (emotion != null) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					Field f = (Field) R.drawable.class.getDeclaredField(emotion
							.getName());
					int j = f.getInt(R.drawable.class);
					map.put("itemImage", j);
					items.add(map);
				}
			}
			items.remove(items.size() - 1);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", R.drawable.img_delete);
			items.add(map);

			SimpleAdapter saImageItems = new SimpleAdapter(this, items,
					R.layout.emotion_item, new String[] { "itemImage" },
					new int[] { R.id.iv_emotion });
			gvEmotions.setSelector(new ColorDrawable(Color.TRANSPARENT));
			gvEmotions.setAdapter(saImageItems);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onButtonClick(View view) {
		int id = view.getId();
		switch (id) {
		case R.id.btn_emotions:
			togglekeyboard(false);
			toggleEmotionList(gvEmotions.getVisibility() == View.GONE);
			break;
		case R.id.btn_send:
			String receiveStr = etInput.getText().toString();
			if(receiveStr == null || receiveStr.equals("")) {
				return;
			}
			Log.d("fbw","receiveStr:"+receiveStr);
			SpannableString spannableString = ExpressionUtil
					.getExpressionString(this, receiveStr, (int)etInput.getTextSize());
			messages.add(spannableString);
			adapter.notifyDataSetChanged();
			lvMsg.setSelection(messages.size() - 1);
			etInput.setText(null);
			break;
		case R.id.et_input:
			togglekeyboard(etInput.getTag() == null);
			toggleEmotionList(false);
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if(position == emotions.size() - 1) {
			int cursorPosition = etInput.getSelectionStart();
			if(cursorPosition > 0) {
				String str = etInput.getText().subSequence(0, cursorPosition).toString();
				int lastIndex = str.lastIndexOf("f");
				if(lastIndex >= 0 && lastIndex < cursorPosition) {
					str = str.substring(lastIndex, cursorPosition);
					boolean match = ExpressionUtil.matchEmotion(str);
					if(match) {
						etInput.getEditableText().delete(lastIndex, cursorPosition);
					} else {
						etInput.getEditableText().delete(cursorPosition - 1, cursorPosition);
					}
				} else {
					etInput.getEditableText().delete(cursorPosition - 1, cursorPosition);
				}
			}
		} else {
			Emotion emotion = emotions.get(position);
			int cursor = etInput.getSelectionStart();
			Field f;
			try {
				f = (Field) R.drawable.class.getDeclaredField(emotion.getName());
				int j = f.getInt(R.drawable.class);
				Drawable d = getResources().getDrawable(j);
				int textSize = (int)etInput.getTextSize();
				d.setBounds(0, 0, textSize, textSize);
				
				String str = null;
				int pos = position + 1;
				if (pos < 10) {
					str = "f00" + pos;
				} else if (pos < 100) {
					str = "f0" + pos;
				} else {
					str = "f" + pos;
				}
				SpannableString ss = new SpannableString(str);
				ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
				ss.setSpan(span, 0, str.length(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				etInput.getText().insert(cursor, ss);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void togglekeyboard(boolean show) {
		if(show) {
			inputManager.showSoftInput(etInput, 0);
			etInput.setTag(1);
		} else {
			inputManager.hideSoftInputFromWindow(etInput.getWindowToken(), 0);
			etInput.setTag(null);
		}
	}

	private void toggleEmotionList(boolean show) {
		gvEmotions.setVisibility(show ? View.VISIBLE : View.GONE);
	}
}