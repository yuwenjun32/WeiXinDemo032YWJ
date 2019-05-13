package com.example.yt.weixindemo;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {
    private TextView tv_content;


    public ContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_content, container, false);
        tv_content=(TextView)view.findViewById(R.id.tv_content);
        String text=getArguments().getString("text");
        tv_content.setText(text);
        return view;
    }

}
