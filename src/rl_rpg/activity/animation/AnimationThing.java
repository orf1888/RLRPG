package rl_rpg.activity.animation;

import java.util.HashMap;
import java.util.Map;

import rl_rpg.activity.RLRPGApplication;
import rl_rpg.activity.RLRPGApplication.LoopListener;
import android.app.Activity;


public class AnimationThing
{
	String name;
	Activity parentActivity;
	Object obj;
	boolean enabled;
	OnTickListener listener;
	int tmpTick;

	public AnimationThing( String name, Object obj, Activity parentActivity, OnTickListener listener )
	{
		this.name= name;
		this.obj= obj;
		this.parentActivity= parentActivity;
		this.listener= listener;
		enabled= false;
		Manager.instance().add( this );
	}

	public interface OnTickListener
	{
		public void onTick( Object data, int tick );
	}

	public void onTick( int tick )
	{
		tmpTick= tick;
		parentActivity.runOnUiThread( func );
	}

	Runnable func= new Runnable()
	{
		@Override
		public void run()
		{
			if( enabled && listener != null )
				listener.onTick( obj, tmpTick );
		}
	};

	public void start()
	{
		enabled= true;
	}

	public void stop()
	{
		enabled= false;
	}


	public static class Manager
	{
		Map<String, AnimationThing> data= null;

		public void init()
		{
			data= new HashMap<String, AnimationThing>();
			RLRPGApplication.addLoopListener( new AnimationLoopListener() );
		}


		public void add( AnimationThing a )
		{
			data.put( a.name, a );
		}


		//
		// singleton
		//

		private static final Manager INSTANCE= new Manager();

		public static Manager instance()
		{
			return INSTANCE;
		}

		//
		// listenery
		//

		static class AnimationLoopListener implements LoopListener
		{
			int ticks= 0;

			@Override
			public void onTick()
			{
				for( AnimationThing anim : Manager.instance().data.values() )
					anim.onTick( ticks );
				++ticks;
			}

			@Override
			public String getNamePrefix()
			{
				return "AnimationThing";
			}
		}
	}
}
