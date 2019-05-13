package com.example.yt.weixindemo;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class AddressFragment extends Fragment {
    private String data[] = { "zhangsan", "lisi", "wangwu", "liuliu",
            "qiqi", "huangsir", "hanhan", "luoluo", "xuxu", "zhengzheng",
            "caicai", "chenchen", "zhuzhu", "daidai", "dengdeng", "liaoliao", "dongdong",
            "dudu", "fengfeng", "tantan", "yuyu", "zhouzhou", "yangyang", "panpan",
            "guoguo", "hehe", "zengzeng", "huhu", "jiangjiang", "yanyan", "kongkong" };
    String nData[];// 数据源，整合了索引字母
    private ListView listView;
    MyAdapter adapter;

    public AddressFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {
        View addressView=inflater.inflate(R.layout.activity_address_fragment, null);
        sortIndex();
        listView = (ListView)addressView.findViewById(R.id.listView1);
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        return addressView;
    }

    private void sortIndex() {
        TreeSet<String> set = new TreeSet<String>();
        // 获取初始化数据源中的首字母，添加到 set 中
        for (String string : data) {
            set.add(String.valueOf(string.charAt(0)));
        }
        // 新数组的长度为原数据加上 set 的大小
        nData = new String[data.length + set.size()];
        int i = 0;
        for (String string : set) {
            nData[i] = string;
            i++;
        }
        // 将原数据拷贝到新数据中
        System.arraycopy(data, 0, nData, set.size(), data.length);
        Arrays.sort(nData, String.CASE_INSENSITIVE_ORDER);
        // 自动按照首字母排序

    }


    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return nData.length;
        }

        @Override
        public Object getItem(int position) {
            return nData[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String item = nData[position];
            // TODO Auto-generated method stub
            if(item.length()==1){
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.addresslist_index, null);
            }
            else {
                convertView =LayoutInflater.from(parent.getContext()).inflate(R.layout.addresslist_item, null);
            }
            TextView tv = (TextView)convertView.findViewById(R.id.textView1);
            tv.setText(item);
            return convertView;

        }
    }
}