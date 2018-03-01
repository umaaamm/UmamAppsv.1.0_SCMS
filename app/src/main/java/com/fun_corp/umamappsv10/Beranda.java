package com.fun_corp.umamappsv10;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;

public class Beranda extends Fragment {

    Firebase bacadata;

    public Beranda() {

    }
    int kec;
    int nli;
    public TextView Kecepatan;
    public EditText batas;
    public ImageButton btnset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_beranda, container, false);
        Kecepatan = (TextView)view.findViewById(R.id.TextKec);
        batas = (EditText) view.findViewById(R.id.editText1);
        Firebase.setAndroidContext(this.getActivity());
        btnset = (ImageButton) view.findViewById(R.id.pencet);
        bacadata = new Firebase("https://scms-awesome.firebaseio.com/Data");
        int nilai;


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:08992255445"));
                startActivity(callIntent);

                //   call();
                //Toast.makeText(getActivity(),"Sedang M data",Toast.LENGTH_SHORT).show();
            }
        });


        btnset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nli  = Integer.parseInt(batas.getText().toString());
                bacadata.child("Batas").setValue(nli);
            }
        });
        try {
            bacadata.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Kecepatan.setText(dataSnapshot.child("Kecepatan").getValue().toString());
                    kec = Integer.parseInt(dataSnapshot.child("Kecepatan").getValue().toString());
                    batas.setText(dataSnapshot.child("Batas").getValue().toString());
                    try {
                        nli = 2;
                        if (Integer.parseInt(dataSnapshot.child("Kecepatan").getValue().toString()) > Integer.parseInt(dataSnapshot.child("Batas").getValue().toString())) {
                            notifkecepatan();
                            //Toast.makeText(getActivity(),dataSnapshot.child("Kecepatan").getValue().toString(),Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Toast.makeText(getActivity(),"Proses pengambilan data",Toast.LENGTH_SHORT).show();
                    }
//                        else {
//                            Toast.makeText(getActivity(), "Sesuai batas Kecepatan!", Toast.LENGTH_SHORT).show();
//
//                        }

                    try{
                        if (dataSnapshot.child("Kecelakaan").getValue().toString().equals("1")) {
                            notifkece();

                        }
//                        else {
//                            Toast.makeText(getActivity(), "Aman", Toast.LENGTH_SHORT).show();
//                        }


                    }catch (Exception e){
                        Toast.makeText(getActivity(),"Proses pengambilan data",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });


        }catch (Exception e){
            Toast.makeText(this.getActivity(),""+e,Toast.LENGTH_LONG).show();
        }



        return view;
    }

//    public void notif() {
//
//        NotificationCompat.Builder builder =
//                (NotificationCompat.Builder) new NotificationCompat.Builder(this.getActivity())
//                        .setSmallIcon(R.mipmap.speedo1)
//                        .setContentTitle("Notifications Example")
//                        .setContentText("This is a test notification");
//
//        Intent notificationIntent = new Intent(this.getActivity(), MainActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(this.getActivity(), 0, notificationIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(contentIntent);
//
//        // Add as notification
//        NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(0, builder.build());
//    }

    public void notifkecepatan() {
        Intent intent = new Intent(this.getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this.getActivity(), 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this.getActivity())
                .setSmallIcon(R.mipmap.speedo1)
                .setContentTitle("Kecepatan Melebihi batas")
                .setContentText("Anak anda melebihi batas yang telah ditentukan")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
    public void notifkece() {
        Intent intent = new Intent(this.getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this.getActivity(), 2, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this.getActivity())
                .setSmallIcon(R.mipmap.ic_warning_black_48dp)
                .setContentTitle("Kecelakaan")
                .setContentText("Anak anda Kecelakaan")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(2, notificationBuilder.build());
    }
    private void call() {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:082132514713"));
            startActivity(callIntent);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Tidak Bisa Nelpon", Toast.LENGTH_SHORT).show();
        }
    }
}
