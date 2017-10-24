package controller;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.util.TypedValue;
import android.util.DisplayMetrics;

import com.example.utsav.edufind.PolytechnicDetails;
import com.example.utsav.edufind.R;
import java.util.List;

import entity.PolytechnicCourse;
import entity.UniversityCourse;
import entity.Course;
import com.example.utsav.edufind.UniversityCourseDetailsUI;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CourseViewHolder> {
    //private Context context;
    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView CourseName;
        TextView InstitutionName;
        TextView CourseGrade;
        TextView CourseIntake;
        ImageView CourseWebsite;
        ImageView InstitutionLogo;
        TextView gradeTitle;

        CourseViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            CourseName = itemView.findViewById(R.id.Course_name);
            CourseGrade= itemView.findViewById(R.id.Course_Grade);
            InstitutionName = itemView.findViewById(R.id.Institution_name);
            CourseIntake = itemView.findViewById(R.id.Course_Intake);
            CourseWebsite = itemView.findViewById(R.id.Course_Website);
            InstitutionLogo = itemView.findViewById(R.id.Institution_Logo);
            gradeTitle = itemView.findViewById(R.id.Course_Grade_Title);

            //GIVE SHADOW AROUND CARD
            cv.setCardElevation(10);
            CardView.LayoutParams lp = new CardView.LayoutParams(CardView.LayoutParams.WRAP_CONTENT, CardView.LayoutParams.WRAP_CONTENT);
            DisplayMetrics dm = cv.getResources().getDisplayMetrics();
            lp.setMargins(convertDpToPx(5, dm), convertDpToPx(5, dm), convertDpToPx(5, dm), convertDpToPx(5, dm));
            //lp.setMargins(5, 5, 5, 5);
            cv.setLayoutParams(lp);
            cv.setContentPadding(0, 0, 0, 0);
        }
    }

    List<Course> courses;

    public RVAdapter(List<Course> courses){
        this.courses = courses;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        CourseViewHolder pvh = new CourseViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(CourseViewHolder CourseViewHolder, final int i) {
        if(courses.get(i) instanceof PolytechnicCourse) {
            switch (courses.get(i).getInstitution().getInstitution()) {
                case "Singapore Polytechnic": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.sp);
                    break;
                }
                case "Ngee Ann Polytechnic": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.np);
                    break;
                }
                case "Republic Polytechnic": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.rp);
                    break;
                }
                case "Nanyang Polytechnic": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.nyp);
                    break;
                }
                case "Temasek Polytechnic": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.tp);
                    break;
                }
                default:
            }
            CourseViewHolder.InstitutionName.setText(courses.get(i).getInstitution().getInstitution());
            CourseViewHolder.CourseName.setText(courses.get(i).getCourseName());
            CourseViewHolder.CourseGrade.setText(String.valueOf(((PolytechnicCourse) courses.get(i)).getL1R4()));
            CourseViewHolder.CourseIntake.setText(String.valueOf(courses.get(i).getIntake()));
            CourseViewHolder.CourseWebsite.setImageResource(R.drawable.website);
            CourseViewHolder.CourseWebsite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(courses.get(i).getWebsite())));
                    (v.getContext()).startActivity(browserIntent);
                }
            });
            CourseViewHolder.gradeTitle.setText("L1R4");

            final String courseName = courses.get(i).getCourseName();
            final String institutionName = courses.get(i).getInstitution().getInstitution();
            final String courseWebsite = courses.get(i).getWebsite();
            final String schDescription = courses.get(i).getInstitution().getInstiDescription();
            final String courseDescription = courses.get(i).getCourseDescription();
            final int courseGrade = ((PolytechnicCourse) courses.get(i)).getL1R4();
            final int courseIntake = courses.get(i).getIntake();
            CourseViewHolder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(v.getContext(), PolytechnicDetails.class);
                    in.putExtra("courseName", courseName);
                    in.putExtra("institutionName", institutionName);
                    in.putExtra("courseWebsite", courseWebsite);
                    in.putExtra("schDescription", schDescription);
                    in.putExtra("courseDescription", courseDescription);
                    in.putExtra("courseGrade", courseGrade);
                    in.putExtra("courseIntake", courseIntake);
                    v.getContext().startActivity(in);
                }
            });
        }
        else if (courses.get(i) instanceof UniversityCourse) {
            switch (courses.get(i).getInstitution().getInstitution()) {
                case "Singapore University of Technology and Design": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.sutd);
                    break;
                }
                case "Nanyang Technological University": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.ntu);
                    break;
                }
                case "Singapore Management University": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.smu);
                    break;
                }
                case "National University of Singapore": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.nus);
                    break;
                }
                case "Singapore Institute of Technology": {
                    CourseViewHolder.InstitutionLogo.setImageResource(R.mipmap.sit);
                    break;
                }
                default:
            }
            CourseViewHolder.InstitutionName.setText(courses.get(i).getInstitution().getInstitution());
            CourseViewHolder.CourseName.setText(courses.get(i).getCourseName());
            CourseViewHolder.CourseGrade.setText(String.valueOf(((UniversityCourse) courses.get(i)).getGradePointAverage()));
            CourseViewHolder.CourseIntake.setText(String.valueOf(courses.get(i).getIntake()));
            CourseViewHolder.CourseWebsite.setImageResource(R.drawable.website);
            CourseViewHolder.CourseWebsite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(courses.get(i).getWebsite())));
                    (v.getContext()).startActivity(browserIntent);
                }
            });
            CourseViewHolder.gradeTitle.setText("GPA");
            CourseViewHolder.CourseGrade.setTextSize(20);

            final String courseName = courses.get(i).getCourseName();
            final String institutionName = courses.get(i).getInstitution().getInstitution();
            final String courseWebsite = courses.get(i).getWebsite();
            final String schDescription = courses.get(i).getInstitution().getInstiDescription();
            final String courseDescription = courses.get(i).getCourseDescription();
            final double courseGrade = ((UniversityCourse) courses.get(i)).getGradePointAverage();
            final int courseIntake = courses.get(i).getIntake();
            CourseViewHolder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(v.getContext(), UniversityCourseDetailsUI.class);
                    in.putExtra("courseName", courseName);
                    in.putExtra("institutionName", institutionName);
                    in.putExtra("courseWebsite", courseWebsite);
                    in.putExtra("schDescription", schDescription);
                    in.putExtra("courseDescription", courseDescription);
                    in.putExtra("courseGrade", courseGrade);
                    in.putExtra("courseIntake", courseIntake);
                    v.getContext().startActivity(in);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    private static int convertDpToPx(int dp, DisplayMetrics displayMetrics) {
        float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
        return Math.round(pixels);
    }
}