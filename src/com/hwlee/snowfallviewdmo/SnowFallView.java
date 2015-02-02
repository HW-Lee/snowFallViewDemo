package com.hwlee.snowfallviewdmo;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.*;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class SnowFallView extends View implements OnTouchListener {

	private Context parentContext;
	private int snow_flake_count = 10;
	private final ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private int[][] coords;
	private final Drawable snow_flake;
	private boolean flag = true;

	public SnowFallView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		snow_flake = context.getResources().getDrawable(R.drawable.snowflake);
		snow_flake.setBounds(0, 0, snow_flake.getIntrinsicWidth(),
				snow_flake.getIntrinsicHeight());
		setOnTouchListener(this);
		parentContext = context;
	}

	public SnowFallView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		snow_flake = context.getResources().getDrawable(R.drawable.snowflake);
		snow_flake.setBounds(0, 0, snow_flake.getIntrinsicWidth(),
				snow_flake.getIntrinsicHeight());
		setOnTouchListener(this);
		parentContext = context;
	}

	public SnowFallView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		snow_flake = context.getResources().getDrawable(R.drawable.snowflake);
		snow_flake.setBounds(0, 0, snow_flake.getIntrinsicWidth(),
				snow_flake.getIntrinsicHeight());
		setOnTouchListener(this);
		parentContext = context;
	}

	@Override
	protected void onSizeChanged(int width, int height, int oldw, int oldh) {
		super.onSizeChanged(width, height, oldw, oldh);
		if (!flag) return;
		Random random = new Random();
		Interpolator interpolator = new LinearInterpolator();

		snow_flake_count = Math.max(width, height) / 20;
		coords = new int[snow_flake_count][];
		drawables.clear();
		for (int i = 0; i < snow_flake_count; i++) {
			Animation animation = new TranslateAnimation(0, height / 10
					- random.nextInt(height / 5), 0, height + 30);
			animation.setDuration(10 * height + random.nextInt(5 * height));
			animation.setRepeatCount(-1);
			animation.initialize(10, 10, 10, 10);
			animation.setInterpolator(interpolator);

			coords[i] = new int[] { random.nextInt(width - 30), -30 };

			drawables.add(new AnimateDrawable(snow_flake, animation));
			animation.setStartOffset(random.nextInt(20 * height));
			animation.startNow();
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (!flag) return;
		for (int i = 0; i < snow_flake_count; i++) {
			Drawable drawable = drawables.get(i);
			canvas.save();
			canvas.translate(coords[i][0], coords[i][1]);
			drawable.draw(canvas);
			canvas.restore();
		}
		invalidate();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
			case MotionEvent.ACTION_UP:
				RelativeLayout.LayoutParams params;
				if (flag) {
					params = (RelativeLayout.LayoutParams)getLayoutParams();
					params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
					params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
					int squareLength = parentContext.getResources().getDisplayMetrics().widthPixels;
					params.width = squareLength / 4;
					params.height = squareLength / 4;
				} else {
					params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				}
				flag = !flag;
				setLayoutParams(params);
				break;
		}
		return true;
	}

}
