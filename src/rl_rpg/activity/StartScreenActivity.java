package rl_rpg.activity;

import rl_rpg.utils.Utils;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;


public class StartScreenActivity extends Activity
{

	Button signIn, createAccount;
	CheckBox rememberMe;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_start_screen );
		getActionBar().hide();
		//init Views
		signIn=(Button) findViewById( R.id.btnSignIn );
		signIn.setOnClickListener( signInListener );
		createAccount=(Button) findViewById( R.id.btnCreateAcoount );
		rememberMe=(CheckBox) findViewById( R.id.checkBoxRememberMe );
	}


	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.start_screen, menu );
		return true;
	}

	OnClickListener signInListener=new OnClickListener()
	{
		
		@Override
		public void onClick( View v )
		{
			//1.dodac activity tworzenia konta
			Utils.startNewActivity( StartScreenActivity.this, MainActivity.class, null );	
		}
	};
	
	OnClickListener createAccountListener=new OnClickListener()
	{
		
		@Override
		public void onClick( View v )
		{
			// TODO Auto-generated method stub
			
		}
	};
	
	OnClickListener rememberMeListener=new OnClickListener()
	{
		
		@Override
		public void onClick( View v )
		{
			// TODO Auto-generated method stub
			
		}
	};
}
