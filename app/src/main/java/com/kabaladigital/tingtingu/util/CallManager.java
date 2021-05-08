package com.kabaladigital.tingtingu.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telecom.Call;
import android.telecom.VideoProfile;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kabaladigital.tingtingu.database.entity.Contact;
import com.kabaladigital.tingtingu.ui.activity.OngoingCallActivity;
import com.kabaladigital.tingtingu.util.validation.Validator;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import timber.log.Timber;

public class CallManager {

    // Variables
    public static Call sCall;
    public static List<Call> sCalls;

    private static boolean sIsAutoCalling = false;
    private static List<Contact> sAutoCallingContactsList = null;
    private static int sAutoCallPosition = 0;


    // -- Call Actions -- //

    /**
     * Call a given number
     *addCall
     * @param context
     * @param number
     */
    public static void call(@NotNull Context context, @NotNull String number) {
        Timber.i("Trying to call: %s", number);
        String uri;
        try {
            // Set the data for the call
            if (number.contains("#")) uri = "tel: " + Uri.encode(number);
            else uri = "tel: " + number;
            Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
            context.startActivity(callIntent); // Start the call
        } catch (SecurityException e) {
            Timber.e(e, "Couldn't call %s", number);
        }
    }

    /**
     * Call voicemail
     */
    public static boolean callVoicemail(Context context) {
        try {
            Uri uri = Uri.parse("voicemail:1");
            Intent voiceMail = new Intent(Intent.ACTION_CALL, uri);
            context.startActivity(voiceMail);
            return true;
        } catch (SecurityException e) {
            Toast.makeText(context, "Couldn't start Voicemail", Toast.LENGTH_LONG).show();
            Timber.e(e);
            return false;
        }
    }

    /**
     * Answers incoming call
     */
    public static void answer() {
        if (sCall != null) {
            sCall.answer(VideoProfile.STATE_AUDIO_ONLY);
        }
    }

    /**
     * Ends call
     * If call ended from the other side, disconnects
     *
     * @return true whether there's no more calls awaiting
     */
    public static void reject() {
        if (sCall != null) {
            if (sCall.getState() == Call.STATE_RINGING) {
                sCall.reject(false, null);
            } else {
                sCall.disconnect();
            }
            if (sIsAutoCalling) sAutoCallPosition++;
        }
    }

    /**
     * Put call on hold
     *
     * @param hold
     */
    public static void hold(boolean hold) {
        if (sCall != null) {
            if (hold) sCall.hold();
            else sCall.unhold();
        }
    }

    /**
     * Open keypad
     *
     * @param c
     */
    public static void keypad(char c) {
        if (sCall != null) {
            sCall.playDtmfTone(c);
            sCall.stopDtmfTone();
        }
    }

    /**
     * Add a call to the current call
     *
     * @param call
     */
    public static void addCall(Call call) {
        sCall.conference(call);
    }

    public static void mergeCall() {
        sCall.mergeConference();
    }

    /**
     * Registers a Callback object to the current call
     *
     * @param callback the callback to register
     */
    public static void registerCallback(OngoingCallActivity.Callback callback) {
        if (sCall == null)
            return;

        for (int i=0;i<sCalls.size();i++){
            sCalls.get(i).registerCallback(callback);
        }
//        sCall.registerCallback(callback);
    }

    /**
     * Unregisters the Callback from the current call
     *
     * @param callback the callback to unregister
     */
    public static void unregisterCallback(Call.Callback callback) {
        if (sCall == null) return;
        sCall.unregisterCallback(callback);
    }

    // -- Auto-Calling -- //

    /**
     * Start the auto calling on the list of contacts
     *
     * @param list          a list of contacts
     * @param context       the context
     * @param startPosition the start position from which to start calling
     */
    public static void startAutoCalling(@NotNull List<Contact> list, @NotNull AppCompatActivity context, int startPosition) {
        sIsAutoCalling = true;
        sAutoCallPosition = startPosition;
        sAutoCallingContactsList = list;
        if (sAutoCallingContactsList.isEmpty()) Timber.e("No contacts in auto calling list");
        nextCall(context);
    }

    /**
     * Go to the next call
     *
     * @param context
     */
    public static void nextCall(@NotNull Context context) {
        if (sAutoCallingContactsList != null && sAutoCallPosition < sAutoCallingContactsList.size()) {
            String phoneNumber = sAutoCallingContactsList.get(sAutoCallPosition).getMainPhoneNumber();
            if (Validator.validatePhoneNumber(phoneNumber)) {
                call(context, phoneNumber);
            } else {
                Toast.makeText(context, "Can't call " + phoneNumber, Toast.LENGTH_SHORT).show();
                sAutoCallPosition++;
                nextCall(context);
            }
        } else {
            finishAutoCall();
        }
    }

    /**
     * Finish the loop
     */
    public static void finishAutoCall() {
        sIsAutoCalling = false;
        sAutoCallPosition = 0;
    }

    /**
     * Check wither is currently auto calling
     *
     * @return
     */
    public static boolean isAutoCalling() {
        return sIsAutoCalling;
    }

    // -- Getters -- //

    /**
     * Gets the phone number of the contact from the end side of the current call
     * in the case of a voicemail number, returns "Voicemail"
     *
     * @return String - phone number, or voicemail. if not recognized, return null.
     */
    public static Contact getDisplayContact(Context context) {

        String uri = null;

        if (sCall.getState() == Call.STATE_DIALING) {
            Toast.makeText(context, "Dialing", Toast.LENGTH_LONG).show();
        }
        if (sCall.getDetails().getHandle() != null)
            uri = sCall.getDetails().getHandle().toString(); // Callers details

        if (uri == null || uri.isEmpty()) return ContactUtils.UNKNOWN;

        // If uri contains 'voicemail' this is a... voicemail dah
        if (uri.contains("voicemail")) return ContactUtils.VOICEMAIL;

        String telephoneNumber = null;

        // If uri contains 'tel' this is a normal number
        if (uri.contains("tel:")) telephoneNumber = uri.replace("tel:", "");

        if (uri.contains("%2B")) telephoneNumber = uri.replace("%2B", "+");

        if (uri.contains("%20")) telephoneNumber = uri.replace("%20", "");

        if (telephoneNumber.contains(" ")) telephoneNumber = telephoneNumber.replace(" ", "");

        if (telephoneNumber.contains("tel:")) telephoneNumber = telephoneNumber.replace("tel:", "");

        if (telephoneNumber.contains("%2B")) telephoneNumber = telephoneNumber.replace("%2B", "");

        if (telephoneNumber.contains("%20")) telephoneNumber = telephoneNumber.replace("%20", "");

        if (telephoneNumber == null || telephoneNumber.isEmpty())
            return ContactUtils.UNKNOWN; // Unknown number

        Contact contact = ContactUtils.getContactByPhoneNumber(context, telephoneNumber); // Get the contacts with the number

        if (contact == null || contact.getName() == null || contact.getName().isEmpty())
            //Changes done by aditya
            return new Contact("", telephoneNumber, null); // No known contacts for the number, return the number

        return contact;
    }


    public static Contact getDisplayContact(Context context, Call mCall) {

        String uri = null;


        if (mCall.getDetails().getHandle() != null)
            uri = mCall.getDetails().getHandle().toString(); // Callers details

        if (uri == null || uri.isEmpty()) return ContactUtils.UNKNOWN;

        // If uri contains 'voicemail' this is a... voicemail dah
        if (uri.contains("voicemail")) return ContactUtils.VOICEMAIL;

        String telephoneNumber = null;

        // If uri contains 'tel' this is a normal number
        if (uri.contains("tel:")) telephoneNumber = uri.replace("tel:", "");

        if (uri.contains("%2B")) telephoneNumber = uri.replace("%2B", "+");

        if (uri.contains("%20")) telephoneNumber = uri.replace("%20", "");

        if (telephoneNumber.contains(" ")) telephoneNumber = telephoneNumber.replace(" ", "");

        if (telephoneNumber.contains("tel:")) telephoneNumber = telephoneNumber.replace("tel:", "");

        if (telephoneNumber.contains("%2B")) telephoneNumber = telephoneNumber.replace("%2B", "");

        if (telephoneNumber.contains("%20")) telephoneNumber = telephoneNumber.replace("%20", "");

        if (telephoneNumber == null || telephoneNumber.isEmpty())
            return ContactUtils.UNKNOWN; // Unknown number

        Contact contact = ContactUtils.getContactByPhoneNumber(context, telephoneNumber); // Get the contacts with the number

        if (contact == null || contact.getName() == null || contact.getName().isEmpty())
            //Changes done by aditya
            return new Contact("", telephoneNumber, null); // No known contacts for the number, return the number

        return contact;
    }
    /**
     * Returnes the current state of the call from the Call object (named sCall)
     *
     * @return Call.State
     */
    public static int getState() {
        if (sCall == null) return Call.STATE_DISCONNECTED;
        return sCall.getState();
    }

    public static String getMobileNumberType(int type){
        switch (type){
            default:
                return "";
            case 1:
                return "Home ";
            case 2:
                return "Mobile ";
            case 3:
                return "Work ";
            case 4:
                return "Fax Work ";
        }
    }

    public static List<String> getCallRejectMessages() {
        return sCall.getCannedTextResponses();
    }
}
