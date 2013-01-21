package pku.sigroup.chisha;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pku.shengbin.utils.MessageBox;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class ChoiceListActivity extends ListActivity {
	private List<String> mChoiceList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choice_list);

		this.initChoiceList();
		
		ListView listView = (ListView) findViewById(android.R.id.list);        
        listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
        	public void onCreateContextMenu(ContextMenu menu, View v,
	        	ContextMenuInfo menuInfo) {
		        	menu.setHeaderTitle("操作");
		        	menu.add(0,0,0,"删除该项");
	        	}
        	}
        );
        
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		final String choice = mChoiceList.get(info.position);
		int id = item.getItemId();
		if (id == 0) {
			new AlertDialog.Builder(this)
        	.setMessage("将删除该项，确定吗？")
        	.setPositiveButton("是", 
        		new DialogInterface.OnClickListener() {
	        		public void onClick(DialogInterface arg0, int arg1) {
	        			if (mChoiceList.size() <= 1) {
	    					Toast.makeText(ChoiceListActivity.this, "至少保留一个吃饭的地儿吧！先添加一个别的再删除最后一个。", Toast.LENGTH_LONG).show();
	    					return;
	        			}
	        			mChoiceList.remove(choice);
	        			updateChoiceList();
    					Toast.makeText(ChoiceListActivity.this, "删除成功!", Toast.LENGTH_SHORT).show();
	        		}
        		}
        	)
        	.setNegativeButton("否", 
        		new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
					}
        		}
        	)
        	.show();
		}
		
		return super.onContextItemSelected(item);
	}

	protected void initChoiceList() {
		mChoiceList = ChoiceManager.getChoiceList();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mChoiceList);
		this.setListAdapter(adapter);
    }
	
	public void updateChoiceList() {
		//mChoiceList = QNchoiceManager.getChoiceList();
		((ArrayAdapter<?>)this.getListAdapter()).notifyDataSetChanged();
	}
	
	@Override
	protected void onResume() {
		updateChoiceList();
		super.onResume();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	}
	
	// Menu item Ids
    public static final int MENU_ADDCHOICE = Menu.FIRST;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu); 
		menu.add(0, MENU_ADDCHOICE, 0, "添加选择");

        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
	        
	        case MENU_ADDCHOICE:
	        {
        		final List<String> autoNames = new ArrayList<String>();
	        	autoNames.add("学");
	        	final AutoCompleteTextView nameEdit = new AutoCompleteTextView(this);
	        	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	                    android.R.layout.simple_dropdown_item_1line, autoNames.toArray(new String[0]));
	        	nameEdit.setAdapter(adapter);
	        	nameEdit.setThreshold(1);

	        	DialogInterface.OnClickListener ok_listener = new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						String input = nameEdit.getText().toString();
						if (input.equals("")) {
							MessageBox.show(ChoiceListActivity.this, "提示", "请输入内容!");
							return ;
						}
						
						try{
							mChoiceList.add(input);
							Toast.makeText(ChoiceListActivity.this, "添加成功!", Toast.LENGTH_SHORT).show();
							ChoiceListActivity.this.updateChoiceList();
						}catch(Exception e) {
							MessageBox.show(ChoiceListActivity.this, "出错了", "添加出现异常:"+ e.getMessage());
						}
					}
	        	};

	        	new AlertDialog.Builder(this)
	        	.setTitle("输入选项:")
	        	.setIcon(android.R.drawable.ic_input_get)
	        	.setView(nameEdit)
	        	.setPositiveButton("确定", ok_listener)
	        	.setNeutralButton("其他方式选择", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				})
	        	.setNegativeButton("取消", null)
	        	.show();
	        	break;	        	
	        }
	        
	        default:
	        	break;
        }
       
        return super.onOptionsItemSelected(item);
    }
	
	
}
