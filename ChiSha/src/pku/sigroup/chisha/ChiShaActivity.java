package pku.sigroup.chisha;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class ChiShaActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		Resources res = this.getResources(); // Resource object to get Drawables
	    TabHost tabHost = this.getTabHost();  // The activity TabHost
	    TabSpec spec;
	    Intent intent; 
 
	    intent = new Intent(this, ChooseActivity.class);;
	    spec = tabHost.newTabSpec("choose_tab")
	    .setIndicator("吃啥", res.getDrawable(android.R.drawable.ic_popup_reminder))
	    .setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent(this, ChoiceListActivity.class);
	    spec = tabHost.newTabSpec("choice_tab")
	    .setIndicator("有啥", res.getDrawable(android.R.drawable.ic_popup_disk_full))
	    .setContent(intent);
	    tabHost.addTab(spec);
	    
	    tabHost.setCurrentTab(0);
	    
	    ChoiceManager.appContext = this.getApplicationContext();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public void finish() {
		ChoiceManager.saveChoiceList();
		super.finish();
	}

}
