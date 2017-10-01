package com.alex.android.cloudpay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class CashActivity extends AppCompatActivity {

    private String fake_atm = "";

    private Button getCashButton;
    private EditText cashAmountEditText;

    private DatabaseReference mbankAccountDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);

//        initialize table
//        mbankAccountDatabaseReference =  MenuActivity.mFirebaseDatabase.getReference().child("bank_account");
        mbankAccountDatabaseReference =  MenuActivity.mFirebaseDatabase.getReference();

        cashAmountEditText = (EditText) findViewById(R.id.cashAmountId);
        getCashButton = (Button) findViewById(R.id.getCashNowId);
        getCashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cashAmount = cashAmountEditText.getText().toString();
                int cashToDespenseInt = Integer.parseInt(cashAmount);
                Log.i("HI", cashAmount);

                dispenseCash(cashToDespenseInt);
            }
        });
    }

    private void dispenseCash(int cashToDispense){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mbankAccountDatabaseReference.child("bank_accounts").child(uid).child("withdraw").setValue(cashToDispense);
//        mbankAccountDatabaseReference.child("bank_accounts").

//        mbankAccountDatabaseReference.child("bank_accounts").child(uid).child("balance").getKey(
    }
}
