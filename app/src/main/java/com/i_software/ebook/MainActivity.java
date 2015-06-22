package com.i_software.ebook;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String prefixURL = "http://mybooks.com.hk/textbook2/login?access_token=i6FHCYlILVKvUXsacwnH&login_key=";
        String postFixURL;
        String fileName = "/ebook.txt";
        String fullURL;
        String path;



       // File file = new File(fileName);
        File folder;
        folder = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.i_software.ebook");
        File file = new File(folder + fileName);
     Toast.makeText(getApplicationContext(), file.getAbsolutePath().toString(),
             Toast.LENGTH_SHORT).show();


            if (file.exists()) {
                //  postFixURL = read_file(getApplicationContext(), fileName);
                path = folder + fileName;
                postFixURL = readFile(path);
                Toast.makeText(getApplicationContext(), postFixURL,
                        Toast.LENGTH_SHORT).show();
                fullURL = prefixURL + postFixURL;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setData(Uri.parse(fullURL));
                startActivity(i);
                this.finish();
            }

        if (!file.exists()) {
            folder.mkdirs();

            path = folder.getAbsoluteFile().toString();

            Toast.makeText(getApplicationContext(), path ,
                    Toast.LENGTH_SHORT).show();
        }




    }





    public String readFile(String fileName) {
        BufferedReader br = null;
        String response = null;


        try {

            StringBuffer output = new StringBuffer();


            br = new BufferedReader(new FileReader(fileName));
            String line = "";
            while ((line = br.readLine()) != null) {
                output.append(line +"");
            }
            response = output.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
        return response;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
