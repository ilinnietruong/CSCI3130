package com.csci3130project14.medapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class MedicationRecyclerViewAdapter extends
        RecyclerView.Adapter<MedicationRecyclerViewAdapter.MedicationViewHolder>{


    Context viewMedContext;
     private List<Medication> mUserMedicationList;

     public MedicationRecyclerViewAdapter(Context medC ,List<Medication> dataList){
         viewMedContext = medC;
         mUserMedicationList = dataList;
     }


    /**
     * To create the template view for showing details of each medication
     *
     * @param parent the activity that uses the adapter to show the medication on screen
     * @param viewType the layout to view the information of each
     * @return
     */
    @NonNull
    @Override
    public MedicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //create some view to show details
        LayoutInflater detailsInflater = LayoutInflater.from(parent.getContext());
        View medDetailsView = detailsInflater.inflate(R.layout.med_details, parent, false);

        MedicationViewHolder medViewHolder = new MedicationViewHolder(medDetailsView);

        return medViewHolder;
    }


    /**
     * Set the details for a single medication
     *
     * @param holder to ge the view items to be manipulated
     * @param position the position of a given medication in the list being passed into the adapter
     */
    @Override
    public void onBindViewHolder(@NonNull final MedicationViewHolder holder, final int position) {
        String medName =  mUserMedicationList.get(position).getMedcationName();
        holder.medNameText.setText(medName);

        String medDosage = "Dosage Amount: " + mUserMedicationList.get(position).getMedicationDosage();
        holder.medDosageText.setText(medDosage);

        String medTimeOfDay = "Time(s) of the day: " + mUserMedicationList.get(position).getMedicationTimeOfDay();
        holder.medDailyTimeText.setText(medTimeOfDay);

        String medDayOfWeek = "Day(s) of the week: " + mUserMedicationList.get(position).getMedicationDayOfWeek();
        holder.medDayOfWeekText.setText(medDayOfWeek);

        //include the expansion code here
        //make the time text views appear
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //visibility of the times
                if(holder.medDailyTimeText.getVisibility() == View.GONE){
                    holder.medDailyTimeText.setVisibility(View.VISIBLE);

                }
                if(holder.medDayOfWeekText.getVisibility() == View.GONE){
                    holder.medDayOfWeekText.setVisibility(View.VISIBLE);

                }

                //visibility of the options buttons

                //set up the edit button
                if(holder.editOptionButton.getVisibility() == View.GONE){
                    holder.editOptionButton.setVisibility(View.VISIBLE);

                    holder.editOptionButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent editIntent = new Intent(viewMedContext, editActivity.class);
                            editIntent.putExtra("Name of medication", mUserMedicationList.get(position).getMedcationName());
                            editIntent.putExtra("Medication Dosage", mUserMedicationList.get(position).getMedicationDosage());
                            editIntent.putExtra("Time of medication", mUserMedicationList.get(position).getMedicationTimeOfDay());
                            editIntent.putExtra("Day of medication", mUserMedicationList.get(position).getMedicationDayOfWeek());

                            viewMedContext.startActivity(editIntent);

                        }
                    });
                }

                //set up the delete button
                if(holder.deleteOptionButton.getVisibility() == View.GONE){
                    holder.deleteOptionButton.setVisibility(View.VISIBLE);

                    holder.deleteOptionButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent deleteIntent = new Intent(viewMedContext, DeleteActivity.class);
                        deleteIntent.putExtra("Name of medication", mUserMedicationList.get(position).getMedcationName());
                        deleteIntent.putExtra("Medication Dosage", mUserMedicationList.get(position).getMedicationDosage());
                        deleteIntent.putExtra("Time of medication", mUserMedicationList.get(position).getMedicationTimeOfDay());
                        deleteIntent.putExtra("Day of medication", mUserMedicationList.get(position).getMedicationDayOfWeek());

                        viewMedContext.startActivity(deleteIntent);
                    }
                    });
                }
                else{
                    holder.medDailyTimeText.setVisibility(View.GONE);
                    holder.medDayOfWeekText.setVisibility(View.GONE);
                    holder.editOptionButton.setVisibility(View.GONE);
                    holder.deleteOptionButton.setVisibility(View.GONE);
                }

                            }
        });


    }





    /**
     * Get the number of items available to the RecyclerView adapter
     *
     * @return The number of items for RecyclerView to handle
     */
    @Override
    public int getItemCount() {
        return mUserMedicationList.size();
    }


    /**
     * ViewHolder class to contain the views to show each medication
     */
    public class MedicationViewHolder extends  RecyclerView.ViewHolder{
        TextView medNameText;
        TextView medDosageText;
        TextView medDailyTimeText;
        TextView medDayOfWeekText;

        //get the buttons
        Button editOptionButton;
        Button deleteOptionButton;

        /**
         *
         * @param itemView the medication to be viewed in the list
         */
        public MedicationViewHolder(@NonNull View itemView) {
            super(itemView);
            medNameText = (TextView) itemView.findViewById(R.id.MedName_TextView);
            medDosageText =  (TextView) itemView.findViewById(R.id.MedDosage_TextView);
            medDailyTimeText = (TextView) itemView.findViewById(R.id.MedDailyTime_TextView);
            medDayOfWeekText = (TextView) itemView.findViewById(R.id.MedDayOfWeek_TextView);
            editOptionButton = (Button) itemView.findViewById(R.id.medEdit_button);
            deleteOptionButton = (Button) itemView.findViewById(R.id.medDelete_button);

        }
    }


}


