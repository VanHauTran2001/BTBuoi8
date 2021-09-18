package com.example.buoi8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.buoi8.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ActivityMainBinding binding;
    SimpleDateFormat simpleDateFormat;
    Calendar calendar;
    Calendar calendar1;
    int vitri = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.txtDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DATE);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                        binding.txtDays.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                } , nam , thang , ngay);
                datePickerDialog.show();
            }
        });
        binding.txtHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDateFormat = new SimpleDateFormat("HH:mm");
                calendar1 = Calendar.getInstance();
                int hour = calendar1.get(Calendar.HOUR);
                int munite = calendar1.get(Calendar.MINUTE);
               TimePickerDialog timePickerDialog= new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                   @Override
                   public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                       calendar1.set(0,0,0,hourOfDay,minute);
                       binding.txtHours.setText(simpleDateFormat.format(calendar1.getTime()));
                   }
               },hour,munite,true);
               timePickerDialog.show();
            }
        });
        binding.btnTune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this,v);
                popup.inflate(R.menu.custom_popup);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return menuClick(item);
                    }
                });
                popup.show();
            }
        });
        String[] courses = { "Work","Friend","Family"};
        binding.spiner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,courses);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spiner.setAdapter(arrayAdapter);

        binding.txtTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogMutilTag();
            }
        });
        binding.txtWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dilogMutilWeek();
            }
        });
    }
    private boolean menuClick(MenuItem item){
        switch (item.getItemId()){
            case R.id.popup_file:
               files();
                break;
            case R.id.popup_defaults:
               defaults();
                break;

        }
        return true;
    }
    private void files(){
        String[] strings = {"Nexus Tune","Winphone tune","Peep tune","Nokia tune","Etc"};
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        vitri = which;
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,strings[vitri], Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
    private void defaults(){
        String[] datas = {"Nexus Tune","Winphone tune","Peep tune","Nokia tune","Etc"};
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setSingleChoiceItems(datas, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        vitri = which;
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,datas[vitri], Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

    }

    private void dialogMutilTag(){
        String[] strings = new String[]{
                "Family","Game","Android","VTC","Friend"
        };
        final boolean[] checkedString = new boolean[]{
                false, false, false, false, false

        };
        final List<String> list = Arrays.asList(strings);

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Choose tags : ")
                .setMultiChoiceItems(strings,checkedString, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked){

                        }


                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        binding.txtTag.setText("");
                        for (int i = 0; i<checkedString.length; i++){
                            boolean checked = checkedString[i];
                            if (checked) {
                                binding.txtTag.setText(binding.txtTag.getText() + list.get(i)+ " , ");
                            }
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
    private void dilogMutilWeek(){
        String[] strings = new String[]{
               "Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"
        };
        final boolean[] checkedString = new boolean[]{
                false, false, false, false, false,false,false

        };
        final List<String> list = Arrays.asList(strings);

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Choose tags : ")
                .setMultiChoiceItems(strings,checkedString, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked){
                        }


                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        binding.txtWeek.setText("");
                        for (int i = 0; i<checkedString.length; i++){
                            boolean checked = checkedString[i];
                            if (checked) {
                                binding.txtWeek.setText(binding.txtWeek.getText()+ list.get(i)+ " , ");
                            }
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.custom_menu,menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}