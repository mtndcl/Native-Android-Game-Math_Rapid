package com.nazo.mathrapid.aboutpanel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nazo.mathrapid.Contant;
import com.nazo.mathrapid.R;
import com.nazo.mathrapid.splashpanel.SplashView;

/**
 * Created by mockingbird on 6/13/2018.
 */

public class About    extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        int   textcolor= Color.argb(250,60,18,69);
        int   linkcolor=Color.argb(250, 12, 79, 233 );


      //  Shader shader = new LinearGradient(Contant.SCREEN_WIDHT/2,0,Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT, Color.rgb(247,245,143),Color.rgb(207,167,76), Shader.TileMode.REPEAT);

        RelativeLayout layout=(RelativeLayout) findViewById(R.id.activity_about);
        layout.setBackgroundColor(Color.rgb(88, 214, 141));

        ////  layout.setBackgroundColor());





        TextView  about=(TextView) findViewById(R.id.abouttext);
        about.setText(getString(R.string.about_text));
        about.setTextColor(textcolor);
        ////
        TextView  version=(TextView) findViewById(R.id.versiontext);
        version.setText(getString(R.string.version_text));
        version.setTextColor(textcolor);

        TextView  versionname=(TextView) findViewById(R.id.versionname);
        versionname.setText(getString(R.string.version_name));
        versionname.setTextColor(textcolor);
        ///
        TextView  privacy=(TextView) findViewById(R.id.privacytext);
        privacy.setText(getString(R.string.privacypolicy_text));
        privacy.setTextColor(textcolor);
        ///
        TextView  ads=(TextView) findViewById(R.id.adverstingtext);
        ads.setText(getString(R.string.adversting_text));
        ads.setTextColor(textcolor);

        TextView  adsview=(TextView) findViewById(R.id.adsview);
        adsview.setText(getString(R.string.adversting_view));
        adsview.setTextColor(textcolor);

        TextView  privacylink=(TextView) findViewById(R.id.googleprivacytext);
        privacylink.setText(getString(R.string.privacy_google_link_text));
        privacylink.setTextColor(linkcolor);


        TextView  displaedads=(TextView) findViewById(R.id.displayedadstext);
        displaedads.setText(getString(R.string.displayed_adstext));
        displaedads.setTextColor(textcolor);

        TextView  displaedadslink=(TextView) findViewById(R.id.moreinfolinlk);
        displaedadslink.setText(getString(R.string.displayed_adstext_link_text));
        displaedadslink.setTextColor(linkcolor);



        TextView  changestext=(TextView) findViewById(R.id.changestext);
        changestext.setText(getString(R.string.changes_text));
        changestext.setTextColor(textcolor);

        TextView changesview=(TextView) findViewById(R.id.changesview);
        changesview.setText(getString(R.string.changes_view));
        changesview.setTextColor(textcolor);


        TextView  contacttext=(TextView) findViewById(R.id.contacttext);
        contacttext.setText(getString(R.string.contact_text));
        contacttext.setTextColor(textcolor);

        TextView  email=(TextView) findViewById(R.id.emailtext);
        email.setText(getString(R.string.e_mail));
        email.setTextColor(linkcolor);



    }

    public void clickonfirstlink(View arg0) {


        Uri uri = Uri.parse(getString(R.string.link0));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);




    }
    public void clickseconlink(View arg0) {


        Uri uri = Uri.parse(getString(R.string.link1));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.version_text));
        startActivity(intent);




    }
    public void clickthirdlink(View arg0) {


        Uri uri = Uri.parse(getString(R.string.link2));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);




    }

}
