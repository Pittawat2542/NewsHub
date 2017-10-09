package com.example.newshub.adapter;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.newshub.R;
import com.example.newshub.activity.MainActivity;
import com.example.newshub.chrometab.CustomTabActivityHelper;
import com.example.newshub.manager.RssReader;
import com.example.newshub.utils.FeedItem;
import com.example.newshub.utils.WebViewFallBack;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by pitta on 8/3/2559.
 */
public class NewsItemListAdapter extends RecyclerView.Adapter<NewsItemListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<FeedItem> feedItems;
    private Activity activity;
    private CustomTabActivityHelper mCustomTabActivityHelper;
    private int index;

    public NewsItemListAdapter(Activity activity, Context context, ArrayList<FeedItem> arrayList, int position) {
        this.feedItems = arrayList;
        this.mContext = context;
        this.activity = activity;
        this.index = position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_news, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        YoYo.with(Techniques.FadeInUp).playOn(holder.cardView);
        final FeedItem current = feedItems.get(position);
        holder.title.setText(current.getTitle());
        holder.description.setText(current.getDescription());
        holder.date.setText(current.getPubDate());
        switch (index) {
            case 1:
                switch (Locale.getDefault().getDisplayLanguage()) {
                    case "English":
                        Glide.with(mContext)
                                .load("http://www.captioningandsubtitling.com.au/core/wp-content/uploads/ABC.jpg")
                                .placeholder(R.drawable.loading)
                                .into(holder.img);
                        break;
                    case "Tiếng Việt":
                        Glide.with(mContext)
                                .load("http://www.sdcc.com.vn/upload/file_upload/819249-logo_default.png")
                                .placeholder(R.drawable.loading)
                                .into(holder.img);
                        break;
                    default:
                        Glide.with(mContext)
                                .load(current.getEnclosure())
                                .placeholder(R.drawable.loading)
                                .into(holder.img);
                }
                break;
            case 2:
                switch (Locale.getDefault().getDisplayLanguage()) {
                    case "ไทย":
                        Glide.with(mContext)
                                .load("http://www.droidsans.com/sites/default/files/droidsans.jpg")
                                .placeholder(R.drawable.loading)
                                .into(holder.img);
                        break;
                    default:
                        Glide.with(mContext)
                                .load(current.getEnclosure())
                                .placeholder(R.drawable.loading)
                                .into(holder.img);
                }
                break;
            case 3:
                switch (Locale.getDefault().getDisplayLanguage()) {
                    case "English":
                        Glide.with(mContext)
                                .load("http://unnetwork.org/unmc/images/un-news-center.png")
                                .placeholder(R.drawable.loading)
                                .into(holder.img);
                        break;
                    case "Tiếng Việt":
                        Glide.with(mContext)
                                .load("http://baoyenbai.xembao.vn/images/logo_yenbai.png")
                                .placeholder(R.drawable.loading)
                                .into(holder.img);
                        break;
                    default:
                        Glide.with(mContext)
                                .load(current.getEnclosure())
                                .placeholder(R.drawable.loading)
                                .into(holder.img);
                }
                break;
            case 4:
                switch (Locale.getDefault().getDisplayLanguage()) {
                    case "English":
                        Glide.with(mContext)
                                .load("https://www.filepicker.io/api/file/eCVM2kufRTCF94LZlN8h")
                                .placeholder(R.drawable.loading)
                                .into(holder.img);
                        break;
                    default:
                        Glide.with(mContext)
                                .load(current.getEnclosure())
                                .placeholder(R.drawable.loading)
                                .into(holder.img);
                }
                break;
            case 6:
                switch (Locale.getDefault().getDisplayLanguage()) {
                    case "ไทย":
                        Glide.with(mContext)
                                .load("http://ptcdn.info/pantip/pantip_logo.png")
                                .placeholder(R.drawable.loading)
                                .into(holder.img);
                        break;
                    default:
                        Glide.with(mContext)
                                .load(current.getEnclosure())
                                .placeholder(R.drawable.loading)
                                .into(holder.img);
                }
                break;
            case 7:
                switch (Locale.getDefault().getDisplayLanguage()) {
                    case "ไทย":
                        Glide.with(mContext)
                                .load("https://www.blognone.com/sites/default/files/blognone-thumb.png")
                                .placeholder(R.drawable.loading)
                                .into(holder.img);
                        break;
                    default:
                        Glide.with(mContext)
                                .load(current.getEnclosure())
                                .placeholder(R.drawable.loading)
                                .into(holder.img);
                }
                break;
            default:
                Glide.with(mContext)
                        .load(current.getEnclosure())
                        .placeholder(R.drawable.loading)
                        .into(holder.img);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupCustomTabHelper(current.getLink());
                openCustomTab(current.getLink());
            }
        });
    }

    @Override
    public int getItemCount() {
        ConnectivityManager cm =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            return feedItems.size();
        } else {
            Toast.makeText(mContext, R.string.error_no_internet, Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, date;
        ImageView img;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tvHeadLine);
            description = (TextView) itemView.findViewById(R.id.tvDesc);
            date = (TextView) itemView.findViewById(R.id.tvPubDate);
            img = (ImageView) itemView.findViewById(R.id.ivImg);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }

    /**************************
     * Chrome Custom Tabs
     *************************/
    private void setupCustomTabHelper(String url) {
        mCustomTabActivityHelper = new CustomTabActivityHelper();
        mCustomTabActivityHelper.setConnectionCallback(mConnectionCallback);
        mCustomTabActivityHelper.mayLaunchUrl(Uri.parse(url), null, null);
    }

    private void openCustomTab(String url) {

        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();

        intentBuilder.setToolbarColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        intentBuilder.setShowTitle(true);
        intentBuilder.setCloseButtonIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_arrow_back));
        intentBuilder.setActionButton(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_share), "Share", createPendingShareIntent());

        PendingIntent menuItemPendingIntent = createPendingShareIntent();
        intentBuilder.addMenuItem("Share", menuItemPendingIntent);

        CustomTabActivityHelper.openCustomTab(activity, intentBuilder.build(), Uri.parse(url), new WebViewFallBack());
    }

    private CustomTabActivityHelper.ConnectionCallback mConnectionCallback = new CustomTabActivityHelper.ConnectionCallback() {
        @Override
        public void onCustomTabsConnected() {
            Toast.makeText(mContext, "Connected to service", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCustomTabsDisconnected() {
            Toast.makeText(mContext, "Disconnected from service", Toast.LENGTH_SHORT).show();
        }
    };

    private PendingIntent createPendingShareIntent() {
        Intent actionIntent = new Intent(Intent.ACTION_SEND);
        actionIntent.setType("text/plain");
        actionIntent.putExtra(Intent.EXTRA_TEXT, "This is sharing some text");
        return PendingIntent.getActivity(mContext, 0, actionIntent, 0);
    }
}