/**
 * 
 */
package com.fegmobile.monopoly.manager;

import java.util.concurrent.TimeUnit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout.LayoutParams;

/**
 * @author felipealmeida
 *
 */
public class UtilManager
{
	public static void dialog(Context ctx,String title, String text)
	{
		new AlertDialog.Builder(ctx)
	    .setTitle(title)
	    .setMessage(text)
	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	        }
	     })
	     .show();
	}
	
	public static void expand(final View v)
	{
		v.measure(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		final int targtetHeight = v.getMeasuredHeight();
		
		v.getLayoutParams().height = 0;
		v.setVisibility(View.VISIBLE);
		Animation a = new Animation()
		{
			@Override
			protected void applyTransformation(float interpolatedTime, Transformation t)
			{
				v.getLayoutParams().height = interpolatedTime == 1 ? LayoutParams.WRAP_CONTENT : (int) (targtetHeight * interpolatedTime);
				v.requestLayout();
			}
			
			@Override
			public boolean willChangeBounds()
			{
				return true;
			}
		};
		
		a.setDuration(TimeUnit.SECONDS.toMillis(1));
		v.startAnimation(a);
	}
	
	public static void collapse(final View v)
	{
		final int initialHeight = v.getMeasuredHeight();
		
		Animation a = new Animation()
		{
			@Override
			protected void applyTransformation(float interpolatedTime, Transformation t)
			{
				if (interpolatedTime == 1) {
					v.setVisibility(View.GONE);
				} else {
					v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
					v.requestLayout();
				}
			}
			
			@Override
			public boolean willChangeBounds()
			{
				return true;
			}
		};
		
		a.setDuration(TimeUnit.SECONDS.toMillis(1));
		v.startAnimation(a);
	}
}
