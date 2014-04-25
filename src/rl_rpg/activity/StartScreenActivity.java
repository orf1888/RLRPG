package rl_rpg.activity;

import rl_rpg.utils.Utils;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
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
		this.requestWindowFeature( Window.FEATURE_NO_TITLE );
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_start_screen );
		//init Views
		signIn= (Button) findViewById( R.id.btnSignIn );
		signIn.setOnClickListener( signInListener );
		createAccount= (Button) findViewById( R.id.btnCreateAccount );
		createAccount.setOnClickListener(createAccountListener);
		rememberMe= (CheckBox) findViewById( R.id.checkBoxRememberMe );
		//set button width
		Utils.setButtonWidth( createAccount, StartScreenActivity.this );
		Utils.setButtonWidth( signIn, StartScreenActivity.this );
	}


	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.start_screen, menu );
		return true;
	}

	OnClickListener signInListener= new OnClickListener()
	{

		@Override
		public void onClick( View v )
		{
			//1.dodac activity tworzenia konta
			Utils.startNewActivity( StartScreenActivity.this, MainActivity.class, null );
		}
	};

	OnClickListener createAccountListener= new OnClickListener()
	{

		@Override
		public void onClick( View v )
		{
			Utils.startNewActivity( StartScreenActivity.this, CreateAccountActivity.class, null );

		}
	};

	OnClickListener rememberMeListener= new OnClickListener()
	{

		@Override
		public void onClick( View v )
		{
			// TODO Auto-generated method stub

		}
	};
}
