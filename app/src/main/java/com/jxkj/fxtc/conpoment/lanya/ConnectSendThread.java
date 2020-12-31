package com.jxkj.fxtc.conpoment.lanya;

//public class ConnectSendThread extends Thread {
//
//    private BluetoothSocket socket;
//    private boolean activeConnect;
//    InputStream inputStream;
//    OutputStream outputStream;
//
//    private ConnectSendThread(BluetoothSocket socket, boolean connect) {
//        this.socket = socket;
//        this.activeConnect = connect;
//    }
//
//    @Override
//    public void run() {
//        //如果是自动连接 则调用连接方法
//        if (activeConnect) {
//            try {
//                socket = (BluetoothSocket) device.getClass().getMethod("createRfcommSocket", new Class[]{int.class}).invoke(device, 1);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            //这里建立蓝牙连接 socket.connect() 这句话必须单开一个子线程
//            //至于原因 暂时不知道为什么
//            new Thread() {
//                @Override
//                public void run() {
//                    try {
//                        socket.connect();
//                        text_state.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                text_state.setText(getResources().getString(R.string.connect_success));
//                            }
//                        });
//
//                        inputStream = socket.getInputStream();
//                        outputStream = socket.getOutputStream();
//
//                        byte[] buffer = new byte[BUFFER_SIZE];
//                        int bytes;
//                        while (true) {
//                            //读取数据
//                            bytes = inputStream.read(buffer);
//
//
//                            if (bytes > 0) {
//                                final byte[] data = new byte[bytes];
//                                System.arraycopy(buffer, 0, data, 0, bytes);
//
//                                text_msg.post(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        text_msg.setText(getResources().getString(R.string.get_msg) + new String(data));
//                                    }
//                                });
//                            }
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }.start();
//        }
//
//    }
//}