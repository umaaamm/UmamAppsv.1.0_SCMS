package com.fun_corp.umamappsv10;


import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.MediaMetadataCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
/**
 * A simple {@link Fragment} subclass.
 * implements ZXingScannerView.ResultHandler
 */
public class Tentang extends Fragment  {
Button btn_panggil;
    Firebase bacadata;
    String scanid;
    TextView idkendaraan;
   // private ZXingScannerView mScannerView;
ImageView scan;
    public Tentang() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tentang, container, false);
        scan = (ImageView) view.findViewById(R.id.scan);
        idkendaraan = (TextView) view.findViewById(R.id.idkendaraan);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Barcode.class);
                       getActivity().startActivity(i);
//                mScannerView = new ZXingScannerView(getActivity());   // Programmatically initialize the scanner view
//                getActivity().setContentView(mScannerView);
//                mScannerView.setResultHandler(getActivity()); // Register ourselves as a handler for scan results.
//                mScannerView.startCamera();
            }
        });

        Firebase.setAndroidContext(this.getActivity());
        bacadata = new Firebase("https://scms-awesome.firebaseio.com/Data");

        try {
            bacadata.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    scanid = dataSnapshot.child("Barcode").getValue().toString();
                    idkendaraan.setText(""+scanid);

                }
                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }catch (Exception e){
            Toast.makeText(this.getActivity(),""+e,Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(this.getActivity(),""+scanid,Toast.LENGTH_LONG).show();



        return view;
    }



    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        btn_panggil.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //EditText edt_tlp = (EditText) view.findViewById(R.id.editText_telp);
//                //Intent call = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+edt_tlp.getText()));
//                //startActivity(call);
//                call();
//            }
//        });

    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        mScannerView.stopCamera();           // Stop camera on pause
//    }
//
//    @Override
//    public void handleResult(Result rawResult) {
//        Toast.makeText(getActivity(), "Contents = " + rawResult.getText() + ", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();
//
//    }
//    private void call() {
//        try {
//            Intent callIntent = new Intent(Intent.ACTION_CALL);
//            callIntent.setData(Uri.parse("tel:08992255445"));
//            startActivity(callIntent);
//        } catch (ActivityNotFoundException e) {
//
//        }
//    }
}
