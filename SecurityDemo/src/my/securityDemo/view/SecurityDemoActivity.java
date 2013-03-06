package my.securityDemo.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SecurityDemoActivity extends Activity {
	
	ListView listView;
	String TAG = "demo";
	MyReceiver receiver;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        listView = (ListView)findViewById(R.id.listView1);
        
        //ArrayAdapter<String> listItemAdapter = new  ArrayAdapter<String>(this, 0, getMenu());
        
        SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.vlist,
        		new String[]{"title","info","img"},
        		new int[]{R.id.title,R.id.info,R.id.img});
        
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				setTitle("�����"+position+"����Ŀ" + id);
				
				if(id == 0){
					Intent intent = new Intent();
					intent.setClass(SecurityDemoActivity.this, SecurityKeepActivity.class);
					startActivity(intent);
				}
				
				if(id == 1){
					Intent intent = new Intent();
					intent.setClass(SecurityDemoActivity.this, SettingActivity.class);
					startActivity(intent);
				}
				
			}
        	
        });
        //ע������� 
        receiver=new MyReceiver(); 
        IntentFilter filter=new IntentFilter(); 
        filter.addAction("android.intent.action.test"); 
        this.registerReceiver(receiver,filter);
        
        //��������
        this.startService(new Intent(this, SecurityLightService.class));
        
      
    }
    
    
    /*private List<String>getMenu(){
    	List<String> menu = new ArrayList<String>();
    	
    	menu.add("Securiy");
    	menu.add("Setting");
    	
    	return menu;
    }*/
    
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
 
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "����");
        map.put("info", "[�������]");
        map.put("img", R.drawable.new_x);
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("title", "����");
        map.put("info", "[��������]");
        map.put("img", R.drawable.new_x);
        list.add(map);
        
        return list;
    }
    
    public void OnDestroy(){
    	this.stopService(new Intent(this, SecurityLightService.class));
    	
    	//���ע������� 
    	this.unregisterReceiver(receiver); 
        
    }
    
    
    class MyReceiver extends BroadcastReceiver { 
        //�Զ���һ���㲥������ 
        @Override 
        public void onReceive(Context context, Intent intent) { 
          // TODO Auto-generated method stub 
          //System.out.println("OnReceiver"); 
          Bundle bundle=intent.getExtras(); 
          bundle.getInt("i"); 
          
          //������յ������� 
          //Log.v(TAG, "get count " + a);
        } 
        public MyReceiver(){ 
          System.out.println("MyReceiver"); 
          //���캯������һЩ��ʼ�����������������κ����� 
        }
		
        
      } 
}