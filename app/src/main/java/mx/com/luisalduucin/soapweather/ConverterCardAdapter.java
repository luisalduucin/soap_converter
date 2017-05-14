package mx.com.luisalduucin.soapweather;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class ConverterCardAdapter extends RecyclerView.Adapter<ConverterCardAdapter.MyViewHolder> {

    private List<ConverterCard> converterCards;

    ConverterCardAdapter(List<ConverterCard> converterCards) {
        this.converterCards = converterCards;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.converter_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ConverterCard converterCard = converterCards.get(position);
        holder.title.setText(converterCard.getTitle());
//        holder.value.setText(converterCard.getValue());
//        holder.image.setImageResource(converterCard.getImageResource());

//        Glide.with(context).load(R.drawable.ic_delete).dontAnimate().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return converterCards.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
//        TextView value;
//        ImageView image;

        MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.converter_title);
//            value = (TextView) view.findViewById(R.id.to_convert_value);
//            image = (ImageView) view.findViewById(R.id.delete_image);
        }
    }
}
