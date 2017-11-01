package codingwithmitch.com.forsale.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by User on 11/1/2017.
 */

public class RecyclerViewMargin extends RecyclerView.ItemDecoration{

    private final int columns;
    private int margin;

    public RecyclerViewMargin(int columns, int margin) {
        this.columns = columns;
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int position = parent.getChildAdapterPosition(view);
        outRect.right = margin;
        outRect.bottom = margin;

        if(position < columns){
            outRect.top = margin;
        }

        if(position % columns == 0) {
            outRect.left = margin;
        }
    }
}
