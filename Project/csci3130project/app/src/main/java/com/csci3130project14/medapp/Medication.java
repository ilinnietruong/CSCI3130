package com.csci3130project14.medapp;

public class Medication {

    //medication attributes
    private String medcationName;
    private String mMedicationDosage;
    private String mMedicationTimeOfDay;
    private String mMedicationDayOfWeek;

    /**
     * Default constructor
     */
    public Medication(){

    }

    /**
     *
     * @param nameEntry Name of medication being entered
     * @param dosage The dosage of medication created
     * @param time The time(s) of day to consume medicatoin
     * @param day The day(s) of week to consume medication
     */
    public Medication(String nameEntry, String dosage, String time, String day){
        medcationName = nameEntry;
        mMedicationDosage= dosage;
        mMedicationTimeOfDay = time;
        mMedicationDayOfWeek = day;
    }


    /**
     *  Get the dosage amount of the medication
     *
     * @return the dosage amount of the medication
     */
    public String getMedicationDosage() {
        return mMedicationDosage;
    }


    /**
     *
     *
     * @param medicationDosage the dosage amount to be set
     */
    public void setMedicationDosage(String medicationDosage) {
        mMedicationDosage = medicationDosage;
    }


    /**
     * Get the medication times of consumption
     *
     * @return the time(s) of day the medication is consumed
     */
    public String getMedicationTimeOfDay() {
        return mMedicationTimeOfDay;
    }

    /**
     * Change the times of day for taking the medication
     *
     * @param medicationTimeOfDay the time(s) of day to be set
     */
    public void setMedicationTimeOfDay(String medicationTimeOfDay) {
        mMedicationTimeOfDay = medicationTimeOfDay;
    }


    /**
     * Get the day(s) to consume the medication
     *
     * @return the day(s) of week to consume medication
     */
    public String getMedicationDayOfWeek() {
        return mMedicationDayOfWeek;
    }

    /**
     * Chanege the days to consume the medication
     *
     * @param medicationDayOfWeek the day(s) of medication consumption to be set
     */
    public void setMedicationDayOfWeek(String medicationDayOfWeek) {
        mMedicationDayOfWeek = medicationDayOfWeek;
    }


    /**
     * Get the name of the medication
     *
     * @return the name of medication
     */
    public String getMedcationName() {
        return medcationName;
    }

    /**
     * Change the name of the medication
     *
     * @param medicationName the name of the medication to be set
     */
    public void setMedcationName(String medicationName) {
        medcationName = medicationName;
    }


}
