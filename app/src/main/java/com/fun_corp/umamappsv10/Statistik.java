package com.fun_corp.umamappsv10;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Interpolator;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.NotificationCompat;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
//import com.github.mikephil.charting.charts.LineChart;
//import com.jjoe64.graphview.GraphView;
//import com.jjoe64.graphview.ValueDependentColor;
//import com.jjoe64.graphview.series.BarGraphSeries;
//import com.jjoe64.graphview.series.DataPoint;
//import com.jjoe64.graphview.series.LineGraphSeries;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import im.dacer.androidcharts.ClockPieHelper;
import im.dacer.androidcharts.ClockPieView;
import im.dacer.androidcharts.LineView;



/**
 * A simple {@link Fragment} subclass.
 */
public class Statistik extends Fragment{
    Firebase st;
    ArrayList<String> kelembapan = new ArrayList<>();
    ArrayList<String> jamK = new ArrayList<>();
    int a=0;
    int b;
    int randomint = 9;
    //GraphView graph1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragmen_statistik, container, false);
        // graph1 = (GraphView) view.findViewById(R.id.graph1);
        //graph = (GraphView) view.findViewById(R.id.graph2);
        Firebase.setAndroidContext(this.getActivity());
        st = new Firebase("https://kandang-pintar.firebaseio.com/rekam_data/kelembapan");

        final LineView lineView = (LineView) view.findViewById(R.id.line_view);
        final LineView lineViewFloat = (LineView) view.findViewById(R.id.line_view_float);


//        kelembapan.add(90);
//        kelembapan.add(34);
//        kelembapan.add(50);


        initLineView(lineView);
        initLineView(lineViewFloat);
//        Button lineButton = (Button) view.findViewById(R.id.line_button);
//        lineButton.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View view) {
//                randomSaet(lineView, lineViewFloat);
//            }
//        });

        //final ArrayList<Integer> dataList = new ArrayList<>();
        st.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    //child.child("data").getValue().toString();
                    a++;
                    String kelembapann= child.child("data").getValue().toString();

                    kelembapan.add(kelembapann);
                    String jamKk = child.child("waktu").getValue().toString();
                    jamK.add(jamKk);
                    // Toast.makeText(getActivity(), "WTF : " + kelembapan + a, Toast.LENGTH_SHORT).show();
                    //randomSet(lineView, lineViewFloat);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        randomSet(lineView, lineViewFloat);

        return view;
    }

    private void initLineView(LineView lineView) {
        ArrayList<String> test = new ArrayList<String>();
        for (int i = 0; i < randomint; i++) {
            test.add(String.valueOf(i + 1));
        }
        lineView.setBottomTextList(test);
        lineView.setColorArray(new int[] {
                Color.parseColor("#F44336"), Color.parseColor("#9C27B0"),
                Color.parseColor("#2196F3"), Color.parseColor("#009688")
        });
        lineView.setDrawDotLine(true);
        lineView.setShowPopup(LineView.SHOW_POPUPS_NONE);
    }


    private void randomSet(LineView lineView, LineView lineViewFloat) {
        final ArrayList<Integer> dataList = new ArrayList<>();
        float random = (float) (Math.random() * 9 + 1);
        //Integer a = kelembapan.size();
        Integer d= 0;

        for (int i = 0; i < randomint; i++) {
            dataList.add((int) (Math.random() * random));
            //Toast.makeText(getActivity(), "WTF : " + a, Toast.LENGTH_SHORT).show();
        }

        ArrayList<Integer> dataList2 = new ArrayList<>();
        random = (int) (Math.random() * 9 + 1);
        for (int i = 0; i < randomint; i++) {
            dataList2.add((int) (Math.random() * random));
        }
//
////        ArrayList<Integer> dataList3 = new ArrayList<>();
////        random = (int) (Math.random() * 9 + 1);
////        for (int i = 0; i < randomint; i++) {
////            dataList3.add((int) (Math.random() * random));
////        }
//
        ArrayList<ArrayList<Integer>> dataLists = new ArrayList<>();
        dataLists.add(dataList);
        dataLists.add(dataList2);
        //dataLists.add(dataList3);

        lineView.setDataList(dataLists);

        ArrayList<Float> dataListF = new ArrayList<>();
        float randomF = (float) (Math.random() * 9 + 1);
        for (int i = 0; i < randomint; i++) {
            dataListF.add((float) (Math.random() * randomF));
        }
//
        ArrayList<Float> dataListF2 = new ArrayList<>();
        randomF = (int) (Math.random() * 9 + 1);
        for (int i = 0; i < randomint; i++) {
            dataListF2.add((float) (Math.random() * randomF));
        }
//
//        ArrayList<Float> dataListF3 = new ArrayList<>();
//        randomF = (int) (Math.random() * 9 + 1);
//        for (int i = 0; i < randomint; i++) {
//            dataListF3.add((float) (Math.random() * randomF));
//        }
//
        ArrayList<ArrayList<Float>> dataListFs = new ArrayList<>();
        dataListFs.add(dataListF);
        dataListFs.add(dataListF2);
//        dataListFs.add(dataListF3);

        lineViewFloat.setFloatDataList(dataListFs);
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}