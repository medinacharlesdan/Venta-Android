package charles.sandbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener{
    private Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        logoutBtn = (Button) findViewById(R.id.logoutbtn);
        logoutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.logoutbtn:

                FirebaseAuth.getInstance().signOut();
                Toast.makeText(this, "signed out", Toast.LENGTH_SHORT).show();
                Intent main = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(main);
                break;
        }
    }
}
