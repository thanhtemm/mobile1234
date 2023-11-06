package com.example.petest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petest.MainActivity;
import com.example.petest.R;
import com.example.petest.model.Product;
import com.example.petest.model.Nsx;

import java.util.List;

public class Adapter extends BaseAdapter {
    private final MainActivity context;
    private List<Product> productList;
    private List<Nsx> nsxList;
    private final int rowProductLayout;
    private Nsx selectedNsx;


    public interface OnNsxSelectedListener {
        void onNsxSelectedListener(Nsx nsx);
    }

    private OnNsxSelectedListener nsxSelectedListener;

    public Adapter(MainActivity context, List<Product> productList, List<Nsx> NsxList, int rowProductLayout) {
        this.context = context;
        this.productList = productList;
        this.nsxList = nsxList;
        this.rowProductLayout = rowProductLayout;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(productList.get(position).getId());
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    public void setNsxList(List<Nsx> nsxList) {
        this.nsxList = nsxList;
    }

    public void set(OnNsxSelectedListener listener) {
        this.nsxSelectedListener = listener;
    }

    private static class ViewHolder {
        TextView tv_prname;
        TextView tv_nhsxname2;
        TextView tv_nhsxname;
        TextView tv_nhsxname3;
        TextView tv_nhsxname4;
        TextView tv_nhsxname5;
        TextView tv_nhsxname6;
        TextView tv_gender;

        TextView tv_date;
        ImageView icDelete;
        ImageView icEdit;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(rowProductLayout, null);
            holder.tv_prname = view.findViewById(R.id.tv_prname);
            holder.tv_nhsxname2 = view.findViewById(R.id.tv_nhsxname2);
            holder.tv_nhsxname = view.findViewById(R.id.tv_nhsxname);
            holder.tv_nhsxname3 = view.findViewById(R.id.tv_nhsxname3);
            holder.tv_nhsxname4 = view.findViewById(R.id.tv_nhsxname4);
            holder.tv_nhsxname5 = view.findViewById(R.id.tv_nhsxname5);
            holder.tv_nhsxname6 = view.findViewById(R.id.tv_nhsxname6);
            holder.tv_date = view.findViewById(R.id.tv_date);
            holder.tv_gender = view.findViewById(R.id.tv_gender);



            holder.icEdit = view.findViewById(R.id.iv_edit);
            holder.icDelete = view.findViewById(R.id.iv_delete);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        Product product = productList.get(position);
        holder.tv_nhsxname2.setText("ID: " + product.getId());
        holder.tv_nhsxname.setText("Name: " + product.getName());
        holder.tv_prname.setText(product.getName());
        holder.tv_nhsxname3.setText("Date: " + product.getDatexs());
        holder.tv_nhsxname4.setText("Gender: " + "NAM");
        holder.tv_nhsxname5.setText("Address: " + "TP. Ho Chi Minh");
        holder.tv_nhsxname6.setText("Major: " + product.getIdNhsx());
        holder.tv_date.setText("Date SX: " + product.getDatexs());
        holder.tv_gender.setText("Quy Cách: " + product.getQuicach());

        holder.icDelete.setOnClickListener(v -> context.deleteProduct(product));
        holder.icEdit.setOnClickListener(v -> context.showDialog(MainActivity.DialogType.UPDATE, product));
        return view;
    }


}
