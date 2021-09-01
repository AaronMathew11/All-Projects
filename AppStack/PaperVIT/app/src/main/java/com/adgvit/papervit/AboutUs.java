package com.adgvit.papervit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AboutUs extends AppCompatActivity {

    private ImageView ishaanLinkedIn, ishaanGitHub, ishaanMail, akshitLinkedIn, akshitGitHub, akshitMail;
    private ImageView panshulLinkedin,panshulGithub,panshulMail,gokulLinkedin,gokulGithub,gokulMail;
    private ImageView harshLinkedin,harshGithub,harshMail,prabalLinkedin,prabalGithub,prabalMail;
    private String ishaanLinkedInLink = "https://www.linkedin.com/in/ishaanohri/";
    private String ishaanGitHubLink = "https://github.com/IshaanOhri";
    private String ishaanMailLink = "ishaan99ohri@gmail.com";
    private String akshitLinkedInLink = "https://www.linkedin.com/in/akshit-sadana-b051ab121/";
    private String akshitGitHubLink = "https://github.com/Akshit8";
    private String akshitMailLink = "akshitsadana@gmail.com";
    private String panshulLinkdeinLink ="https://www.linkedin.com/in/panshul-jindal-392746199" ;
    private String panshulGithubLink ="https://github.com/panshuljindal" ;
    private String panshulMailLink = "panshuljindal@gmail.com";
    private String gokulLinkdeinLink ="https://www.linkedin.com/in/gokul-r-nair/" ;
    private String gokulGithubLink ="https://github.com/gokulnair2001" ;
    private String gokulMailLink = "gokulnair.2001@gmail.com";
    private String harshLinkdeinLink ="https://www.linkedin.com/in/harshlondhekar/" ;
    private String harshGithubLink ="https://github.com/Harsh4601" ;
    private String harshMailLink = "londhekarh4601@gmail.com";
    private String prabalLinkdeinLink ="https://www.linkedin.com/in/prabaljit-walia-5800571a0" ;
    private String prabalGithubLink ="https://github.com/prabal4546" ;
    private String prabalMailLink = "prabaljit.walia2019@vitstudent.ac.in";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_about_us);

        ishaanLinkedIn = findViewById(R.id.ishaanLinkedin);
        ishaanGitHub = findViewById(R.id.ishaanGithub);
        ishaanMail = findViewById(R.id.ishaanEmail);

        akshitLinkedIn = findViewById(R.id.akshitLinkedin);
        akshitGitHub = findViewById(R.id.akshitGithub);
        akshitMail = findViewById(R.id.akshitEmail);

        panshulLinkedin = findViewById(R.id.panshulLinkedin);
        panshulGithub = findViewById(R.id.panshulGithub);
        panshulMail = findViewById(R.id.panshulEmail);

        gokulLinkedin = findViewById(R.id.gokulLinkedin);
        gokulGithub = findViewById(R.id.gokulGithub);
        gokulMail = findViewById(R.id.gokulEmail);

        harshLinkedin = findViewById(R.id.harshLinkedin);
        harshGithub = findViewById(R.id.harshGithub);
        harshMail = findViewById(R.id.harshEmail);

        prabalLinkedin = findViewById(R.id.prabalLinkedin);
        prabalGithub = findViewById(R.id.prabalGithub);
        prabalMail = findViewById(R.id.prabalEmail);

        //Ishaan
        ishaanLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(ishaanLinkedInLink));
                startActivity(intent);
            }
        });

        ishaanGitHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(ishaanGitHubLink));
                startActivity(intent);
            }
        });

        ishaanMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",ishaanMailLink,null
                ));
                email.putExtra(Intent.EXTRA_SUBJECT, "Contact regarding PaperVIT");
                startActivity(Intent.createChooser(email,"Send Email..."));
            }
        });

        //Akshit
        akshitLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(akshitLinkedInLink));
                startActivity(intent);
            }
        });

        akshitGitHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(akshitGitHubLink));
                startActivity(intent);
            }
        });

        akshitMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",akshitMailLink,null
                ));
                email.putExtra(Intent.EXTRA_SUBJECT, "Contact regarding PaperVIT");
                startActivity(Intent.createChooser(email,"Send Email..."));
            }
        });

        // Panshul
        panshulLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(panshulLinkdeinLink));
                startActivity(intent);
            }
        });
        panshulGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(panshulGithubLink));
                startActivity(intent);
            }
        });

        panshulMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",panshulMailLink,null
                ));
                email.putExtra(Intent.EXTRA_SUBJECT, "Contact regarding PaperVIT");
                startActivity(Intent.createChooser(email,"Send Email..."));
            }
        });

        //Gokul
        gokulLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(gokulLinkdeinLink));
                startActivity(intent);
            }
        });
        gokulGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(gokulGithubLink));
                startActivity(intent);
            }
        });

        gokulMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",gokulMailLink,null
                ));
                email.putExtra(Intent.EXTRA_SUBJECT, "Contact regarding PaperVIT");
                startActivity(Intent.createChooser(email,"Send Email..."));
            }
        });

        //Harsh
        harshLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(harshLinkdeinLink));
                startActivity(intent);
            }
        });
        harshGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(harshGithubLink));
                startActivity(intent);
            }
        });

        harshMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",harshMailLink,null
                ));
                email.putExtra(Intent.EXTRA_SUBJECT, "Contact regarding PaperVIT");
                startActivity(Intent.createChooser(email,"Send Email..."));
            }
        });

        //Prabaljit
        prabalLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(prabalLinkdeinLink));
                startActivity(intent);
            }
        });
        prabalGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(prabalGithubLink));
                startActivity(intent);
            }
        });

        prabalMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",prabalMailLink,null
                ));
                email.putExtra(Intent.EXTRA_SUBJECT, "Contact regarding PaperVIT");
                startActivity(Intent.createChooser(email,"Send Email..."));
            }
        });

    }
}