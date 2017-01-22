package com.udacity.capstone.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mangesh on 14/1/17.
 */
public class AartiKathaDetails implements Parcelable {

    String aartiName;

    public String getAartiName() {
        return aartiName;
    }

    public int getImageID() {
        return imageID;
    }

    public String getFileName() {
        return fileName;
    }

    int imageID;

    String fileName;

    public int getIndex() {
        return index;
    }

    private int index;

    public AartiKathaDetails(String aartiName, int imageID, String fileName) {
        this.aartiName = aartiName;
        this.imageID = imageID;
        this.fileName = fileName;
    }

    public AartiKathaDetails(String name, int index, int resourceID) {
        this.aartiName = name;
        this.index = index;
        this.imageID = resourceID;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.aartiName);
        dest.writeInt(this.imageID);
        dest.writeString(this.fileName);
        dest.writeInt(this.index);
    }

    protected AartiKathaDetails(Parcel in) {
        this.aartiName = in.readString();
        this.imageID = in.readInt();
        this.fileName = in.readString();
        this.index = in.readInt();
    }

    public static final Creator<AartiKathaDetails> CREATOR = new Creator<AartiKathaDetails>() {
        @Override
        public AartiKathaDetails createFromParcel(Parcel source) {
            return new AartiKathaDetails(source);
        }

        @Override
        public AartiKathaDetails[] newArray(int size) {
            return new AartiKathaDetails[size];
        }
    };
}
