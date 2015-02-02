package com.rowsun.attendance;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;

import static android.view.View.*;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

   /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    ListView list;
    ArrayList<String> arrayList;
    ArrayAdapter ll;
    int i;
    excelRead er=new excelRead();
    public MainActivity() {
    }

    public void getList(int xx) throws Exception {
        String sdPath = Environment.getExternalStorageDirectory().getPath() + File.separator+"Attendance"+File.separator+"karkhanaAttendance.xlsx";
        File f = new File(sdPath);
        File g=new File(Environment.getExternalStorageDirectory().getPath() + File.separator+"Attendance"+File.separator);
        if(f.exists())
        {

        }
        else
        {
           g.mkdirs();
           new generateExcel();
        }
        excelRead er=new excelRead();
        list = (ListView) findViewById(R.id.listView);

        try {
            arrayList=new ArrayList<String>();
            for(i=0;i<er.excelRead(xx).length;i++)
            {
                arrayList.add(i, er.excelRead(xx)[i]);
            }
            ll = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,arrayList);
            list.setAdapter(ll);

        } catch (Exception e) {
            Toast.makeText(MainActivity.this,e.getMessage()+"",Toast.LENGTH_SHORT).show();
        }

        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }
    int n=0;
    public void addUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View custom = getLayoutInflater().inflate(R.layout.dialog_add_user, null);
        builder.setView(custom).setPositiveButton("Save",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText name=(EditText)custom.findViewById(R.id.name);
                String aa=name.getText().toString();
                if(aa.length()>0){
                try {
                    new Addstudent(aa,n);
                    getList(n);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT);
                }
            }else{
                    Toast.makeText(MainActivity.this,"Name Empty",Toast.LENGTH_SHORT);
                }
            }
        }).setNegativeButton("Cancel",null).create().show();


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            clearData cr=new clearData();
            cr.clearAll();
        } catch (Exception e) {
            Toast.makeText(this,"Unable To delete",Toast.LENGTH_SHORT).show();
        }
        try {
            getList(n);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Button save=(Button)findViewById(R.id.save);
        save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected = "";
                int[] checked=new int[list.getCheckedItemCount()];
                int cntChoice = list.getCount();
                SparseBooleanArray sparseBooleanArray = list.getCheckedItemPositions();
                excelWrite ew=new excelWrite();
                int j=0;
                for(int i = 0; i < cntChoice; i++)
                    if (sparseBooleanArray.get(i)) {
                        checked[j] = i;
                        j++;
                        selected += list.getItemAtPosition(i).toString() + "\n";
                    }
                try {
                    ew.write(checked,n);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Saved",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this,selected,Toast.LENGTH_LONG).show();

            }
        });


        Button btn=(Button)findViewById(R.id.newStd);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    protected void toast(String string) {
        Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        excelRead er=new excelRead();
        switch (number) {
            case 1:
                n=0;
                try {
                   mTitle = er.sheetName(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    getList(n);
                }catch(Exception e)
                {
                    Toast.makeText(MainActivity.this,e.getMessage()+" ",Toast.LENGTH_SHORT).show();
                }
                list.invalidateViews();
                break;
            case 2:

                n=1;
                try {
                    mTitle = er.sheetName(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    getList(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                list.invalidateViews();
                break;
            case 3:

                n=2;
                try {
                    mTitle = er.sheetName(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    getList(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                list.invalidateViews();
                break;
            case 4:
                n=3;
                try {
                    mTitle = er.sheetName(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    getList(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                list.invalidateViews();
                break;
            case 5:
                n=4;
                try {
                  mTitle = er.sheetName(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    getList(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                list.invalidateViews();
                break;
            case 6:
                n=5;
                try {
                   mTitle = er.sheetName(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    getList(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                list.invalidateViews();
                break;
            case 7:
                n=6;
                try {
                    mTitle = er.sheetName(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    getList(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                list.invalidateViews();
                break;
            case 8:
                n=7;
                try {
                    mTitle = er.sheetName(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    getList(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                list.invalidateViews();
                break;
            case 9:
                n=8;
                try {
                    mTitle = er.sheetName(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    getList(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                list.invalidateViews();
                break;
            case 10:
                n=9;
                try {
                    mTitle = er.sheetName(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    getList(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                list.invalidateViews();
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
           restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
           {

            }
        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
