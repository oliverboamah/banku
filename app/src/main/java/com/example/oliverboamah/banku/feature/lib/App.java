package com.example.oliverboamah.banku.feature.lib;

import android.content.Context;

import com.chootdev.csnackbar.Align;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Type;

/**
 * Created by Oliver Boamah on 11/29/2018.
 */

public class App {

    public static void displayAlert(Context context, Type type, int message)
    {
        CustomSnackBar.with(context, null)
                .type(type)
                .message(context.getResources().getString(message))
                .duration(Duration.SHORT)
                .fillParent(true)
                .textAlign(Align.LEFT)
                .show();
    }

}
