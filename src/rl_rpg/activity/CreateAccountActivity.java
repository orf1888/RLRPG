package rl_rpg.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class CreateAccountActivity extends Activity
{

	Spinner comoboGroup;
	Button createAccount;
	EditText nick, password, conformPassword, email;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_create_account );

		// init Views
		comoboGroup= (Spinner) findViewById( R.id.spinnerGroup );
		createAccount= (Button) findViewById( R.id.btnCreateAccountCreate );
		nick= (EditText) findViewById( R.id.editTexNick );
		password= (EditText) findViewById( R.id.editTextPassword );
		conformPassword= (EditText) findViewById( R.id.editTextPaswordConform );
		email= (EditText) findViewById( R.id.editTextEmail );
		//init combo
		ArrayList<String> list= new ArrayList<String>();
		for( int i= 0; i < 15; i++ ) {
			list.add( "Specjalnoœæ " + i );
		}
		ArrayAdapter<String> dataAdapter= new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, list );
		dataAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		comoboGroup.setAdapter( dataAdapter );
	}



	//Getters Setters ewentualnie po validacji danych TU tworzyæ encje klasy Profil
	public Spinner getComoboGroup()
	{
		return comoboGroup;
	}

	public void setComoboGroup( Spinner comoboGroup )
	{
		this.comoboGroup= comoboGroup;
	}

	public EditText getNick()
	{
		return nick;
	}

	public void setNick( EditText nick )
	{
		this.nick= nick;
	}

	public EditText getPassword()
	{
		return password;
	}

	public void setPassword( EditText password )
	{
		this.password= password;
	}

	public EditText getConformPassword()
	{
		return conformPassword;
	}

	public void setConformPassword( EditText conformPassword )
	{
		this.conformPassword= conformPassword;
	}

	public EditText getEmail()
	{
		return email;
	}

	public void setEmail( EditText email )
	{
		this.email= email;
	}


}
