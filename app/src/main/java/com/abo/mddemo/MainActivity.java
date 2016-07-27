package com.abo.mddemo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManger;
    private String [] myDateset;
    private MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("编程语言");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        mRecyclerView.setHasFixedSize(true);
        //创建默认的线性
        mLayoutManger = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManger);

        myDateset = new String[]{"JAVA","Object-C","C","Android","Python","Ruby","HTML5","C++","SQL","Swift","GO"};
        mAdapter = new MyAdapter(this,myDateset);
        mRecyclerView.setAdapter(mAdapter)  ;
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        private  int mBackground;
        private String [] mDataset;
        private final TypedValue mTypedValue = new TypedValue();


        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            public TextView mTextView;
            public ViewHolder(View v) {
                super(v);
                mTextView = (TextView) itemView.findViewById(R.id.textView);
                v.setOnClickListener(this);
            }
            @Override
            public void onClick(View v) {
                String text = "I love " + mTextView.getText()+ ".";
                Snackbar.make(v,text,Snackbar.LENGTH_LONG).show();

            }
        }

        public MyAdapter(Context context, String[] myDateset){
            mDataset = myDateset;
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground,mTypedValue,true);
            mBackground = mTypedValue.resourceId;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
            v.setBackgroundResource(mBackground);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.mTextView.setText(mDataset[position]);
        }

        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}
