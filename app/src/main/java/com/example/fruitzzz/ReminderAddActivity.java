package com.example.fruitzzz;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class ReminderAddActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private static final int EXISTING_VEHICLE_LOADER = 0;

    private Toolbar toolbar;
    private EditText titleText;
    private TextView dateText, timeText, repeatText, repeatIntText, repeatTypeText;
    private FloatingActionButton mFAB1;
    private FloatingActionButton mFAB2;
    private Calendar calendar;
    private int mYear, mMonth, mMinute, mDay, mHour;
    private long repeatTime;
    private Switch repeatSwitch;
    private String mTitle;
    private String mTime;
    private String mDate;
    private String mRepeat;
    private String mRepeatInt;
    private String mRepeatType;
    private String mActive;

    // Constant values in milisecond
    private static final long milMinute = 60000L;
    private static final long milHour = 3600000L;
    private static final long milDay = 86400000L;
    private static final long milWeek = 604800000L;
    private static final long milMonth = 2592000000L;

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_add);

        // initialize views
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        titleText = (EditText) findViewById(R.id.title_reminder);
        dateText = (TextView) findViewById(R.id.set_date);
        timeText = (TextView) findViewById(R.id.set_time);
        repeatText = (TextView) findViewById(R.id.set_repeat);
        repeatIntText = (TextView) findViewById(R.id.set_rep_int);
        repeatTypeText = (TextView) findViewById(R.id.set_rep_type);
        repeatSwitch = (Switch) findViewById(R.id.repeat_switch);
        mFAB1 = (FloatingActionButton) findViewById(R.id.starred1);
        mFAB2 = (FloatingActionButton) findViewById(R.id.starred2);

        // initialize default values
        mActive = "true";
        mRepeat = "true";
        mRepeatInt = Integer.toString(1);
        mRepeatType = "Hour";

        calendar = Calendar.getInstance();
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DATE);

        mDate = mDay + " - " + mMonth + " - " + mYear;
        mDate = mHour + " : " + mMinute;

        titleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTitle = s.toString().trim();
                titleText.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        // Setup TextView using reminder values
        dateText.setText(mDate);
        timeText.setText(mTime);
        repeatIntText.setText(mRepeatInt);
        repeatTypeText.setText(mRepeatType);
        repeatText.setText("Every " + mRepeatInt + " " + mRepeatType + "(s)");

        // To save station on device rotation

        if (mActive.equals("false")) {
            mFAB1.setVisibility(View.VISIBLE);
            mFAB2.setVisibility(View.GONE);
        } else if (mActive.equals("true")) {
            mFAB1.setVisibility(View.GONE);
            mFAB2.setVisibility(View.VISIBLE);
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Member");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
    }

//    //On Clicking Time Picker
//    public void setTime (View v) {
//        Calendar now = Calendar.getInstance();
//        TimePicker tpd = TimePickerDialog.newInstance(this, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false);
//        tpd.setThemeDark(false);
//        tpd.show(getFragmentManager(), "TimePickerDialog");
//    }
//
//    //On Clicking Time Picker
//    public void setDate (View v) {
//        Calendar now = Calendar.getInstance();
//        DatePicker dpd = DatePicker.newInstance(this, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
//        dpd.show(getFragmentManager(), "TimePickerDialog");
//    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mHour = hourOfDay;
        mMinute = minute;
        if (minute < 10) {
            mTime = hourOfDay + ":" + "0" + minute;
        } else {
            mTime = hourOfDay + ":" + minute;
        }
        timeText.setText(mTime);
    }

    // Obtain date from date picker
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        monthOfYear ++;
        mDay = dayOfMonth;
        mMonth = monthOfYear;
        mYear = year;
        mDate = dayOfMonth + " / " + monthOfYear + " / " + year;
        dateText.setText(mDate);
    }

//    // On clicking the active button
//    public void selectFAB1(View v) {
//        mFAB1 = (FloatingActionButton) findViewById(R.id.starred1);
//        mFAB1.setVisibility(View.GONE);
//        mFAB2 = (FloatingActionButton) findViewById(R.id.starred2);
//        mFAB2.setVisibility(View.VISIBLE);
//        mActive = "true";
//    }
//
//    // On clicking the inactive button
//    public void selectFAB2(View v) {
//        mFAB2 = (FloatingActionButton) findViewById(R.id.starred2);
//        mFAB2.setVisibility(View.GONE);
//        mFAB1 = (FloatingActionButton) findViewById(R.id.starred1);
//        mFAB1.setVisibility(View.VISIBLE);
//        mActive = "false";
//    }

    // On clicking the repeat switch
    public void onSwitchRepeat(View view) {
        boolean on = ((Switch) view).isChecked();
        if (on) {
            mRepeat = "true";
            repeatText.setText("Every" + mRepeatInt + " " + mRepeatType + "(s)");
        } else {
            mRepeat = "false";
            repeatText.setText("Off");
        }
    }

    public void selectRepeatType(View v) {
        final String[] items = new String[5];

        items[0] = "Minute";
        items[1] = "Hour";
        items[2] = "Day";
        items[3] = "Week";
        items[4] = "Month";

        // Create List Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Type");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int item) {
                mRepeatType = items[item];
                repeatTypeText.setText(mRepeatType);
                repeatText.setText("Every " + mRepeatInt + " " + mRepeatType + " (s)");
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void setRepeatInt(View v) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Enter Number");

        // Create EditText box to input repeat number
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                if (input.getText().toString().length() == 0) {
                    mRepeatInt = Integer.toString(1);
                } else {
                    mRepeatInt = input.getText().toString().trim();
                }
                repeatIntText.setText(mRepeatInt);
                repeatText.setText("Every " + mRepeatInt + " " + mRepeatType + " (s)");
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();
    }

//    @Override
//    public boolean onCreateOptionMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_add_reminder, menu);
//        return true;
//    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_reminder, menu);
        super.onPrepareOptionsMenu(menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.save_reminder:
//                if (titleText.getText().toString().length() == 0) {
//                    titleText.setError("Reminder Title Cannot be block!");
//                } else {
//                    saveReminder();
//                    finish();
//                }
//                return true;
//            case R.id.discard_reminder:
//                showDeleteConfirmationDialog();
//                return true;
//            case android.R.id.home:
//                if (!mVehicleHasChanged) {
//                    NavUtils.navigateUpFromSameTask(ReminderAddActivity.this);
//                    return true;
//                }
//                DialogInterface.OnClickListener discardButtonClickListener = new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        NavUtils.navigateUpFromSameTask(ReminderAddActivity.this);
//                    }
//                };
//                showUnsavedChangesDialog(discardButtonClickListener);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    public void showUnsavedChangesDialog(DialogInterface.OnClickListener discardButtonClickListener) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Discard your changes and quit editing?");
//        builder.setPositiveButton("Discard", discardButtonClickListener);
//        builder.setNegativeButton("Keep Editing", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                if (dialogInterface != null) {
//                    dialogInterface.dismiss();
//                }
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }

//    private void showDeleteConfirmationDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Delete this reminder?");
//        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                deleteReminder();
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
//     private void deleteReminder() {
//        if (mCurrentReminderUri != null) {
//            int rowsDeleted = getContentResolver().delete(mCurrentReminderUri, null, null);
//
//            if (rowsDeleted == 0) {
//                Toast.makeText(this, "Error with deleting reminder", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(this, "Reminder deleted", Toast.LENGTH_LONG).show();
//            }
//        }
//        finish();
//     }

//     public void saveReminder() {
//         ContentValues values = new ContentValues();
//
//         values.put(AlarmReminderContract.AlarmReminderEntry.KEY_TITLE, mTitle);
//         values.put(AlarmReminderContract.AlarmReminderEntry.KEY_DATE, mDate);
//         values.put(AlarmReminderContract.AlarmReminderEntry.KEY_TIME, mTitle);
//         values.put(AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT, mRepeat);
//         values.put(AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_INT, mRepeatInt);
//         values.put(AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_TYPE, mRepeatType);
//         values.put(AlarmReminderContract.AlarmReminderEntry.KEY_ACTIVE, mActive);
//
//         calendar.set(Calendar.MONTH, --mMonth);
//         calendar.set(Calendar.YEAR, mYear);
//         calendar.set(Calendar.DAY_OF_MONTH, mDay);
//         calendar.set(Calendar.HOUR_OF_DAY, mHour);
//         calendar.set(Calendar.MINUTE, mMinute);
//         calendar.set(Calendar.SECOND, 0);
//
//         long selectedTimeStamp = calendar.getTimeInMillis();
//
//         if (mRepeatType.equals("Minute")) {
//             repeatTime = Integer.parseInt(mRepeatInt) * milMinute;
//         } else if (mRepeatType.equals("Hour")) {
//             repeatTime = Integer.parseInt(mRepeatInt) * milHour;
//         } else if (mRepeatType.equals("Day")) {
//             repeatTime = Integer.parseInt(mRepeatInt) * milDay;
//         } else if (mRepeatType.equals("Week")) {
//             repeatTime = Integer.parseInt(mRepeatInt) * milWeek;
//         } else if (mRepeatType.equals("Month")) {
//             repeatTime = Integer.parseInt(mRepeatInt) * milMonth;
//         }
//
//         if (mCurrentReminderUri == null) {
//             Uri newUri = getContentResolver().insert(AlarmReminderContract.AlarmReminderEntry.CONTENT_URI, values);
//
//             if (newUri == null) {
//                 Toast.makeText(this, "Error with saviing reminder", Toast.LENGTH_LONG).show();
//             } else {
//                 Toast.makeText(this, "Reminder saved", Toast.LENGTH_LONG).show();
//             }
//         } else {
//             int rowsAffected = getContentResolver().update(mCurrentReminderUri, values, null, null);
//
//             if (rowsAffected == 0) {
//                 Toast.makeText(this, "Error updating reminder", Toast.LENGTH_LONG).show();
//             } else {
//                 Toast.makeText(this, "Reminder updated", Toast.LENGTH_LONG).show();
//             }
//         }
//
//         if (mActive.equals("true")) {
//             if (mRepeat.equals("true")) {
//                 new AlarmScheduler().setRepeatAlarm(getApplicationContext(), selectedTimeStamp, mCurrentReminderUri, repeatTime);
//             } else if (mRepeat.equals("false")) {
//                 new AlarmScheduler().setAlarm(getApplicationContext(), selectedTimeStamp, mCurrentReminderUri);
//             }
//             Toast.makeText(this, "Alarm time is " + selectedTimeStamp, Toast.LENGTH_LONG).show();
//         }
//
//         Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
//     }

     @Override
     public void onBackPressed() {
        super.onBackPressed();
     }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}