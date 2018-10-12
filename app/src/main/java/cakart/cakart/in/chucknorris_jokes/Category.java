package cakart.cakart.in.chucknorris_jokes;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static java.util.Calendar.getInstance;

public class Category extends AppCompatActivity {

    String category;
    ImageView image;
    TextView textView;
    ProgressDialog progressDialog;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        category = getIntent().getStringExtra("category");
        download_joke(category);

      button=findViewById(R.id.next_joke);
      image=findViewById(R.id.icon_url);
      textView=findViewById(R.id.value);

      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              download_joke(category);
          }
      });

    }
    private void download_joke(String category) {
        if(progressDialog==null){
            progressDialog=new ProgressDialog(Category.this);
            progressDialog.setMessage("Loading....");
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, "https://api.chucknorris.io/jokes/random?category="+category, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response","aaaaa"+response);
                        if(response==null){
                            Toast.makeText(Category.this,"No Internet Try again!",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            return;
                        }
                        try{
                           // JSONArray categories=response.getJSONArray("category");
                          // String icon_url=response.getString("icon_url");
                            String value=response.getString("value");
                            progressDialog.dismiss();
                           // Glide.with(getApplicationContext()).load(icon_url).into(image);
                            textView.setText(value);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                progressDialog.hide();
            }
        });
        RequestQueue q= Volley.newRequestQueue(getApplicationContext());
        q.add(jsonObjectRequest);
    }
}

