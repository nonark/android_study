package search.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import search.android.aos_search.R;
import search.android.vo.SummaryPage;

/**
 * Created by nhnent on 2017. 4. 5..
 */

public class SummaryPageAdapter extends RecyclerView.Adapter<SummaryPageAdapter.ViewHolder> {

    private List<SummaryPage> wikiPages;
    private int itemLayout;

    private OnRecyclerViewItemClickedListener relatedItemClickedListner;
    private OnRecyclerViewItemClickedListener headerItemClickedLListner;

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_RELATED_PAGE = 1;

    //Getter and Setter
    public List<SummaryPage> getWikiPages() {
        return wikiPages;
    }
    public void setWikiPages(List<SummaryPage> wikiPages) {
        this.wikiPages = wikiPages;
    }

    public int getItemLayout() {
        return itemLayout;
    }
    public void setItemLayout(int itemLayout) {
        this.itemLayout = itemLayout;
    }

    public OnRecyclerViewItemClickedListener getRelatedItemClickedListner() { return relatedItemClickedListner; }
    public void setRelatedListner(OnRecyclerViewItemClickedListener relatedItemClickedListner) { this.relatedItemClickedListner = relatedItemClickedListner; }

    public OnRecyclerViewItemClickedListener getHeaderItemClickedLListner() { return headerItemClickedLListner; }
    public void setHeaderItemClickedLListner(OnRecyclerViewItemClickedListener headerItemClickedLListner) { this.headerItemClickedLListner = headerItemClickedLListner; }

    public SummaryPageAdapter(List<SummaryPage> wikiPages, int itemLayout) {
        this.wikiPages = wikiPages;
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if( viewType == VIEW_TYPE_HEADER ) {
            View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
            return new ViewHolder(view, headerItemClickedLListner);
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(view, relatedItemClickedListner);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SummaryPage wikiPage = wikiPages.get(position);
        holder.title.setText(wikiPage.getTitle());
        holder.summary.setText(wikiPage.getSummary());
    }

    @Override
    public int getItemCount() {
        return wikiPages.size();
    }

    @Override
    public int getItemViewType(int position) {

        if(position == 0) {
            return VIEW_TYPE_HEADER;
        }

        return VIEW_TYPE_RELATED_PAGE;
    }

    /*
         * ViewHolder
         */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView thumbnail;
        public TextView title;
        public TextView summary;
        public OnRecyclerViewItemClickedListener listner;

        public ViewHolder(View itemView) {
            this(itemView, null);
        }

        public ViewHolder(View itemView, OnRecyclerViewItemClickedListener listner) {
            super(itemView);

            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.title);
            summary = (TextView) itemView.findViewById(R.id.summary);
            this.listner = listner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listner != null) {
                listner.onItemClicked(title.getText().toString());
            }
        }
    }

    public interface OnRecyclerViewItemClickedListener {
        void onItemClicked(String searchText);
    }
}
