package mx.com.luisalduucin.soapweather;


import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

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

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ConverterCard converterCard = converterCards.get(position);

        List<String> availableUnits = converterCard.getUnits();
        final String[] units = availableUnits.toArray(new String[availableUnits.size()]);

        View.OnClickListener fromUnitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(converterCard.getContext())
                        .setTitle(R.string.available_units)
                        .setItems(units, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                holder.fromUnit.setText(units[which]);
                            }
                        }).create().show();
            }
        };

        View.OnClickListener toUnitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(converterCard.getContext())
                        .setTitle(R.string.available_units)
                        .setItems(units, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                holder.toUnit.setText(units[which]);
                            }
                        }).create().show();
            }
        };

        holder.title.setText(converterCard.getTitle());
        holder.fromUnit.setOnClickListener(fromUnitClickListener);
        holder.toUnit.setOnClickListener(toUnitClickListener);
        holder.convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                converterCard.getConverter()
                        .setFromUnits(holder.fromUnit.getText().toString())
                        .setToUnits(holder.toUnit.getText().toString())
                        .setToConvertValue(holder.toConvertValue.getText().toString())
                        .convert()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Double>() {
                            @Override
                            public void call(Double convertedValue) {
                                holder.result.setText(String.valueOf(convertedValue));
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return converterCards.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextInputEditText toConvertValue;
        TextView fromUnit;
        TextView toUnit;
        TextView result;
        Button convert;

        MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.converter_title);
            toConvertValue = (TextInputEditText) view.findViewById(R.id.to_convert_value);
            fromUnit = (TextView) view.findViewById(R.id.from_unit_value);
            toUnit = (TextView) view.findViewById(R.id.to_unit_value);
            result = (TextView) view.findViewById(R.id.result_value);
            convert = (Button) view.findViewById(R.id.convert);
        }
    }
}
