package rl_rpg.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ProgressBar;


public class MyAccountActivity extends Activity
{
ProgressBar p1, p2, p3;
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_my_account );
		p1=(ProgressBar) findViewById( R.id.progressBar1 );
		p1.setProgress( 25 );
		p2=(ProgressBar) findViewById( R.id.progressBar2 );
		p2.setProgress( 75 );
		p3=(ProgressBar) findViewById( R.id.progressBar3 );
		p3.setProgress( 55 );
	}


	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.my_account, menu );
		return true;
	}

}
