package com.andreborud.fadingtabs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class CustomTabView extends RelativeLayout {
	
	AlphaTextView text1;
	AlphaImageView icon1;
	int alpha;
	
	public CustomTabView(Context context, String text, Drawable icon) {
        super(context);
        init(context, text, icon);
    }

    public CustomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, null, null);     
    }

    public CustomTabView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);    
        init(context, null, null);
    }

    private void init(Context context, String text, Drawable icon) {
        LayoutInflater inflater = (LayoutInflater)  getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.tab_view, this, true);
        
        text1 = (AlphaTextView) findViewById(R.id.title);
	    text1.setText(text);
	    
	    icon1 = (AlphaImageView) findViewById(R.id.icon);
	    icon1.setImageDrawable(icon);
	    
	    setTheAlpha(128);
    }
	
	public void setTheAlpha(int aleph) {
		this.alpha = aleph;
		if (alpha < 128) alpha = 128;
		if (alpha > 255) alpha = 255;
		text1.onSetAlpha(alpha);
		icon1.onSetAlpha(alpha);
	}
	
	public int getTheAlpha() {
		return alpha;
	}
}
