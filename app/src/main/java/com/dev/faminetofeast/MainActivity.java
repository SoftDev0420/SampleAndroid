package com.dev.faminetofeast;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    ImageButton btnInfo;
    LinearLayout llCashDonation, llScheduleDonation;
    EditText txtAddress, txtInstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        setupView();
    }

    public void setupView() {
        btnInfo = (ImageButton) findViewById(R.id.btnInfo);
        llCashDonation = (LinearLayout) findViewById(R.id.llCashDonation);
        llScheduleDonation = (LinearLayout) findViewById(R.id.llScheduleDonation);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        txtInstruction = (EditText) findViewById(R.id.txtInstruction);
    }

    ////////////////////////////////

    public void onInfo(View v) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://faminetofeast.leadpages.co/welcome/"));
        startActivity(browserIntent);
    }

    public void onSchedule(View v) {
        llCashDonation.setVisibility(View.INVISIBLE);
        llScheduleDonation.setVisibility(View.VISIBLE);
        btnInfo.setVisibility(View.INVISIBLE);
    }

    public void onSubmit(View v) {
        String address = txtAddress.getText().toString();
        String instruction = txtInstruction.getText().toString();
        String content = String.format("Verify Pickup Address and Change if Needed\n\n**** Address ****\n%s\n*****************\n\n==== Special Instructions ====\n%s\n========================\n\nThanks for helping!\nSincerely,\nFamine To Feast",
                address, instruction);

        Intent intent = new Intent(Intent.ACTION_SEND);
        String[] recipients = {"faminetofeastnow@gmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Famine To Feast Donation Pickup");
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setType("text/html");
        startActivity(Intent.createChooser(intent, "Send mail"));
    }

    public void onCancel(View v) {
        llScheduleDonation.setVisibility(View.INVISIBLE);
        llCashDonation.setVisibility(View.VISIBLE);
        btnInfo.setVisibility(View.VISIBLE);
    }

    ////////////////////////////////

    public void onDonate10(View v) {
        donate("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=V5WDMU6YLPHLU");
    }

    public void onDonate25(View v) {
        donate("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=HXZK9YGAU42LE");
    }

    public void onDonate50(View v) {
        donate("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=UNBJQ9XGDA5GQ");
    }

    public void onDonateOther(View v) {
        donate("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=C7DVMYNV2G4SY");
    }

    public void donate(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    ////////////////////////////////
}
