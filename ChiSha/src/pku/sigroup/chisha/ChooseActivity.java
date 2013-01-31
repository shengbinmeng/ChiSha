package pku.sigroup.chisha;

import java.util.Arrays;
import java.util.List;

import pku.shengbin.utils.MessageBox;
import pku.tangkai.utils.ShakeListener;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;

public class ChooseActivity extends Activity {
	private List<String> mChoiceList;
	private ShakeListener mShaker;
	String[] canteen;
	TextView text;
	int last;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose); 
		mChoiceList = ChoiceManager.getChoiceList();
		// add some default choices if empty (only first time)
		if (mChoiceList.isEmpty()) {
			String[] canteens = this.getResources().getStringArray(R.array.canteen);
			mChoiceList.addAll(Arrays.asList(canteens));
		}
		
		text = (TextView) findViewById(R.id.choose);
		canteen = mChoiceList.toArray(new String[0]);
		
		final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		
		//TODO: if you throw an exception, you should catch it somewhere, or the whole app collapses
		try {
		    mShaker = new ShakeListener(this);
		    mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
			    public void onShake() {
			    	int r;
			    	do {
			    		r =  (int)(Math.random() * canteen.length);
			    	} while(r==last);
			    	last = r;
			    	text.setText(canteen[r]);
			    	vibe.vibrate(100);
			    }
		    });
		} catch (Exception e) {
			MessageBox.show(this, "Sorry", e.getMessage());
		}
	    
	}
	
	@Override
	protected void onResume() {
		text.setText(R.string.welcome);
		if (mShaker != null) 
			mShaker.onResume();
		super.onResume();
	}
	
	@Override
	protected void onPause() {
	    if (mShaker != null) 
	    	mShaker.onPause();
	    super.onPause();
	}

	@Override
    protected void onStop(){
		if (mShaker != null) 
			mShaker.onStop();
    	super.onStop();
    }
	
}
