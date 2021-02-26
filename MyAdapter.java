package com.chenkui.listpicapplication.adapter;

import com.chenkui.listpicapplication.ResourceTable;
import com.chenkui.listpicapplication.bean.MessageInfo;
import com.chenkui.listpicapplication.slice.TestMainAbilitySlice;
import ohos.agp.components.*;
import ohos.app.Context;
import ohos.multimodalinput.event.TouchEvent;
import java.util.ArrayList;

public class MyAdapter extends RecyclerAdapter<RecyclerAdapter.ViewHolder> {
    private int listType = 1;
    private int bottomType = 2;

    private ArrayList<MessageInfo.DataBean> data;
    private  TestMainAbilitySlice abilitySlice;

    public MyAdapter(ArrayList<MessageInfo.DataBean> list, TestMainAbilitySlice abilitySlice) {
        super();
        this.data = list;
        this.abilitySlice=abilitySlice;
    }


    @Override
    public int getItemViewType(int position) {
        int type = 0;
        if (position < getItemCount() - 1) {
            type = listType;
        } else {
            type = bottomType;
        }
        return type;
    }


    @Override
    public int getItemCount() {
        return data.size() +1;
    }


    @Override
    public ViewHolder onCreateViewHolder(ComponentContainer componentContainer, int viewType) {
        Context context = componentContainer.getContext();

        ViewHolder viewHolder = null;
        if (viewType == listType) {
            Component itemComponent = LayoutScatter.getInstance(abilitySlice)
                    .parse(ResourceTable.Layout_test_list_item, componentContainer, false);
            viewHolder = new ListViewHolder(itemComponent);
        } else if (viewType == bottomType) {
            Component itemComponent = LayoutScatter.getInstance(context)
                    .parse(ResourceTable.Layout_buttom_layout, componentContainer, false);
            viewHolder = new ButtomViewHolder(itemComponent);
        }
        return viewHolder;
    }



    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof ListViewHolder) {
            ListViewHolder viewHolder = (ListViewHolder) holder;
            viewHolder.nicknametext.setText(data.get(position).getNickname());
            viewHolder.companynametext.setText(data.get(position).getCompanyname() + "|" + data.get(position).getJobname());
            viewHolder.msgtext.setText(data.get(position).getMsg());
            viewHolder.msgtext.setTouchEventListener(new Component.TouchEventListener() {
                @Override
                public boolean onTouchEvent(Component component, TouchEvent touchEvent) {
                    return false;
                }
            });
        }
    }


    class ListViewHolder extends ViewHolder {
        Text nicknametext;
        Text companynametext;
        Text msgtext;
        public ListViewHolder(Component itemComponent) {
            super(itemComponent);
            nicknametext = (Text) itemComponent.findComponentById(ResourceTable.Id_message_nicknametext);
            companynametext = (Text) itemComponent.findComponentById(ResourceTable.Id_message_companynametext);
            msgtext = (Text) itemComponent.findComponentById(ResourceTable.Id_msg_text);
        }
    }

    class  ButtomViewHolder extends ViewHolder{
        public ButtomViewHolder(Component itemComponent) {
            super(itemComponent);



        }
    }

}
