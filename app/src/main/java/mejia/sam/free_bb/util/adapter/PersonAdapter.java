package mejia.sam.free_bb.util.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

import mejia.sam.free_bb.DetailActivity;
import mejia.sam.free_bb.ListActivity;
import mejia.sam.free_bb.OnEachItemClick;
import mejia.sam.free_bb.R;
import mejia.sam.free_bb.model.Header;
import mejia.sam.free_bb.model.Person;

import static mejia.sam.free_bb.util.Constants.BASE_PHOTO;

public class PersonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    boolean flag = true;
    private int mobileLength;
    private int CXPLegth;
    private int launchPadLenth;
    private Header header;
    private List<Person> personList;
    int count = 0;
    private OnEachItemClick myOnEachItemClick ;

    public PersonAdapter(List<Person> personList, int mobileLength, int CXPLegth, int launchPadLenth ,OnEachItemClick myOnEachItemClickl ) {
        this.personList = personList;
        this.mobileLength = mobileLength;
        this.CXPLegth = CXPLegth;
        this.launchPadLenth = launchPadLenth;
        this.header = new Header("Mobile");
        this.myOnEachItemClick = myOnEachItemClickl;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent, false);
            return new HeaderItem(v);
        } else   {
            View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.recy_list_row, parent, false);

            return new MyViewHolder(view, mobileLength, CXPLegth, launchPadLenth );
        }
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeaderItem)
        {
            HeaderItem VHheader = (HeaderItem)holder;
            VHheader.txtTitle.setText(header.getHeader());
            count++;
        }
        else{

            MyViewHolder myViewHodler = (MyViewHolder)holder;
            Person myCurrentPerson = myViewHodler.getcorrectPerson(position);
            myViewHodler.title.setText(myCurrentPerson.getName() +" " +myCurrentPerson.getSurname());
//           if(myCurrentPerson.getPhoto()!= null) {
               Glide.with((ListActivity) myOnEachItemClick).load(BASE_PHOTO + myCurrentPerson.getPhoto()).error(R.mipmap.ic_launcher).into(myViewHodler.imgperson);
//           }else
//           {
//               myViewHodler.imgperson.setImageResource(R.mipmap.ic_launcher);
//           }



        }


    }

    @Override
    public int getItemCount() {
        return personList.size()+3;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == mobileLength)
        {
            this.header.setHeader("CXP");
        }

        if (position == CXPLegth)
        {
            this.header.setHeader("LaunchPad");
        }

        if(position == (mobileLength+1) || position == (CXPLegth+2) || position == 0) {
            return TYPE_HEADER;

        }
        return TYPE_ITEM;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        public TextView title;
        public ImageView imgperson;
        private int imobileLength;
        private int iCXPLegth;
        private int ilaunchPadLenth;




        public MyViewHolder(View itemView, int mobileLength, int CXPLegth, int launchPadLenth) {
            super(itemView);
            this.imobileLength = mobileLength;
            this.iCXPLegth = CXPLegth;
            this.ilaunchPadLenth = launchPadLenth;
            title = (TextView) itemView.findViewById(R.id.tv);
            imgperson = (ImageView) itemView.findViewById(R.id.imgview);
            itemView.setOnClickListener(this);
        }

         @Override
        public void onClick(View v) {
            String department = getCorrectDepartment(getAdapterPosition());
            myOnEachItemClick.onEachClick(v,getcorrectPerson(getAdapterPosition()),department);
        }

        private String getCorrectDepartment(int arbPosition) {

            if (arbPosition >=0 && arbPosition < (mobileLength+1))
            {

                return "Mobile";
            }
            else if (arbPosition >= (mobileLength+1) && arbPosition < (CXPLegth+2))

            {
                return "CXP";
            }

            else
            {
                return "LauchPAD";
            }
        }


        public Person getcorrectPerson(int arbPosition)
        { Person per = new Person();
            if (arbPosition >=0 && arbPosition < (mobileLength+1))
            {
                arbPosition  = arbPosition-1;
                per = personList.get(arbPosition);
            }
            else if (arbPosition >= (mobileLength+1) && arbPosition < (CXPLegth+2))

            {
                arbPosition  = arbPosition-2;
                per = personList.get(arbPosition);
            }
            else if (arbPosition >= (mobileLength+1) && arbPosition < (CXPLegth+2))

            {
                arbPosition  = arbPosition-2;
                per = personList.get(arbPosition);
            }
            else if (arbPosition >= (CXPLegth+2) && arbPosition < (launchPadLenth+3))
            {
                arbPosition  = arbPosition-3;
                per = personList.get(arbPosition);
            }


            return  per;
        }
    }



    public class HeaderItem extends RecyclerView.ViewHolder{
        TextView txtTitle;
        public HeaderItem(View itemView) {
            super(itemView);
            this.txtTitle = (TextView)itemView.findViewById(R.id.txtHeader);
        }
    }

}
