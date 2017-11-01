package codingwithmitch.com.forsale;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by User on 10/22/2017.
 */

public class ViewPostFragment extends Fragment {

    private static final String TAG = "ViewPostFragment";

    //vars
    private String mPostId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPostId = (String) getArguments().get(getString(R.string.arg_post_id));
        Log.d(TAG, "onCreate: got the post id: " + mPostId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_post, container, false);

        return view;
    }
}
