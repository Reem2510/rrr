package com.example.finalproject.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.finalproject.Activites.ShowMoreActivity;
import com.example.finalproject.Adapters.BPAdapter;
import com.example.finalproject.Adapters.YCAAdapter;
import com.example.finalproject.Classes.CCClass;
import com.example.finalproject.Classes.ItemData;
import com.example.finalproject.R;
import com.example.finalproject.Interface.RecyclerViewInterface;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragmentt#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragmentt extends Fragment implements RecyclerViewInterface {

  private   ArrayList<ItemData>itemDataYY=new ArrayList<>();

    private   ArrayList<ItemData>itemDataBB=new ArrayList<>();
    private   ArrayList<ItemData>itemDatapp=new ArrayList<>();
    private ArrayList<CCClass>itemDatacc=new ArrayList<>();

private  int[]BBImages={R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4,R.drawable.b5};
    private  int[]PPImages={R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5,R.drawable.p6,R.drawable.p7};

    private  int[]ChannelImages={R.drawable.t1,R.drawable.t2,R.drawable.t3,R.drawable.t4,R.drawable.t5,R.drawable.t6,R.drawable.t7};
    private  int[]ChannelImagesbb={R.drawable.baseline_ads_click_24,R.drawable.baseline_ads_click_24,R.drawable.baseline_ads_click_24,R.drawable.baseline_ads_click_24,R.drawable.baseline_ads_click_24,R.drawable.baseline_ads_click_24,R.drawable.baseline_ads_click_24};

    private  int[]CCImages={R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,R.drawable.c7};

    private RecyclerView cRecycler,BRecycler,ppRecycler,ccRecycler;
  private  YCAAdapter ycaAdapter,Badapter,PPAdapter;
  private BPAdapter ccAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragmentt() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragmentt.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragmentt newInstance(String param1, String param2) {
        HomeFragmentt fragment = new HomeFragmentt();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private void SetDataModalsYY(){
        String[]  channelsname=getResources().getStringArray(R.array.channels_array);
        String[]  channelsdd=getResources().getStringArray(R.array.channels_array);
        for (int  i=0;i< channelsname.length&& i < ChannelImages.length;i++)
        {
            itemDataYY.add(new ItemData(channelsname[i],channelsdd[i],ChannelImagesbb[i],ChannelImages[i]));
        }
    }
    private void SetDataModalsBB(){
        String[]  Booksname=getResources().getStringArray(R.array.Books_array);
        String[]  Booksdd=getResources().getStringArray(R.array.Books_array);
        for (int  i=0;i< Booksname.length&& i < BBImages.length;i++)
        {
            itemDataBB.add(new ItemData(Booksname[i],Booksdd[i],ChannelImagesbb[i],BBImages[i]));
        }
    }
    private void SetDataModalsPP(){
        String[]  podcastname=getResources().getStringArray(R.array.Podcast_array);
        String[]  podcastdd=getResources().getStringArray(R.array.Podcast_array);

        for (int  i=0;i< podcastname.length&& i < PPImages.length;i++)
        {
            itemDatapp.add(new ItemData(podcastname[i],podcastdd[i],ChannelImagesbb[i], PPImages[i]));
        }
    }
    private void SetDataModalsCC(){
        String[] descc=getResources().getStringArray(R.array.Books_array);
        for (int  i=0;i < CCImages.length;i++)
        {
            itemDatacc.add(new CCClass(CCImages[i],descc[i]));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_fragmentt, container, false);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(getActivity(), ShowMoreActivity.class);
        intent.putExtra("des",itemDatacc.get(position).getDes());
       startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        cRecycler=getActivity().findViewById(R.id.channelRecycler);
        BRecycler=getActivity().findViewById(R.id.BooksRecycler);
        ppRecycler=getActivity().findViewById(R.id.recpp);
        ccRecycler=getActivity().findViewById(R.id.Courserecently);
        ycaAdapter=new YCAAdapter(getContext(),itemDataYY);
         Badapter=new YCAAdapter(getContext(),itemDataBB);
         PPAdapter=new YCAAdapter(getContext(),itemDatapp);
        ccAdapter=new BPAdapter(getContext(),itemDatacc,this);
        SetDataModalsYY();
        SetDataModalsBB();
        SetDataModalsPP();
        SetDataModalsCC();
         cRecycler.setAdapter(ycaAdapter);
         BRecycler.setAdapter(Badapter);
         ppRecycler.setAdapter(PPAdapter);
         ccRecycler.setAdapter(ccAdapter);
        BRecycler.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL, false));
        cRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        ppRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        ccRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

    }
}