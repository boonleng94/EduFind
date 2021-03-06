package boundary;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utsav.edufind.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import controller.MapController;

/**
 * Initializes and display the actual details of for the selected Polytechnic Course
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-24
 */
public class PolytechnicDetailsTab1 extends Fragment implements OnMapReadyCallback{
    TextView CourseGrade;
    TextView CourseIntake;
    TextView CourseName;
    TextView InstitutionName;
    ImageView InstitutionLogo;
    ImageView CourseWebsite;
    TextView institutionDescription;
    TextView courseDescription;
    TextView career;
    TextView direction;
    TextView CourseDescription;
    TextView InstitutionDescription;
    TextView CareerProspect;
    ImageView imageView;
    GoogleMap mGoogleMap;
    String postalCode;
    String insName;
    String insCode;

    /**
     * Sets data and display details of the Course dynamically
     * @param inflater Instantiates a layout XML
     * @param container Main view containing all other views
     * @param savedInstanceState Current state of application
     * @return View Polytechnic Course Details view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab1_details, container, false);
        CourseGrade = rootView.findViewById(R.id.tab1_Course_Grade);
        CourseIntake = rootView.findViewById(R.id.tab1_Course_Intake);
        CourseName = rootView.findViewById(R.id.tab1_Course_name);
        InstitutionName = rootView.findViewById(R.id.tab1_Institution_name);
        InstitutionLogo = rootView.findViewById(R.id.tab1_Institution_Logo);
        CourseWebsite = rootView.findViewById(R.id.tab1_Course_Website);
        institutionDescription = rootView.findViewById(R.id.institution_desc_text);
        courseDescription = rootView.findViewById(R.id.course_desc_text);
        direction = rootView.findViewById(R.id.direction);
        career = rootView.findViewById(R.id.career_prospect_text);
        CourseDescription = rootView.findViewById(R.id.course_desc_detail_text);
        InstitutionDescription = rootView.findViewById(R.id.institution_desc_detail_text);
        CareerProspect = rootView.findViewById(R.id.career_prospect_detail_text);
        imageView = rootView.findViewById(R.id.imageView);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        Intent i = getActivity().getIntent();
        String courseName = i.getExtras().getString("courseName", "No courseName found");
        final String courseWebsite = i.getExtras().getString("courseWebsite", "No courseWebsite found");
        String institutionDesc = i.getExtras().getString("institutionDescription", "No institutionDescription found");
        String courseDesc = i.getExtras().getString("courseDescription", "No courseDescription found");
        String institutionName = i.getExtras().getString("institutionName", "No institutionName found");
        String courseGrade = String.valueOf(i.getExtras().getInt("courseGrade", 0));
        String courseIntake = String.valueOf(i.getExtras().getInt("courseIntake", 0));
        String postCode = String.valueOf(i.getExtras().getInt("postalCode", 238801));
        String instPostCode = String.valueOf(i.getExtras().getInt("instPostalCode", 238801));
        String careerProspect = i.getExtras().getString("careerProspect", "No careerProspect found");
        postalCode = postCode;
        insCode = instPostCode;
        insName = institutionName;

        CourseGrade.setText(courseGrade);
        CourseIntake.setText(courseIntake);
        CourseName.setText(courseName);
        InstitutionName.setText(institutionName);
        CourseDescription.setText(courseDesc);
        InstitutionDescription.setText(institutionDesc);
        CareerProspect.setText(careerProspect);

        switch (institutionName) {
            case "Singapore Polytechnic": {
                InstitutionLogo.setImageResource(R.mipmap.sp);
                imageView.setImageResource(R.mipmap.sp_sch_image);
                break;
            }
            case "Ngee Ann Polytechnic": {
                InstitutionLogo.setImageResource(R.mipmap.np);
                imageView.setImageResource(R.drawable.np);
                break;
            }
            case "Republic Polytechnic": {
                InstitutionLogo.setImageResource(R.mipmap.rp);
                imageView.setImageResource(R.drawable.rp);
                break;
            }
            case "Nanyang Polytechnic": {
                InstitutionLogo.setImageResource(R.mipmap.nyp);
                imageView.setImageResource(R.drawable.nyp);
                break;
            }
            case "Temasek Polytechnic": {
                InstitutionLogo.setImageResource(R.mipmap.tp);
                imageView.setImageResource(R.drawable.tp);
                break;
            }
            default:
        }
        CourseWebsite.setImageResource(R.drawable.website);
        CourseWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(courseWebsite)));
                (v.getContext()).startActivity(browserIntent);
            }
        });
        institutionDescription.setPaintFlags(institutionDescription.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        courseDescription.setPaintFlags(courseDescription.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        direction.setPaintFlags(direction.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        career.setPaintFlags(career.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        return rootView;
    }

    /**
     * This function is called when the map has been fetched and is ready to be altered
     * The function first centers and zooms the map on Singapore, and then places two pins with markers specifying
     * the user location and the location of the polytechnic being viewed
     * @param googleMap an instance of GoogleMap class
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        MapController m1 = new MapController();
        MapController m2 = new MapController();
        double[] latlng = {1.35,103.82};
        LatLng SINGAPORE = new LatLng(latlng[0], latlng[1]);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SINGAPORE, 10));
        try {
            m1.execute(postalCode);
            double[] location = m1.get();
            LatLng HOME = new LatLng(location[0], location[1]);
            mGoogleMap.addMarker(new MarkerOptions().position(HOME).title("Your Location"));
            m2.execute(insCode);
            location = m2.get();
            LatLng INST = new LatLng(location[0], location[1]);
            mGoogleMap.addMarker(new MarkerOptions().position(INST).title(insName));
        } catch (Exception e){
        }
    }
}
