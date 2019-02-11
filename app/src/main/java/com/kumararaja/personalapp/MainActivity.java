package com.kumararaja.personalapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView tx;
    String[]s={"In-Hand","Bank"};

    DatabaseHelper myDb = new DatabaseHelper(this);
//    SQLiteDatabase ddb=myDb.getReadableDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displayedSeleceted(R.id.nav_camera);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            final ArrayAdapter<String>adp=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);
           // tx= (TextView)findViewById(R.id.txt1);
/*            final Spinner sp = new Spinner(MainActivity.this);

            sp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            sp.setAdapter(adp);*/

            AlertDialog.Builder at=new AlertDialog.Builder(this);

            at.setTitle("Choose Source");
            at.setSingleChoiceItems(s, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (s[i].equals("In-Hand")) {
                        Toast.makeText(MainActivity.this, "" + s[i], Toast.LENGTH_SHORT).show();
                        Intent kk = new Intent(MainActivity.this, HandCash.class);
                        startActivity(kk);
                    } else {
                        Toast.makeText(MainActivity.this, "" + s[i], Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                        Intent kk = new Intent(MainActivity.this, AddBank.class);
                        startActivity(kk);
                        // return true;
                    }
                }
            });

            AlertDialog alert=at.create();
            alert.show();
            //final AlertDialog ad=at.create();

            /**/
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayedSeleceted(int itemId){
        SQLiteDatabase d=myDb.getReadableDatabase();
        Cursor cc=d.rawQuery("SELECT * FROM cdtable",null);
        Cursor cursor=d.rawQuery("SELECT * FROM banktable",null);
        boolean rowexist;
        String title="";
        Fragment fragment=null;


        switch (itemId){
            case R.id.nav_camera:
                fragment=new HomeActivity();
                title="Home";
                break;
            case R.id.nav_gallery:
                if (cc.moveToFirst()){
                rowexist=true;
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                fragment=new MiniStatement();
                title="Ministatement";
            } else {
                if (cursor.moveToFirst()){
                    rowexist=true;
                    Toast.makeText(this, "No Transactions are available", Toast.LENGTH_SHORT).show();
                } else {
                  /*  Intent i =new Intent(this,AddBank.class);
                    startActivity(i);*/
                    Toast.makeText(this, "Please add bank details", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            }

                break;
            case R.id.nav_slideshow:
                if (cursor.moveToFirst()) {
                    rowexist = true;
                    Toast.makeText(this, "bank details", Toast.LENGTH_SHORT).show();
                    fragment=new AddCredit();
                    title="Credit";
                } else {
                    rowexist=false;
                    Toast.makeText(this, "Please add bank details", Toast.LENGTH_SHORT).show();
                    /*Intent i =new Intent(this,AddBank.class);
                    startActivity(i);*/
                }
                break;
            case R.id.nav_manage:
                if (cursor.moveToFirst()) {
                    rowexist = true;
                    Toast.makeText(this, "bank details", Toast.LENGTH_SHORT).show();
                    fragment=new AddDebit();
                    title="Debit";
                } else {
                    rowexist=false;
                   /* Intent i =new Intent(this,AddBank.class);
                    startActivity(i);*/
                    Toast.makeText(this, "Please add bank details", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.nav_share:
                if (cc.moveToFirst()){
                 rowexist=true;
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                    Intent share=new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                    startActivity(Intent.createChooser(share,"SHARE"));
                   // createPdf();
                } else {
                    if (cursor.moveToFirst()){
                        rowexist=true;
                        Toast.makeText(this, "No Transactions are available", Toast.LENGTH_SHORT).show();
                    } /*else {
                        Intent i =new Intent(this,AddBank.class);
                        startActivity(i);*/
                        Toast.makeText(this, "Please add bank details", Toast.LENGTH_SHORT).show();
                    //}
                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                }
        }

        if (fragment!=null){
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame,fragment);
            getSupportActionBar().setTitle(title);
            /*getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);*/
            ft.commit();
        }

        DrawerLayout dl=(DrawerLayout)findViewById(R.id.drawer_layout);
        dl.closeDrawer(GravityCompat.START);
    }

    /*private void createPdf() throws FileNotFoundException, DocumentException {

        String dir=Environment.getExternalStorageDirectory()+File.separator+"mylogs";
        File folder=new File(dir);
        folder.mkdir();

        File file=new File(dir,"ministate.pdf");

        Document doc=new Document();
        PdfWriter.getInstance(doc,new FileOutputStream(file));
        doc.open();


        Paragraph p=new Paragraph();
        p.add("Your Ministatement /n");

        PdfTable table=new PdfTable(4);
        table.addCell("Amount");
        table.addCell("Date");
        table.addCell("Description");
        table.addCell("Bank");
        table.addCell("Type");

        while (cc.moveToNext()){
            String amount=cc.getString(1);
            String date=cc.getString(2);
            String desc=cc.getString(3);
            String bank=cc.getString(4);
            String type=cc.getString(5);

            table.addCell(amount);
            table.addCell(date);
            table.addCell(desc);
            table.addCell(bank);
            table.addCell(type);
        }

        doc.add(table);
        doc.addCreationDate();
        doc.close();

    }*/

    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        displayedSeleceted(item.getItemId());
        return true;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main,menu);
    }*/

   /* @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        *//*} else if (id == R.id.nav_send) {*//*

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
}
