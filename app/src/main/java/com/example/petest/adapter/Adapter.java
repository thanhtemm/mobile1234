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
    }

    public void setNsxList(List<Nsx> nsxList) {
        this.nsxList = nsxList;
    }

    public void set(OnNsxSelectedListener listener) {
        this.nsxSelectedListener = listener;
    }

    private static class ViewHolder {
        TextView tvProductname;
        TextView tvNhsxname;
        TextView tvDatesx;
        TextView tvQuicach;
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
            holder.tvProductname = view.findViewById(R.id.tv_prname);
            holder.tvDatesx = view.findViewById(R.id.tv_date);
            holder.tvQuicach = view.findViewById(R.id.tv_gender);
            holder.icEdit = view.findViewById(R.id.iv_edit);
            holder.icDelete = view.findViewById(R.id.iv_delete);
            holder.tvNhsxname = view.findViewById(R.id.tv_nhsxname);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        Product product = productList.get(position);
        holder.tvProductname.setText(product.getName());
        holder.tvDatesx.setText(product.getDatexs());
        holder.tvNhsxname.setText(product.getIdNhsx());
        holder.tvQuicach.setText(product.getQuicach());
        holder.icDelete.setOnClickListener(v -> context.deleteProduct(product));
        holder.icEdit.setOnClickListener(v -> context.showDialog(MainActivity.DialogType.UPDATE, product));
        return view;
    }


}
