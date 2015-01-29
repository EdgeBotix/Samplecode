package com.retrorat.v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.util.Log;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class MonitorActivity extends MyActivity {

	private static final int REQUEST_DISCOVERY = 0x1;;
	private final String TAG = "MonitorActivity";
	private Handler _handler = new Handler();
	private final int maxlength = 2048;
	private BluetoothDevice device = null;
	private BluetoothSocket socket = null;
	
	private OutputStream outputStream;
	private InputStream in;
	private InputStream inputStream;
	
	private Object obj1 = new Object();
	private Object obj2 = new Object();
	private OnTouchListener OnTouchListener;
	public static boolean canRead = true;

	public static StringBuffer hexString = new StringBuffer();
	String test="Liu Su ";
	String test2="Liu Su";
	boolean obstacle=false;
	boolean ldr=false;
	boolean connection=false;
	boolean connect=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		this.getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.monitor);

		BluetoothDevice finalDevice = this.getIntent().getParcelableExtra(
				BluetoothDevice.EXTRA_DEVICE);
		SocketApplication app = (SocketApplication) getApplicationContext();
		device = app.getDevice();
		Log.d(TAG, "test1");
		if (finalDevice == null) {
			if (device == null) {
				Log.d(TAG, "test2");
				Intent intent = new Intent(this, SearchDeviceActivity.class);
				startActivity(intent);
				finish();
				return;
			}
			Log.d(TAG, "test4");
		} else if (finalDevice != null) {
			Log.d(TAG, "test3");
			app.setDevice(finalDevice);
			device = app.getDevice();
		}
		new Thread() {
			public void run() {
				connect(device);
			};
		}.start();
        
		ImageButton LDRButton = (ImageButton) findViewById(R.id.Button12);
	    LDRButton.setOnTouchListener(startButtonListener12);
		
		ImageButton SonarButton = (ImageButton) findViewById(R.id.Button11);
	    SonarButton.setOnTouchListener(startButtonListener11);
		
	    ImageButton CheckConnectionButton = (ImageButton) findViewById(R.id.Button10);
        CheckConnectionButton.setOnTouchListener(startButtonListener10);
        
        ImageButton startEngineButton = (ImageButton) findViewById(R.id.Button9);
        startEngineButton.setOnTouchListener(startButtonListener9);
        
        ImageButton pushForwardButton = (ImageButton) findViewById(R.id.Button1);
        pushForwardButton.setOnTouchListener(startButtonListener);
        
        ImageButton pushBackwardButton = (ImageButton) findViewById(R.id.Button2);
        pushBackwardButton.setOnTouchListener(startButtonListener2);
        
        
        ImageButton pushLeftButton = (ImageButton) findViewById(R.id.Button3);
        pushLeftButton.setOnTouchListener(startButtonListener3);
       
        ImageButton pushRightButton = (ImageButton) findViewById(R.id.Button4);
        pushRightButton.setOnTouchListener(startButtonListener4);
     
        ImageButton pushFLButton = (ImageButton) findViewById(R.id.Button5);
        pushFLButton.setOnTouchListener(startButtonListener5);
        
        ImageButton pushFRButton = (ImageButton) findViewById(R.id.Button6);
        pushFRButton.setOnTouchListener(startButtonListener6);
       
        ImageButton pushBLButton = (ImageButton) findViewById(R.id.Button7);
        pushBLButton.setOnTouchListener(startButtonListener7);
        
        ImageButton pushBRButton = (ImageButton) findViewById(R.id.Button8);
        pushBRButton.setOnTouchListener(startButtonListener8);
        

	}
	
	private OnTouchListener startButtonListener12 = new OnTouchListener(){
        public boolean onTouch(View v, MotionEvent event) {
           switch ( event.getAction() ) {
            case MotionEvent.ACTION_DOWN: 
            	obstacle=false;
            	ldr=true;
            	String relay1 = "2D";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay1.getBytes());
						}
						
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send 4... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
				
				
            case MotionEvent.ACTION_UP:
				v.setBackgroundResource(R.drawable.check_connect);
           }
           return false;
        }
   };
	
	private OnTouchListener startButtonListener11 = new OnTouchListener(){
        public boolean onTouch(View v, MotionEvent event) {
           switch ( event.getAction() ) {
            case MotionEvent.ACTION_DOWN: 
            	ldr=false;
            	obstacle=true;
            	String relay1 = "2O";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay1.getBytes());
						}
						
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send 4... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
				
				
            case MotionEvent.ACTION_UP:
            	
				v.setBackgroundResource(R.drawable.check_connect);
           }
           return false;
        }
   };
	
	private OnTouchListener startButtonListener10 = new OnTouchListener(){
        public boolean onTouch(View v, MotionEvent event) {
           switch ( event.getAction() ) {
            case MotionEvent.ACTION_DOWN: 
            	
            	String relay1 = "<<1E";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay1.getBytes());
						}
						
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send 4... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
				Toast.makeText(getBaseContext(), "Not checked", Toast.LENGTH_SHORT).show();
                String relay2 = "<<1O";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay2.getBytes());
						}
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send 4... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
				
            case MotionEvent.ACTION_UP:
				v.setBackgroundResource(R.drawable.check_connect);
           }
           return false;
        }
   };
	
	private OnTouchListener startButtonListener9 = new OnTouchListener(){
        public boolean onTouch(View v, MotionEvent event) {
           switch ( event.getAction() ) {
            case MotionEvent.ACTION_DOWN: 
            	connection=true;
            	if(connect=true) v.setBackgroundResource(R.drawable.engine_start_pressed);
            	
            	String relay1 = "F";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay1.getBytes());
						}
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send ... 5",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
            case MotionEvent.ACTION_UP:
            	 String relay2 = "2b";
					try {
						if (outputStream != null) {
							synchronized (obj2) {
								outputStream.write(relay2.getBytes());
							}
						} else {
							Toast.makeText(getBaseContext(),
									"failed to send ... 5",
									Toast.LENGTH_SHORT).show();
						}
					} catch (IOException e) {
						Log.e(TAG, ">>", e);
						e.printStackTrace();
					}
           
           }
           return false;
        }
   };
	
	private OnTouchListener startButtonListener = new OnTouchListener(){
        public boolean onTouch(View v, MotionEvent event) {
           switch ( event.getAction() ) {
            case MotionEvent.ACTION_DOWN: 
            	v.setBackgroundResource(R.drawable.pressed_f);
            	String relay1 = "8w300;300";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay1.getBytes());
						}
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send start... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
				return false;
            case MotionEvent.ACTION_UP:
            	v.setBackgroundResource(R.drawable.unpressed_f);
            	String relay2 = "2H";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay2.getBytes());
						}
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send ... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
            }
           return false;
        }  
   };
//	
//   
   private OnTouchListener startButtonListener2 = new OnTouchListener(){
       public boolean onTouch(View v, MotionEvent event) {
          switch ( event.getAction() ) {
           case MotionEvent.ACTION_DOWN: 
        	   	v.setBackgroundResource(R.drawable.pressed_b);
           		String relay1 = "8w150;150";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay1.getBytes());
						}
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send start... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
				return false;
           case MotionEvent.ACTION_UP:
           	String relay2 = "2H";
           	v.setBackgroundResource(R.drawable.unpressed_b);
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay2.getBytes());
						}
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send ... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
           }
          return false;
       }  
  };
//  
  private OnTouchListener startButtonListener3 = new OnTouchListener(){
      public boolean onTouch(View v, MotionEvent event) {
         switch ( event.getAction() ) {
          case MotionEvent.ACTION_DOWN: 
        	  v.setBackgroundResource(R.drawable.pressed_l);
        	  String relay1 = "8w150;250";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay1.getBytes());
						}
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send start... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
				return false;
          case MotionEvent.ACTION_UP:
        	v.setBackgroundResource(R.drawable.unpressed_l);
          	String relay2 = "2H";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay2.getBytes());
						}
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send ... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
          }
         return false;
      }  
 };
// 
 private OnTouchListener startButtonListener4 = new OnTouchListener(){
     public boolean onTouch(View v, MotionEvent event) {
        switch ( event.getAction() ) {
         case MotionEvent.ACTION_DOWN: 
        	v.setBackgroundResource(R.drawable.pressed_r);
         	String relay1 = "8w250;150";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay1.getBytes());
						}
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send start... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
				return false;
         case MotionEvent.ACTION_UP:
        	 v.setBackgroundResource(R.drawable.unpressed_r);
         	String relay2 = "2H";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay2.getBytes());
						}
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send ... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
         }
        return false;
     }  
};
private OnTouchListener startButtonListener5 = new OnTouchListener(){
    public boolean onTouch(View v, MotionEvent event) {
       switch ( event.getAction() ) {
        case MotionEvent.ACTION_DOWN: 
        	v.setBackgroundResource(R.drawable.pressed_fl);
        	String relay1 = "8w250;300";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay1.getBytes());
						}
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send start... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
				return false;
        case MotionEvent.ACTION_UP:
        	v.setBackgroundResource(R.drawable.unpressed_fl);
        	String relay2 = "2H";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay2.getBytes());
						}
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send ... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
        }
       return false;
    }  
};
private OnTouchListener startButtonListener6 = new OnTouchListener(){
    public boolean onTouch(View v, MotionEvent event) {
       switch ( event.getAction() ) {
        case MotionEvent.ACTION_DOWN: 
        	v.setBackgroundResource(R.drawable.pressed_fr);
        	String relay1 = "8w300;250";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay1.getBytes());
						}
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send start... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
				return false;
        case MotionEvent.ACTION_UP:
        	v.setBackgroundResource(R.drawable.unpressed_rf);
        	String relay2 = "2H";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay2.getBytes());
						}
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send ... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
        }
       return false;
    }  
};
private OnTouchListener startButtonListener7 = new OnTouchListener(){
    public boolean onTouch(View v, MotionEvent event) {
       switch ( event.getAction() ) {
        case MotionEvent.ACTION_DOWN: 
        	v.setBackgroundResource(R.drawable.pressed_bl);
        	String relay1 = "8w150;100";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay1.getBytes());
						}
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send start... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
				return false;
        case MotionEvent.ACTION_UP:
        	v.setBackgroundResource(R.drawable.unpressed_bl);
        	String relay2 = "2H";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay2.getBytes());
						}
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send ... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
        }
       return false;
    }  
};
private OnTouchListener startButtonListener8 = new OnTouchListener(){
    public boolean onTouch(View v, MotionEvent event) {
       switch ( event.getAction() ) {
        case MotionEvent.ACTION_DOWN: 
        	v.setBackgroundResource(R.drawable.pressed_br);
        	String relay1 = "8w100;150";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay1.getBytes());
						}
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send start... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
				return false;
        case MotionEvent.ACTION_UP:
        	v.setBackgroundResource(R.drawable.unpressed_br);
        	String relay2 = "2H";
				try {
					if (outputStream != null) {
						synchronized (obj2) {
							outputStream.write(relay2.getBytes());
						}
					} else {
						Toast.makeText(getBaseContext(),
								"failed to send ... ",
								Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
					e.printStackTrace();
				}
        }
       return false;
    }  
};
//   
   

	/* after select, connect to device */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode != REQUEST_DISCOVERY) {
			finish();
			return;
		}
		if (resultCode != RESULT_OK) {
			finish();
			return;
		}
		final BluetoothDevice device = data
				.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		new Thread() {
			public void run() {
				connect(device);
			};
		}.start();
	}

	protected void onDestroy() {
		super.onDestroy();
		try {
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			Log.e(TAG, ">>", e);
		}
	}

	protected void connect(BluetoothDevice device) {
		try {
			Log.d(TAG, "³¢ÊÔÁ¬½Ó");
			// Create a Socket connection: need the server's UUID number of
			// registered
			Method m = device.getClass().getMethod("createRfcommSocket",
					new Class[] { int.class });
			socket = (BluetoothSocket) m.invoke(device, 1);
			socket.connect();
			Log.d(TAG, ">>Client connectted");
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
			int read = -1;
			final byte[] bytes = new byte[4];
			while (true) {
				synchronized (obj1) {
					read = inputStream.read(bytes);
					Log.d(TAG, "HAHAHA");
					Log.d(TAG, "read:" + read);
					if (read > 0) {
						final int count = read;
						String str = SamplesUtils.byteToHex(bytes, count);
						Log.d(TAG, str);
						test2=str;
						if(connection==true){
							Pattern p=Pattern.compile("3e");
							Matcher ma=p.matcher(test2);
							while(ma.find()){
								Log.d(TAG, "Connected!");
								connect=true;
							}
							connection=false;
						}
						if(obstacle==true){
							test+="Obstacle";
							obstacle=false;
						}
						if(ldr==true){
							test+="LDR";
							ldr=false;
						}
						test=test+str.toString();
						
						Log.d(TAG, test);
						String hex = hexString.toString();
						if (hex == "") {
							hexString.append("<--");
						} else {
							if (hex.lastIndexOf("<--") < hex.lastIndexOf("-->")) {
								hexString.append("\n<--");
							}
						}
						hexString.append(str);
						hex = hexString.toString();
//						Log.d(TAG, "test2:" + hex);
						if (hex.length() > maxlength) {
							try {
								hex = hex.substring(hex.length() - maxlength,
										hex.length());
								hex = hex.substring(hex.indexOf(" "));
								hex = "<--" + hex;
								hexString = new StringBuffer();
								hexString.append(hex);
							} catch (Exception e) {
								e.printStackTrace();
								Log.e(TAG, "e", e);
							}
						}
						_handler.post(new Runnable() {
							public void run() {
							}
						});
					}
				}
			}
		} catch (Exception e) {
//			if(test=="Liu Su 3e 3e 31 42 30 0a ") Log.d(TAG, "Correct");
//			else Log.d(TAG, "Incorrect");
			Log.e(TAG, ">>", e);
			Toast.makeText(getBaseContext(),
					getResources().getString(R.string.ioexception),
					Toast.LENGTH_SHORT).show();
			return;
		} finally {
			if (socket != null) {
				try {
					Log.d(TAG, ">>Client Socket Close");
					socket.close();
					socket = null;
					// this.finish();
					return;
				} catch (IOException e) {
					Log.e(TAG, ">>", e);
				}
			}
		}
		
	}
	
	
}

