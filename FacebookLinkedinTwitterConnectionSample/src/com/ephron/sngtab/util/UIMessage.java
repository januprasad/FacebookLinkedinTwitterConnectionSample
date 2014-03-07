package com.ephron.sngtab.util;

import com.ephron.sngtab.R;
import com.ephron.sngtab.base.JenuineActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class UIMessage {
    private Context context;

	public UIMessage(Context context) {
    	this.context=context;
		// TODO Auto-generated constructor stub
	}

	public void showAlertDialog(final Activity activity, String title, String message,
            Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
 
        if(status != null)
            // Setting alert dialog icon
            alertDialog.setIcon((status) ? R.drawable.logo : R.drawable.logo);
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	activity.finish();
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
    }

	public void showProgressLoading() {
		// TODO Auto-generated method stub
		
		final ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setCancelable(false);
		progressDialog.setMessage("Loading....");
		progressDialog.show();

		Thread mThread = new Thread() {
			@Override
			public void run() {
				try {
					synchronized (this) {
						// Wait given period of time or exit on touch
						wait(5000);

					}
				} catch (InterruptedException ex) {
				}

				// finish();
				progressDialog.dismiss();

			}
		};
		mThread.start();
	}

	public static void toast(JenuineActivity activity, String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
	}
	public static void toast(Context context, String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
}