package com.example.faizan.eyespeak;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Faizan on 2017-03-12.
 */

public class OldPicListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<OldPic> oldPicsList;

    public OldPicListAdapter(Context context, int layout, ArrayList<OldPic> oldPicsList) {
        this.context = context;
        this.layout = layout;
        this.oldPicsList = oldPicsList;
    }

    @Override
    public int getCount() {
        return oldPicsList.size();
    }

    @Override
    public Object getItem(int i) {
        return oldPicsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        ImageView thumbnail;
        TextView word;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.word = (TextView) row.findViewById(R.id.word);
            holder.thumbnail = (ImageView) row.findViewById(R.id.thumbnail);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        OldPic oldPic = oldPicsList.get(i);
        holder.word.setText(oldPic.getWord());

        byte[] oldPicBytes = oldPic.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(oldPicBytes, 0, oldPicBytes.length);
        holder.thumbnail.setImageBitmap(bitmap);

        return row;

    }
}
