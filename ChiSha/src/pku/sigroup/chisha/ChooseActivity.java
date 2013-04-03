package pku.sigroup.chisha;

import java.util.Arrays;
import java.util.List;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import pku.shengbin.utils.MessageBox;
import pku.tangkai.utils.ShakeListener;
import android.app.Activity;
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
		String[] canteens;
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
    			String[] canteenss = this.getResources().getStringArray(R.array.canteens);
    			mChoiceList.addAll(Arrays.asList(canteenss));
    		}
    		
    		canteens = mChoiceList.toArray(new String[0]);
    		
    		final Vibrator vibe = (Vibrator)this.getActivity().getSystemService(Context.VIBRATOR_SERVICE);
    		try {
	    	    mShaker = new ShakeListener(this.getActivity());
	    	    mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
	    		    public void onShake() {
	    		    	int r;
	    		    	do {
	    		    		r =  (int)(Math.random() * canteens.length);
	    		    	} while(r==last);
	    		    	last = r;
	    		    	text.setText(canteens[r]);
	    		    	vibe.vibrate(100);
	    		    }
	    	    });
    		} catch (Exception e) {
    			MessageBox.show(this.getActivity(), "Sorry", e.getMessage());
    		}
    	        		
            return v;
        }
        
        @Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
		}


		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
		}
			    	

		@Override
		public void onDestroyView() {
			// TODO Auto-generated method stub
			super.onDestroyView();
		}

		@Override
		public void onViewCreated(View view, Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onViewCreated(view, savedInstanceState);
		}

		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
		}
        
        @Override
		public void onAttach(Activity activity) {
			// TODO Auto-generated method stub
			super.onAttach(activity);
		}


		@Override
		public void onDetach() {
			// TODO Auto-generated method stub
			super.onDetach();
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
