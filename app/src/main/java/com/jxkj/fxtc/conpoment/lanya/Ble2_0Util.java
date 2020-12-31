package com.jxkj.fxtc.conpoment.lanya;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.jxkj.fxtc.conpoment.utils.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;

public class Ble2_0Util {

    private BluetoothAdapter adapter = null;
    private BluetoothReceiver bluetoothReceiver = null;
    public static  final  int BLE_STATUS_DISCONNECT = 0;
    public static  final  int BLE_STATUS_CONNECT = 1;
    private Context mContext;
    private Handler mHandler;
    private InputStream in = null;
    private OutputStream out = null;
    private BluetoothSocket socket = null;
    public static String BT_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    private onScanleDev	onScanleDev;
    private onDevValueChang onDevValueChang;
    public static BluetoothDevice ble;
    private volatile boolean isConnect;
    public static String BLUETOOTH_NAME ="";//HC-06


    public Ble2_0Util(Context mContext, Handler mHandler) {
        this.mContext = mContext;
        this.mHandler = mHandler;
    }

    public void setOnSCanleLisnter(onScanleDev on){
        this.onScanleDev = on;
    }
    public void setonDevValueChangLisnter(onDevValueChang on){
        this.onDevValueChang = on;
    }


    public boolean isConnect() {
        return isConnect;
    }

    /**
     * 初始化蓝牙适配器
     *
     * 指数基金怎么选？
     *
     */
    public void Init(){
        //获取蓝牙适配器
        adapter= BluetoothAdapter.getDefaultAdapter();
        if(adapter==null){
            sendMsg(322,"当前设备不支持蓝牙功能");
        }
        if(!adapter.isEnabled()){
	             /* Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	              startActivity(i);*/
            adapter.enable();
        }

        //动态注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        // 注册广播接收器，接收并处理搜索结果
        bluetoothReceiver=new BluetoothReceiver();
        mContext.registerReceiver(bluetoothReceiver, intentFilter);


        ServerThread serverThread = new ServerThread();
        serverThread.start();
    }

    public void disconnect(){
        if (socket != null) {
            try {
                isConnect = false;
                canclePeidui(); //取消配对
                socket.close();
                socket = null;
//                ble=null;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private Boolean canclePeidui(){
        Boolean returnValue;
        try {
            if (ble.getBondState() == BluetoothDevice.BOND_BONDED){
                Method removeBondMethod = BluetoothDevice.class.getMethod("removeBond");
                returnValue = (Boolean) removeBondMethod.invoke(ble);

                return  returnValue;
            }

        }catch (Exception e){

        }
        return false;
    }
    boolean isClose = false;//是否手动关闭
    /**
     * 关闭适配器
     * @return true - 已关闭 false - 未关闭
     */
    public boolean close(){
        isClose=true;
        BluetoothAdapter adapter= BluetoothAdapter.getDefaultAdapter();
        disconnect();
        if (adapter!=null) {
            if(adapter.isEnabled()){
                adapter.disable();
                Log.d("BruceZhang", "设备关闭中。。。");
                return true;
            } else {
                Log.d("BruceZhang", "设备已经关闭，不需再进行操作。。。");
                return true;
            }
        }
        else {
            Log.d("BruceZhang", "此设备不存在蓝牙设备。。。");
        }
        if (bluetoothReceiver != null) {
            mContext.unregisterReceiver(bluetoothReceiver);
        }

        return false;
    }

    /*
     * 蓝牙的可见性设置
     * 1.设置的本地设备的可见性，即能否被其他的蓝牙设备扫描到
     * 2.蓝牙可见的持续时间默认是120秒，这里修改为180秒，以作为参考
     */
    public void showBle(){
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 180);
        mContext.startActivity(discoverableIntent);
    }
    /**
     * 本地蓝牙设备扫描远程蓝牙设备
     *   使用BluetoothAdapter的startDiscovery()方法来搜索蓝牙设备
     startDiscovery()方法是一个异步方法，调用后会立即返回。该方法会进行对其他蓝牙设备的搜索，该过程会持续12秒。
     该方法调用后，搜索过程实际上是在一个System Service中进行的，
     所以可以调用cancelDiscovery()方法来停止搜索（该方法可以在未执行discovery请求时调用）。
     请求Discovery后，系统开始搜索蓝牙设备，在这个过程中，系统会发送以下三个广播：
     ACTION_DISCOVERY_START：开始搜索
     ACTION_DISCOVERY_FINISHED：搜索结束
     ACTION_FOUND：找到设备，这个Intent中包含两个extra fields：
     EXTRA_DEVICE和EXTRA_CLASS，分别包含BluetooDevice和BluetoothClass。
     */
    boolean isScan = false;
    public void startScanle(){
        if (adapter == null) {
            return;
        }
//        if(ble!=null){
//            connect(ble);
//            return;
//        }
        adapter.startDiscovery();
        isScan = true;
        mHandler.sendEmptyMessage(100);  //开始扫描
        if (mHandler != null) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (adapter.isDiscovering() && isScan) {
                        isScan = false;
                        adapter.cancelDiscovery();
                        mHandler.sendEmptyMessage(102);  //扫描结束
                    }
                }
            }, 12000);
        }
    }


    /**
     * 连接设备  蓝牙对象
     * @param device
     */
    public void connect(BluetoothDevice device){
        if (adapter.isDiscovering()) {
            adapter.cancelDiscovery();
        }
        ble = device;
        sendMsg(97,"连接中");
        //连接设备
        //创建Socket
        clientThread clientThread = new clientThread();
        clientThread.start();

    }

    class ServerThread extends Thread {
        @Override
        public void run() {
            try {
                BluetoothServerSocket mserverSocket = adapter.listenUsingRfcommWithServiceRecord("btspp",
                        UUID.fromString(BT_UUID));
                show("服务端:等待连接");

                socket = mserverSocket.accept();
                show("服务端:连接成功");

                ConnectThread mreadThread = new ConnectThread(socket,true);
                mreadThread.start();
                show("服务端:启动接受数据");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public  void show(String msg){
        Log.e("error2222",msg);
    }

    class ConnectThread extends Thread {

        private BluetoothSocket socket;

        public ConnectThread(BluetoothSocket socket, boolean iscon) {
            this.socket = socket;
            isConnect = iscon;
        }

        @Override
        public void run() {
            if (socket == null) {
                isConnect = false;
                sendMsg(99,"连接失败01");
            }else{
                try {
                    isConnect = true;
                    in = socket.getInputStream();
                    out = socket.getOutputStream();
                    if(in != null && out != null){
                        sendMsg(98,"已连接");
                        while (isConnect) {

                            byte[] data = new byte[1024];
                            int len = 0;
                            try{
                                len =  in.read(data);
                            }catch (Exception e){
                                if(isConnect){
                                    sendMsg(99,"断开连接123");
                                }
                                isConnect = false;
                            }
                            if (len > 0) {
                                final byte[] dataz = new byte[len];
                                System.arraycopy(data, 0, dataz, 0, len);

                                if (onDevValueChang != null) {
                                    onDevValueChang.getRep(dataz);
                                }
                            }
                            Thread.sleep(1);
                        }
                    }else {
                        isConnect = false;
                        sendMsg(99,"连接失败02");
                    }

                } catch (IOException e) {
                    sendMsg(99,"断开连接03");
                    e.printStackTrace();
                    try {
                        in.close();
                        out.close();
                        socket.close();
                        in = null;
                        out = null;
                        socket = null;
                        isConnect = false;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    sendMsg(99,"断开连接04");
                    e.printStackTrace();
                    try {
                        in.close();
                        out.close();
                        socket.close();
                        in = null;
                        out = null;
                        socket = null;
                        isConnect = false;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    private void sendMsg(int sendCode, String sendStr){
        if (mHandler == null) {
            return;
        }
        Message message = new Message();
        message.what = sendCode;
        message.obj = sendStr;
        mHandler.sendMessage(message);
    }

    //广播接收器，当远程蓝牙设备被发现时，回调函数onReceiver()会被执行
    private class BluetoothReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if(device!=null){
                Log.d("BruceZhang", "扫描到可连接--："+device.getName()+";"+device.getAddress());
            }
            if(isScan && device!=null && StringUtil.isNotBlank(device.getName()) && device.getName().equals(BLUETOOTH_NAME)){
                Log.d("BruceZhang", "扫描到可连接："+device.getAddress());
                if(!isConnect){
                    if (adapter.isDiscovering()) {
                        adapter.cancelDiscovery();
                        mHandler.sendEmptyMessage(101);  //扫描结束
                    }
                    isScan = false;
                    isClose = false;
                    onScanleDev.findDev(device);
                }
            }
        }
    }

    /**
     * 开启客户端
     */
    private class clientThread extends Thread {

        @Override
        public void run() {
            sendMsg(111,"开始连接");
            try {
                //创建一个Socket连接：只需要服务器在注册时的UUID号
                socket = ble.createRfcommSocketToServiceRecord(UUID.fromString(BT_UUID));
                //连接
                show("客户端:开始连接...");
                socket.connect();
                show("客户端:连接成功");
                //启动接受数据
                show("客户端:启动接受数据");
                ConnectThread mreadThread = new ConnectThread(socket,true);
                mreadThread.start();
            } catch (IOException e) {
                if(!isClose){
                    sendMsg(99,"连接失败");
                    show("客户端:连接服务端异常！断开连接重新试一试");
                }
                e.printStackTrace();
            }
        }
    }

    public  interface onScanleDev{
        void findDev(BluetoothDevice device);
    }
    public interface onDevValueChang{
        void getRep(byte[] dataz);
    }


}

