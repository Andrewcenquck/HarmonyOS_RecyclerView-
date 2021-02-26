package com.chenkui.listpicapplication.adapter;

import ohos.agp.components.BaseItemProvider;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;

public abstract class RecyclerAdapter<VH extends RecyclerAdapter.ViewHolder> extends BaseItemProvider {
    public static final int NO_POSITION = -1;
    public abstract ViewHolder onCreateViewHolder(ComponentContainer componentContainer, int viewType);

    public abstract int getItemCount();

    public abstract void onBindViewHolder(VH holder, int position);

    public abstract int getItemViewType(int position);

    @Override
    public Component getComponent(int position, Component component, ComponentContainer componentContainer) {
        Component itemComponent = component;
        ViewHolder itemViewHolder;
        if (itemComponent == null) {
            itemViewHolder = onCreateViewHolder(componentContainer, getItemViewType(position));
            itemViewHolder.itemComponent.setTag(itemViewHolder);
        } else {
            itemViewHolder = (ViewHolder) itemComponent.getTag();
        }
        itemViewHolder.mPosition=position;
        onBindViewHolder((VH) itemViewHolder, position);
        return itemViewHolder.itemComponent;
    }

    @Override
    public int getCount() {
        return getItemCount();
    }



    @Override
    public void notifyDataSetItemRemoved(int position) {
        super.notifyDataSetItemRemoved(position);
    }

    public static class ViewHolder {
        int mPosition = NO_POSITION;

        int mPreLayoutPosition = NO_POSITION;

        Component itemComponent;
        public ViewHolder(Component itemComponent) {
            if (itemComponent == null) {
                throw new IllegalArgumentException("itemComponent may not be null");
            }
            this.itemComponent = itemComponent;

        }


        public final int getLayoutPosition() {
            return mPreLayoutPosition == NO_POSITION ? mPosition : mPreLayoutPosition;
        }

    }

}
