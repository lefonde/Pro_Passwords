package com.projects.pro_passwords;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupFragment extends Fragment implements OnClickListener    {
	
	EditText usernameEdit;
	EditText passwordEdit;
	String username;
	String password;
	
	public SignupFragment(){
		
	}
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        
	}
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
		
		 View signupView = getActivity().getLayoutInflater().inflate(R.layout.fragment_signup, null);
		 Button signup = (Button) signupView.findViewById(R.id.buttonsignup);
		 signup.setOnClickListener(this);
		 
		 usernameEdit = (EditText) signupView.findViewById(R.id.usernameRegister);
		 passwordEdit = (EditText) signupView.findViewById(R.id.passwordResgisteration);
	            return signupView;
	        
	 }

	@Override
	public void onClick(View v) {
		 switch(v.getId()){
		 	case R.id.buttonsignup:
		 		username = usernameEdit.getText().toString();
		 		password = passwordEdit.getText().toString();
            	//UsersDataSource usersSource =((MainActivity) getActivity()).usersSource;
		 		UsersDataSource usersSource = new UsersDataSource(getActivity());
            	usersSource.open();
            	usersSource.createUser(username , password);
            	usersSource.close();
            	UsersDataSource usersSourceTest = new UsersDataSource(getActivity());
            	usersSourceTest.open();
            	Toast.makeText(getActivity(), usersSourceTest.getPassword(username), Toast.LENGTH_LONG).show();
            	usersSourceTest.close();
            	
		 		
		 	break;
		
		 }
	}
	 


}
