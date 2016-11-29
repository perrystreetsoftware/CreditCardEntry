package com.devmarvel.creditcardentry.fields;

import android.content.Context;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;

import com.devmarvel.creditcardentry.R;

/**
 * Created by Perry Street Software Inc. on Nov 28, 2016.
 *
 * @author Steve Tsourounis {steve@scruff.com}
 */

public class NameText extends CreditEntryFieldBase {

	private String mHelperText;

	public NameText(Context context) {
		super(context);
	}

	public NameText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NameText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	void init(AttributeSet attrs) {
		super.init(attrs);
		this.setHint(R.string.NameHelp);
		this.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
		this.setGravity(Gravity.START);
	}

	@Override
	public boolean isValid() {
		return !TextUtils.isEmpty(getText());
	}

	@Override
	public void setHelperText(String helperText) {
		this.mHelperText = helperText;
	}

	@Override
	public String getHelperText() {
		return (mHelperText != null ? mHelperText : context.getString(R.string.NameHelp));
	}

	@Override
	public void formatAndSetText(String updatedString) {
		this.removeTextChangedListener(this);
		this.setText(updatedString);
		this.addTextChangedListener(this);
	}
}
