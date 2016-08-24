package com.ng.app.helloworld;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.Preference;

/**
 * Created by GameMaster on 8/22/2016.
 */
public class BundleSavedState extends Preference.BaseSavedState {
    private Bundle bundle;

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public BundleSavedState(Parcel source) {
        super(source);
        bundle = source.readBundle();
    }

    public BundleSavedState(Parcelable superState) {
        super(superState);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeBundle(bundle);
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel parcel) {
            return new BundleSavedState(parcel);
        }

        @Override
        public Object[] newArray(int i) {
            return new BundleSavedState[i];
        }
    };
}
