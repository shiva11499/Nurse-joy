package com.gshivansh37.nursejoy;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.UUID;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private IntentIntegrator qrScan;
    private EditText text;
    private Button send;
    private TextView mTextViewAngleLeft;
    private TextView mTextViewStrengthLeft;
    private ImageButton qr_code;
    private TextView qr_result;

//    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
//    private BluetoothSocket btSocket = null;
//    BluetoothAdapter mBluetoothAdapter;
      BluetoothDevice mDevice = null;
//    private static final String TAG = "bluetooth1";
//    OutputStream outStream;
    String address = null;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qr_result = (TextView) findViewById(R.id.qr_result);
        text = (EditText) findViewById(R.id.text);
        send = (Button) findViewById(R.id.send);
        qr_code = (ImageButton) findViewById(R.id.qr_code);

        new ConnectBT().execute(); //Call the class to connect

        mTextViewAngleLeft = (TextView) findViewById(R.id.textView_angle_left);
        mTextViewStrengthLeft = (TextView) findViewById(R.id.textView_strength_left);

        //intializing scan object
        qrScan = new IntentIntegrator(this);

        //attaching onclick listener
        qr_code.setOnClickListener(this);

        JoystickView joystickLeft = (JoystickView) findViewById(R.id.joystickView);
        joystickLeft.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                mTextViewAngleLeft.setText(angle + "");
                mTextViewStrengthLeft.setText(strength + "");
                int anglea = Integer.valueOf(mTextViewAngleLeft.getText().toString());
                if(strength> 10)
                {

                    if(anglea < 115 && angle >80) {
                        if (btSocket!=null)
                        {
                            try
                            {
                                btSocket.getOutputStream().write("1".getBytes());
                            }
                            catch (IOException e)
                            {
                                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    else if(anglea < 285 && anglea > 260){
                        if (btSocket!=null)
                        {
                            try
                            {
                                btSocket.getOutputStream().write("2".getBytes());
                            }
                            catch (IOException e)
                            {
                                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    else if(anglea < 205 && anglea >155) {
                        if (btSocket!=null)
                        {
                            try
                            {
                                btSocket.getOutputStream().write("3".getBytes());
                            }
                            catch (IOException e)
                            {
                                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    else if(anglea < 25 && anglea > 345) {
                        if (btSocket!=null)
                        {
                            try
                            {
                                btSocket.getOutputStream().write("4".getBytes());
                            }
                            catch (IOException e)
                            {
                                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    else if(anglea < 79 && anglea > 25) {
                        if (btSocket!=null)
                        {
                            try
                            {
                                btSocket.getOutputStream().write("5".getBytes());
                            }
                            catch (IOException e)
                            {
                                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    else if(anglea < 259 && anglea > 206) {
                        if (btSocket!=null)
                        {
                            try
                            {
                                btSocket.getOutputStream().write("7".getBytes());
                            }
                            catch (IOException e)
                            {
                                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    else if(anglea < 344 && anglea > 286) {
                        if (btSocket!=null)
                        {
                            try
                            {
                                btSocket.getOutputStream().write("8".getBytes());
                            }
                            catch (IOException e)
                            {
                                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    else if(anglea < 154 && anglea > 116) {
                        if (btSocket!=null)
                        {
                            try
                            {
                                btSocket.getOutputStream().write("6".getBytes());
                            }
                            catch (IOException e)
                            {
                                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
                else {
                    if (btSocket!=null)
                    {
                        try
                        {
                            btSocket.getOutputStream().write("0".getBytes());
                        }
                        catch (IOException e)
                        {
                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });



//        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        if (mBluetoothAdapter == null) {
//            Toast.makeText(getApplicationContext(), "Device does not support bluetooth", Toast.LENGTH_SHORT).show();
//            finish();
//        }
//        if (!mBluetoothAdapter.isEnabled()) {
//            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableBtIntent, 1);
//        }

//        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        if (mBluetoothAdapter == null) {
//            Toast.makeText(getApplicationContext(), "Device does not support bluetooth", Toast.LENGTH_SHORT).show();
//            finish();
//        }
//        if (!mBluetoothAdapter.isEnabled()) {
//            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableBtIntent, 1);
//        }
//        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
//        if (pairedDevices.size() > 0) {
//            for (BluetoothDevice device : pairedDevices) {
//                if (device.getName().trim().equals("HC-05")) {
//                    mDevice = device;
//                    Log.v("Message :", "Device connected");
//                    break;
//                }
//            }
//        }


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });

    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        try {
            final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[]{UUID.class});
            return (BluetoothSocket) m.invoke(device, myUUID);
        } catch (Exception e) {
        }
        if (device != null)
            return device.createRfcommSocketToServiceRecord(myUUID);
        else
            return null;
    }

    private void sendData() {
        byte[] data = text.getText().toString().getBytes();

        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write(data);
            }
            catch (IOException e)
            {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
            }
        }

//        if (outStream == null) {
//            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
//            if (pairedDevices.size() > 0) {
//                for (BluetoothDevice device : pairedDevices) {
//                    if (device.getName().trim().equals("HC-05")) {
//                        mDevice = device;
//                        Log.v("Message :", "Device connected");
//                        break;
//                    }
//                }
//            }
//
//            try {
//                if (mDevice != null)
//                    btSocket = createBluetoothSocket(mDevice);
//
//                // Discovery is resource intensive.  Make sure it isn't going on
//                // when you attempt to connect and pass your message.
//                mBluetoothAdapter.cancelDiscovery();
//
//                // Establish the connection.  This will block until it connects.
//                Log.d(TAG, "...Connecting...");
//                try {
//                    if (btSocket != null) {
//                        btSocket.connect();
//                        Log.d(TAG, "...Connection ok...");
//                    }
//
//                } catch (IOException e) {
//                    try {
//                        btSocket.close();
//                    } catch (IOException e2) {
//                    }
//                }
//                Log.d(TAG, "...Create Socket...");
//            } catch (IOException e) {
//            }
//            try {
//                if (btSocket != null)
//                    outStream = btSocket.getOutputStream();
//            } catch (IOException e) {
//            }
//        }
//        try {
//            if (data != null)
//                outStream.write(data);
//            Log.d(TAG, "...Data send...");
//        } catch (IOException e) {
//        }
//
//        Log.e("Device", mDevice + "");
//        Log.e("Socket", btSocket + "");
//    }

//        try {
//            btSocket = createBluetoothSocket(mDevice);
//
//            // Discovery is resource intensive.  Make sure it isn't going on
//            // when you attempt to connect and pass your message.
//            mBluetoothAdapter.cancelDiscovery();
//
//            // Establish the connection.  This will block until it connects.
//            Log.d(TAG, "...Connecting...");
//            try {
//                btSocket.connect();
//                Log.d(TAG, "...Connection ok...");
//            } catch (IOException e) {
//                try {
//                    btSocket.close();
//                } catch (IOException e2) {
//                }
//            }
//            Log.d(TAG, "...Create Socket...");
//            try {
//                outStream = btSocket.getOutputStream();
//            } catch (IOException e) {
//            }
//
//        } catch (IOException e1) {
//        }
//
//        try {
//            outStream.write(data);
//            Log.d(TAG, "...Data send...");
//        } catch (IOException e) {
//
//        }
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews
                    qr_result.setText(obj.getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onClick(View view) {
        //initiating the qr code scan
        qrScan.initiateScan();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            BluetoothAdapter myBluetooth = BluetoothAdapter.getDefaultAdapter();
            Set<BluetoothDevice> pairedDevices = myBluetooth.getBondedDevices();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    if (device.getName().trim().equals("HC-05")) {
                        mDevice = device;
                        Log.v("Message :", "Device connected");
                        break;
                    }
                }
            }

            try {
                if (mDevice != null)
                    btSocket = createBluetoothSocket(mDevice);

                // Discovery is resource intensive.  Make sure it isn't going on
                // when you attempt to connect and pass your message.
                myBluetooth.cancelDiscovery();

                // Establish the connection.  This will block until it connects.
               // Log.d(TAG, "...Connecting...");
                try {
                    if (btSocket != null) {
                        btSocket.connect();
                 //       Log.d(TAG, "...Connection ok...");
                    }

                } catch (IOException e) {
                    try {
                        btSocket.close();
                    } catch (IOException e2) {
                    }
                }
                //Log.d(TAG, "...Create Socket...");
            } catch (IOException e) {
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess) {
                Toast.makeText(getApplicationContext(),"Connection Failed. Is it a SPP Bluetooth? Try again.", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(getApplicationContext(),"Connected.",Toast.LENGTH_LONG).show();
                isBtConnected = true;
            }
        }
    }
}