package com.mysoftpanda.android.dunyomamlakatlari;

//public class WsAppListActivity extends Activity implements OnItemClickListener {
//    String[] contactType;
//    String[] member_link;
//    String[] member_names;
//    ListView mylistview;
//    TypedArray profile_pics;
//    List<WsRowItem> rowItems;
//    List<WsRowItem> rowItems_link;
//    String[] statues;
//
//    /* Access modifiers changed, original: protected */
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        setContentView(R.layout.ws_app_list);
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        getWindow().setLayout((int) (((double) displayMetrics.widthPixels) * 0.9d), (int) (((double) displayMetrics.heightPixels) * 0.8d));
//        this.rowItems = new ArrayList();
//        this.member_names = getResources().getStringArray(R.array.ws_app_names);
//        this.profile_pics = getResources().obtainTypedArray(R.array.ws_profile_pics);
//        this.statues = getResources().getStringArray(R.array.ws_statues);
//        this.member_link = getResources().getStringArray(R.array.ws_links);
//        this.contactType = getResources().getStringArray(R.array.ws_contactType);
//        for (int i = 0; i < this.member_link.length; i++) {
//            this.rowItems.add(new WsRowItem(this.member_names[i], this.profile_pics.getResourceId(i, -1), this.statues[i], this.member_link[i], this.contactType[i]));
//        }
//        this.mylistview = (ListView) findViewById(R.id.list);
//        this.mylistview.setAdapter(new WsCustomAdapter(this, this.rowItems));
//        this.mylistview.setOnItemClickListener(this);
//    }
//
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
//        String member_name = ((WsRowItem) this.rowItems.get(i)).getMember_name();
//        Context applicationContext = getApplicationContext();
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("");
//        stringBuilder.append(member_name);
//        Toast.makeText(applicationContext, stringBuilder.toString(), 0).show();
//        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((WsRowItem) this.rowItems.get(i)).getLinks())));
//    }
//}
