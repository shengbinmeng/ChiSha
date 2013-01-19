package pku.sigroup.chisha;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ChooseActivity extends Activity {
	private List<String> mChoiceList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose); 
		mChoiceList = ChoiceManager.getChoiceList();
		//TODO:
	}
	
	@Override
	protected void onResume() {
		TextView tv = (TextView)this.findViewById(R.id.choose);
		tv.setText(tv.getText() + mChoiceList.toString());
		super.onResume();
	}

}
