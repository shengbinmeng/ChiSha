package pku.sigroup.chisha;

import java.util.Arrays;
import java.util.List;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import pku.shengbin.utils.MessageBox;
import pku.tangkai.utils.ShakeListener;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ChooseActivity extends SherlockFragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FragmentManager fm = getSupportFragmentManager();

        // Create the fragment and add it as our sole content.
        if (fm.findFragmentById(android.R.id.content) == null) {
            ChooseFragment choose = new ChooseFragment();
            fm.beginTransaction().add(android.R.id.content, choose).commit();
        }		
	}
	
	
	public static class ChooseFragment extends SherlockFragment {
		private List<String> mChoiceList;
		private ShakeListener mShaker;
		String[] canteen;
		TextView text;
		int last;
		
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        		Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.choose, container, false);
    		text = (TextView) v.findViewById(R.id.choose);
    		mChoiceList = ChoiceManager.getChoiceList();
    		// add some default choices if empty (only first time)
    		if (mChoiceList.isEmpty()) {
    			String[] canteens = this.getResources().getStringArray(R.array.canteen);
    			mChoiceList.addAll(Arrays.asList(canteens));
    		}
    		
    		canteen = mChoiceList.toArray(new String[0]);
    		
    		final Vibrator vibe = (Vibrator)this.getActivity().getSystemService(Context.VIBRATOR_SERVICE);
    		try {
	    	    mShaker = new ShakeListener(this.getActivity());
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
    			MessageBox.show(this.getActivity(), "Sorry", e.getMessage());
    		}
    	        		
            return v;
        }
        
        @Override
		public void onResume() {
    		super.onResume();
    		text.setText(R.string.welcome);
    		if (mShaker != null)
    			mShaker.onResume();
    	}
        

		@Override
		public void onPause() {
    		if (mShaker != null)
    			mShaker.onPause();
    	    super.onPause();
    	}

    	@Override
		public void onStop() {
    		if (mShaker != null)
    			mShaker.onStop();
        	super.onStop();
        }
       
    }
	
}
