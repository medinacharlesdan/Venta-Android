package charles.sandbox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import static com.bumptech.glide.request.RequestOptions.fitCenterTransform;
/**
 * Created by cha on 12/21/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public RecyclerViewAdapter() {
    }

    public Context context;

    public List<ImageUploadInfo> MainImageUploadInfoList;
    public ImageUploadInfo UploadInfo;
    public RecyclerViewAdapter(Context context, List<ImageUploadInfo> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }

    public List<ImageUploadInfo> getMainImageUploadInfoList() {
        return MainImageUploadInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        UploadInfo = MainImageUploadInfoList.get(position);

        holder.productNameTV.setText(UploadInfo.getProductName());
        holder.PriceTV.setText(UploadInfo.getPrice());

        holder.optionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(context, holder.optionBtn);
                //inflating menu from xml resource
                popup.inflate(R.menu.actionmenu);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {

                            case R.id.callOpt:

                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:" + UploadInfo.contact));
                                if (callIntent.resolveActivity(context.getPackageManager()) != null) {
                                    context.startActivity(callIntent);
                                }


                            case R.id.emailOpt:
                                Intent intenta = new Intent(Intent.ACTION_SEND);
                                intenta.setType("plain/text");
                                intenta.putExtra(Intent.EXTRA_EMAIL, new String[]{UploadInfo.getEmail()});
                                intenta.putExtra(Intent.EXTRA_SUBJECT, "Interested in buying your " + UploadInfo.productName);
                                //intent.putExtra(Intent.EXTRA_TEXT, "C");
                                context.startActivity(Intent.createChooser(intenta, "Send email using:"));


                            case R.id.smsOpt:
                                Uri smsUri = Uri.parse("tel:" + UploadInfo.getContact());
                                Intent intentt = new Intent(Intent.ACTION_VIEW, smsUri);
                                intentt.putExtra("address", UploadInfo.getContact());
                                intentt.putExtra("sms_body", "Interested in buying your " + UploadInfo.getProductName());
                                intentt.setType("vnd.android-dir/mms-sms");
                                context.startActivity(Intent.createChooser(intentt, "Send msg using:"));


                        }
                        return false;
                    }

                });
                popup.show();
            }
        });




        //Loading image from Glide library.
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        Glide.with(context)
                .load(UploadInfo.getImageURL())
                .apply(fitCenterTransform())
                .apply(options)
                .into(holder.productImage)
        ;
    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  {

        public ImageView productImage,optionBtn;
        public TextView productNameTV,PriceTV;


        public ViewHolder(View itemView) {
            super(itemView);

            productImage = (ImageView) itemView.findViewById(R.id.theIV);

            optionBtn = (ImageView) itemView.findViewById(R.id.theOption);
            optionBtn.setClickable(true);





            productNameTV = (TextView) itemView.findViewById(R.id.productNametextView);
            PriceTV = (TextView) itemView.findViewById(R.id.priceTextView);

        }


    }

}
