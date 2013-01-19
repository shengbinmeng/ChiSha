package pku.sigroup.chisha;

import pku.tangkai.utils.ShakeListener;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;

public class ChooseActivity extends Activity {
	//private List<String> mChoiceList;
	private ShakeListener mShaker;
	String[] canteen;
	TextView text;
	int last;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose); 
		//mChoiceList = ChoiceManager.getChoiceList();
		text = (TextView) findViewById(R.id.choose);
		canteen = getResources().getStringArray(R.array.canteen);
		// TODO:
		final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		
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
	}
	
	@Override
	protected void onResume() {
		text.setText(R.string.welcome);
		mShaker.onResume();
		super.onResume();
	}
	
	@Override
	protected void onPause() {
	    mShaker.onPause();
	    super.onPause();
	}

	@Override
    protected void onStop(){
		mShaker.onStop();
    	super.onStop();
    }
	
}
