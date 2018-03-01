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


/**
 * A simple {@link Fragment} subclass.
 */
public class Aplikasi extends Fragment{

    TextView Alkohol;
    Firebase bacadata,bacadata1;
    Double tempal,kecepatan_temp;
    int tabrakan;
    FloatingActionButton  mati,hidup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_aplikasi, container, false);
        Alkohol = (TextView)view.findViewById(R.id.Alkohol);
        FloatingActionButton nelpon = (FloatingActionButton) view.findViewById(R.id.fab);
        mati = (FloatingActionButton) view.findViewById(R.id.faba);
        hidup = (FloatingActionButton) view.findViewById(R.id.fab2);
        Firebase.setAndroidContext(this.getActivity());
        bacadata = new Firebase("https://scms-awesome.firebaseio.com/Data");

        try {
            bacadata.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Alkohol.setText(dataSnapshot.child("Alkohol").getValue().toString());
                    tempal = Double.parseDouble(dataSnapshot.child("Alkohol").getValue().toString());
                    kecepatan_temp = Double.parseDouble(dataSnapshot.child("Kecepatan").getValue().toString());

                    try {
                        //untuk notif alkohol
                        double alkohol = 30;
                        if (tempal >= alkohol) {
                            notifalkohol();
                        }
// else {
//                            Toast.makeText(getActivity(), "Batas Alkohol Aman", Toast.LENGTH_SHORT).show();
//                        }

                    }catch (Exception e){
                        Toast.makeText(getActivity(), "Proses Pengambilan Data", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }catch (Exception e){
            Toast.makeText(this.getActivity(),""+e,Toast.LENGTH_LONG).show();
        }


        //untuk mematikan mobil
        double k = 0;
        final double al=30;
        final double amul = 0;
//        if (tempal == amul) {
            mati.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  try {
                      if (kecepatan_temp == amul && tempal < al) {
                          bacadata.child("Mobil").setValue("off1");
                          Toast.makeText(getActivity(), "Mobil dimatikan", Toast.LENGTH_SHORT).show();
                      } else {
                          Toast.makeText(getActivity(), "Mobil tidak bisa dimatikan", Toast.LENGTH_SHORT).show();
                      }
                  }catch (Exception e){
                      Toast.makeText(getActivity(), "Masi ngambil data", Toast.LENGTH_SHORT).show();
                  }
                }
            });
//        }else  {
//            Toast.makeText(getActivity(), "Batas Alkohol Aman, Mobil tidak bisa dimatikan", Toast.LENGTH_SHORT).show();
//        }

        //untuk menghidupkan mobil
        hidup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try {
                   if (kecepatan_temp == amul && tempal == amul) {
                       bacadata.child("Mobil").setValue("on1");
                       Toast.makeText(getActivity(), "Mobil dihidupkan", Toast.LENGTH_SHORT).show();
                   } else {
                       Toast.makeText(getActivity(), "Mobil tidak bisa dihidupkan karna kadar alkohol & kecepatan masi berbahaya", Toast.LENGTH_SHORT).show();
                   }
               }catch (Exception e){
                   Toast.makeText(getActivity(), "Masi ngambil data", Toast.LENGTH_SHORT).show();
               }
            }

        });
//        }else {
//            Toast.makeText(getActivity(), "Mobil tidak bisa dihidupkan karna kadar alkohol masi berbahaya", Toast.LENGTH_SHORT).show();
//        }

        nelpon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });


        return view;


    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void notifalkohol() {
        Intent intent = new Intent(this.getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.setAction("alkohol");
        PendingIntent pendingIntent = PendingIntent.getActivity(this.getActivity(), 1, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this.getActivity())
                .setSmallIcon(R.mipmap.alkoholblack)
                .setContentTitle("Alkohol Melebihi batas")
                .setContentText("Anak anda melebihi batas alkohol yang telah ditentukan")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, notificationBuilder.build());
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
