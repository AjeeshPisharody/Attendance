package com.rowsun.attendance;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

import java.io.File;


public class setting extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        final String sdPath = Environment.getExternalStorageDirectory().getPath() + File.separator+"Attendance"+File.separator;
        Button btn=(Button)findViewById(R.id.clear);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Workbook workbook = new Workbook(sdPath + "karkhanaAttendance.xlsx");
                    for(int i=10;i<workbook.getWorksheets().getCount();i++) {
                        workbook.getWorksheets().removeAt(i);
                    }
                    workbook.save(sdPath + "karkhanaAttendance.xlsx");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
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
