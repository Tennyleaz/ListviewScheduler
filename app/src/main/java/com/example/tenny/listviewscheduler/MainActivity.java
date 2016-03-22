package com.example.tenny.listviewscheduler;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //static final String SERVERIP = "192.168.1.250";  //"140.113.167.14";
    //static final int SERVERPORT = 9000; //8000= echo server, 9000=real server
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private String str1 = "default error -1";
    private String schedule_detail, work_area, work_type;
    private static ProgressDialog pd;
    private AsyncTask task = null;
    private static ListView lv1_1, lv1_2, lv1_3, lv1_4, lv1_5, lv1_6, lv1_7, lv1_8, lv1_9, lv2_1, lv2_2, lv2_3, lv2_4, lv2_5, lv2_6, lv2_7, lv2_8, lv2_9, lvO1, lvO2, lvO3, lvO4, lvO5, lvO6, lvO7, lvOff1, lvOff2, lvOff3;
    private static WorkerArrayAdapter listAdapter1_1, listAdapter1_2, listAdapter1_3, listAdapter1_4, listAdapter1_5, listAdapter1_6, listAdapter1_7, listAdapter1_8, listAdapter1_9,
            listAdapter2_1, listAdapter2_2, listAdapter2_3, listAdapter2_4, listAdapter2_5, listAdapter2_6, listAdapter2_7, listAdapter2_8, listAdapter2_9,
            listAdapterO1, listAdapterO2, listAdapterO3, listAdapterO4, listAdapterO5, listAdapterO6, listAdapterO7, listAdapterOff1, listAdapterOff2, listAdapterOff3;
    //private static ArrayList<Worker> workerList;
    public static HashMap reverseWorkPlacesMap, reverseWorkTimesMap;
    private static HashMap workerList, workPlacesMap, workTimesMap;
    private static boolean dataChanged;
    private static String changedWorkerName;
    private static int currentPage;
    private ArrayList<Worker> lm11, lm12, lm13, lm14, lm15, lm16, lm17, lm18, lm19, lm21, lm22, lm23, lm24, lm25, lm26, lm27, lm28, lm29, lo1, lo2, lo3, lo4, lo5, lo6, lo7, lof1, lof2, lof3;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        currentPage = 0;
        /*tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("mylog", "currentPage is: " + tab.getText());
                String pagename = tab.getText().toString();
                switch (pagename) {
                    case "主生產線":
                        currentPage = 1;
                        break;
                    case "其他部門":
                        currentPage = 2;
                        break;
                    case "請假差勤":
                        currentPage = 3;
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });*/
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        /*msg = (TextView) findViewById(R.id.msg);
        Intent intent = getIntent();
        String m = intent.getStringExtra("MSG");
        msg.setText(m);*/
        workerList = new HashMap<String, Worker>();
        lm11 = new ArrayList<Worker>();
        lm12 = new ArrayList<Worker>();
        lm13 = new ArrayList<Worker>();
        lm14 = new ArrayList<Worker>();
        lm15 = new ArrayList<Worker>();
        lm16 = new ArrayList<Worker>();
        lm17 = new ArrayList<Worker>();
        lm18 = new ArrayList<Worker>();
        lm19 = new ArrayList<Worker>();
        lm21 = new ArrayList<Worker>();
        lm22 = new ArrayList<Worker>();
        lm23 = new ArrayList<Worker>();
        lm24 = new ArrayList<Worker>();
        lm25 = new ArrayList<Worker>();
        lm26 = new ArrayList<Worker>();
        lm27 = new ArrayList<Worker>();
        lm28 = new ArrayList<Worker>();
        lm29 = new ArrayList<Worker>();
        lo1 = new ArrayList<Worker>();
        lo2 = new ArrayList<Worker>();
        lo3 = new ArrayList<Worker>();
        lo4 = new ArrayList<Worker>();
        lo5 = new ArrayList<Worker>();
        lo6 = new ArrayList<Worker>();
        lo7 = new ArrayList<Worker>();
        lof1 = new ArrayList<Worker>();
        lof2 = new ArrayList<Worker>();
        lof3 = new ArrayList<Worker>();
        listAdapter1_1 = new WorkerArrayAdapter(this, lm11);
        listAdapter1_2 = new WorkerArrayAdapter(this, lm12);
        listAdapter1_3 = new WorkerArrayAdapter(this, lm13);
        listAdapter1_4 = new WorkerArrayAdapter(this, lm14);
        listAdapter1_5 = new WorkerArrayAdapter(this, lm15);
        listAdapter1_6 = new WorkerArrayAdapter(this, lm16);
        listAdapter1_7 = new WorkerArrayAdapter(this, lm17);
        listAdapter1_8 = new WorkerArrayAdapter(this, lm18);
        listAdapter1_9 = new WorkerArrayAdapter(this, lm19);
        listAdapter2_1 = new WorkerArrayAdapter(this, lm21);
        listAdapter2_2 = new WorkerArrayAdapter(this, lm22);
        listAdapter2_3 = new WorkerArrayAdapter(this, lm23);
        listAdapter2_4 = new WorkerArrayAdapter(this, lm24);
        listAdapter2_5 = new WorkerArrayAdapter(this, lm25);
        listAdapter2_6 = new WorkerArrayAdapter(this, lm26);
        listAdapter2_7 = new WorkerArrayAdapter(this, lm27);
        listAdapter2_8 = new WorkerArrayAdapter(this, lm28);
        listAdapter2_9 = new WorkerArrayAdapter(this, lm29);
        listAdapterO1 =  new WorkerArrayAdapter(this, lo1);
        listAdapterO2 =  new WorkerArrayAdapter(this, lo2);
        listAdapterO3 =  new WorkerArrayAdapter(this, lo3);
        listAdapterO4 =  new WorkerArrayAdapter(this, lo4);
        listAdapterO5 =  new WorkerArrayAdapter(this, lo5);
        listAdapterO6 =  new WorkerArrayAdapter(this, lo6);
        listAdapterO7 =  new WorkerArrayAdapter(this, lo7);
        listAdapterOff1 =  new WorkerArrayAdapter(this, lof1);
        listAdapterOff2 =  new WorkerArrayAdapter(this, lof2);
        listAdapterOff3 =  new WorkerArrayAdapter(this, lof3);
        dataChanged = false;
        changedWorkerName = null;
        //mainLineNeedUpdate = false;
        //officeNeedUpdate = false;
        //offlineNeedUpdate = false;

        pd = ProgressDialog.show(MainActivity.this, "連線中", "Please wait...");    /* 開啟一個新線程，在新線程裡執行耗時的方法 */
        new Thread(new Runnable() {
            @Override
            public void run() {
                InitServer();
                handler.sendEmptyMessage(0);// 執行耗時的方法之後發送消給handler
            }

        }).start();

    }


    /*@Override
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
    }*/

    private void InitServer() {
        Log.d("mylog", "InitServer...");
        SocketHandler.setTimeout(true, 2000);
        Log.d("mylog", "InitServer...");
        String s = "QUERY\tSCHEDULE_DETAIL<END>";
        SocketHandler.writeToSocket(s);
        //Log.d("mylog", "send:" + s);
        schedule_detail = SocketHandler.getOutput();
        Log.d("mylog", "schedule_detail=" + schedule_detail);
        s = "QUERY\tWORK_AREA<END>";
        SocketHandler.writeToSocket(s);
        //Log.d("mylog", "send:" + s);
        work_area = SocketHandler.getOutput();
        Log.d("mylog", "work_area=" + work_area);
        s = "QUERY\tWORK_TYPE<END>";
        SocketHandler.writeToSocket(s);
        //Log.d("mylog", "send:" + s);
        work_type = SocketHandler.getOutput();
        Log.d("mylog", "work_type=" + work_type);

        workPlacesMap = new HashMap<String, String>();
        workTimesMap = new HashMap<String, String>();
        reverseWorkPlacesMap = new HashMap<String, String>();
        reverseWorkTimesMap = new HashMap<String, String>();

        if(work_area!=null) {
            work_area = work_area.replaceAll("QUERY_REPLY\t", "");
            work_area = work_area.replaceAll("<END>", "");
            String[] places = work_area.split("<N>");
            for(String p: places) {
                if(p==null) continue;
                p = p.replaceAll("<N>", "");
                String[] w = p.split("\t");
                if(w.length >= 2) {
                    workPlacesMap.put(w[1], w[0]);
                    reverseWorkPlacesMap.put(w[0], w[1]);
                }
            }
        }

        if(work_type!=null) {
            work_type = work_type.replaceAll("QUERY_REPLY\t", "");
            work_type = work_type.replaceAll("<END>", "");
            String[] times = work_type.split("<N>");
            for(String p: times) {
                if(p==null) continue;
                p = p.replaceAll("<N>", "");
                String[] w = p.split("\t");
                if(w.length >= 2) {
                    workTimesMap.put(w[1], w[0]);
                    reverseWorkTimesMap.put(w[0], w[1]);
                }
            }
        }
        /*try {
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {// handler接收到消息後就會執行此方法
            updateUI();
            Log.d("mylog", "handleMessage...");
            task = new UpdateTask().execute();
            pd.dismiss();// 關閉ProgressDialog
        }
    };

    private void updateUI() {
        Log.d("mylog", "updateUI...");
        listAdapterOff1.clear();
        listAdapterOff2.clear();
        listAdapterOff3.clear();
        listAdapterO1.clear();
        listAdapterO2.clear();
        listAdapterO3.clear();
        listAdapterO4.clear();
        listAdapterO5.clear();
        listAdapterO6.clear();
        listAdapterO7.clear();
        listAdapter1_1.clear();
        listAdapter1_2.clear();
        listAdapter1_3.clear();
        listAdapter1_4.clear();
        listAdapter1_5.clear();
        listAdapter1_6.clear();
        listAdapter1_7.clear();
        listAdapter1_8.clear();
        listAdapter1_9.clear();
        listAdapter2_1.clear();
        listAdapter2_2.clear();
        listAdapter2_3.clear();
        listAdapter2_4.clear();
        listAdapter2_5.clear();
        listAdapter2_6.clear();
        listAdapter2_7.clear();
        listAdapter2_8.clear();
        listAdapter2_9.clear();

        if(schedule_detail!=null) {
            schedule_detail = schedule_detail.replaceAll("QUERY_REPLY\t", "");
            schedule_detail = schedule_detail.replaceAll("<END>", "");
            String[] workers = schedule_detail.split("<N>");
            for(String s: workers){
                if(s==null) continue;
                s = s.replaceAll("<N>", "");
                String[] w = s.split("\t");
                if(w.length >= 4) {
                    Worker newWorker = new Worker(w[0], w[1], w[2], w[3]);
                    Log.d("mylog", "new worker:" + newWorker.Name + "/" + newWorker.WorkPlace + "/" + newWorker.WorkTime);
                    workerList.put(newWorker.Name, newWorker);
                    boolean isAbsent = false;
                    switch (w[2]) {
                        case "5002":
                            listAdapterOff1.add(newWorker);
                            isAbsent = true;
                            break;
                        case "5003":
                            listAdapterOff2.add(newWorker);
                            isAbsent = true;
                            break;
                        case "5004":
                            listAdapterOff3.add(newWorker);
                            isAbsent = true;
                            break;
                    }
                    if(!isAbsent) {
                        switch (w[3]) {
                            case "6000":
                                listAdapterO1.add(newWorker);
                                break;
                            case "6001":
                                listAdapterO2.add(newWorker);
                                break;
                            case "6002":
                                listAdapterO3.add(newWorker);
                                break;
                            case "6003":
                                listAdapterO4.add(newWorker);
                                break;
                            case "6004":
                                listAdapterO5.add(newWorker);
                                break;
                            case "6005":
                                listAdapterO6.add(newWorker);
                                break;
                            case "6010":
                                listAdapter1_1.add(newWorker);
                                break;
                            case "6011":
                                listAdapter2_1.add(newWorker);
                                break;
                            case "6020":
                                listAdapter1_2.add(newWorker);
                                break;
                            case "6021":
                                listAdapter2_2.add(newWorker);
                                break;
                            case "6030":
                                listAdapter1_3.add(newWorker);
                                break;
                            case "6031":
                                listAdapter2_3.add(newWorker);
                                break;
                            case "6040":
                                listAdapter1_4.add(newWorker);
                                break;
                            case "6041":
                                listAdapter2_4.add(newWorker);
                                break;
                            case "6050":
                                listAdapter1_5.add(newWorker);
                                break;
                            case "6051":
                                listAdapter2_5.add(newWorker);
                                break;
                            case "6060":
                                listAdapter1_6.add(newWorker);
                                break;
                            case "6061":
                                listAdapter2_6.add(newWorker);
                                break;
                            case "6070":
                                listAdapter1_7.add(newWorker);
                                break;
                            case "6071":
                                listAdapter2_7.add(newWorker);
                                break;
                            case "6080":
                                listAdapter1_8.add(newWorker);
                                break;
                            case "6081":
                                listAdapter2_8.add(newWorker);
                                break;
                            case "6090":
                                listAdapter1_9.add(newWorker);
                                break;
                            case "6091":
                                listAdapter2_9.add(newWorker);
                                break;
                            case "6999":
                                listAdapterO7.add(newWorker);
                                break;
                        } //end switch
                    } //end if
                }
            } //end for
        }
    }

    private static AdapterView.OnItemClickListener changeScheduleListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ListView listView = (ListView) parent;
            final Worker w = (Worker) listView.getItemAtPosition(position);
            String p = (String) reverseWorkPlacesMap.get(w.WorkPlace);
            String t = (String) reverseWorkTimesMap.get(w.WorkTime);

            AlertDialog.Builder dialog = new AlertDialog.Builder(listView.getContext());
            LayoutInflater inflater = LayoutInflater.from(listView.getContext());
            final View v = inflater.inflate(R.layout.alert_dialog_layout, null);
            final TextView name = (TextView) v.findViewById(R.id.name);
            name.setText(w.Name);
            final TextView place = (TextView) v.findViewById(R.id.place);
            place.setText(p);
            final TextView time = (TextView) v.findViewById(R.id.time);
            time.setText(t);

            final Spinner placeSpinner = (Spinner) v.findViewById(R.id.placeSelector);
            final Spinner timeSpinner = (Spinner) v.findViewById(R.id.timeSelector);
            ArrayAdapter<String> placeList, timeList;
            List<String> places = new ArrayList<String>(workPlacesMap.keySet());
            /*{"辦公室", "濾嘴風送機", "濾嘴捲製機", "裝箱機", "香菸解剖機", "維修部", "生產線1,捲菸機", "生產線1,包裝機", "生產線2,捲菸機", "生產線2,包裝機", "生產線3,捲菸機", "生產線3,包裝機",
                "生產線4,捲菸機", "生產線4,包裝機", "生產線5,捲菸機", "生產線5,包裝機", "生產線6,捲菸機", "生產線6,包裝機", "生產線7,捲菸機", "生產線7,包裝機", "生產線8,捲菸機", "生產線8,包裝機", "生產線9,捲菸機", "生產線9,包裝機"};*/
            List<String> times = new ArrayList<String>(workTimesMap.keySet());//{"早班", "晚班", "出差", "請假"};
            placeList = new ArrayAdapter<String>(listView.getContext(), android.R.layout.simple_spinner_dropdown_item, places);
            timeList = new ArrayAdapter<String>(listView.getContext(), android.R.layout.simple_spinner_dropdown_item, times);
            placeSpinner.setAdapter(placeList);
            timeSpinner.setAdapter(timeList);
            int timeSpinnerPosition = timeList.getPosition(t);
            int placeSpinnerPosition = placeList.getPosition(p);
            timeSpinner.setSelection(timeSpinnerPosition);
            placeSpinner.setSelection(placeSpinnerPosition);

            dialog.setTitle("排班編輯");
            dialog.setView(v);
            dialog.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //remove old worker place
                            if (w.WorkTime.equals("5000") || w.WorkTime.equals("5001")) {  //{"早班", "晚班"
                                switch (w.WorkPlace) {
                                    case "6000":
                                        listAdapterO1.remove(w);
                                        listAdapterO1.notifyDataSetChanged();
                                        break;
                                    case "6001":
                                        listAdapterO2.remove(w);
                                        listAdapterO2.notifyDataSetChanged();
                                        break;
                                    case "6002":
                                        listAdapterO3.remove(w);
                                        listAdapterO3.notifyDataSetChanged();
                                        break;
                                    case "6003":
                                        listAdapterO4.remove(w);
                                        listAdapterO4.notifyDataSetChanged();
                                        break;
                                    case "6004":
                                        listAdapterO5.remove(w);
                                        listAdapterO5.notifyDataSetChanged();
                                        break;
                                    case "6005":
                                        listAdapterO6.remove(w);
                                        listAdapterO6.notifyDataSetChanged();
                                        break;
                                    case "6010":
                                        listAdapter1_1.remove(w);
                                        listAdapter1_1.notifyDataSetChanged();
                                        break;
                                    case "6011":
                                        listAdapter2_1.remove(w);
                                        listAdapter2_1.notifyDataSetChanged();
                                        break;
                                    case "6020":
                                        listAdapter1_2.remove(w);
                                        listAdapter1_2.notifyDataSetChanged();
                                        break;
                                    case "6021":
                                        listAdapter2_2.remove(w);
                                        listAdapter2_2.notifyDataSetChanged();
                                        break;
                                    case "6030":
                                        listAdapter1_3.remove(w);
                                        listAdapter1_3.notifyDataSetChanged();
                                        break;
                                    case "6031":
                                        listAdapter2_3.remove(w);
                                        listAdapter2_3.notifyDataSetChanged();
                                        break;
                                    case "6040":
                                        listAdapter1_4.remove(w);
                                        listAdapter1_4.notifyDataSetChanged();
                                        break;
                                    case "6041":
                                        listAdapter2_4.remove(w);
                                        listAdapter2_4.notifyDataSetChanged();
                                        break;
                                    case "6050":
                                        listAdapter1_5.remove(w);
                                        listAdapter1_5.notifyDataSetChanged();
                                        break;
                                    case "6051":
                                        listAdapter2_5.remove(w);
                                        listAdapter2_5.notifyDataSetChanged();
                                        break;
                                    case "6060":
                                        listAdapter1_6.remove(w);
                                        listAdapter1_6.notifyDataSetChanged();
                                        break;
                                    case "6061":
                                        listAdapter2_6.remove(w);
                                        listAdapter2_6.notifyDataSetChanged();
                                        break;
                                    case "6070":
                                        listAdapter1_7.remove(w);
                                        listAdapter1_7.notifyDataSetChanged();
                                        break;
                                    case "6071":
                                        listAdapter2_7.remove(w);
                                        listAdapter2_7.notifyDataSetChanged();
                                        break;
                                    case "6080":
                                        listAdapter1_8.remove(w);
                                        listAdapter1_8.notifyDataSetChanged();
                                        break;
                                    case "6081":
                                        listAdapter2_8.remove(w);
                                        listAdapter2_8.notifyDataSetChanged();
                                        break;
                                    case "6090":
                                        listAdapter1_9.remove(w);
                                        listAdapter1_9.notifyDataSetChanged();
                                        break;
                                    case "6091":
                                        listAdapter2_9.remove(w);
                                        listAdapter2_9.notifyDataSetChanged();
                                        break;
                                    case  "6999":
                                        listAdapterO7.remove(w);
                                        listAdapterO7.notifyDataSetChanged();
                                        break;
                                    default:
                                        Log.e("mylog", "error case:" + w.Name);
                                        break;
                                } //end switch
                            } else if (w.WorkTime.equals("5002")) {  //remove 出差
                                listAdapterOff1.remove(w);
                                listAdapterOff1.notifyDataSetChanged();
                            } else if (w.WorkTime.equals("5003")) {  //remove 請假
                                listAdapterOff2.remove(w);
                                listAdapterOff2.notifyDataSetChanged();
                            } else if (w.WorkTime.equals("5004")) {  //remove 請假
                                listAdapterOff3.remove(w);
                                listAdapterOff3.notifyDataSetChanged();
                            }//end if

                            boolean isAbsent = false;
                            String selectedPlace = placeSpinner.getSelectedItem().toString();
                            String selectedTime = timeSpinner.getSelectedItem().toString();
                            w.WorkPlace = workPlacesMap.get(selectedPlace).toString();
                            w.WorkTime = workTimesMap.get(selectedTime).toString();
                            switch (w.WorkTime) {
                                case "5000":
                                    break;
                                case "5001":
                                    break;
                                case "5002":
                                    isAbsent = true;
                                    listAdapterOff1.add(w);
                                    listAdapterOff1.notifyDataSetChanged();
                                    break;
                                case "5003":
                                    isAbsent = true;
                                    listAdapterOff2.add(w);
                                    listAdapterOff2.notifyDataSetChanged();
                                    break;
                                case "5004":
                                    isAbsent = true;
                                    listAdapterOff3.add(w);
                                    listAdapterOff3.notifyDataSetChanged();
                                    break;
                            }
                            Log.d("mylog", "worker " + w.Name + " goto " + w.WorkPlace + " time:" +w.WorkTime + ", isAbsent=" + isAbsent);
                            if (!isAbsent) { //not absent, change schedule
                                switch (w.WorkPlace) {
                                    case "6000":
                                        listAdapterO1.add(w);
                                        listAdapterO1.notifyDataSetChanged();
                                        break;
                                    case "6001":
                                        listAdapterO2.add(w);
                                        listAdapterO2.notifyDataSetChanged();
                                        break;
                                    case "6002":
                                        listAdapterO3.add(w);
                                        listAdapterO3.notifyDataSetChanged();
                                        break;
                                    case "6003":
                                        listAdapterO4.add(w);
                                        listAdapterO4.notifyDataSetChanged();
                                        break;
                                    case "6004":
                                        listAdapterO5.add(w);
                                        listAdapterO5.notifyDataSetChanged();
                                        break;
                                    case "6005":
                                        listAdapterO6.add(w);
                                        listAdapterO6.notifyDataSetChanged();
                                        break;
                                    case "6010":
                                        listAdapter1_1.add(w);
                                        listAdapter1_1.notifyDataSetChanged();
                                        break;
                                    case "6011":
                                        listAdapter2_1.add(w);
                                        listAdapter2_1.notifyDataSetChanged();
                                        break;
                                    case "6020":
                                        listAdapter1_2.add(w);
                                        listAdapter1_2.notifyDataSetChanged();
                                        break;
                                    case "6021":
                                        listAdapter2_2.add(w);
                                        listAdapter2_2.notifyDataSetChanged();
                                        break;
                                    case "6030":
                                        listAdapter1_3.add(w);
                                        listAdapter1_3.notifyDataSetChanged();
                                        break;
                                    case "6031":
                                        listAdapter2_3.add(w);
                                        listAdapter2_3.notifyDataSetChanged();
                                        break;
                                    case "6040":
                                        listAdapter1_4.add(w);
                                        listAdapter1_4.notifyDataSetChanged();
                                        break;
                                    case "6041":
                                        listAdapter2_4.add(w);
                                        listAdapter2_4.notifyDataSetChanged();
                                        break;
                                    case "6050":
                                        listAdapter1_5.add(w);
                                        listAdapter1_5.notifyDataSetChanged();
                                        break;
                                    case "6051":
                                        listAdapter2_5.add(w);
                                        listAdapter2_5.notifyDataSetChanged();
                                        break;
                                    case "6060":
                                        listAdapter1_6.add(w);
                                        listAdapter1_6.notifyDataSetChanged();
                                        break;
                                    case "6061":
                                        listAdapter2_6.add(w);
                                        listAdapter2_6.notifyDataSetChanged();
                                        break;
                                    case "6070":
                                        listAdapter1_7.add(w);
                                        listAdapter1_7.notifyDataSetChanged();
                                        break;
                                    case "6071":
                                        listAdapter2_7.add(w);
                                        listAdapter2_7.notifyDataSetChanged();
                                        break;
                                    case "6080":
                                        listAdapter1_8.add(w);
                                        listAdapter1_8.notifyDataSetChanged();
                                        break;
                                    case "6081":
                                        listAdapter2_8.add(w);
                                        listAdapter2_8.notifyDataSetChanged();
                                        break;
                                    case "6090":
                                        listAdapter1_9.add(w);
                                        listAdapter1_9.notifyDataSetChanged();
                                        break;
                                    case "6091":
                                        listAdapter2_9.add(w);
                                        listAdapter2_9.notifyDataSetChanged();
                                        break;
                                    case "6999":
                                        listAdapterO7.add(w);
                                        listAdapterO7.notifyDataSetChanged();
                                    default:
                                        Log.e("mylog", "error case:" + workPlacesMap.get(selectedPlace));
                                        break;
                                }  //end switch
                            }  //end if
                            //TODO: send to server
                            dataChanged = true;
                            changedWorkerName = w.Name;
                        }  //end onclick
                    });
            dialog.setNegativeButton("取消", null);
            dialog.show();
        }
    };

    private class UpdateTask extends AsyncTask<Void, String, String> {
        private boolean needReUpdate = false, afterUpdate = false;
        @Override
        protected String doInBackground(Void... v) {
            while (!isCancelled()) {
                if(dataChanged) {
                    Worker w = (Worker) workerList.get(changedWorkerName);
                    String cmd = "EXE\tSCHEDULE\t" + w.ID + "\t" + w.WorkTime + "\t" + w.WorkPlace + "<END>";
                    SocketHandler.writeToSocket(cmd);
                    dataChanged = false;
                    changedWorkerName = null;
                }
                if(needReUpdate) {
                    InitServer();
                    afterUpdate = true;
                    needReUpdate = false;
                    publishProgress("");
                    continue;
                }
                String result;
                result = SocketHandler.getOutput();
                publishProgress(result);
                if(result!=null && result.length()>0)
                    Log.d("Mylog", "result=" + result);
            }
            return null;
        }

        protected void onProgressUpdate(String... values) {
            if(afterUpdate) {
                afterUpdate = false;
                updateUI();
            }
            String result = values[0];
            if(result==null ||result.length() == 0) return;
            String[] lines = result.split("<END>");
            int length = lines.length;

            Log.d("Mylog", "lines.length=" + length);
            boolean updateList = false;
            for(String s: lines) {
                Log.d("Mylog", "s in line=" + s);
                if (s != null && s.contains("MSG\t")) {
                    //s = s.replaceAll("MSG\t", "");
                    //swapMsg.setVisibility(View.VISIBLE);
                    //swapMsg.setText(s);
                } else if (s != null && s.contains("UPDATE_SCHEDULE")) {
                    //s = s.replaceAll("UPDATE_SCHEDULE\t", "");
                    needReUpdate = true;
                }
            }
        }
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Log.d("mylog", "position="+position);
            switch (position) {
                case 0:
                    return PlaceholderFragment.newInstance(position+1);
                case 1:
                    return FragmentOffice.newInstance(position+1);
                case 2:
                    return FragmentOffline.newInstance(position+1);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "主生產線";
                case 1:
                    return "其他部門";
                case 2:
                    return "請假差勤";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
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
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            lv1_1 = (ListView) rootView.findViewById(R.id.listView1_1);
            lv1_2 = (ListView) rootView.findViewById(R.id.listView1_2);
            lv1_3 = (ListView) rootView.findViewById(R.id.listView1_3);
            lv1_4 = (ListView) rootView.findViewById(R.id.listView1_4);
            lv1_5 = (ListView) rootView.findViewById(R.id.listView1_5);
            lv1_6 = (ListView) rootView.findViewById(R.id.listView1_6);
            lv1_7 = (ListView) rootView.findViewById(R.id.listView1_7);
            lv1_8 = (ListView) rootView.findViewById(R.id.listView1_8);
            lv1_9 = (ListView) rootView.findViewById(R.id.listView1_9);
            lv2_1 = (ListView) rootView.findViewById(R.id.listView2_1);
            lv2_2 = (ListView) rootView.findViewById(R.id.listView2_2);
            lv2_3 = (ListView) rootView.findViewById(R.id.listView2_3);
            lv2_4 = (ListView) rootView.findViewById(R.id.listView2_4);
            lv2_5 = (ListView) rootView.findViewById(R.id.listView2_5);
            lv2_6 = (ListView) rootView.findViewById(R.id.listView2_6);
            lv2_7 = (ListView) rootView.findViewById(R.id.listView2_7);
            lv2_8 = (ListView) rootView.findViewById(R.id.listView2_8);
            lv2_9 = (ListView) rootView.findViewById(R.id.listView2_9);
            lv1_1.setAdapter(listAdapter1_1);
            lv1_2.setAdapter(listAdapter1_2);
            lv1_3.setAdapter(listAdapter1_3);
            lv1_4.setAdapter(listAdapter1_4);
            lv1_5.setAdapter(listAdapter1_5);
            lv1_6.setAdapter(listAdapter1_6);
            lv1_7.setAdapter(listAdapter1_7);
            lv1_8.setAdapter(listAdapter1_8);
            lv1_9.setAdapter(listAdapter1_9);
            lv2_1.setAdapter(listAdapter2_1);
            lv2_2.setAdapter(listAdapter2_2);
            lv2_3.setAdapter(listAdapter2_3);
            lv2_4.setAdapter(listAdapter2_4);
            lv2_5.setAdapter(listAdapter2_5);
            lv2_6.setAdapter(listAdapter2_6);
            lv2_7.setAdapter(listAdapter2_7);
            lv2_8.setAdapter(listAdapter2_8);
            lv2_9.setAdapter(listAdapter2_9);
            lv1_1.setOnItemClickListener(changeScheduleListener);
            lv1_2.setOnItemClickListener(changeScheduleListener);
            lv1_3.setOnItemClickListener(changeScheduleListener);
            lv1_4.setOnItemClickListener(changeScheduleListener);
            lv1_5.setOnItemClickListener(changeScheduleListener);
            lv1_6.setOnItemClickListener(changeScheduleListener);
            lv1_7.setOnItemClickListener(changeScheduleListener);
            lv1_8.setOnItemClickListener(changeScheduleListener);
            lv1_9.setOnItemClickListener(changeScheduleListener);
            lv2_1.setOnItemClickListener(changeScheduleListener);
            lv2_2.setOnItemClickListener(changeScheduleListener);
            lv2_3.setOnItemClickListener(changeScheduleListener);
            lv2_4.setOnItemClickListener(changeScheduleListener);
            lv2_5.setOnItemClickListener(changeScheduleListener);
            lv2_6.setOnItemClickListener(changeScheduleListener);
            lv2_7.setOnItemClickListener(changeScheduleListener);
            lv2_8.setOnItemClickListener(changeScheduleListener);
            lv2_9.setOnItemClickListener(changeScheduleListener);
            /*listAdapter1_1.add("123");
            listAdapter1_1.add("456");
            listAdapter1_1.add("abc");
            listAdapter1_1.add("def");
            listAdapter1_1.notifyDataSetChanged();*/
            return rootView;
        }

        @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);
            if (isVisibleToUser) {
                //相當於Fragment的onResume
                Log.d("mylog","fragment 1 is visible");
                currentPage = 1;
            }
        }
    }

    public static class FragmentOffice extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static FragmentOffice newInstance(int sectionNumber) {
            FragmentOffice fragment = new FragmentOffice();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public FragmentOffice() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_office, container, false);
            lvO1 = (ListView) rootView.findViewById(R.id.listViewO1);
            lvO2 = (ListView) rootView.findViewById(R.id.listViewO2);
            lvO3 = (ListView) rootView.findViewById(R.id.listViewO3);
            lvO4 = (ListView) rootView.findViewById(R.id.listViewO4);
            lvO5 = (ListView) rootView.findViewById(R.id.listViewO5);
            lvO6 = (ListView) rootView.findViewById(R.id.listViewO6);
            lvO7 = (ListView) rootView.findViewById(R.id.listViewO7);
            lvO1.setAdapter(listAdapterO1);
            lvO2.setAdapter(listAdapterO2);
            lvO3.setAdapter(listAdapterO3);
            lvO4.setAdapter(listAdapterO4);
            lvO5.setAdapter(listAdapterO5);
            lvO6.setAdapter(listAdapterO6);
            lvO7.setAdapter(listAdapterO7);
            lvO1.setOnItemClickListener(changeScheduleListener);
            lvO2.setOnItemClickListener(changeScheduleListener);
            lvO3.setOnItemClickListener(changeScheduleListener);
            lvO4.setOnItemClickListener(changeScheduleListener);
            lvO5.setOnItemClickListener(changeScheduleListener);
            lvO6.setOnItemClickListener(changeScheduleListener);
            lvO7.setOnItemClickListener(changeScheduleListener);
            return rootView;
        }

        @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);
            if (isVisibleToUser) {
                //相當於Fragment的onResume
                Log.d("mylog","fragment 2 is visible");
                currentPage = 2;
            }
        }
    }

    public static class FragmentOffline extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static FragmentOffline newInstance(int sectionNumber) {
            FragmentOffline fragment = new FragmentOffline();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public FragmentOffline() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_offline, container, false);
            lvOff1 = (ListView) rootView.findViewById(R.id.listViewOff1);
            lvOff2 = (ListView) rootView.findViewById(R.id.listViewOff2);
            lvOff3 = (ListView) rootView.findViewById(R.id.listViewOff3);
            lvOff1.setAdapter(listAdapterOff1);
            lvOff2.setAdapter(listAdapterOff2);
            lvOff3.setAdapter(listAdapterOff3);
            lvOff1.setOnItemClickListener(changeScheduleListener);
            lvOff2.setOnItemClickListener(changeScheduleListener);
            lvOff3.setOnItemClickListener(changeScheduleListener);
            return rootView;
        }

        @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);
            if (isVisibleToUser) {
                //相當於Fragment的onResume
                Log.d("mylog","fragment 3 is visible");
                currentPage = 3;
            }
        }
    }

    @Override
    public void onStop(){
        System.exit(0);
        task.cancel(true);
        SocketHandler.closeSocket();
        finish();
        super.onStop();
    }
}
