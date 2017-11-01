package codingwithmitch.com.forsale;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import codingwithmitch.com.forsale.models.Post;
import codingwithmitch.com.forsale.util.PostListAdapter;
import codingwithmitch.com.forsale.util.RecyclerViewMargin;

/**
 * Created by User on 10/22/2017.
 */

public class WatchListFragment extends Fragment {

    private static final String TAG = "WatchListFragment";
    private static final int NUM_GRID_COLUMNS = 3;
    private static final int GRID_ITEM_MARGIN = 5;

    //widgets
    private RecyclerView mRecyclerView;
    private FrameLayout mFrameLayout;

    //vars
    private PostListAdapter mAdapter;
    private ArrayList<Post> mPosts;
    private ArrayList<String> mPostsIds;
    private DatabaseReference mReference;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_watch_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.watchListRecyclerView);
        mFrameLayout = (FrameLayout) view.findViewById(R.id.watch_list_container);

        init();

        return view;
    }

    private void init(){
        Log.d(TAG, "init: initializing.");
        mPosts = new ArrayList<>();
        setupPostsList();

        //reference for listening when items are added or removed from the watch list
        mReference = FirebaseDatabase.getInstance().getReference()
                .child(getString(R.string.node_watch_list))
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        //set the listener to the reference
        mReference.addValueEventListener(mLisenter);

    }

    private void getWatchListIds(){
        Log.d(TAG, "getWatchListIds: getting users watch list.");
        if(mPosts != null){
            mPosts.clear();
        }
        if(mPostsIds != null){
            mPostsIds.clear();
        }

        mPostsIds = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query = reference.child(getString(R.string.node_watch_list))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildren().iterator().hasNext()){
                    DataSnapshot singleSnapshot = dataSnapshot.getChildren().iterator().next();
                    for(DataSnapshot snapshot: singleSnapshot.getChildren()){
                        String id = snapshot.child(getString(R.string.field_post_id)).getValue().toString();
                        Log.d(TAG, "onDataChange: found a post id: " + id);
                        mPostsIds.add(id);
                    }
                    getPosts();
                }else{
                    getPosts();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getPosts(){
        if(mPostsIds.size() > 0){
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

            for(int i  = 0; i < mPostsIds.size(); i++){
                Log.d(TAG, "getPosts: getting post information for: " + mPostsIds.get(i));

                Query query = reference.child(getString(R.string.node_posts))
                        .orderByKey()
                        .equalTo(mPostsIds.get(i));

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        DataSnapshot singleSnapshot = dataSnapshot.getChildren().iterator().next();
                        Post post = singleSnapshot.getValue(Post.class);
                        Log.d(TAG, "onDataChange: found a post: " + post.getTitle());
                        mPosts.add(post);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }else{
            mAdapter.notifyDataSetChanged(); //still need to notify the adapter if the list is empty
        }
    }

    public void viewPost(String postId){
        ViewPostFragment fragment = new ViewPostFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        Bundle args = new Bundle();
        args.putString(getString(R.string.arg_post_id), postId);
        fragment.setArguments(args);

        transaction.replace(R.id.watch_list_container, fragment, getString(R.string.fragment_view_post));
        transaction.addToBackStack(getString(R.string.fragment_view_post));
        transaction.commit();

        mFrameLayout.setVisibility(View.VISIBLE);
    }

    private void setupPostsList(){
        RecyclerViewMargin itemDecorator = new RecyclerViewMargin(GRID_ITEM_MARGIN, NUM_GRID_COLUMNS);
        mRecyclerView.addItemDecoration(itemDecorator);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), NUM_GRID_COLUMNS);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new PostListAdapter(getActivity(), mPosts);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mReference.removeEventListener(mLisenter);
    }

    ValueEventListener mLisenter = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Log.d(TAG, "onDataChange: a change was made to this users watch lits node.");
            getWatchListIds();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}





















