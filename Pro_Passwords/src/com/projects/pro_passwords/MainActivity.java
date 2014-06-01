package com.projects.pro_passwords;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	UsersDataSource usersSource;
	User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();   
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements OnClickListener {
    	
    	

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            
            Button chat = (Button) rootView.findViewById(R.id.buttonEnter);
            chat.setOnClickListener(this);

            Button signUp = (Button) rootView.findViewById(R.id.buttonSignUp);
            signUp.setOnClickListener(this);
            
            return rootView;
        }

		@Override
		public void onClick(View arg0) {
			 switch(arg0.getId()){
			 	case R.id.buttonEnter:
			 		UsersDataSource usersSource = new UsersDataSource(getActivity());
	            	usersSource.open();
	            	
	            	EditText passwordEdit = (EditText) getActivity().findViewById(R.id.password);
	            	String password = passwordEdit.getText().toString();
			 		EditText usernameEdit = (EditText) getActivity().findViewById(R.id.username);
			 		String username = usernameEdit.getText().toString();
			 		
			 		if(usersSource.getPassword(username).equals(password))
			 		{
				 		FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
		            	fragmentTransaction1.replace(R.id.container, new ChatFragment(), "chatfragment");
		            	fragmentTransaction1.addToBackStack("chatfragment");
		            	fragmentTransaction1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		            	fragmentTransaction1.commit();
			 		}
			 		
			 		else
			 		{
			 			Toast.makeText(getActivity().getApplicationContext(), "Wrong username or password.",
			 				   Toast.LENGTH_LONG).show();
			 		}
			 		usersSource.close();
			 	break;
	           
			 	case R.id.buttonSignUp:
			 		
	            	FragmentTransaction fragmentTransaction2 = getFragmentManager().beginTransaction();
	            	fragmentTransaction2.replace(R.id.container, new SignupFragment(), "signupfragment");
	            	fragmentTransaction2.addToBackStack("signupfragment");
	            	fragmentTransaction2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
	            	fragmentTransaction2.commit();
	            break;
			
		}
    }

}

}
