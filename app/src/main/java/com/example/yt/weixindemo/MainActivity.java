package com.example.yt.weixindemo;

import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private android.support.v4.app.FragmentManager fManager;
    private android.support.v4.app.FragmentTransaction fTransaction;
    private Fragment fragment;
    private LinearLayout chat,friend;

    private DrawerLayout drawer_layout;
    private ListView list_left_drawer;
    private ArrayList<Item> menuLists;
    private MyAdapter myAdapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment=new ChatFragment();
        fManager=getSupportFragmentManager();
        fTransaction=fManager.beginTransaction();
        fTransaction.replace(R.id.fm_content,fragment);
        fTransaction.commit();
        ImageButton imageButton=(ImageButton)findViewById(R.id.add_menu);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(MainActivity.this,view);
                popupMenu.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.action_chat:
                                Toast.makeText(MainActivity.this,"选择了创建群聊",Toast.LENGTH_LONG).show();
                                break;
                            case R.id.action_add:
                                Toast.makeText(MainActivity.this,"选择了添加好友",Toast.LENGTH_LONG).show();
                                break;
                            case R.id.action_see:
                                Toast.makeText(MainActivity.this,"选择了扫一扫",Toast.LENGTH_LONG).show();
                                break;
                            case R.id.action_pay:
                                Toast.makeText(MainActivity.this,"选择了收付款",Toast.LENGTH_LONG).show();
                                break;
                            case R.id.action_help:
                                Toast.makeText(MainActivity.this,"选择了帮助与反馈",Toast.LENGTH_LONG).show();
                                break;
                                default:
                                    break;
                        }
                        return false;
                    }
                });
            }
        });

        ImageButton imageButton2=(ImageButton)findViewById(R.id.add_menu2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View myview= LayoutInflater.from(MainActivity.this).inflate(R.layout.popowindow,null);
                final PopupWindow mypopwindow=new PopupWindow(myview, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
                mypopwindow.setTouchable(true);
                mypopwindow.setOutsideTouchable(true);
                mypopwindow.showAsDropDown(view);
                chat=(LinearLayout)myview.findViewById(R.id.ll_chat);
                friend=(LinearLayout)myview.findViewById(R.id.ll_friend);
                chat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"点击了发起群聊",Toast.LENGTH_LONG).show();
                        mypopwindow.dismiss();
                    }
                });
                friend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"点击了添加好友",Toast.LENGTH_LONG).show();
                        mypopwindow.dismiss();
                    }
                });
            }
        });


        //侧滑
        drawer_layout=(DrawerLayout)findViewById(R.id.drawer_layout);
        list_left_drawer=(ListView)findViewById(R.id.list_left_drawer);
        menuLists=new ArrayList<Item>();
        menuLists.add(new Item(R.drawable.iv_menu_realtime,"实时信息"));
        menuLists.add(new Item(R.drawable.iv_menu_alert,"提醒通知"));
        menuLists.add(new Item(R.drawable.iv_menu_trace,"活动路线"));
        menuLists.add(new Item(R.drawable.iv_menu_settings,"相关设置"));
        myAdapter=new MyAdapter();
        list_left_drawer.setAdapter(myAdapter);
        list_left_drawer.setOnItemClickListener(this);
    }
    //侧滑部分
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ContentFragment contentFragment=new ContentFragment();
        Bundle args=new Bundle();
        args.putString("text",menuLists.get(position).getIconName());
        contentFragment.setArguments(args);
        FragmentManager fm=getFragmentManager();
        fm.beginTransaction().replace(R.id.ly_content,contentFragment).commit();
        drawer_layout.closeDrawer(list_left_drawer);
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return menuLists.size();
        }

        @Override
        public Object getItem(int position) {
            return menuLists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View converview, ViewGroup parent) {
            View view=LayoutInflater.from(MainActivity.this).inflate(R.layout.item_list,null);
            ImageView imageView=(ImageView)view.findViewById(R.id.img_icon);
            TextView textView=(TextView)view.findViewById(R.id.txt_content);
            imageView.setImageResource(menuLists.get(position).getIconId());
            textView.setText(menuLists.get(position).getIconName());
            return view;
        }
    }

    public void toChat(View view){
        fragment=new ChatFragment();
        fManager=getSupportFragmentManager();
        fTransaction=fManager.beginTransaction();
        fTransaction.replace(R.id.fm_content,fragment);
        fTransaction.commit();
    }

    public void toAddress (View view){
        fragment=new AddressFragment();
        fManager=getSupportFragmentManager();
        fTransaction=fManager.beginTransaction();
        fTransaction.replace(R.id.fm_content,fragment);
        fTransaction.commit();
    }

    public void toFind(View view){
        fragment=new FindFragment();
        fManager=getSupportFragmentManager();
        fTransaction=fManager.beginTransaction();
        fTransaction.replace(R.id.fm_content,fragment);
        fTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu!=null){
            if (menu.getClass()== MenuBuilder.class){
                try {
                    Method m=menu.getClass().getDeclaredMethod("setOptionalIconsVisible",Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu,true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId,menu);
    }
}