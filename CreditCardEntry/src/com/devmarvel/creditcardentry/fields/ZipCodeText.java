package com.devmarvel.creditcardentry.fields;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;

import com.devmarvel.creditcardentry.R;

import java.util.Locale;

public class ZipCodeText extends CreditEntryFieldBase {

	private int maxChars;
	
    private String mHelperText;

	public ZipCodeText(Context context) {
		super(context);
		init();
	}

	public ZipCodeText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ZipCodeText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	void init() {
		super.init();
		maxChars = 10; // zipcodes are varying in length
		setMaxChars(maxChars);
		setHint("   ZIP   ");

		// zipcodes are not always numeric
		this.setInputType(InputType.TYPE_CLASS_TEXT);
	}

	@Override
	public void setHelperText(String helperText) {
		mHelperText = helperText;
	}

	@Override
	public String getHelperText() {
		return (mHelperText != null ? mHelperText : context.getString(R.string.ZipHelp));
	}

	@Override
    public void textChanged(CharSequence s, int start, int before, int end) {
        // Check if only digits (for US zip codes)
//        if (s.toString().matches("^\\d+$")) {
//            if (s.length() == maxChars) {
//                setValid(true);
//                delegate.onZipCodeValid();
//            } else {
//                setValid(false);
//            }
//
//            // For other countries like the UK, postal codes are alphanumeric
//            // and can be anything from 3 to max chars.
//        } else {
//            if (s.length() > 3) {
//                setValid(true);
//            }
//            if (s.length() == maxChars && maxChars > 0) {
//                delegate.onZipCodeValid();
//            } else {
//                setValid(false);
//            }
//        }

		// Global zipcode validation is too hard
		if (s.length() >= 5) {
			setValid(true);
			delegate.onZipCodeValid();
		} else {
			setValid(false);
		}
	}

	public void formatAndSetText(String text) {
		this.removeTextChangedListener(this);
		this.setText(text);
		this.addTextChangedListener(this);
	}

	public void setMaxChars(int maxChars){
		if (maxChars <= 0) {
			return;
		}
		this.maxChars = maxChars;
		setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxChars)});
	}
}
