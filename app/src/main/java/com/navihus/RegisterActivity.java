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
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
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
import android.view.View;

public class RegisterActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> m = new HashMap<>();
	private String t = "";
	private String s = "";
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private ImageView imageview1;
	private ImageView imageview8;
	private ImageView pi;
	private LinearLayout unl;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private LinearLayout linear13;
	private TextView textview1;
	private TextView go;
	private TextView textview2;
	private ImageView unp;
	private EditText un;
	private ImageView imageview3;
	private EditText n;
	private ImageView imageview4;
	private EditText dob;
	private ImageView imageview5;
	private EditText bio;
	private ImageView imageview6;
	private EditText npwd;
	private ImageView imageview7;
	private EditText cpwd;
	
	private Intent i = new Intent();
	private SharedPreferences sp;
	private DatabaseReference Users = _firebase.getReference("/UserInfo/");
	private ChildEventListener _Users_child_listener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.register);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		imageview8 = (ImageView) findViewById(R.id.imageview8);
		pi = (ImageView) findViewById(R.id.pi);
		unl = (LinearLayout) findViewById(R.id.unl);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		textview1 = (TextView) findViewById(R.id.textview1);
		go = (TextView) findViewById(R.id.go);
		textview2 = (TextView) findViewById(R.id.textview2);
		unp = (ImageView) findViewById(R.id.unp);
		un = (EditText) findViewById(R.id.un);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		n = (EditText) findViewById(R.id.n);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		dob = (EditText) findViewById(R.id.dob);
		imageview5 = (ImageView) findViewById(R.id.imageview5);
		bio = (EditText) findViewById(R.id.bio);
		imageview6 = (ImageView) findViewById(R.id.imageview6);
		npwd = (EditText) findViewById(R.id.npwd);
		imageview7 = (ImageView) findViewById(R.id.imageview7);
		cpwd = (EditText) findViewById(R.id.cpwd);
		sp = getSharedPreferences("sp", Activity.MODE_PRIVATE);
		
		go.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!(((un.getText().toString().equals("") || n.getText().toString().equals("")) || dob.getText().toString().equals("")) || (bio.getText().toString().equals("") || (npwd.getText().toString().equals("") || cpwd.getText().toString().equals(""))))) {
					if (npwd.getText().toString().equals(cpwd.getText().toString())) {
						if (npwd.getText().toString().length() > 5) {
							if (15 > un.getText().toString().length()) {
								if (true) {
									m = new HashMap<>();
									t = un.getText().toString().toLowerCase();
									m.put("Username", t.replace(" ", "_"));
									m.put("Name", n.getText().toString());
									m.put("DOB", dob.getText().toString());
									m.put("Bio", bio.getText().toString());
									m.put("Password", npwd.getText().toString());
									m.put("Profile picture", "gs://vispark66.appspot.com/us/default");
									Users.child(t.replace(" ", "_")).updateChildren(m);
									m.clear();
									SketchwareUtil.showMessage(getApplicationContext(), "Account Created!");
									sp.edit().putString("un", t.replace(" ", "_")).commit();
									sp.edit().putString("login", "y").commit();
									i.setClass(getApplicationContext(), HomeActivity.class);
									startActivity(i);
								}
								else {
									un.setError("Already taken");
								}
							}
							else {
								un.setError("Maximum lenght is 15");
							}
						}
						else {
							npwd.setError("Password minimum lenght is 6");
						}
					}
					else {
						npwd.setError("Password doesn't match");
						cpwd.setError("Password doesn't match");
					}
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Fields Required");
				}
			}
		});
		
		textview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), LoginActivity.class);
				startActivity(i);
			}
		});
		
		_Users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
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
		cpwd.setInputType (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		npwd.setInputType (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
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
