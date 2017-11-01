package codingwithmitch.com.forsale.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import codingwithmitch.com.forsale.R;
import codingwithmitch.com.forsale.SearchActivity;
import codingwithmitch.com.forsale.SearchFragment;
import codingwithmitch.com.forsale.models.Post;

/**
 * Created by User on 11/1/2017.
 */

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder>{

    private static final String TAG = "PostListAdapter";
    private static final int NUM_GRID_COLUMNS = 3;

    private ArrayList<Post> mPosts;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder{

        SquareImageView mPostImage;

        public ViewHolder(View itemView) {
            super(itemView);
            mPostImage = (SquareImageView) itemView.findViewById(R.id.post_image);

            int gridWidth = mContext.getResources().getDisplayMetrics().widthPixels;
            int imageWidth = gridWidth/NUM_GRID_COLUMNS;
            mPostImage.setMaxHeight(imageWidth);
            mPostImage.setMaxWidth(imageWidth);
        }
    }

    public PostListAdapter(Context context, ArrayList<Post> posts) {
        mPosts = posts;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_view_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UniversalImageLoader.setImage(mPosts.get(position).getImage(), holder.mPostImage);

        final int pos = position;
        holder.mPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: selected a post");
                //TODO

                //view the post in more detail
                Fragment fragment = (Fragment)((SearchActivity)mContext).getSupportFragmentManager()
                        .findFragmentByTag("android:switcher:" + R.id.viewpager_container + ":" +
                                ((SearchActivity)mContext).mViewPager.getCurrentItem());
                if(fragment != null){
                    //SearchFragment (AKA #0)
                    if(fragment.getTag().equals("android:switcher:" + R.id.viewpager_container + ":0")){
                        Log.d(TAG, "onClick: switching to: " + mContext.getString(R.string.fragment_view_post));

                        SearchFragment searchFragment = (SearchFragment)((SearchActivity)mContext).getSupportFragmentManager()
                                .findFragmentByTag("android:switcher:" + R.id.viewpager_container + ":" +
                                        ((SearchActivity)mContext).mViewPager.getCurrentItem());

                        searchFragment.viewPost(mPosts.get(pos).getPost_id());
                    }
                    //WatchList Fragment (AKA #1)
                    else if(fragment.getTag().equals("android:switcher:" + R.id.viewpager_container + ":1")){
                        Log.d(TAG, "onClick: switching to: " + mContext.getString(R.string.fragment_watch_list));
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }


}













