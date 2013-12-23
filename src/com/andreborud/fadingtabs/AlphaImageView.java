package com.andreborud.fadingtabs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AlphaImageView extends ImageView {

	public AlphaImageView(Context context) {
		super(context);
	}

	public AlphaImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AlphaImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onSetAlpha(int alpha) {
		setImageAlpha(alpha);
		return true;
	}
}
