package com.abo.mddemo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abo.mddemo.example.AppBarActivity;
import com.abo.mddemo.example.BottomTabActivity;
import com.abo.mddemo.example.CardViewAvtivity;
import com.abo.mddemo.widget.DividerItemDecoration;
import com.abo.mddemo.example.EditTextExampleActivity;
import com.abo.mddemo.R;
import com.abo.mddemo.example.RecycleViewExampleActivity;
import com.abo.mddemo.widget.RecyclerItemClickListener;

/**
 * Created by abo on 16/7/28.
 */
public class ExampleFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private String [] myDateset;
    private MyAdapter myAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_example, null);

        recyclerView = (RecyclerView) view.findViewById(R.id.frag_recycleview);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        //增加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        //监听器
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),onItemClickListener));

        //加载数据

        myDateset = new String[]{"RecycleView","TextInputLayout","CardView","ToolBar","TabLayout"};
        myAdapter = new MyAdapter(getActivity(),myDateset);
        recyclerView.setAdapter((RecyclerView.Adapter) myAdapter);
        return view;
    }

    private RecyclerItemClickListener.OnItemClickListener onItemClickListener = new RecyclerItemClickListener.OnItemClickListener(){

        @Override
        public void onItemClick(View view, int position) {
            Intent intent = null;
            switch (position){
                case 0:
                    intent = new Intent(getActivity(), RecycleViewExampleActivity.class);
                    startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(getActivity(), EditTextExampleActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(getActivity(), CardViewAvtivity.class);
                    startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(getActivity(),AppBarActivity.class);
                    startActivity(intent);
                case 4:
                    intent = new Intent(getActivity(), BottomTabActivity.class);
                    startActivity(intent);
            }
        }
    };

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private final int mBackground ;
        private String[] mDateset;
        //?????
        private final TypedValue mTypedValue = new TypedValue();

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public int position;

            public ViewHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.textView);

            }
        }

        public MyAdapter(Context context, String[] myDateset){
            mDateset = myDateset;
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground,mTypedValue,true);
            mBackground = mTypedValue.resourceId;
        }
        //创建控件
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
            v.setBackgroundResource(mBackground);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }
        //为控件添加内容
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.mTextView.setText(mDateset[position]);
        }
        //获取控件数
        @Override
        public int getItemCount() {
            return mDateset.length;
        }


    }
}
