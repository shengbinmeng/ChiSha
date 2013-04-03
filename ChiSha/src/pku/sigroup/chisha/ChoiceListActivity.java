package pku.sigroup.chisha;

import pku.shengbin.utils.MessageBox;
import java.util.ArrayList;
import java.util.List;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class ChoiceListActivity extends SherlockFragmentActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		FragmentManager fm = getSupportFragmentManager();
        // Create the list fragment and add it as our sole content.
        if (fm.findFragmentById(android.R.id.content) == null) {
            ChoiceListFragment list = new ChoiceListFragment();
            fm.beginTransaction().add(android.R.id.content, list).commit();
        }  
	}
	
	
	
    public static class ChoiceListFragment extends SherlockListFragment {
    	private List<String> mChoiceList;

		@Override public void onActivityCreated(Bundle savedInstanceState) {
		    super.onActivityCreated(savedInstanceState);
		
		    // Give some text to display if there is no data.  In a real
		    // application this would come from a resource.
		    setEmptyText("No Items");
		    // We have a menu item to show in action bar.
		    setHasOptionsMenu(true); // if not set this, the fragment will not receive onCreateOptionsMenu


			this.initChoiceList();
			
			ListView listView = this.getListView();        
	        listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
	        	public void onCreateContextMenu(ContextMenu menu, View v,
		        	ContextMenuInfo menuInfo) {
			        	menu.setHeaderTitle("操作");
			        	menu.add(0,0,0,"删除该项");
			        	menu.add(0,1,1,"编辑");
			        	menu.add(0,2,2,"查看详情");

		        	}
	        	}
	        );
		}
		
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
		}


		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			return super.onCreateView(inflater, container, savedInstanceState);
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
		public void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
		}


		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
		}


		@Override
		public void onStop() {
			// TODO Auto-generated method stub
			super.onStop();
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
		public boolean onContextItemSelected(android.view.MenuItem item) {
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
			final String choice = mChoiceList.get(info.position);
			int id = item.getItemId();
			if (id == 0) {
				new AlertDialog.Builder(this.getActivity())
	        	.setMessage("将删除该项，确定吗？")
	        	.setPositiveButton("是", 
	        		new DialogInterface.OnClickListener() {
		        		public void onClick(DialogInterface arg0, int arg1) {
		        			if (mChoiceList.size() <= 1) {
		    					Toast.makeText(ChoiceListFragment.this.getActivity(), "至少保留一个吃饭的地儿吧！先添加一个别的再删除最后一个。", Toast.LENGTH_LONG).show();
		    					return;
		        			}
		        			mChoiceList.remove(choice);
		        			updateChoiceList();
	    					Toast.makeText(ChoiceListFragment.this.getActivity(), "删除成功!", Toast.LENGTH_SHORT).show();
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
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, mChoiceList);
			this.setListAdapter(adapter);
	    }
		
		public void updateChoiceList() {
			ArrayAdapter<?> adapter = ((ArrayAdapter<?>)this.getListAdapter());
			adapter.notifyDataSetChanged();
		}
		
		@Override
		public void onResume() {
			super.onResume();
			this.updateChoiceList();
		}

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			Toast.makeText(ChoiceListFragment.this.getActivity(), "长按列表项可以开启上下文菜单", Toast.LENGTH_SHORT).show();
		}
		
		
		
		// Menu item Ids
	    public static final int MENU_ADDCHOICE = Menu.FIRST;

	    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    	MenuItem addItem = menu.add(0, MENU_ADDCHOICE, 0, "添加选择");
	    	addItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
	    	SubMenu sub = menu.addSubMenu("更多...");
	        sub.add(0, R.style.Theme_Sherlock, 0, "One");
	        sub.add(0, R.style.Theme_Sherlock_Light, 0, "Two");
	        sub.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	    }
	    
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
		        case MENU_ADDCHOICE:
		        {
	        		final List<String> autoNames = new ArrayList<String>();
		        	autoNames.add("学");
		        	final AutoCompleteTextView nameEdit = new AutoCompleteTextView(this.getActivity());
		        	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
		                    android.R.layout.simple_dropdown_item_1line, autoNames.toArray(new String[0]));
		        	nameEdit.setAdapter(adapter);
		        	nameEdit.setThreshold(1);

		        	DialogInterface.OnClickListener ok_listener = new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
							String input = nameEdit.getText().toString();
							if (input.equals("")) {
								MessageBox.show(ChoiceListFragment.this.getActivity(), "提示", "内容为空!");
								return ;
							}
							
							try{
								mChoiceList.add(input);
								Toast.makeText(ChoiceListFragment.this.getActivity(), "添加成功!", Toast.LENGTH_SHORT).show();
								ChoiceListFragment.this.updateChoiceList();
							}catch(Exception e) {
								MessageBox.show(ChoiceListFragment.this.getActivity(), "出错了", "添加出现异常:"+ e.getMessage());
							}
						}
		        	};

		        	new AlertDialog.Builder(this.getActivity())
		        	.setTitle("输入选项:")
		        	.setIcon(android.R.drawable.ic_input_get)
		        	.setView(nameEdit)
		        	.setPositiveButton("确定", ok_listener)
		        	.setNeutralButton("导入...", new DialogInterface.OnClickListener() {
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

    
    
}
