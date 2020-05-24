package com.navihus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.EditText;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

public class LoginActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String s = "";
	private HashMap<String, Object> m = new HashMap<>();
	
	private ArrayList<String> ls = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> lm = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear14;
	private LinearLayout linear13;
	private LinearLayout unl;
	private LinearLayout linear12;
	private TextView go;
	private TextView textview1;
	private ImageView unp;
	private EditText un;
	private ImageView imageview6;
	private EditText pwd;
	private ImageView imageview7;
	private ImageView imageview8;
	
	private Intent i = new Intent();
	private SharedPreferences sp;
	private DatabaseReference Users = _firebase.getReference("/UserInfo/");
	private ChildEventListener _Users_child_listener;
	private AlertDialog.Builder d;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.login);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		unl = (LinearLayout) findViewById(R.id.unl);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		go = (TextView) findViewById(R.id.go);
		textview1 = (TextView) findViewById(R.id.textview1);
		unp = (ImageView) findViewById(R.id.unp);
		un = (EditText) findViewById(R.id.un);
		imageview6 = (ImageView) findViewById(R.id.imageview6);
		pwd = (EditText) findViewById(R.id.pwd);
		imageview7 = (ImageView) findViewById(R.id.imageview7);
		imageview8 = (ImageView) findViewById(R.id.imageview8);
		sp = getSharedPreferences("sp", Activity.MODE_PRIVATE);
		d = new AlertDialog.Builder(this);
		
		go.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!(un.getText().toString().equals("") || pwd.getText().toString().equals(""))) {
					if (ls.contains(un.getText().toString().toLowerCase())) {
						if (lm.get((int)ls.indexOf(un.getText().toString().toLowerCase())).get("Password").toString().equals(pwd.getText().toString())) {
							sp.edit().putString("un", un.getText().toString().toLowerCase()).commit();
							sp.edit().putString("login", "y").commit();
							i.setClass(getApplicationContext(), HomeActivity.class);
							startActivity(i);
						}
						else {
							pwd.setError("Wrong password");
						}
					}
					else {
						d.setTitle("Error");
						d.setMessage("No User Found");
						d.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								
							}
						});
						d.create().show();
					}
				}
				else {
					un.setError("This is required");
					pwd.setError("This is required");
				}
			}
		});
		
		textview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), RegisterActivity.class);
				startActivity(i);
			}
		});
		
		_Users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				Users.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						lm = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								lm.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						ls.add(_childKey);
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		Users.addChildEventListener(_Users_child_listener);
	}
	private void initializeLogic() {
		android.graphics.drawable.GradientDrawable intro = new
		android.graphics.drawable.GradientDrawable();
		intro.setColor(Color.parseColor("#000000"));
		intro.setStroke(5,
		Color.parseColor("#4CAF50"));
		intro.setCornerRadius(50);
		go.setBackground(intro);
		pwd.setInputType (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
